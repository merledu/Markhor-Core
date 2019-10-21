// See LICENSE.txt for license details.
package datapath

import chisel3.iotesters.{Driver, TesterOptionsManager}
import utils.TutorialRunner

object Launcher {
  val examples = Map(
	"InstructionTypeDecode" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new InstructionTypeDecode(), manager) {
          (c) => new InstructionTypeDecodeTest(c)
        }
      },
	"ControlDecode" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new ControlDecode(), manager) {
          (c) => new ControlDecodeTest(c)
        }
      },
	"Control" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new Control(), manager) {
          (c) => new ControlTest(c)
        }
      },
	"ALUControl" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new ALUControl(), manager) {
          (c) => new ALUControlTest(c)
        }
      },
      "ImmediateGeneration" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new ImmediateGeneration(), manager) {
          (c) => new ImmediateGenerationTest(c)
        }
      },
      "ALU" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new ALU(), manager) {
          (c) => new ALUTest(c)
        }
      },
      "RegisterFile" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new RegisterFile(), manager) {
          (c) => new RegisterFileTest(c)
        }
      },
      "Top" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new Top(), manager) {
          (c) => new TopTest(c)
        }
      },
      "PC" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new PC(), manager) {
          (c) => new PCTest(c)
        }
      },
      "InsMem" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new InsMem(), manager) {
          (c) => new InsMemTest(c)
        }
      },
      "Jalr" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new Jalr(), manager) {
          (c) => new JalrTest(c)
        }
      },
      "And" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new And(), manager) {
          (c) => new AndTest(c)
        }
      },
      "DataMemory" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new DataMemory(), manager) {
          (c) => new DataMemoryTest(c)
        }
      }
  )
  def main(args: Array[String]): Unit = {
    TutorialRunner("examples", examples, args)
  }
}

