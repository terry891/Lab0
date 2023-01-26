
package problems

import chiseltest._
import scala.util.Random
import org.scalatest.flatspec.AnyFlatSpec


class FibTest extends AnyFlatSpec with ChiselScalatestTester {
  behavior.of("Fib")

  it should "Perform Fibonacci" in {
    test(new Fib()) { fib =>
      fib.io.input.poke(1)
      fib.clock.step(1)
      fib.io.output.expect(1)
      fib.io.done.expect(true)
      fib.io.valid.expect(true)
    }
  }
}
