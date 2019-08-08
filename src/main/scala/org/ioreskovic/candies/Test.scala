package org.ioreskovic.candies

import org.ioreskovic.candies.parse.message._
import fastparse._

object Test {

  def main(args: Array[String]): Unit = {
    val messageText =
      s"""
         |  BO_ 133 MSG_1: 7 PRO_0
         |    SG_ SIG_1 : 0|8@0+ (-1.4,-2) [-15,0] "km/h" CON_1,CON_2
         |    SG_ SIG_2 : 8|16@0+ (-1.4,-2) [-15,0] "km/h"
         |    SG_ SIG_3 : 24|30@0+ (-1.4,-2) [-15,0] "km/h" CON_3
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
  }
}
