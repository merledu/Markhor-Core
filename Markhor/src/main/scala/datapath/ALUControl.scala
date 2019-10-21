package datapath

import chisel3._
import chisel3.util.Cat

class ALUControl extends Module {
	val io=IO(new Bundle{
		val ALUOp = Input(UInt(3.W))
		val Func3 = Input(UInt(3.W))
		val Func7 = Input(UInt(1.W))
		val ALUsel = Output(UInt(5.W))
	})
		io.ALUsel := "b00000".U

		when (io.ALUOp === "b000".U){
			io.ALUsel := Cat(0.U, io.Func7, io.Func3)
		}.elsewhen (io.ALUOp === "b100".U){
			io.ALUsel := "b00000".U
		}.elsewhen (io.ALUOp === "b101".U){
			io.ALUsel := "b00000".U
		}.elsewhen (io.ALUOp === "b010".U){
			io.ALUsel := Cat("b10".U, io.Func3)
		}.elsewhen (io.ALUOp === "b001".U){
			when (io.Func3 === "b101".U){
				io.ALUsel := Cat(0.U, io.Func7, io.Func3)
			}.otherwise{
				io.ALUsel := Cat("b00".U, io.Func3)
			}
			
		}.elsewhen (io.ALUOp === "b011".U){
			io.ALUsel := "b11111".U
		}.elsewhen (io.ALUOp === "b110".U){
			io.ALUsel := "b00000".U
	}
}
