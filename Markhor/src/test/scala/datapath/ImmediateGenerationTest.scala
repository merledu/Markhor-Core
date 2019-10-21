package datapath

import chisel3.iotesters.PeekPokeTester

class ImmediateGenerationTest(c:ImmediateGeneration) extends PeekPokeTester(c){

    poke(c.io.Instruction, 57344)
    poke(c.io.PC, 57344)
    step(1)
    expect(c.io.I, 0)
    expect(c.io.S, 0)
    expect(c.io.U, 0)
    expect(c.io.UJ, 0)
    expect(c.io.SB, 0)
}