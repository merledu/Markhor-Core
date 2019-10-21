package datapath

import chisel3.iotesters.PeekPokeTester

class InstructionTypeDecodeTest(c:InstructionTypeDecode) extends PeekPokeTester(c){
	poke(c.io.opcode, 3)
	step(1)
	expect(c.io.RFormat, 0)
	expect(c.io.IType, 0)
	expect(c.io.Load, 0)
	expect(c.io.Store, 0)
	expect(c.io.Jal, 0)
	expect(c.io.Jalr, 0)
	expect(c.io.Branch, 0)
	expect(c.io.Lui, 0)
}
