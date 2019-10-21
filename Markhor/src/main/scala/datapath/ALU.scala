package datapath

import chisel3._

class ALU extends Module {
	val io=IO(new Bundle{
		val ALUc = Input(UInt(5.W))
        val A = Input(SInt(32.W))
        val B = Input(SInt(32.W))
        val output = Output(SInt(32.W))
        val branch = Output(UInt(1.W))
    })
    var btaken = 0.U
    when (io.ALUc === "b00000".U){
        io.output := io.A + io.B
    }.elsewhen (io.ALUc === "b00001".U) {
        val ls=io.B.asUInt
        io.output := io.A << ls(4,0)
    }.elsewhen (io.ALUc === "b00010".U) {
        when (io.A < io.B) {
            io.output := 1.S
        }.otherwise {
            io.output := 0.S
        }
    }.elsewhen (io.ALUc === "b00011".U) {
        when (io.A.asUInt < io.B.asUInt) {
            io.output := 1.S
        }.otherwise {
            io.output := 0.S
        }
    }.elsewhen (io.ALUc === "b00100".U) {
        io.output := io.A ^ io.B 
    }.elsewhen (io.ALUc === "b00101".U) {
        val UA = io.A.asUInt
        val ls1=io.B.asUInt
        val logshift = UA >> ls1(4,0)
        val logshiftS = logshift.asSInt
        io.output :=  logshiftS
    }.elsewhen (io.ALUc === "b00110".U) {
        io.output := io.A | io.B 
    }.elsewhen (io.ALUc === "b00111".U) {
        io.output := io.A & io.B 
    }.elsewhen (io.ALUc === "b01000".U) {
        io.output := io.A - io.B 
    }.elsewhen (io.ALUc === "b01101".U) {
        val ls2=io.B(4,0)
        io.output := io.A >> ls2
    }.elsewhen (io.ALUc === "b10000".U) {
        when (io.A === io.B) {
            io.output := 1.S
            btaken = 1.U
        }.otherwise {
            btaken = 0.U
            io.output := 0.S
        }
    }.elsewhen (io.ALUc === "b10001".U) {
        when (io.A =/= io.B) {
            io.output := 1.S
            btaken = 1.U
        }.otherwise {
            btaken = 0.U
            io.output := 0.S
        }
    }.elsewhen (io.ALUc === "b10100".U) {
        when (io.A < io.B) {
            io.output := 1.S
            btaken = 1.U
        }.otherwise {
            btaken = 0.U
            io.output := 0.S
        }
    }.elsewhen (io.ALUc === "b10101".U) {
        when (io. A >= io.B) {
            io.output := 1.S
            btaken = 1.U
        }.otherwise {
            btaken = 0.U
            io.output := 0.S
        }
    }.elsewhen (io.ALUc === "b10110".U) {
        when (io.A.asUInt < io.B.asUInt) {
            io.output := 1.S
            btaken = 1.U
        }.otherwise {
            btaken = 0.U
            io.output := 0.S
        }
    }.elsewhen (io.ALUc === "b10111".U) {
        when (io. A.asUInt > io.B.asUInt ||io. A.asUInt === io.B.asUInt ) {
            io.output := 1.S
            btaken = 1.U
        }.otherwise {
            btaken = 0.U
            io.output := 0.S
        }
    }.elsewhen (io.ALUc === "b11111".U) {
        io.output := io.A
    }.otherwise {
        io.output := 0.S
    }
    when (io.ALUc(4,3) === "b10".U && io.output === 1.S){
        io.branch := 1.U
    }.otherwise {
        io.branch := 0.U
    }
}
