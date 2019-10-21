package datapath

import chisel3.iotesters.PeekPokeTester

class PCTest(c:PC) extends PeekPokeTester(c){

	poke(c.io.inp, 4)
	step(1)
    expect(c.io.pc1, 4)
    expect(c.io.pc4, 8)
}