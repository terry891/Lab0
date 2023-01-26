// See LICENSE.txt for license details.
package examples

import util.Random
import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class LifeTests extends AnyFlatSpec with ChiselScalatestTester {
  behavior.of("Life")

  it should "Simulate Life Game" in {
    test(new Life(10, 10)) { c =>
      setMode(c, run = false)
      // uncomment one of these
      //  initBlinker
      initGlider(c)
      //  randomize()
      printBoard(c)

      setMode(c, run = true)

      for (time <- 0 until 100) {
        println(s"Period: $time")
        printBoard(c)
        c.clock.step(1)
      }
    }
  }
  private def setMode(c: Life, run: Boolean): Unit = {
    c.io.running.poke(run)
    c.clock.step(1)
  }

  private def clear_board(c: Life): Unit = {
    c.io.writeValue.poke(0)

    for {
      i <- 0 until c.rows
      j <- 0 until c.cols
    } {
      c.io.writeRowAddress.poke(i)
      c.io.writeColAddress.poke(j)
      c.clock.step(1)
    }
  }

  private def initBlinker(c: Life): Unit = {
    clear_board(c)

    c.io.writeValue.poke(1)
    c.io.writeRowAddress.poke(3)
    for (addr <- Seq(3, 5)) {
      c.io.writeColAddress.poke(addr)
      c.clock.step(1)
    }
    c.io.writeRowAddress.poke(4)
    for (addr <- Seq(4, 5)) {
      c.io.writeColAddress.poke(addr)
      c.clock.step(1)
    }
    c.io.writeRowAddress.poke(5)
    for (addr <- Seq(4)) {
      c.io.writeColAddress.poke(addr)
      c.clock.step(1)
    }

  }

  private def initGlider(c: Life): Unit = {
    clear_board(c)

    c.io.writeValue.poke(1)
    c.io.writeRowAddress.poke(3)
    for (addr <- Seq(3, 5)) {
      c.io.writeColAddress.poke(addr)
      c.clock.step(1)
    }
    c.io.writeRowAddress.poke(4)
    for (addr <- Seq(4, 5)) {
      c.io.writeColAddress.poke(addr)
      c.clock.step(1)
    }
    c.io.writeRowAddress.poke(5)
    for (addr <- Seq(4)) {
      c.io.writeColAddress.poke(addr)
      c.clock.step(1)
    }
  }

  private def randomize(c: Life): Unit = {
    clear_board(c)

    for (addr <- 0 until c.rows * c.rows) {
      c.io.writeValue.poke(Random.nextBoolean())
      c.io.writeRowAddress.poke(addr)
      c.clock.step(1)
    }
  }

  private def printBoard(c: Life): Unit = {
    // Print column number
    print("   ")
    for (i <- 0 until c.cols)
      print(" " + i.toString.last)
    println()

    for (i <- 0 until c.rows) {
      // Print line number
      print(f"$i%2d")
      print(" ")

      // Print cell state
      for {
        j <- 0 until c.cols
      } {
        val s = c.io.state(i)(j).peekInt()
        if (s == 1)
          print(" *")
        else
          print("  ")
      }

      println()
    }
    println()
  }
}
