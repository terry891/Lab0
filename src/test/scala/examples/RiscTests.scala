// See LICENSE.txt for license details.
package examples

import chisel3._
import chiseltest._
import scala.util.Random
import org.scalatest.flatspec.AnyFlatSpec
import chiseltest.simulator.SimulatorAnnotation

class RiscTests extends AnyFlatSpec with ChiselScalatestTester {
  behavior.of("Risc")

  it should "Execute Simple Program" in {
    test(new Risc) { c =>
      val app = Array(
        I(c.imm_op, 1, 0, 1), // r1 <- 1
        I(c.add_op, 1, 1, 1), // r1 <- r1 + r1
        I(c.add_op, 1, 1, 1), // r1 <- r1 + r1
        I(c.add_op, 255, 1, 0)
      ) // rh <- r1

      wr(c, 0, 0) // skip reset
      for (addr <- 0 until app.length)
        wr(c, addr, app(addr))
      boot(c)
      var k = 0
      do {
        tick(c); k += 1
      } while (c.io.valid.peekBoolean() == false && k < 10)
      assert(k < 10, "TIME LIMIT")
      c.io.out.expect(4)
    }
  }

  private def wr(c: Risc, addr: BigInt, data: BigInt): Unit = {
    c.io.isWr.poke(1)
    c.io.wrAddr.poke(addr)
    c.io.wrData.poke(data)
    c.clock.step(1)
  }
  private def boot(c: Risc): Unit = {
    c.io.isWr.poke(0)
    c.io.boot.poke(1)
    c.clock.step(1)
  }
  private def tick(c: Risc): Unit = {
    c.io.isWr.poke(0)
    c.io.boot.poke(0)
    c.clock.step(1)
  }
  /*
  def I (op: UInt, rc: Int, ra: Int, rb: Int) =
    Cat(op, UInt(rc, 8), UInt(ra, 8), UInt(rb, 8))
   */

  private def I(op: UInt, rc: Int, ra: Int, rb: Int) =
    ((op.litValue & 1) << 24) | ((rc & Integer
      .parseInt("FF", 16)) << 16) | ((ra & Integer.parseInt("FF", 16)) << 8) | (rb & Integer.parseInt("FF", 16))
}
