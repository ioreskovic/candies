package org.ioreskovic.candies.parse

import org.ioreskovic.candies._
import org.ioreskovic.candies.parse.common._
import fastparse.SingleLineWhitespace._
import fastparse._
import org.ioreskovic.candies.Signal.{Multiplexed, Multiplexer, Regular, Type}

private[parse] trait SignalP {
  private def start[_: P]: P[Unit] = P("SG_")

  private def name[_: P]: P[Signal.Name] = nameString.map(Signal.Name)

  private def offset[_: P]: P[Signal.Offset] = integral.!.map(_.toInt).map(Signal.Offset)

  private def length[_: P]: P[Signal.Length] = integral.!.map(_.toInt).map(Signal.Length)

  private def position[_: P]: P[(Signal.Offset, Signal.Length)] = P(":" ~ offset ~ "|" ~ length)

  private def endianness[_: P]: P[Endianness] = P(CharIn("01").!.map(Endianness.fromString))

  private def signing[_: P]: P[Signing] = P(CharIn("+\\-").!.map(Signing.fromString))

  private def bitInfo[_: P]: P[(Endianness, Signing)] = P("@" ~ endianness ~ signing)

  private def factor[_: P]: P[Signal.Factor] = number.!.map(_.toDouble).map(Signal.Factor)

  private def addend[_: P]: P[Signal.Addend] = number.!.map(_.toDouble).map(Signal.Addend)

  private def coding[_: P]: P[(Signal.Factor, Signal.Addend)] = P("(" ~ factor ~ "," ~ addend ~ ")")

  private def min[_: P]: P[Signal.Min] = number.!.map(_.toDouble).map(Signal.Min)

  private def max[_: P]: P[Signal.Max] = number.!.map(_.toDouble).map(Signal.Max)

  private def range[_: P]: P[(Signal.Min, Signal.Max)] = P("[" ~ min ~ "|" ~ max ~ "]")

  private def unit[_: P]: P[Signal.TheUnit] = string.map(Signal.TheUnit)

  private def consumers[_: P]: P[List[Signal.Consumer]] = nodeRefs.map(_.map(Signal.Consumer))

  private def mtype[_: P]: P[Type] =
    P("M".!.map(_ => Multiplexer) | ("m" ~ integral.!.map(g => Multiplexed(g.toInt)))).?.map(
      _.getOrElse(Regular)
    )

  def signal[_: P]: P[Signal] =
    P(
      start ~ name ~ mtype ~ position ~ bitInfo ~ coding ~ range ~ unit ~/ consumers
    ).map {
      case (
          sName,
          mType,
          (sOffset, sLength),
          (sEndian, sSign),
          (sFactor, sAddend),
          (sMin, sMax),
          sUnit,
          sCons
          ) =>
        Signal(
          sName,
          mType,
          sOffset,
          sLength,
          sFactor,
          sAddend,
          sMin,
          sMax,
          sUnit,
          sSign,
          sEndian,
          sCons
        )
    }

  def signals[_: P]: P[List[Signal]] = P(newLine.rep ~ (signal ~ newLine.rep).rep).map(_.toList)
}
