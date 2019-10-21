package datapath

import chisel3._

class And extends Module {
	val io=IO(new Bundle{
		val a=Input(UInt(1.W))
		val b=Input(UInt(1.W))
		val x=Output(UInt(1.W))
 	})
	io.x := io.a & io.b
}
