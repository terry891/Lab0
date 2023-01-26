// See LICENSE.txt for license details.
package examples

import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class VecSearchTests extends AnyFlatSpec with ChiselScalatestTester {
  behavior.of("VecSearch")

  it should "Perform Vector Search" in {
    test(new VecSearch) { c =>
      val list = VecSearchTest.pattern
      for (elt <- list) {
        c.io.out.expect(elt)
        c.clock.step(1)
      }
    }
  }
}
