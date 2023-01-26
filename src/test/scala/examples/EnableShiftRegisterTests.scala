// See LICENSE.txt for license details.
package examples

import chiseltest._
import scala.util.Random
import org.scalatest.flatspec.AnyFlatSpec
import chiseltest.simulator.SimulatorAnnotation

class EnableShiftRegisterTests extends AnyFlatSpec with ChiselScalatestTester {
  behavior.of("Enable Shift Register")

  it should "shifting" in {
    test(new EnableShiftRegister()) { c =>
      val reg = Array.fill(4) { 0 }
      val rnd = new Random
      for (t <- 0 until 16) {
        val in = rnd.nextInt(16)
        val shift = rnd.nextInt(2)
        c.io.in.poke(in)
        c.io.shift.poke(shift)
        c.clock.step(1)
        if (shift == 1) {
          for (i <- 3 to 1 by -1)
            reg(i) = reg(i - 1)
          reg(0) = in
        }
        c.io.out.expect(reg(3))
      }
    }
  }
}
