package datapath

import chisel3._

class Control extends Module {
	val io=IO(new Bundle{
		val opcode=Input(UInt(7.W))
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
		val TypeDecode = Module(new InstructionTypeDecode())
		val ControlDecode = Module (new ControlDecode())

		TypeDecode.io.opcode := io.opcode
		ControlDecode.io.RFormat := TypeDecode.io.RFormat
		ControlDecode.io.IType := TypeDecode.io.IType
		ControlDecode.io.Load := TypeDecode.io.Load
		ControlDecode.io.Store := TypeDecode.io.Store
		ControlDecode.io.Jal := TypeDecode.io.Jal
		ControlDecode.io.Jalr := TypeDecode.io.Jalr
		ControlDecode.io.Branch := TypeDecode.io.Branch
		ControlDecode.io.Lui := TypeDecode.io.Lui
	
		io.MemWrite := ControlDecode.io.MemWrite
		io.MemRead := ControlDecode.io.MemRead
		io.MemToReg := ControlDecode.io.MemToReg
		io.RegWrite := ControlDecode.io.RegWrite
		io.branch := ControlDecode.io.branch
		io.ALUOperation := ControlDecode.io.ALUOperation
		io.OperandASel := ControlDecode.io.OperandASel
		io.OperandBSel := ControlDecode.io.OperandBSel
		io.ExtendSel := ControlDecode.io.ExtendSel
		io.NextPCSel := ControlDecode.io.NextPCSel

}

