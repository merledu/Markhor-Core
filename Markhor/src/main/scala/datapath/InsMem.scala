package datapath

import chisel3._
import chisel3.util.experimental.loadMemoryFromFile

class InsMem extends Module {
    val io = IO(new Bundle {
            val wrAddr = Input(UInt(10.W))
            val rdData = Output(UInt(32.W))
    })
    val mem = Mem(1024,UInt(32.W))
    io.rdData := mem(io.wrAddr)
    loadMemoryFromFile(mem,"/home/waleeds1/Desktop/Core/Markhor/src/test/scala/datapath/instr.txt")
}