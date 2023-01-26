// See LICENSE.txt for license details.
package problems

import chiseltest._
import scala.util.Random
import org.scalatest.flatspec.AnyFlatSpec
class Mux2Tests extends AnyFlatSpec with ChiselScalatestTester {
  behavior.of("Mux2")

  it should "Muxing 2 inputs" in {
    test(new Mux2) { c =>
      for (s <- 0 until 2) {
        for (i0 <- 0 until 2) {
          for (i1 <- 0 until 2) {
            c.io.sel.poke(s)
            c.io.in1.poke(i1)
            c.io.in0.poke(i0)
            c.clock.step(1)
            c.io.out.expect(if (s == 1) i1 else i0)
          }
        }
      }
    }
  }
}
