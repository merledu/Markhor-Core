package datapath

import chisel3._

class Top extends Module {
	val io=IO(new Bundle{
        val instruction = Output(UInt(32.W))
        val output = Output(SInt(32.W))
    })
    
    val control = Module(new Control())
    val alu = Module(new ALU())
    val immGen = Module(new ImmediateGeneration())
    val register = Module(new RegisterFile())
    val alucont = Module(new ALUControl())
    val pc = Module(new PC())
    val ins= Module(new InsMem())
    val jalr = Module(new Jalr())
    val dataMem = Module(new DataMemory())

    alu.io.A:= 0.S
    alu.io.B:= 0.S
    pc.io.inp := 0.U
    
    jalr.io.A := immGen.io.I
    jalr.io.B := register.io.rs1


    ins.io.wrAddr := pc.io.pc1(11,2)
    io.instruction := ins.io.rdData
    
    control.io.opcode :=io.instruction(6,0)

    immGen.io.Instruction :=io.instruction
    immGen.io.PC := pc.io.pc1

    register.io.rs1_sel :=io.instruction(19,15)
    register.io.rs2_sel :=io.instruction(24,20)
    register.io.rd_sel :=io.instruction(11,7)

    alucont.io.Func3 :=io.instruction(14,12)
    alucont.io.Func7 :=io.instruction(30)
    alucont.io.ALUOp := control.io.ALUOperation
   
    when ((control.io.NextPCSel === 1.U) &&  ((alu.io.branch & control.io.branch) === 1.U) ) {
        pc.io.inp := immGen.io.SB.asUInt
    }.otherwise {
        pc.io.inp := pc.io.pc4
    }

    alu.io.ALUc := alucont.io.ALUsel
    when (control.io.OperandASel === 0.U || control.io.OperandASel === 3.U ){
        alu.io.A:= register.io.rs1
    }.elsewhen (control.io.OperandASel === 1.U){
        alu.io.A:= pc.io.pc1.asSInt
    }.elsewhen (control.io.OperandASel === 2.U){
        alu.io.A:= pc.io.pc4.asSInt
    }

    when (control.io.OperandBSel === 0.U) {
        alu.io.B:= register.io.rs2
    }.otherwise {
        when (control.io.ExtendSel === 0.U){
            alu.io.B:= immGen.io.I
        }.elsewhen (control.io.ExtendSel ===2.U){
            alu.io.B := immGen.io.S
        }.elsewhen (control.io.ExtendSel === 1.U){
            alu.io.B := immGen.io.U
        }
    }
    when (control.io.NextPCSel === 0.U) {
        pc.io.inp := pc.io.pc4
    }.elsewhen (control.io.NextPCSel === 2.U) {
       pc.io.inp := immGen.io.UJ.asUInt
    }.elsewhen (control.io.NextPCSel === 3.U){
        pc.io.inp := jalr.io.out.asUInt
    }
        
    register.io.regWrite := control.io.RegWrite
    dataMem.io.addr := alu.io.output(9,2).asUInt
    dataMem.io.dataIn := register.io.rs2
    dataMem.io.str:= control.io.MemWrite
    dataMem.io.ld:= control.io.MemRead
    
    
    when (control.io.MemToReg === 1.U){
        register.io.writeData := dataMem.io.dataOut.asSInt
    }.otherwise{
        register.io.writeData := alu.io.output
    }
    

    io.output := alu.io.output
    
}

    
