// See LICENSE.txt for license details.
package problems

import chiseltest._
import scala.util.Random
import org.scalatest.flatspec.AnyFlatSpec
class VendingMachineTests extends AnyFlatSpec with ChiselScalatestTester {
  behavior.of("VendingMachine")

  it should "vending" in {
    test(new VendingMachine) { c =>
      var money = 0
      var isValid = false
      val rnd = new Random
      for (t <- 0 until 20) {
        val coin = rnd.nextInt(3) * 5
        val isNickel = coin == 5
        val isDime = coin == 10

        // Advance circuit
        c.io.nickel.poke(if (isNickel) 1 else 0)
        c.io.dime.poke(if (isDime) 1 else 0)
        c.clock.step(1)

        // Advance model
        money = if (isValid) 0 else money + coin
        isValid = money >= 20

        // Compare
        c.io.valid.expect(if (isValid) 1 else 0)
      }
    }
  }
}
