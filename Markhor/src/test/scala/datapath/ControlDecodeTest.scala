package datapath

import chisel3.iotesters.PeekPokeTester

class ControlDecodeTest(c:ControlDecode) extends PeekPokeTester(c){

	poke(c.io.RFormat, 1)
	poke(c.io.IType, 0)
	poke(c.io.Load, 0)
	poke(c.io.Store, 0)
	poke(c.io.Jal, 0)
	poke(c.io.Jalr, 0)
	poke(c.io.Branch, 0)
	poke(c.io.Lui, 0)

	step(1)

	expect(c.io.MemWrite , 0)
	expect(c.io.MemRead , 0)
	expect(c.io.RegWrite , 1)
	expect(c.io.MemToReg , 0)
	expect(c.io.branch , 0)
	expect(c.io.ALUOperation , 0)
	expect(c.io.OperandASel , 0)
	expect(c.io.OperandBSel , 0)
	expect(c.io.ExtendSel , 0)
	expect(c.io.NextPCSel , 0)
}
