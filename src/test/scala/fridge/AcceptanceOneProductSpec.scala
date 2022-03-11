package fridge

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class AcceptanceOneProductSpec() extends AnyFlatSpec with Matchers {
  "Fridge" should "work acceptance spec Lettuce" in {
     val fridge = new Fridge()

    fridge.setCurrentDate("20/10/2021")


    fridge.signalFridgeDoorOpened()
    fridge.scanAddedItem(name= "Lettuce", expiry= "22/10/21", condition= "opened")
    fridge.signalFridgeDoorClosed()

    fridge.signalFridgeDoorOpened()
    fridge.signalFridgeDoorClosed()

    fridge.simulateDayOver()

    fridge.showDisplay() should be(
      """Lettuce: 1 day remaining"""
    )
  }

  "Fridge" should "work acceptance spec Cheese" in {
    val fridge = new Fridge()


    fridge.setCurrentDate("18/10/2021")

    fridge.signalFridgeDoorOpened()
    fridge.scanAddedItem(name= "Cheese", expiry= "18/11/21", condition= "sealed")
    fridge.signalFridgeDoorClosed()

    fridge.simulateDayOver()

    fridge.simulateDayOver()

    fridge.simulateDayOver()

    fridge.showDisplay() should be(
      """Cheese: 28 days remaining"""
    )
  }

  "Fridge" should "work acceptance spec Milk" in {
     val fridge = new Fridge()

    fridge.setCurrentDate("21/10/2021")

    fridge.signalFridgeDoorOpened()
    fridge.scanAddedItem(name= "Milk", expiry= "26/10/21", condition= "opened")
    fridge.signalFridgeDoorClosed()


    fridge.signalFridgeDoorOpened()
    fridge.signalFridgeDoorClosed()

    fridge.signalFridgeDoorOpened()
    fridge.signalFridgeDoorClosed()

    fridge.signalFridgeDoorOpened()
    fridge.signalFridgeDoorClosed()


    fridge.showDisplay() should be(
      """Milk: 5 days remaining"""
    )



  }

}
