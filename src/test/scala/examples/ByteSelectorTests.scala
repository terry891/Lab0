// See LICENSE.txt for license details.
package examples

import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec
import chiseltest.simulator.SimulatorAnnotation

class ByteSelectorTests extends AnyFlatSpec with ChiselScalatestTester {
  behavior.of("Byte Selector")

  it should "select byte by input" in {
    test(new ByteSelector()) { c =>
      val test_in = 12345678
      for (t <- 0 until 4) {
        c.io.in.poke(test_in)
        c.io.offset.poke(t)
        c.clock.step(1)
        c.io.out.expect((test_in >> (t * 8)) & 0xff)
      }
    }
  }
}
