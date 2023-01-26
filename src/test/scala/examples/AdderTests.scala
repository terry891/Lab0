// See LICENSE.txt for license details.
package examples
import scala.util.Random
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec
import chiseltest.simulator.SimulatorAnnotation

//Following  https://github.com/ucb-bar/chiseltest/blob/main/src/test/scala/chiseltest/tests/AluTest.scala
// We want to reuse the same logic for different bitwidth
trait AdderBehavior {
  this: AnyFlatSpec with ChiselScalatestTester =>
  def testAddition(bitwidth: Int): Unit = {
    it should s"Compute Sum of two $bitwidth-bit numbers" in {
      test(new Adder(8)) { c =>
        val rnd = new Random()
        for (t <- 0 until (1 << (c.n + 1))) {
          val rnd0 = rnd.nextInt(1 << c.n)
          val rnd1 = rnd.nextInt(1 << c.n)
          val rnd2 = rnd.nextInt(2)

          c.io.A.poke(rnd0)
          c.io.B.poke(rnd1)
          c.io.Cin.poke(rnd2)
          c.clock.step(1)
          val rsum = rnd0 + rnd1 + rnd2
          val mask = BigInt("1" * c.n, 2)

          c.io.Sum.expect(rsum & mask)
          c.io.Cout.expect(((1 << c.n) & rsum) >> c.n)
        }
      }
    }
  }
}
class AdderTests extends AnyFlatSpec with AdderBehavior with ChiselScalatestTester {
  behavior of "Adder"
  val reqWidth: List[Int] = List[Int](4, 8, 16, 32, 64)
  reqWidth.foreach(width => (it should behave).like(testAddition(width)))
}
