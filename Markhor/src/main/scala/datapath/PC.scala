package datapath

import chisel3._

class PC extends Module {
    val io = IO(new Bundle {
            val inp = Input(UInt(32.W))
            val pc1 = Output(UInt(32.W))
            val pc4 = Output(UInt(32.W))
    })
    val reg = RegInit(0.U(32.W))
    reg := io.inp
    io.pc1 := reg
    io.pc4 := reg + 4.U
}