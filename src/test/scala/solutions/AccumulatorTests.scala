// See LICENSE.txt for license details.
package solutions

import chiseltest._
import scala.util.Random
import org.scalatest.flatspec.AnyFlatSpec

class AccumulatorTests extends AnyFlatSpec with ChiselScalatestTester {
  behavior.of("Accumulator")

  it should "Perform Accumulation" in {
    test(new Accumulator) { c =>
      var tot = 0
      val rnd = new Random
      for (t <- 0 until 16) {
        val in = rnd.nextInt(2)
        c.io.in.poke(in)
        c.clock.step(1)
        if (in == 1) tot += 1
        c.io.out.expect(tot)
      }
    }
  }
}
