
package fridge

import org.scalatest._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import java.time.LocalDate


class FridgeSpec extends AnyFlatSpec with Matchers with BeforeAndAfter {

    var fridge: Fridge = null

    before {
        fridge = new Fridge()
    }

    "Fridge setCurrentDate 18/10" should "set the currentDate" in {
        fridge.setCurrentDate("18/10/2021")
        fridge.getTempCurrentDate() should be(LocalDate.of(2021, 10, 18))
    }

    "Fridge setCurrentDate 18/04" should "set the currentDate" in {
        fridge.setCurrentDate("18/04/2021")
        fridge.getTempCurrentDate() should be(LocalDate.of(2021, 4, 18))
    }

    "Fridge simulateDayOver" should "add a new day" in {
        fridge.setCurrentDate("18/04/2021")
        fridge.simulateDayOver()
        fridge.getTempCurrentDate() should be(LocalDate.of(2021, 4, 19))
    }

    "Fridge simulateDayOver" should "add two days" in {
        fridge.setCurrentDate("18/04/2021")
        fridge.simulateDayOver()
        fridge.simulateDayOver()
        fridge.getTempCurrentDate() should be(LocalDate.of(2021, 4, 20))
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
