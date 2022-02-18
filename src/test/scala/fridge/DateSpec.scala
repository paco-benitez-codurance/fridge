
package fridge

import org.scalatest._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import java.time.LocalDate


class DateSpec extends AnyFlatSpec with Matchers with BeforeAndAfter {

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
}
