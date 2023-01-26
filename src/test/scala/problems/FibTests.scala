package problems


import chiseltest._
import scala.util.Random
import org.scalatest.flatspec.AnyFlatSpec


class FibTests extends AnyFlatSpec with ChiselScalatestTester {
  behavior.of("Fib")

  it should "Perform Fibonacci 1" in {
    test(new Fib()) { fib =>
      fib.io.input.poke(1)
      fib.clock.step(1)
      fib.io.output.expect(1)
      fib.io.done.expect(true)
      fib.io.valid.expect(true)
    }
  }
  it should "Perform Fibonacci 5" in {
    test(new Fib()) { fib =>
      fib.io.input.poke(6)
      fib.clock.step(1)
      fib.io.output.expect(1)
      fib.io.done.expect(false)
      fib.io.valid.expect(true)
      fib.clock.step(1)
      fib.io.output.expect(1)
      fib.io.done.expect(false)
      fib.io.valid.expect(true)
      fib.clock.step(1)
      fib.io.output.expect(2)
      fib.io.done.expect(false)
      fib.io.valid.expect(true)
      fib.clock.step(1)
      fib.io.output.expect(3)
      fib.io.done.expect(false)
      fib.io.valid.expect(true)
      fib.clock.step(1)
      fib.io.output.expect(5)
      fib.io.done.expect(false)
      fib.io.valid.expect(true)
      fib.clock.step(1)
      fib.io.output.expect(8)
      fib.io.done.expect(true)
      fib.io.valid.expect(true)
    }
  }
  it should "Perform Fibonacci 0" in {
    test(new Fib()) { fib =>
      fib.io.input.poke(0)
      fib.clock.step(1)
      fib.io.output.expect(0)
      fib.io.done.expect(true)
      fib.io.valid.expect(false)
      fib.clock.step(1)
      fib.io.output.expect(0)
      fib.io.done.expect(true)
      fib.io.valid.expect(false)
    }
  }
  it should "Perform Fibonacci 71" in {
    test(new Fib()) { fib =>
      fib.io.input.poke(71)
      fib.clock.step(1)
      fib.io.output.expect(0)
      fib.io.done.expect(true)
      fib.io.valid.expect(false)
      fib.clock.step(20)
      fib.io.output.expect(0)
      fib.io.done.expect(true)
      fib.io.valid.expect(false)
    }
  }
  it should "Perform Fibonacci 70" in {
    test(new Fib()) { fib =>
      fib.io.input.poke(70)
      for (i <- 1 until 70) {
        fib.clock.step(1)
        fib.io.done.expect(false)
        fib.io.valid.expect(true)
      }
      fib.clock.step(1)
      fib.io.output.expect(190392490709135L)
      fib.io.done.expect(true)
      fib.io.valid.expect(true)
    }
  }
}
