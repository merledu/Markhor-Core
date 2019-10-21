package datapath

import chisel3._
import chisel3.util.Cat
import chisel3.util.Fill

class ImmediateGeneration extends Module {
    val io = IO(new Bundle{
        val Instruction = Input(UInt(32.W))
        val I = Output(SInt(32.W))
        val PC = Input(UInt(32.W))
        val S = Output(SInt(32.W))
        val U = Output(SInt(32.W))
        val UJ = Output(SInt(32.W))
        val SB = Output(SInt(32.W))
    })

    val Ibits = io.Instruction(31,20)
    io.I := Cat(Fill(20,Ibits(11)),Ibits).asSInt

    val Sbits0 = io.Instruction(11,7)
    val Sbits1 = io.Instruction(31,25)
    io.S := Cat(Fill(20,Sbits1(6)),Sbits1,Sbits0).asSInt

    val Ubits = io.Instruction(31,12)
    io.U := Cat(Fill(12,Ubits(19)),Ubits).asSInt << 12.U

    val UJbits0 = io.Instruction(30,21)
    val UJbits1 = io.Instruction(20)
    val UJbits2 = io.Instruction(19,12)
    val UJbits3 = io.Instruction(31)
    val UJbitsU = Cat(Fill(11,UJbits3),UJbits3,UJbits2,UJbits1,UJbits0,0.U)
    val UJbitsS = UJbitsU.asSInt
    io.UJ := UJbitsS + io.PC.asSInt

    val SBbits0 = io.Instruction(11,8)
    val SBbits1 = io.Instruction(30,25)
    val SBbits2 = io.Instruction(7)
    val SBbits3 = io.Instruction(31)
    val SBbitsU = Cat(Fill(19,SBbits3),SBbits3,SBbits2,SBbits1,SBbits0,0.U)
    val SBbitsS = SBbitsU.asSInt
    io.SB := SBbitsS + io.PC.asSInt

}