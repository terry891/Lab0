// See LICENSE.txt for license details.
package examples

import chiseltest._
import scala.util.Random
import org.scalatest.flatspec.AnyFlatSpec
import chiseltest.simulator.SimulatorAnnotation

class ShiftRegisterTests extends AnyFlatSpec with ChiselScalatestTester {
  behavior.of("Shift Register")

  it should "shifting" in {
    test(new ShiftRegister()) { c =>
      val reg = Array.fill(4) { 0 }
      val rnd = new Random
      for (t <- 0 until 64) {
        val in = rnd.nextInt(2)
        c.io.in.poke(in)
        c.clock.step(1)
        for (i <- 3 to 1 by -1)
          reg(i) = reg(i - 1)
        reg(0) = in
        if (t >= 4) c.io.out.expect(reg(3))
      }
    }
  }
}
