package org.ioreskovic.candies.parse

import java.util.concurrent.TimeUnit

import fastparse._
import fastparse.SingleLineWhitespace._
import org.ioreskovic.candies.{Cycle, Message}
import org.ioreskovic.candies.parse.common._

import scala.concurrent.duration.Duration

private[parse] trait CycleP {
  private def start[_: P]: P[Unit] = P("BA_")
  private def ident[_: P]: P[Unit] = P("\"GenMsgCycleTime\"")
  private def msgRef[_: P]: P[Message.Id] = P("BO_" ~ integral.!).map(_.toLong).map(Message.Id)
  private def interval[_: P]: P[Duration] =
    integral.!.map(_.toLong).map(i => Duration(i, TimeUnit.MILLISECONDS))
  private def end[_: P]: P[Unit] = P(";")

  def cycle[_: P]: P[Cycle] = P(newLine.rep ~ start ~ ident ~ msgRef ~ interval ~ end).map {
    case (msgId, duration) =>
      Cycle(msgId, duration)
  }
}
