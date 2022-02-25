package fridge

import org.scalatest._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import java.time.LocalDate

class FormatterSpec extends AnyFlatSpec with Matchers with BeforeAndAfter {


  "Formatter" should "show now display of no product added" in {
    Formatter.showDisplay(Seq(), LocalDate.now) should be("")
  }

  "Formatter" should "show display of one product" in {
    val items = Seq(
      Item("Milk", LocalDate.of(2021, 10, 21))
    )
    Formatter.showDisplay(items, LocalDate.of(2021, 10, 18)) should be( "Milk: 3 days remaining")
  }
  
  "Formatter" should "show display of two product with same date (3 days)" in {
    val items = Seq(
      Item("Milk", LocalDate.of(2021, 10, 21)),
      Item("Cheese", LocalDate.of(2021, 10, 21))
    )

    Formatter.showDisplay(items, LocalDate.of(2021, 10, 18)) should be("""Milk: 3 days remaining
      |Cheese: 3 days remaining""".stripMargin
    )
  }


  "Formatter" should "show display of one product with 1 day remaining" in {
    val items = Seq(
      Item("Milk", LocalDate.of(2021, 10, 29))
    )
    Formatter.showDisplay(items, LocalDate.of(2021, 10, 28)) should be("Milk: 1 day remaining")
  }

}
