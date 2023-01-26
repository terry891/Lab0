package problems
import chisel3._



// 1, 1, 2, 3, 5, 8, 11, ..., 190392490709135 (70th) in binary is 1011010110010110101010101101100110010110 (64 bits)

class Fib extends Module {
  val io = IO(new Bundle {
    val input = Input(UInt(7.W))
    val output = Output(UInt(64.W))
    val valid = Output(Bool())
    val done = Output(Bool())
  })

  val target = Wire(UInt(7.W))
  val count = RegInit(0.U(7.W))
  val prev = RegInit(0.U(64.W))
  val curr = RegInit(1.U(64.W))
 

  target := io.input
  io.valid := (target <= 70.U && target > 0.U)
  io.done := (count === target || !io.valid)

  when (count < target && io.valid) {
    val temp = curr
    curr := curr + prev
    prev := temp
    count := count + 1.U
  }

//*  If the sequence starts at 1 (1, 1, 2, 3,...)
  io.output := prev

//*  If the sequence starts at 0 (0, 1, 1, 2, 3,...)
//   val isOne = (target === 1.U)
//   io.output := Mux(isOne, 0.U, fib1)
}
