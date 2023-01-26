// See LICENSE.txt for license details.
package examples

import chiseltest._
import scala.util.Random
import org.scalatest.flatspec.AnyFlatSpec
import chiseltest.simulator.SimulatorAnnotation

class HiLoMultiplierTests extends AnyFlatSpec with ChiselScalatestTester {
  behavior.of("HiLoMultiplier")

  it should "Compute 16-bit Product for lo and hi" in {
    test(new HiLoMultiplier()) { c =>
      val rnd = new Random
      for (t <- 0 until 4) {
        val rnd0 = rnd.nextInt(65535)
        val rnd1 = rnd.nextInt(65535)
        val ref_out = rnd0 * rnd1
        c.io.A.poke(rnd0)
        c.io.B.poke(rnd1)
        c.clock.step(1)
        c.io.Lo.expect(ref_out & BigInt("ffff", 16))
        c.io.Hi.expect((ref_out & BigInt("ffff0000", 16)) >> 16)
      }
    }
  }
}
