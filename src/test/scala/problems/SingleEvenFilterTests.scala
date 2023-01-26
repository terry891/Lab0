// See LICENSE.txt for license details.
package problems

import chisel3._
import chiseltest._
import scala.util.Random
import org.scalatest.flatspec.AnyFlatSpec
class SingleEvenFilterTests extends AnyFlatSpec with ChiselScalatestTester {
  behavior.of("SingleEvenFilter")

  it should "filterEvenNumber for UInt" in {
    test(new SingleEvenFilter[UInt](UInt(16.W))) { c =>
      val rnd = new Random
      val maxInt = 1 << 16
      for (i <- 0 until 10) {
        val in = rnd.nextInt(maxInt)
        c.io.in.valid.poke(1)
        c.io.in.bits.poke(in)
        val isSingleEven = (in <= 9) && (in % 2 == 1)
        c.clock.step(1)
        c.io.out.valid.expect(if (isSingleEven) 1 else 0)
        c.io.out.bits.expect(in)
      }
    }
  }
}
