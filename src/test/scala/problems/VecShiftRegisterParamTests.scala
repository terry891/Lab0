// See LICENSE.txt for license details.
package problems

import chiseltest._
import scala.util.Random
import org.scalatest.flatspec.AnyFlatSpec
class VecShiftRegisterParamTests extends AnyFlatSpec with ChiselScalatestTester {
  behavior.of("VecShiftRegisterParam")

  it should "shifting" in {
    test(new VecShiftRegisterParam(8, 4)) { c =>
      val reg = Array.fill(c.n) { 0 }
      val rnd = new Random
      for (t <- 0 until 16) {
        val in = rnd.nextInt(1 << c.w)
        c.io.in.poke(in)
        c.clock.step(1)
        for (i <- c.n - 1 to 1 by -1)
          reg(i) = reg(i - 1)
        reg(0) = in
        c.io.out.expect(reg(c.n - 1))
      }
    }
  }
}
