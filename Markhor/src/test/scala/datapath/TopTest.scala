package datapath

import chisel3.iotesters.PeekPokeTester

class TopTest(c:Top) extends PeekPokeTester(c){
    step(25)
}