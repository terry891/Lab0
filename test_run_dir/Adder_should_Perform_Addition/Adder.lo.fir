FIRRTL version 1.1.0
circuit Adder :
  module Adder :
    input clock : Clock
    input reset : UInt<1>
    input io_in0 : UInt<16>
    input io_in1 : UInt<16>
    output io_out : UInt<16>

    node _io_out_T = add(io_in0, io_in1) @[Adder.scala 20:22]
    node _io_out_T_1 = tail(_io_out_T, 1) @[Adder.scala 20:22]
    io_out <= _io_out_T_1 @[Adder.scala 20:12]
