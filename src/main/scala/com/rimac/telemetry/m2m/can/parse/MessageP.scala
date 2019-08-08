package com.rimac.telemetry.m2m.can.parse

import com.rimac.telemetry.m2m.can._
import com.rimac.telemetry.m2m.can.parse.common._
import com.rimac.telemetry.m2m.can.parse.signal._
import fastparse.SingleLineWhitespace._
import fastparse._

private[parse] trait MessageP {
  private def start[_: P]: P[Unit] = P("BO_")

  private def id[_: P]: P[Message.Id] = integral.!.map(_.toLong).map(Message.Id)

  private def name[_: P]: P[Message.Name] = nameString.map(Message.Name)

  private def length[_: P]: P[Message.Length] = integral.!.map(_.toInt).map(Message.Length)

  private def producers[_: P]: P[List[Message.Producer]] = nodeRefs.map(_.map(Message.Producer))

  private def definition[_: P]: P[Message.Definition] =
    P(start ~ newLine.rep ~ id ~ name ~ length ~ producers).map {
      case (mId, mName, mLength, mProducers) => Message.Definition(mId, mName, mLength, mProducers)
    }

  def message[_: P]: P[Message] = P(newLine.rep ~ definition ~ signals).map {
    case (mDef, mSigs) => Message(mDef, mSigs)
  }

}
