// See LICENSE.txt for license details.
package examples

import chiseltest._
import scala.util.Random
import org.scalatest.flatspec.AnyFlatSpec
import chiseltest.simulator.SimulatorAnnotation

class LogShifterTests extends AnyFlatSpec with ChiselScalatestTester {
  behavior.of("Log Shifter")

  it should "shifting" in {
    test(new LogShifter()) { c =>
      val rnd = new Random
      val in_num = rnd.nextInt(65536) //16-bit
      val in_shamt = rnd.nextInt(16) //4-bit
      c.io.in.poke(in_num)
      c.io.shamt.poke(in_shamt)
      // 4 stage shifting
      c.clock.step(4)
      c.io.out.expect((in_num << in_shamt) % 65536)
    }
  }
}
