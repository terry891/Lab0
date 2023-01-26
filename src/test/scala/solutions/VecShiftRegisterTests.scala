// See LICENSE.txt for license details.
package solutions

import chiseltest._
import scala.util.Random
import org.scalatest.flatspec.AnyFlatSpec
class VecShiftRegisterTests extends AnyFlatSpec with ChiselScalatestTester {
  behavior.of("VecShiftRegister")

  it should "shifting" in {
    test(new VecShiftRegister) { c =>
      val rnd = new Random
      val reg = Array.fill(4) { 0 }
      val ins = Array.fill(4) { 0 }
      // Initialize the delays.
      for (i <- 0 until 4)
        c.io.ins(i).poke(0)
      c.io.load.poke(1)
      c.clock.step(1)

      for (t <- 0 until 16) {
        for (i <- 0 until 4)
          ins(i) = rnd.nextInt(16)
        val shift = rnd.nextInt(2)
        val load = rnd.nextInt(2)
        for (i <- 0 until 4)
          c.io.ins(i).poke(ins(i))
        c.io.load.poke(load)
        c.io.shift.poke(shift)
        c.clock.step(1)
        if (load == 1) {
          for (i <- 0 until 4)
            reg(i) = ins(i)
        } else if (shift == 1) {
          for (i <- 3 to 1 by -1)
            reg(i) = reg(i - 1)
          reg(0) = ins(0)
        }
        c.io.out.expect(reg(3))
      }
    }
  }
}
