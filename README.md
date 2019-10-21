# RISC-V Single Cycle Core Markhor
## Designed by Waleed Muhammad Sohail
I am a student of 3rd sem BSE at UIT and I created this core being a part of MERL team. To try out this core or get it working for yourself, you should follow the instructions below.
First of all get started by cloning this repository on your machine.  
```ruby
git clone https://github.com/merledu/Markhor-Core.git
```
Create a .txt file and place the ***hexadecimal*** code of your instructions simulated on ***Venus*** (RISC-V Simulator)\
Each instruction's hexadecimal code must be on seperate line as following. This program consists of 8 instructions.
```
00500293
00600313
008005EF
00000A63
006284B3
40628933
405309B3
00058067
```
Then perform the following step
```ruby
cd Core/Markhor/src/main/scala/datapath
```
Open **InstructionMem.scala** with this command. You can also manually go into the above path and open the file in your favorite text editor.
```ruby
open InstructionMem.scala
```
Find the following line
``` python
loadMemoryFromFile(mem, "/home/waleeds1/Desktop/Core/Markhor/src/test/scala/datapath/instr.txt")
```
Change the .txt file path to match your file that you created above storing your own program instructions.\
After setting up the InstructionMem.scala file, go inside the Markhor folder.
```ruby
cd Core/Markhor
```
And enter
```ruby
sbt
```
When the terminal changes to this type
```ruby
sbt:Markhor>
```
Enter this command
```ruby
sbt:Markhor> test:runMain datapath.Launcher Top
```
After you get success
```ruby
sbt:Markhor> test:runMain datapath.Launcher Top --backend-name verilator
```
After success you will get a folder ***test_run_dir*** on root of your folder. Go into the examples folder inside.\
There you will find the folder named Top. Enter in it and you can find the Top.vcd file which you visualise on **gtkwave** to\
see your program running.
