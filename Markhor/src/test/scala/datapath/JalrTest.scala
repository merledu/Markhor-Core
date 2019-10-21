package datapath

import chisel3.iotesters.PeekPokeTester

class JalrTest(c: Jalr) extends PeekPokeTester(c){
    poke (c.io.A, 4)
    poke (c.io.B, 7)
    step(1)
    expect (c.io.out, 10)
}