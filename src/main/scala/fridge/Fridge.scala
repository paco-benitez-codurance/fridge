package fridge

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

object Fridge {
    val FormatterCurrentDate = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val FormatterItems = DateTimeFormatter.ofPattern("dd/MM/yy")

    private def days(from: LocalDate, to: LocalDate): RemainingDays = {
        val days = ChronoUnit.DAYS.between(to, from)
        RemainingDays(days)
    }
}

class Fridge {

    var currentDate: LocalDate = LocalDate.now
    var doorOpened = false
    var items: Seq[Item]= Seq()

    def signalFridgeDoorOpened() = changeDoorState(true)
    def signalFridgeDoorClosed() = changeDoorState(false)


    def scanAddedItem(name: String, expiry: String, condition: String) = {
        assertDoorOpen()
        this.items = this.items :+ Item(name, LocalDate.parse(expiry, Fridge.FormatterItems)) 
    }

    def scanRemovedItem(name: String) = {
        assertDoorOpen()
        if(containsItem(name) == false) throw new UnsupportedOperationException
    }

    def showDisplay(): String = {
        val itemsWithDays = items.map(item => (item, Fridge.days(item.expiry, currentDate)))
        Formatter.showDisplay(itemsWithDays)
    }

    def simulateDayOver() = {
        currentDate = currentDate.plusDays(1)
    }

    def setCurrentDate(date: String) = {
        currentDate = LocalDate.parse(date, Fridge.FormatterCurrentDate)
    }

    def getTempCurrentDate(): LocalDate = currentDate

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
