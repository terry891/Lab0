// See LICENSE.txt for license details.
package solutions

import chiseltest._
import scala.util.Random
import org.scalatest.flatspec.AnyFlatSpec
class Mux4Tests extends AnyFlatSpec with ChiselScalatestTester {
  behavior.of("Mux4")

  it should "Muxing 4 inputs" in {
    test(new Mux4) { c =>
      for (s0 <- 0 until 2) {
        for (s1 <- 0 until 2) {
          for (i0 <- 0 until 2) {
            for (i1 <- 0 until 2) {
              for (i2 <- 0 until 2) {
                for (i3 <- 0 until 2) {
                  c.io.sel.poke(s1 << 1 | s0)
                  c.io.in0.poke(i0)
                  c.io.in1.poke(i1)
                  c.io.in2.poke(i2)
                  c.io.in3.poke(i3)
                  c.clock.step(1)
                  val out = if (s1 == 1) {
                    if (s0 == 1) i3 else i2
                  } else {
                    if (s0 == 1) i1 else i0
                  }
                  c.io.out.expect(out)
                }
              }
            }
          }
        }
      }
    }
  }
}
