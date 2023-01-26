// See LICENSE.txt for license details.
package solutions
import chiseltest._
import scala.util.Random
import org.scalatest.flatspec.AnyFlatSpec
class AdderTests extends AnyFlatSpec with ChiselScalatestTester {
  behavior.of("Adder")

  it should "Perform Addition" in {
    test(new Adder(16)) { c =>
      val rnd = new Random
      for (i <- 0 until 10) {
        val in0 = rnd.nextInt(1 << c.w)
        val in1 = rnd.nextInt(1 << c.w)
        c.io.in0.poke(in0)
        c.io.in1.poke(in1)
        c.clock.step(1)
        c.io.out.expect((in0 + in1) & ((1 << c.w) - 1))
      }
    }
  }
}
