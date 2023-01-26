// See LICENSE.txt for license details.
package problems

import chiseltest._
import scala.util.Random
import org.scalatest.flatspec.AnyFlatSpec
class Max2Tests extends AnyFlatSpec with ChiselScalatestTester {
  behavior.of("Max2")

  it should "find max from 2" in {
    test(new Max2) { c =>
      for (i <- 0 until 10) {
        // Implement below ----------
        c.io.in0.poke(0)
        c.io.in1.poke(0)
        c.clock.step(1)
        c.io.out.expect(1)
        // Implement above ----------
      }
    }
  }
}
