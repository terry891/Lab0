// See LICENSE.txt for license details.
package problems

import chiseltest._
import scala.util.Random
import org.scalatest.flatspec.AnyFlatSpec
class MemoTests extends AnyFlatSpec with ChiselScalatestTester {
  behavior.of("Memo")

  it should "implement read and write" in {
    test(new Memo) { c =>
      def rd(addr: Int, data: Int) = {
        c.io.ren.poke(1)
        c.io.rdAddr.poke(addr)
        c.clock.step(1)
        c.io.rdData.expect(data)
      }
      def wr(addr: Int, data: Int) = {
        c.io.wen.poke(1)
        c.io.wrAddr.poke(addr)
        c.io.wrData.poke(data)
        c.clock.step(1)
      }
      wr(0, 1)
      rd(0, 1)
      wr(9, 11)
      rd(9, 11)
    }
  }
}
