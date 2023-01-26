// See LICENSE.txt for license details.
package solutions

import chiseltest._
import scala.util.Random
import org.scalatest.flatspec.AnyFlatSpec
class VecShiftRegisterSimpleTests extends AnyFlatSpec with ChiselScalatestTester {
  behavior.of("VecShiftRegisterSimple")

  it should "shifting" in {
    test(new VecShiftRegisterSimple) { c =>
      val rnd = new Random
      val reg = Array.fill(4) { 0 }
      for (t <- 0 until 16) {
        val in = rnd.nextInt(256)
        c.io.in.poke(in)
        c.clock.step(1)
        for (i <- 3 to 1 by -1)
          reg(i) = reg(i - 1)
        reg(0) = in
        c.io.out.expect(reg(3))
      }
    }
  }
}
