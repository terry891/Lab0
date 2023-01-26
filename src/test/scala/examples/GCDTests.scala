// See LICENSE.txt for license details.
package examples

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec
import chiseltest.simulator.SimulatorAnnotation
// import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

class GCDTests extends AnyFlatSpec with ChiselScalatestTester {

  behavior of "GCD" 

  it should "compute GCD within 100 cycles" in{
    test(new GCD()){ c=>
      val inputs = List( (48, 32), (7, 3), (100, 10) )
      val outputs = List( 16, 1, 10)

      var i = 0

      do {
        var t = 0
        c.io.a.poke(inputs(i)._1)
        c.io.b.poke(inputs(i)._2)

        c.io.load.poke(1)
        c.clock.step(1)
        c.io.load.poke(0)

        var ready = false

        do {
          ready = c.io.valid.peekBoolean()
          c.clock.step(1)
          t += 1
        } while (t < 100 && ! ready)
        c.io.out.expect(outputs(i))
        assert(t<100, "Circuit has timeout")
        i += 1

      } while (i < 3)

    }
  }
}