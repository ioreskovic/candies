package org.ioreskovic.candies

import org.ioreskovic.candies.parse.network._
import fastparse._

object Test {

  def main(args: Array[String]): Unit = {
    val input =
      s"""
         |BA_ "GenMsgCycleTime" BO_ 1 33;
         |
         |BO_ 0 MSG1: 8 Vector__XXX
         | SG_ Mux1 M : 39|4@0+ (1,0) [0|0] "" Vector__XXX
         | SG_ Reg1 : 16|4@1- (1,0) [0|0] "" Vector__XXX
         | SG_ Mex0 m0 : 7|16@0- (0.1,-40) [0|0] "degC" Vector__XXX
         | SG_ Mex3 m3 : 7|16@0- (0.1,-40) [0|0] "degC" Vector__XXX
         | SG_ Mex11 m11 : 7|16@0- (0.1,-40) [0|0] "degC" Vector__XXX
         |
         |BA_ "GenMsgCycleTime" BO_ 0 25;
         |
         |BO_ 1 MSG2: 8 Vector__XXX
         | SG_ Mux1 M : 39|4@0+ (1,0) [0|0] "" Vector__XXX
         | SG_ Reg1 : 16|4@1- (1,0) [0|0] "" Vector__XXX
         | SG_ Mex0 m0 : 7|16@0- (0.1,-40) [0|0] "degC" Vector__XXX
         | SG_ Mex3 m3 : 7|16@0- (0.1,-40) [0|0] "degC" Vector__XXX
         | SG_ Mex11 m11 : 7|16@0- (0.1,-40) [0|0] "degC" Vector__XXX
         |
         |""".stripMargin

    val result = parse(
      input,
      network(_)
    )

    println(result)
  }
}
