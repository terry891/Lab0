// See LICENSE.txt for license details.
package examples

import scala.util.Random
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec
import chiseltest.simulator.SimulatorAnnotation

class Adder4Tests extends AnyFlatSpec with ChiselScalatestTester {

  behavior.of("Adder4")

  it should "Compute Sum of two 4-bit numbers" in {
    test(new Adder4()) { c =>
      val rnd = new Random()
      val rnd2 = rnd.nextInt(2)
      for (t <- 0 until 4) {
        val rnd0 = rnd.nextInt(16)
        val rnd1 = rnd.nextInt(16)
        val rnd2 = rnd.nextInt(2)
        c.io.A.poke(rnd0)
        c.io.B.poke(rnd1)
        c.io.Cin.poke(rnd2)
        c.clock.step(1)
        val rsum = (rnd0 & 0xf) + (rnd1 & 0xf) + (rnd2 & 0x1)
        c.io.Sum.expect((rsum & 0xf))
        c.io.Cout.expect(rsum >> 4)
      }
    }
  }

}
