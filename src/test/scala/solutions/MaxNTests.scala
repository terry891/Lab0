// See LICENSE.txt for license details.
package solutions

import chiseltest._
import scala.util.Random
import org.scalatest.flatspec.AnyFlatSpec
class MaxNTests extends AnyFlatSpec with ChiselScalatestTester {
  behavior.of("MaxN")

  it should "find max from N numbers" in {
    test(new MaxN(8, 16)) { c =>
      val rnd = new Random
      val ins = Array.fill(c.n) { 0 }
      for (i <- 0 until 10) {
        var mx = 0
        for (i <- 0 until c.n) {
          ins(i) = rnd.nextInt(1 << c.w)
          c.io.ins(i).poke(ins(i))
          mx = if (ins(i) > mx) ins(i) else mx
        }
        c.clock.step(1)
        c.io.out.expect(mx)
      }
    }
  }
}
