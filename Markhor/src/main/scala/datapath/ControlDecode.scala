package datapath

import chisel3._

class ControlDecode extends Module {
	val io=IO(new Bundle{
		val RFormat=Input(UInt(1.W))
		val IType=Input(UInt(1.W))
		val Load=Input(UInt(1.W))
		val Store=Input(UInt(1.W))
		val Jalr=Input(UInt(1.W))
		val Jal=Input(UInt(1.W))
		val Branch=Input(UInt(1.W))
		val Lui=Input(UInt(1.W))
		
		val MemWrite = Output(UInt(1.W))
		val MemRead = Output(UInt(1.W))
		val RegWrite = Output(UInt(1.W))
		val MemToReg = Output(UInt(1.W))
		val branch = Output(UInt(1.W))
		val ALUOperation = Output(UInt(3.W))
		val OperandASel = Output(UInt(2.W))
		val OperandBSel = Output(UInt(1.W))
		val ExtendSel = Output(UInt(2.W))
		val NextPCSel = Output(UInt(2.W))

	})

		io.MemWrite := 0.U
		io.MemRead := 0.U
		io.RegWrite := 0.U
		io.MemToReg := 0.U
		io.branch := 0.U
		io.ALUOperation := "b000".U
		io.OperandASel := "b00".U
		io.OperandBSel := 0.U
		io.ExtendSel := "b00".U
		io.NextPCSel := "b00".U

		when (io.RFormat === 1.U){
			io.RegWrite := 1.U
		}.elsewhen (io.IType === 1.U){
			io.RegWrite := 1.U
			io.ALUOperation := "b001".U
			io.OperandBSel := 1.U
		}.elsewhen (io.Load === 1.U){
			io.MemRead := 1.U
			io.RegWrite := 1.U
			io.MemToReg := 1.U
			io.OperandBSel := 1.U
			io.ALUOperation := "b100".U
		}.elsewhen (io.Store === 1.U){
			io.MemWrite := 1.U
			io.OperandBSel := 1.U
			io.ALUOperation := "b101".U
			io.ExtendSel := "b10".U
		}.elsewhen (io.Jalr === 1.U){
			io.ALUOperation := "b011".U
			io.OperandASel := "b10".U
			io.RegWrite := 1.U
			io.NextPCSel := "b11".U
		}.elsewhen (io.Jal === 1.U){
			io.ALUOperation := "b011".U
			io.OperandASel := "b10".U
			io.RegWrite := 1.U
			io.NextPCSel := "b10".U
		}.elsewhen (io.Branch === 1.U){
			io.branch := 1.U
			io.ALUOperation := "b010".U
			io.NextPCSel := "b01".U
		}.elsewhen (io.Lui === 1.U){
			io.ALUOperation := "b110".U
			io.OperandASel := "b11".U
			io.RegWrite := 1.U
			io.ExtendSel := 1.U
			io.OperandBSel := 1.U
		}
}






