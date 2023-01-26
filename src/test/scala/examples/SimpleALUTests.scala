// See LICENSE.txt for license details.
package examples

import chisel3._
import chiseltest._
import scala.util.Random
import org.scalatest.flatspec.AnyFlatSpec
import chiseltest.simulator.SimulatorAnnotation

class SimpleALUTests extends AnyFlatSpec with ChiselScalatestTester {
  behavior.of("SimpleALU")

  it should "Perform Basic Calculation" in {
    test(new SimpleALU) { c =>
      val rnd = new Random
      for (n <- 0 until 64) {
        val a = rnd.nextInt(16)
        val b = rnd.nextInt(16)
        val opcode = rnd.nextInt(4)
        // for (a <- 0 until 16) {
        // for (b <- 0 until 16) {
        // for (opcode <- 0 until 4) {
        var output = 0
        if (opcode == 0) {
          output = ((a + b) & 0xf)
        } else if (opcode == 1) {
          output = ((a - b) & 0xf)
        } else if (opcode == 2) {
          output = a
        } else {
          output = b
        }
        c.io.a.poke(a)
        c.io.b.poke(b)
        c.io.opcode.poke(opcode)
        c.clock.step(1)
        c.io.out.expect(output)
      }
    // }}}
    }
  }
}
