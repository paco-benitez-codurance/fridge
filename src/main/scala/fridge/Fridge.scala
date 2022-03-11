package fridge

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

object Fridge {
    def degradation(item: Item, days: Long) : Long = {
        val hoursDegraded = if(item.condition == "opened") {
            item.numberOfOpen * 5  
        } else {
            item.numberOfOpen * 1
        }

        var daysDegarded = hoursDegraded / 24
        days - daysDegarded
    }

    def daysToTimeToConsume(days: Long): TimeToConsume = {
        if(days >= 0) RemainingDays(days)
        else Expired
    }

    def remainingDays(item: Item, currentDate: LocalDate) = {
        daysToTimeToConsume(
            degradation(
                item,
                DateUtil.days(item.expiry, currentDate)
            )
        )

    }
}

class Fridge {

    var currentDate: LocalDate = LocalDate.now
    var doorOpened = false
    var items: Seq[Item]= Seq()

    def signalFridgeDoorOpened() = {
        this.items = items.map(item => item.copy(numberOfOpen = item.numberOfOpen + 1))
        changeDoorState(true)
    }

    def signalFridgeDoorClosed() = changeDoorState(false)

    def scanAddedItem(name: String, expiry: String, condition: String) = {
        assertDoorOpen()
        this.items = this.items :+ Item(name, DateUtil.formatItemDate(expiry), condition) 
    }

    def scanRemovedItem(name: String) = {
        assertDoorOpen()
        if(containsItem(name) == false) throw new UnsupportedOperationException
        this.items = this.items.filter(_.name != name)
    }

    def showDisplay(): String = {
        val itemsWithDays = items.map(
            item => (item, Fridge.remainingDays(item, currentDate))
        )
        Formatter.showDisplay(itemsWithDays)
    }

    def simulateDayOver() = {
        currentDate = DateUtil.addOneDay(currentDate)
    }

    def setCurrentDate(date: String) = {
        currentDate = DateUtil.formatCurrentDate(date)
    }

    private def containsItem(name: String): Boolean = 
        this.items.map(_.name).contains(name)


    private def changeDoorState(wantedState: Boolean) = {
        if(doorOpened == wantedState) 
            throw new UnsupportedOperationException
        doorOpened = wantedState
    }

    private def assertDoorOpen() = {
        if(doorOpened == false) throw new UnsupportedOperationException
    }
}
