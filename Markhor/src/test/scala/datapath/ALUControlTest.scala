package datapath

import chisel3.iotesters.PeekPokeTester

class ALUControlTest(c:ALUControl) extends PeekPokeTester(c){

	poke(c.io.ALUOp, 4)
	poke(c.io.Func3, 1)
	poke(c.io.Func7, 1)
	step(1)
	expect(c.io.ALUsel, 0)

}
