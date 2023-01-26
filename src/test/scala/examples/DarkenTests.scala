// See LICENSE.txt for license details.
package examples

import chiseltest._
import scala.util.Random
import org.scalatest.flatspec.AnyFlatSpec
import chiseltest.simulator.SimulatorAnnotation

class DarkenTests extends AnyFlatSpec with ChiselScalatestTester {
  behavior.of("Darken")

  it should "Darken the Image" in {
    test(new Darken()) { c =>
      val inPic = Image(getClass.getResourceAsStream("/in.im24"))
      val outPic = Image(inPic.w, inPic.h, inPic.d)
      c.clock.step(1)
      // Avoid timeout since the length is 3000
      c.clock.setTimeout(0)
      for (i <- 0 until inPic.data.length) {
        val rin = inPic.data(i)
        val in = if (rin < 0) 256 + rin else rin
        c.io.in.poke(in)
        c.clock.step(1)
        c.io.out.expect((in<<1)%256)
        val out = c.io.out.peekInt()
        outPic.data(i) = out.toByte
      }
      outPic.write("out.im24")
    }
  }
}
