package datapath
import chisel3.iotesters.PeekPokeTester

class InsMemTest(c:InsMem) extends PeekPokeTester(c){
    poke(c.io.wrAddr, 0)
    step(1)
}