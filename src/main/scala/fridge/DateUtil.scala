package fridge

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit


object DateUtil {
    val FormatterCurrentDate = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val FormatterItems = DateTimeFormatter.ofPattern("dd/MM/yy")

    def days(from: LocalDate, to: LocalDate): Long = {
        ChronoUnit.DAYS.between(to, from)
    }

    def formatCurrentDate(date: String): LocalDate = 
        LocalDate.parse(date, FormatterCurrentDate)

    def formatItemDate(date: String): LocalDate = 
        LocalDate.parse(date, FormatterItems)

    def addOneDay(date: LocalDate): LocalDate = date.plusDays(1)
  
}
