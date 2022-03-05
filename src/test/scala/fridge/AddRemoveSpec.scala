
package fridge

import org.scalatest._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import java.time.LocalDate


class AddRemoveSpec extends AnyFlatSpec with Matchers with BeforeAndAfterEach {

    var fridge: Fridge = null

    override def beforeEach() = {
        fridge = new Fridge()
    }

    "Fridge scanAddedItem" should "add element without error" in {
        noException should be thrownBy {
            fridge.signalFridgeDoorOpened()
            fridge.scanAddedItem(name = "Milk", expiry= "21/10/21", condition= "sealed")
        }
    }

    "Fridge scanAddedItem" should "not allow add element if door is not open" in {
        assertThrows[java.lang.UnsupportedOperationException] {
            fridge.scanAddedItem(name = "Milk", expiry= "21/10/21", condition= "sealed")
        }
    }

    "Fridge scanRemovedItem" should "remove element without error" in {
        noException should be thrownBy {
            fridge.signalFridgeDoorOpened()
            fridge.scanAddedItem(name = "Milk", expiry= "21/10/21", condition= "sealed")
            fridge.scanRemovedItem("Milk")
        }
    }

    "Fridge scanAddedItem" should "not allow remove element if door is not open" in {
        assertThrows[java.lang.UnsupportedOperationException] {
            fridge.scanRemovedItem("Milk")
        }
    }

    "Fridge scanAddedItem" should "not allow remove element if it is not in fridge" in {
        assertThrows[java.lang.UnsupportedOperationException] {
            fridge.signalFridgeDoorOpened()
            fridge.scanRemovedItem("Milk")
        }
    }

    "Fridge scanAddedItem" should "add two element and remove one without error" in {
        noException should be thrownBy {
            fridge.signalFridgeDoorOpened()
            fridge.scanAddedItem(name = "Milk", expiry= "21/10/21", condition= "sealed")
            fridge.scanAddedItem(name= "Cheese", expiry= "18/11/21", condition= "sealed")
            fridge.scanRemovedItem("Milk")
        }
    }

    "Fridge scanAddedItem" should "show item in display" in {
        fridge.signalFridgeDoorOpened()
        fridge.scanAddedItem(name = "Milk", expiry= "21/10/21", condition= "sealed")
        fridge.showDisplay() should include ("Milk")
    }

    "Fridge scanRemoveItem" should "show remove item in display" in {
        fridge.signalFridgeDoorOpened()
        fridge.scanAddedItem(name = "Milk", expiry= "21/10/21", condition= "sealed")
        fridge.scanRemovedItem("Milk")
        fridge.showDisplay() should not include ("Milk")
    }

}
