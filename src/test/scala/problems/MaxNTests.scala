// See LICENSE.txt for license details.
package problems

import chiseltest._
import scala.util.Random
import org.scalatest.flatspec.AnyFlatSpec
class MaxNTests extends AnyFlatSpec with ChiselScalatestTester {
  behavior.of("MaxN")

  it should "find max from N numbers" in {
    test(new MaxN(8, 16)) { c =>
      for (i <- 0 until 10) {
        var mx = 0
        for (i <- 0 until c.n) {
          // Implement below ----------
          c.io.ins(0).poke(0)
          // Implement above ----------
        }
        c.clock.step(1)
        // Implement below ----------
        c.io.out.expect(1)
        // Implement above ----------
      }
    }
  }
}
