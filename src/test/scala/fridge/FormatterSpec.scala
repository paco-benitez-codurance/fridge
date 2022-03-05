package fridge

import org.scalatest._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import java.time.LocalDate

class FormatterSpec extends AnyFlatSpec with Matchers with BeforeAndAfter {

  "Formatter" should "show now display of no product added" in {
    Formatter.showDisplay(Seq()) should be("")
  }

  "Formatter" should "show display of one product" in {
    val items = Seq(
      (Item("Milk", LocalDate.of(2021, 10, 21)), RemainingDays(3.toLong))
    )
    Formatter.showDisplay(items) should be( "Milk: 3 days remaining")
  }
  
  "Formatter" should "show display of two product with same date (3 days)" in {
    val items = Seq(
      (Item("Milk", LocalDate.of(2021, 10, 21)), RemainingDays(3)),
      (Item("Cheese", LocalDate.of(2021, 10, 21)), RemainingDays(3))
    )

    Formatter.showDisplay(items) should be("""Cheese: 3 days remaining
      |Milk: 3 days remaining""".stripMargin
    )
  }

  "Formatter" should "show display of one product with 1 day remaining" in {
    val items = Seq(
      (Item("Milk", LocalDate.of(2021, 10, 29)), RemainingDays(1))
    )
    Formatter.showDisplay(items) should be("Milk: 1 day remaining")
  }

  "Formatter" should "show show product by days remaining" in {
    val items = Seq(
      (Item("Cheese", LocalDate.of(2021, 10, 21)), RemainingDays(3)),
      (Item("Milk", LocalDate.of(2021, 10, 21)), RemainingDays(2))
    )

    Formatter.showDisplay(items) should be("""Milk: 2 days remaining
      |Cheese: 3 days remaining""".stripMargin
    )
  }

  "Formatter" should "show display of one expired product " in {
    val items = Seq(
      (Item("Milk", LocalDate.of(2021, 10, 29)), Expired)
    )
    Formatter.showDisplay(items) should be("EXPIRED: Milk")
  }




}
