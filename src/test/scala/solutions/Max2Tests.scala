// See LICENSE.txt for license details.
package solutions

import chiseltest._
import scala.util.Random
import org.scalatest.flatspec.AnyFlatSpec
class Max2Tests extends AnyFlatSpec with ChiselScalatestTester {
  behavior.of("Max2")

  it should "find max from 2" in {
    test(new Max2) { c =>
      val rnd = new Random
      // FILL THIS IN HERE
      for (i <- 0 until 10) {
        // FILL THIS IN HERE
        val in0 = rnd.nextInt(256)
        val in1 = rnd.nextInt(256)
        c.io.in0.poke(in0)
        c.io.in1.poke(in1)
        c.clock.step(1)
        c.io.out.expect(if (in0 > in1) in0 else in1)
      }
    }
  }
}
