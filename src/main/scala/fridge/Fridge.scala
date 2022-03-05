package fridge

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class Fridge {

    var currentDate: LocalDate = LocalDate.now
    var doorOpened = false
    var items: Seq[Item]= Seq()

    def signalFridgeDoorOpened() = changeDoorState(true)
    def signalFridgeDoorClosed() = changeDoorState(false)


    def scanAddedItem(name: String, expiry: String, condition: String) = {
        assertDoorOpen()
        this.items = this.items :+ Item(name, DateUtil.formatItemDate(expiry)) 
    }

    def scanRemovedItem(name: String) = {
        assertDoorOpen()
        if(containsItem(name) == false) throw new UnsupportedOperationException
    }

    def showDisplay(): String = {
        val itemsWithDays = items.map(item => (item, RemainingDays(DateUtil.days(item.expiry, currentDate))))
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
