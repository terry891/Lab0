// See LICENSE.txt for license details.
package examples

import chiseltest._
import scala.util.Random
import org.scalatest.flatspec.AnyFlatSpec
import chiseltest.simulator.SimulatorAnnotation

class ParityTests extends AnyFlatSpec with ChiselScalatestTester {
  behavior.of("Parity")

  it should "Verify Even/Odd" in {
    test(new Parity) { c =>
      val rnd = new Random
      var isOdd = 0
      for (t <- 0 until 10) {
        val bit = rnd.nextInt(2)
        c.io.in.poke(bit)
        c.clock.step(1)
        isOdd = (isOdd + bit) % 2;
        c.io.out.expect(isOdd)
      }
    }
  }
}
