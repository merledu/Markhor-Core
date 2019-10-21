package datapath

import chisel3.iotesters.PeekPokeTester

class ALUTest(c:ALU) extends PeekPokeTester(c){

    poke (c.io.ALUc, 16)
    poke (c.io.A, 0)
    poke (c.io.B, 0)

    step(1)
    expect (c.io.output, 1)
    expect (c.io.output,1)

}