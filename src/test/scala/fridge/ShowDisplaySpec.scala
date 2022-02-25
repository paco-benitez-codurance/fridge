package fridge

import org.scalatest._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ShowDisplaySpec extends AnyFlatSpec with Matchers with BeforeAndAfter {

  var fridge: Fridge = null
  before {
    fridge = new Fridge()
  }

  "Fridge" should "show now display of no product added" in {
    fridge.showDisplay() should be("")
  }

  "Fridge" should "show display of one product" in {
    fridge.setCurrentDate("18/10/2021")
    fridge.signalFridgeDoorOpened()
    fridge.scanAddedItem(name = "Milk", expiry= "21/10/21", condition= "sealed")

    fridge.showDisplay() should be( "Milk: 3 days remaining")

  }
  
  "Fridge" should "show display of two product with same date (3 days)" in {
    fridge.setCurrentDate("18/10/2021")
    fridge.signalFridgeDoorOpened()
    fridge.scanAddedItem(name = "Milk", expiry= "21/10/21", condition= "sealed")
    fridge.scanAddedItem(name = "Cheese", expiry= "21/10/21", condition= "sealed")

    fridge.showDisplay() should be("""Milk: 3 days remaining
      |Cheese: 3 days remaining""".stripMargin
    )
  }


  "Fridge" should "show display of one product with 1 day remaining" in {
    fridge.setCurrentDate("18/10/2021")
    fridge.signalFridgeDoorOpened()
    fridge.scanAddedItem(name = "Milk", expiry= "19/10/21", condition= "sealed")

    fridge.showDisplay() should be("Milk: 1 day remaining")
  }

}
