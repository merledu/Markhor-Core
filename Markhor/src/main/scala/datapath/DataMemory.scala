package datapath

import chisel3._
import chisel3.util.experimental.loadMemoryFromFile

class DataMemory extends Module {
    val io = IO(new Bundle {
            val addr = Input(UInt(8.W))
            val dataOut = Output(SInt(32.W))
            val dataIn = Input(SInt(32.W))
            val str = Input(UInt(1.W))
            val ld = Input(UInt(1.W))
    })
    val mem = Mem(1024,SInt(32.W))
    when (io.str === 1.U){
        mem.write(io.addr, io.dataIn) 
        io.dataOut := DontCare 
    }.elsewhen(io.ld === 1.U){
        io.dataOut := mem.read(io.addr)
        io.dataIn := DontCare
    }.otherwise{
        io.dataOut := DontCare
        io.dataIn := DontCare
    }
            
    
    
}