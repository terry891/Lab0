// See LICENSE.txt for license details.
package solutions

import chiseltest._
import scala.util.Random
import org.scalatest.flatspec.AnyFlatSpec
class RealGCDTests extends AnyFlatSpec with ChiselScalatestTester {
  behavior.of("RealGCD")

  it should "find GCD" in {
    test(new RealGCD) { c =>
      val inputs = List((48, 32), (7, 3), (100, 10))
      val outputs = List(16, 1, 10)

      var i = 0
      var t = 0
      do {
        var transfer = false

        do {
          c.io.in.bits.a.poke(inputs(i)._1)
          c.io.in.bits.b.poke(inputs(i)._2)
          c.io.in.valid.poke(1)
          transfer = c.io.in.ready.peekInt() == BigInt(1)
          c.clock.step(1)
          t += 1
        } while (t < 100 && !transfer)

        do {
          c.io.in.valid.poke(0)
          c.clock.step(1)
          t += 1
        } while (t < 100 && (c.io.out.valid.peekInt() == BigInt(0)))

        c.io.out.bits.expect(outputs(i))
        i += 1
        t = 0
      } while (t < 100 && i < 3)
      if (t >= 100) fail("Timeout")
    }
  }
}
