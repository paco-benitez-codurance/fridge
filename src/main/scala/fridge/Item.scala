package fridge

import java.time.LocalDate

case class Item(name: String, expiry: LocalDate, numberOfOpen: Int = 0)