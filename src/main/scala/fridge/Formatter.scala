package fridge

import java.time.LocalDate
import java.time.temporal.ChronoUnit

object Formatter {

    def showDisplay(items: Seq[(Item, Long)]): String = {
        items.map(item => formatItem(item._1, item._2)).mkString("\n")
    }

    private def formatItem(item: Item, remainingDays: Long) = {
        val daysBet = remainingDays
        val wordDays = if(daysBet == 1) "day" else "days"
        s"${item.name}: ${daysBet } ${wordDays} remaining"
    }
}
