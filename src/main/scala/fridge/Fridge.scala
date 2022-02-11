package fridge

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Fridge {
    val Formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
}

class Fridge {

    var currentDate: LocalDate = LocalDate.now


    def signalFridgeDoorOpened() = {}
    def signalFridgeDoorClosed() = {}
    def scanAddedItem(name: String, expiry: String, condition: String) = ???
    def scanRemovedItem(name: String) = ???
    def showDisplay(): String = ???


    def simulateDayOver() = {
        currentDate = currentDate.plusDays(1)
    }

    def setCurrentDate(date: String) = {
        currentDate = LocalDate.parse(date, Fridge.Formatter)
    }

    def getTempCurrentDate(): LocalDate = currentDate
}
