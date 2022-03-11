package fridge

import java.time.LocalDate

case class Item(name: String, expiry: LocalDate, condition: String, numberOfOpen: Int = 0)