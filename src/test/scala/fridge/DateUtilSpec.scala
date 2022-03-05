
package fridge

import org.scalatest._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import java.time.LocalDate

import DateUtil._


class DateUtilSpec extends AnyFlatSpec with Matchers with BeforeAndAfter {


    "Fridge setCurrentDate 18/10" should "set the currentDate" in {
        formatCurrentDate("18/10/2021") should be(LocalDate.of(2021, 10, 18))
    }

    "Fridge setCurrentDate 18/04" should "set the currentDate" in {
        formatCurrentDate("18/04/2021") should be(LocalDate.of(2021, 4, 18))
    }

    "Fridge simulateDayOver" should "add a new day" in {
        addOneDay(
            formatCurrentDate("18/04/2021") 
        ) should be(LocalDate.of(2021, 4, 19))
    }

    "Fridge simulateDayOver" should "add two days" in {
        addOneDay(
            addOneDay(
                formatCurrentDate("18/04/2021") 
            )
        ) should be(LocalDate.of(2021, 4, 20))
    }
}
