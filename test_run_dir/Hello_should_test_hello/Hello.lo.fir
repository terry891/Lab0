FIRRTL version 1.1.0
circuit Hello :
  module Hello :
    input clock : Clock
    input reset : UInt<1>
    output io_out : UInt<8>

    io_out <= UInt<8>("h2a") @[Hello.scala 13:10]
