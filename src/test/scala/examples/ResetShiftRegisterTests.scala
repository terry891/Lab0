// See LICENSE.txt for license details.
package examples

import chiseltest._
import scala.util.Random
import org.scalatest.flatspec.AnyFlatSpec
import chiseltest.simulator.SimulatorAnnotation

class ResetShiftRegisterTests extends AnyFlatSpec with ChiselScalatestTester {
  behavior.of("Reset Shift Register")

  it should "reset" in {
    test(new ResetShiftRegister()) { c =>
      val rnd = Random
      val ins = Array.fill(4) { 0 }
      val regs = Array.fill(4) { 0 }
      var k = 0
      for (n <- 0 until 16) {
        val in = rnd.nextInt(16)
        val shift = rnd.nextInt(2)
        c.io.in.poke(in)
        c.io.shift.poke(shift)
        c.clock.step(1)
        if (shift == 1) {
          ins(k % 4) = in
          k = k + 1
        }
       c.io.out.expect((if (k < 4) 0 else ins(k % 4)))
      }
    }
  }
}
