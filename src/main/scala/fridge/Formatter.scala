package fridge

import java.time.LocalDate
import java.time.temporal.ChronoUnit

trait TimeToConsume
sealed case class RemainingDays(days: Long) extends TimeToConsume
case object Expired extends TimeToConsume

object Formatter {

    def showDisplay(items: Seq[(Item, TimeToConsume)]): String = {
        items
            .sortWith((item1, item2) => comparator(item1._2, item2._2))
            .reverse
            .map(item => formatItem(item._1, item._2)).mkString("\n")
    }

    private def formatItem(item: Item, remainingTime: TimeToConsume): String =  {
        remainingTime match {
            case RemainingDays(days) => formatItem(item, days)
            case Expired => formatExpiryItem(item)
        }
    }

    private def formatItem(item: Item, remainingDays: Long): String = {
        val daysBet = remainingDays
        val wordDays = if(daysBet == 1) "day" else "days"
        s"${item.name}: ${daysBet} ${wordDays} remaining"
    }

    private def formatExpiryItem(item: Item): String = {
        s"EXPIRED: ${item.name}" 
    }

    private def comparator(ttc1: TimeToConsume, ttc2: TimeToConsume): Boolean =  {
        (ttc1, ttc2) match {
            case (RemainingDays(d1), RemainingDays(d2)) => d1.compare(d2) > 0
            case (Expired, _) => true
            case (_, Expired) => false
            case (_, _) => false
        }
    }
}
