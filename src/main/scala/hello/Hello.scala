// See LICENSE.txt for license details.
package hello

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec
// import chisel3.iotesters.{PeekPokeTester, Driver}

class Hello extends Module {
  val io = IO(new Bundle {
    val out = Output(UInt(8.W))
  })
  io.out := 42.U
}

class HelloTest extends AnyFlatSpec with ChiselScalatestTester {
  behavior of "Hello"

  it should "test hello" in {
    test(new Hello()) { c =>
      c.io.out.expect(42.U)
    }
  }
}
object Hello {
  def main(args: Array[String]): Unit = {
    (new HelloTest).execute(stats = true)
  }
}