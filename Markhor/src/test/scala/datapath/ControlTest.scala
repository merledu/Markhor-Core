package datapath

import chisel3.iotesters.PeekPokeTester

class ControlTest(c:Control) extends PeekPokeTester(c){

	poke(c.io.opcode, 51)

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
