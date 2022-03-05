package fridge

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class AcceptanceSpec(ignore: Boolean) extends AnyFlatSpec with Matchers {
  "Fridge" should "run simulation of three days" in {
    val fridge = new Fridge()

    fridge.setCurrentDate("18/10/2021")

    fridge.signalFridgeDoorOpened()
    fridge.scanAddedItem(name = "Milk", expiry= "21/10/21", condition= "sealed")
    fridge.scanAddedItem(name= "Cheese", expiry= "18/11/21", condition= "sealed")
    fridge.scanAddedItem(name= "Beef", expiry= "20/10/21", condition= "sealed")
    fridge.scanAddedItem(name= "Lettuce", expiry= "22/10/21", condition= "sealed")
    fridge.signalFridgeDoorClosed()

    fridge.simulateDayOver()

    fridge.signalFridgeDoorOpened()
    fridge.signalFridgeDoorClosed()

    fridge.signalFridgeDoorOpened()
    fridge.signalFridgeDoorClosed()

    fridge.signalFridgeDoorOpened()
    fridge.scanRemovedItem(name= "Milk")
    fridge.signalFridgeDoorClosed()

    fridge.signalFridgeDoorOpened()
    fridge.scanAddedItem(name= "Milk", expiry= "26/10/21", condition= "opened")
    fridge.scanAddedItem(name= "Peppers", expiry= "23/10/21", condition= "opened")
    fridge.signalFridgeDoorClosed()

    fridge.simulateDayOver()

    fridge.signalFridgeDoorOpened()
    fridge.scanRemovedItem(name= "Beef")
    fridge.scanRemovedItem(name= "Lettuce")
    fridge.signalFridgeDoorClosed()

    fridge.signalFridgeDoorOpened()
    fridge.scanAddedItem(name= "Lettuce", expiry= "22/10/21", condition= "opened")
    fridge.signalFridgeDoorClosed()

    fridge.signalFridgeDoorOpened()
    fridge.signalFridgeDoorClosed()

    fridge.simulateDayOver()

    fridge.showDisplay() should be(
      """EXPIRED: Milk
Lettuce: 0 days remaining
Peppers: 1 day remaining
Cheese: 31 days remaining"""
    )


  }
}
