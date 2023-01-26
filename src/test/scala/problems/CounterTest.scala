// See LICENSE.txt for license details.
package problems

import chiseltest._
import scala.util.Random
import org.scalatest.flatspec.AnyFlatSpec
class CounterTests extends AnyFlatSpec with ChiselScalatestTester {
  behavior.of("Counter")

  it should "Perform Counting" in {
    test(new Counter) { c =>
      val rnd = new Random
      val maxInt = 16
      var curCnt = 0

      c.io.inc.poke(0)
      c.io.amt.poke(0)

      // let it spin for a bit
      for (i <- 0 until 5) {
        c.clock.step(1)
      }

      for (i <- 0 until 10) {
        val inc = rnd.nextBoolean()
        val amt = rnd.nextInt(maxInt)
        c.io.inc.poke(if (inc) 1 else 0)
        c.io.amt.poke(amt)
        c.clock.step(1)
        curCnt = if (inc) intWrapAround(curCnt + amt, 255) else curCnt
        c.io.tot.expect(curCnt)
      }
    }
  }
  private def intWrapAround(n: Int, max: Int) =
    if (n > max) 0 else n
}
