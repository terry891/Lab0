// See LICENSE.txt for license details.
package solutions

import chiseltest._
import scala.util.Random
import org.scalatest.flatspec.AnyFlatSpec
class DynamicMemorySearchTests extends AnyFlatSpec with ChiselScalatestTester {
  behavior.of("DynamicMemorySearch")

  it should "Perform Dynamic Search" in {
    test(new DynamicMemorySearch(8, 4)) { c =>
      val rnd = new Random
      val list = Array.fill(c.n) { 0 }
      // Initialize the memory.
      for (k <- 0 until c.n) {
        c.io.en.poke(0)
        c.io.isWr.poke(1)
        c.io.wrAddr.poke(k)
        c.io.data.poke(0)
        c.clock.step(1)
      }

      for (k <- 0 until 16) {
        // WRITE A WORD
        c.io.en.poke(0)
        c.io.isWr.poke(1)
        val wrAddr = rnd.nextInt(c.n - 1)
        val data = rnd.nextInt((1 << c.w) - 1) + 1 // can't be 0
        c.io.wrAddr.poke(wrAddr)
        c.io.data.poke(data)
        c.clock.step(1)
        list(wrAddr) = data
        // SETUP SEARCH
        val target = if (k > 12) rnd.nextInt(1 << c.w) else data
        c.io.isWr.poke(0)
        c.io.data.poke(target)
        c.io.en.poke(1)
        c.clock.step(1)
        do {
          c.io.en.poke(0)
          c.clock.step(1)
        } while (c.io.done.peekInt() == BigInt(0))
        val addr = c.io.target.peekInt()
        if (list contains target)
          assert(list(addr.toInt) == target, "LOOKING FOR " + target + " FOUND " + addr)
        else
          assert(addr == (list.length - 1), "LOOKING FOR " + target + " FOUND " + addr)
      }
    }
  }
}
