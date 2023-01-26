// See LICENSE.txt for license details.
package solutions

import chiseltest._
import scala.util.Random
import org.scalatest.flatspec.AnyFlatSpec
class MulTests extends AnyFlatSpec with ChiselScalatestTester {
  behavior.of("Mul")

  it should "Compute Product" in {
    test(new Mul) { c =>
      val rnd = new Random
      val maxInt = 1 << 4
      for (i <- 0 until 10) {
        val x = rnd.nextInt(maxInt)
        val y = rnd.nextInt(maxInt)
        c.io.x.poke(x)
        c.io.y.poke(y)
        c.clock.step(1)
        c.io.z.expect(x * y)
      }
    }
  }
}
