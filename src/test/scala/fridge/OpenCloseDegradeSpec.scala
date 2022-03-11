package fridge

import org.scalatest._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class OpenCloseDegradeSpec extends AnyFlatSpec with Matchers  with BeforeAndAfterEach {
  
  var fridge: Fridge = null

  override def beforeEach() = {
    fridge = new Fridge()

    fridge.setCurrentDate("18/10/2021")

  }

  def addMilkSealed(): Unit = {
    fridge.signalFridgeDoorOpened()
    fridge.scanAddedItem(name = "Milk", expiry= "21/10/21", condition= "sealed")
    fridge.signalFridgeDoorClosed()
  }

  def openAndClose(times: Int): Unit = {
    (1 to times).foreach(_ => {
      fridge.signalFridgeDoorOpened()
      fridge.signalFridgeDoorClosed()
    })
  }

  "One sealed product" should "no degraded at all in 23 opens" in {
    addMilkSealed()
    openAndClose(23)
    fridge.showDisplay() should be(
      """Milk: 3 days remaining"""
    )
  }

  "One sealed product" should "degraded one day in 24 opens" in {
    addMilkSealed()
    openAndClose(24)
    fridge.showDisplay() should be(
      """Milk: 2 days remaining"""
    )
  }

  "One sealed product" should "degraded two days in 48 opens" in {
    addMilkSealed()
    openAndClose(48)
    fridge.showDisplay() should be(
      """Milk: 1 day remaining"""
    )
  }

  "Two sealed product" should "degraded one day each in 24 opens" in {
    addMilkSealed()
    fridge.signalFridgeDoorOpened()
    fridge.scanAddedItem(name = "Beef", expiry= "22/10/21", condition= "sealed")
    fridge.signalFridgeDoorClosed()
    openAndClose(24)

    fridge.showDisplay() should be(
      """Milk: 2 days remaining
      |Beef: 3 days remaining""".stripMargin
    )
  }

  "Two sealed product" should "one expiry by open and close, one not" in {
    addMilkSealed()
    openAndClose(24)

    fridge.signalFridgeDoorOpened()
    fridge.scanAddedItem(name = "Beef", expiry= "21/10/21", condition= "sealed")
    fridge.signalFridgeDoorClosed()

    fridge.showDisplay() should be(
      """Milk: 2 days remaining
      |Beef: 3 days remaining""".stripMargin
    )
  }

  "One opened product" should "degraded 5 hours each open" in {
    fridge.signalFridgeDoorOpened()
    fridge.scanAddedItem(name = "Milk", expiry= "21/10/21", condition= "opened")
    fridge.signalFridgeDoorClosed()

    openAndClose(4)
    fridge.showDisplay() should be(
      """Milk: 3 days remaining"""
    )
    openAndClose(1)

  }



}
