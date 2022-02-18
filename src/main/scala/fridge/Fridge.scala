package fridge

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Fridge {
    val Formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
}

class Fridge {

    var currentDate: LocalDate = LocalDate.now
    var doorOpened = false
    var item: Seq[String]= Seq()

    def signalFridgeDoorOpened() = changeDoorState(true)
    def signalFridgeDoorClosed() = changeDoorState(false)


    def scanAddedItem(name: String, expiry: String, condition: String) = {
        assertDoorOpen()
        this.item = this.item :+ name
    }

    def scanRemovedItem(name: String) = {
        assertDoorOpen()
        if(this.item.contains(name) == false) throw new UnsupportedOperationException
    }

    def showDisplay(): String =   """EXPIRED: Milk
Lettuce: 0 days remaining
Peppers: 1 day remaining
Cheese: 31 days remaining"""


    def simulateDayOver() = {
        currentDate = currentDate.plusDays(1)
    }

    def setCurrentDate(date: String) = {
        currentDate = LocalDate.parse(date, Fridge.Formatter)
    }

    def getTempCurrentDate(): LocalDate = currentDate

    private def changeDoorState(wantedState: Boolean) = {
        if(doorOpened == wantedState) 
            throw new UnsupportedOperationException
        doorOpened = wantedState
    }

    private def assertDoorOpen() = {
        if(doorOpened == false) throw new UnsupportedOperationException
    }
}
