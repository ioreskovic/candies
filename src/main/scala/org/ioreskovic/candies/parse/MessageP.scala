package org.ioreskovic.candies.parse

import org.ioreskovic.candies.parse.common._
import org.ioreskovic.candies.parse.signal._
import fastparse.SingleLineWhitespace._
import fastparse._
import org.ioreskovic.candies
import org.ioreskovic.candies.internal.Message

private[parse] trait MessageP {
  private def start[_: P]: P[Unit] = P("BO_")

  private def id[_: P]: P[Message.Id] = integral.!.map(_.toLong).map(Message.Id)

  private def name[_: P]: P[Message.Name] = P(nameString.map(Message.Name) ~ ":")

  private def length[_: P]: P[Message.Length] = integral.!.map(_.toInt).map(Message.Length)

  private def producer[_: P]: P[Message.Producer] = nameString.map(Message.Producer)

  private def definition[_: P]: P[Message.Definition] =
    P(start ~ newLine.rep ~ id ~ name ~ length ~ producer).map {
      case (mId, mName, mLength, producer) => Message.Definition(mId, mName, mLength, producer)
    }

  def message[_: P]: P[Message] = P(newLine.rep ~ definition ~ signals).map {
    case (mDef, mSigs) => candies.internal.Message(mDef, mSigs)
  }

}
