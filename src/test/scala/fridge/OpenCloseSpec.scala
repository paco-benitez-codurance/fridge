
package fridge

import org.scalatest._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import java.time.LocalDate


class OpenCloseSpec extends AnyFlatSpec with Matchers with BeforeAndAfter {

    var fridge: Fridge = null

    before {
        fridge = new Fridge()
    }

    "Fridge signalFridgeDoorOpened" should "allow open door" in {
        noException should be thrownBy fridge.signalFridgeDoorOpened()
    }

    "Fridge signalFridgeDoorOpened" should "not allow open door twice" in {
        fridge.signalFridgeDoorOpened()

        assertThrows[java.lang.UnsupportedOperationException] {
            fridge.signalFridgeDoorOpened()
        }
    }

    "Fridge signalFridgeDoorClosed" should "allow close door if it is open" in {
        fridge.signalFridgeDoorOpened()
        noException should be thrownBy fridge.signalFridgeDoorClosed()
    }

    "Fridge signalFridgeDoorOpened / signalFridgeDoorClosed" should "be cooherent" in {
        noException should be thrownBy {
            fridge.signalFridgeDoorOpened()
            fridge.signalFridgeDoorClosed()
            fridge.signalFridgeDoorOpened()
            fridge.signalFridgeDoorClosed()
        }
    }

    "Fridge signalFridgeDoorClosed / " should "allow not allow close door if it is not open" in {
        assertThrows[java.lang.UnsupportedOperationException] {
            fridge.signalFridgeDoorClosed()
        }
    }

}
