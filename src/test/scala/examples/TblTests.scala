// See LICENSE.txt for license details.
package examples

import chiseltest._
import scala.util.Random
import org.scalatest.flatspec.AnyFlatSpec

class TblTests extends AnyFlatSpec with ChiselScalatestTester {
  behavior.of("Tbl")

  it should "Perform Table Lookup" in {
    test(new Tbl) { c =>
      val rnd = new Random
      for (t <- 0 until 16) {
        val addr = rnd.nextInt(256)
        c.io.addr.poke(addr)
        c.clock.step(1)
        c.io.out.expect(addr)
      }
    }
  }
}
