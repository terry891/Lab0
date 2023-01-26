FIRRTL version 1.1.0
circuit Fib :
  module Fib :
    input clock : Clock
    input reset : UInt<1>
    input io_input : UInt<7>
    output io_output : UInt<64>
    output io_valid : UInt<1>
    output io_done : UInt<1>

    reg count : UInt<7>, clock with :
      reset => (UInt<1>("h0"), count) @[Fib.scala 17:22]
    reg prev : UInt<64>, clock with :
      reset => (UInt<1>("h0"), prev) @[Fib.scala 18:21]
    reg curr : UInt<64>, clock with :
      reset => (UInt<1>("h0"), curr) @[Fib.scala 19:21]
    node target = io_input @[Fib.scala 16:20 22:10]
    node _io_valid_T = leq(target, UInt<7>("h46")) @[Fib.scala 23:23]
    node _io_valid_T_1 = gt(target, UInt<1>("h0")) @[Fib.scala 23:41]
    node _io_valid_T_2 = and(_io_valid_T, _io_valid_T_1) @[Fib.scala 23:31]
    node _io_done_T = eq(count, target) @[Fib.scala 24:21]
    node _T = lt(count, target) @[Fib.scala 26:15]
    node _curr_T = add(curr, prev) @[Fib.scala 28:18]
    node _curr_T_1 = tail(_curr_T, 1) @[Fib.scala 28:18]
    node _count_T = add(count, UInt<1>("h1")) @[Fib.scala 30:20]
    node _count_T_1 = tail(_count_T, 1) @[Fib.scala 30:20]
    node _GEN_0 = mux(_T, _curr_T_1, curr) @[Fib.scala 26:25 28:10 19:21]
    node _GEN_1 = mux(_T, curr, prev) @[Fib.scala 26:25 29:10 18:21]
    node _GEN_2 = mux(_T, _count_T_1, count) @[Fib.scala 26:25 30:11 17:22]
    io_output <= curr @[Fib.scala 34:13]
    io_valid <= _io_valid_T_2 @[Fib.scala 23:12]
    io_done <= _io_done_T @[Fib.scala 24:11]
    count <= mux(reset, UInt<7>("h0"), _GEN_2) @[Fib.scala 17:{22,22}]
    prev <= mux(reset, UInt<64>("h0"), _GEN_1) @[Fib.scala 18:{21,21}]
    curr <= mux(reset, UInt<64>("h1"), _GEN_0) @[Fib.scala 19:{21,21}]
