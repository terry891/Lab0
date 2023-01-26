// See LICENSE.txt for license details.
package examples

import scala.collection.mutable.{ArrayStack => ScalaStack}

import chisel3._
import chiseltest._
import scala.util.Random
import org.scalatest.flatspec.AnyFlatSpec

class StackTests extends AnyFlatSpec with ChiselScalatestTester {
  behavior.of("Stack")

  it should "FILO" in {
    test(new Stack(8)) { c =>
      var nxtDataOut = 0
      var dataOut = 0
      val stack = new ScalaStack[Int]()

      c.io.push.poke(0)
      c.io.pop.poke(1)
      c.io.dataIn.poke(232)
      c.io.en.poke(1)

      c.clock.step(1)

      println(s"dataOut ${c.io.dataOut.peekInt()}")

      c.io.push.poke(1)
      c.io.pop.poke(1)
      c.io.dataIn.poke(90)
      c.io.en.poke(1)

      c.clock.step(1)
      c.clock.step(1)

      println(s"dataOut ${c.io.dataOut.peekInt()}")

      c.io.push.poke(1)
      c.io.pop.poke(1)
      c.io.dataIn.poke(33)
      c.io.en.poke(1)

      c.clock.step(1)
      c.clock.step(1)

      println(s"dataOut ${c.io.dataOut.peekInt()}")

      c.io.push.poke(0)
      c.io.pop.poke(1)
      c.io.dataIn.poke(22)
      c.io.en.poke(1)

      println(s"dataOut ${c.io.dataOut.peekInt()}")
    // expect(c.io.dataOut, dataOut)
    }
  }
}
