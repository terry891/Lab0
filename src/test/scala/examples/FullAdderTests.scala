// See LICENSE.txt for license details.
package examples

import chiseltest._
import scala.util.Random
import org.scalatest.flatspec.AnyFlatSpec
import chiseltest.simulator.SimulatorAnnotation

class FullAdderTests extends AnyFlatSpec with ChiselScalatestTester {
  behavior.of("Full Adder")

  it should "Add Two input bits" in {
    test(new FullAdder()) { c =>
      val rnd = new Random
      for (t <- 0 until 4) {
        val a = rnd.nextInt(2)
        val b = rnd.nextInt(2)
        val cin = rnd.nextInt(2)
        val res = a + b + cin
        val sum = res & 1
        val cout = (res >> 1) & 1
        c.io.a.poke(a)
        c.io.b.poke(b)
        c.io.cin.poke(cin)
        c.clock.step(1)
        c.io.sum.expect(sum)
        c.io.cout.expect(cout)
      }
    }
  }
}
