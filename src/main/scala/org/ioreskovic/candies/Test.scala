package org.ioreskovic.candies

import org.ioreskovic.candies.parse.message._
import org.ioreskovic.candies.parse.cycle._

import fastparse._

object Test {

  def main(args: Array[String]): Unit = {
    val messageText =
      s"""
         |BO_ 1845 TemperatureMsg: 8 Vector__XXX
         | SG_ MultiplexIndexSignal M : 39|4@0+ (1,0) [0|0] "" Vector__XXX
         | SG_ NormalSignalAlwaysPresent : 16|4@1- (1,0) [0|0] "" Vector__XXX
         | SG_ TemperatureIndoorsMultiplexed m0 : 7|16@0- (0.1,-40) [0|0] "degC" Vector__XXX
         | SG_ TemperatureOutdoorsMultiplexed m3 : 7|16@0- (0.1,-40) [0|0] "degC" Vector__XXX
         | SG_ TemperatureUndergroundMultiplexed m11 : 7|16@0- (0.1,-40) [0|0] "degC" Vector__XXX
         |
         |""".stripMargin

    val messageResult = parse(
      messageText,
      message(_)
    )
    println("Input")
    println("_" * 60)
    println(messageText)
    println("_" * 60)
    println("Result")
    println(messageResult)

    val intervalText =
      s"""
         | BA_ "GenMsgCycleTime" BO_ 1845 25;
         |
         |""".stripMargin

    val intervalResult = parse(
      intervalText,
      cycle(_)
    )
    println(intervalResult)
  }
}
