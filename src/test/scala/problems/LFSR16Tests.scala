// See LICENSE.txt for license details.
package problems

import chiseltest._
import scala.util.Random
import org.scalatest.flatspec.AnyFlatSpec
class LFSR16Tests extends AnyFlatSpec with ChiselScalatestTester {
  behavior.of("LFSR16")

  it should "Perform LSFR" in {
    test(new LFSR16) { c =>
      val rnd = new Random
      var res = 1
      for (t <- 0 until 16) {
        val inc = rnd.nextInt(2)
        c.io.inc.poke(inc)
        c.clock.step(1)
        if (inc == 1) {
          val bit = ((res >> 0) ^ (res >> 2) ^ (res >> 3) ^ (res >> 5)) & 1
          res = (res >> 1) | (bit << 15)
        }
        c.io.out.expect(res)
      }
    }
  }
}
