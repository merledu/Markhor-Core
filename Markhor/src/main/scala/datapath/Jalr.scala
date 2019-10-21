package datapath

import chisel3._

class Jalr extends Module {
    val io = IO(new Bundle {
            val A = Input(SInt(32.W))
            val B = Input(SInt(32.W))
            val out = Output(SInt(32.W))
    })

    io.out := (io.A + io.B) & 4294967294l.S
    
}