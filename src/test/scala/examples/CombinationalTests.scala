// See LICENSE.txt for license details.
package examples

import chiseltest._
import scala.util.Random
import org.scalatest.flatspec.AnyFlatSpec
import chiseltest.simulator.SimulatorAnnotation

class CombinationalTests extends AnyFlatSpec with ChiselScalatestTester {
  behavior.of("Simple Combional Circuit")

  it should "add two input numbers" in {
    test(new Combinational()) { c =>
      val maxInt = 1 << 16
      val rnd = new Random()
      for (i <- 0 until 10) {
        val x = rnd.nextInt(maxInt)
        val y = rnd.nextInt(maxInt)
        c.io.x.poke(x)
        c.io.y.poke(y)
        c.clock.step(1)
        c.io.z.expect((x + y) & (maxInt - 1))
      }
    }
  }
}
