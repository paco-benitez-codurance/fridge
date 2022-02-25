package fridge

import java.time.LocalDate
import java.time.temporal.ChronoUnit

object Formatter {

    def showDisplay(items: Seq[Item], currentDate: LocalDate): String = {
        items.map(formatItem(_, currentDate)).mkString("\n")
    }

    private def formatItem(item: Item, currentDate: LocalDate) = {
        val daysBet = days(item.expiry, currentDate)
        val wordDays = if(daysBet == 1) "day" else "days"
        s"${item.name}: ${daysBet } ${wordDays} remaining"
    }

     private def days(from: LocalDate, to: LocalDate): Long = 
        ChronoUnit.DAYS.between(to, from)
  
}
