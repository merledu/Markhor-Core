package datapath
import chisel3.iotesters.PeekPokeTester

class DataMemoryTest(c:DataMemory) extends PeekPokeTester(c){
    poke(c.io.addr, 0)
    poke(c.io.dataIn,1)
    step(1)
    expect(c.io.dataOut,5)

}