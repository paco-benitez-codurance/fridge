package fridge

import org.scalatest._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class SimulateDayOverSpec extends AnyFlatSpec with Matchers  with BeforeAndAfterEach {
  
  var fridge: Fridge = null

  override def beforeEach() = {
    fridge = new Fridge()

    fridge.setCurrentDate("18/10/2021")
    fridge.signalFridgeDoorOpened()
    fridge.scanAddedItem(name = "Milk", expiry= "21/10/21", condition= "sealed")
  }

  "Fridge" should "simulate one day over" in {
    fridge.simulateDayOver()
    fridge.showDisplay() should be(
      """Milk: 2 days remaining"""
    )
  }

  "Fridge" should "simulate three day over" in {
    (1 to 3).map(_ => fridge.simulateDayOver())

    fridge.showDisplay() should be(
      """Milk: 0 days remaining"""
    )
  }

   "Fridge" should "simulate EXPIRY" in {
    (1 to 4).map(_ => fridge.simulateDayOver())

    fridge.simulateDayOver()

    fridge.showDisplay() should be(
      """EXPIRED: Milk"""
    )
  }


}
