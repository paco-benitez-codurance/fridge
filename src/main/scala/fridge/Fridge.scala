package fridge

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

object Fridge {
    val Formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val Formatter2 = DateTimeFormatter.ofPattern("dd/MM/yy")
}

class Fridge {

    var currentDate: LocalDate = LocalDate.now
    var doorOpened = false
    var items: Seq[Item]= Seq()

    def signalFridgeDoorOpened() = changeDoorState(true)
    def signalFridgeDoorClosed() = changeDoorState(false)


    def scanAddedItem(name: String, expiry: String, condition: String) = {
        assertDoorOpen()
        this.items = this.items :+ Item(name, LocalDate.parse(expiry, Fridge.Formatter2)) 
    }

    def scanRemovedItem(name: String) = {
        assertDoorOpen()
        if(containsItem(name) == false) throw new UnsupportedOperationException
    }

    def showDisplay(): String = {
        this.items.map(formatItem).mkString("\n")
    }

    private def formatItem(item: Item) = {
        val daysBet = days(item.expiry, currentDate)
        val wordDays = if(daysBet == 1) "day" else "days"
        s"${item.name}: ${daysBet } ${wordDays} remaining"
    }

    def simulateDayOver() = {
        currentDate = currentDate.plusDays(1)
    }

    def setCurrentDate(date: String) = {
        currentDate = LocalDate.parse(date, Fridge.Formatter)
    }

    def getTempCurrentDate(): LocalDate = currentDate

    private def containsItem(name: String): Boolean = 
        this.items.map(_.name).contains(name)

    private def days(from: LocalDate, to: LocalDate): Long = 
        ChronoUnit.DAYS.between(to, from)


    private def changeDoorState(wantedState: Boolean) = {
        if(doorOpened == wantedState) 
            throw new UnsupportedOperationException
        doorOpened = wantedState
    }

    private def assertDoorOpen() = {
        if(doorOpened == false) throw new UnsupportedOperationException
    }
}
