package com.rimac.telemetry.m2m.can

final case class Name(value: String) extends AnyVal

final case class Offset(value: Int) extends AnyVal

final case class Length(value: Int) extends AnyVal

final case class Factor(value: Double) extends AnyVal

final case class Addend(value: Double) extends AnyVal

final case class Min(value: Double) extends AnyVal

final case class Max(value: Double) extends AnyVal

final case class TheUnit(value: String) extends AnyVal

final case class Consumer(value: String) extends AnyVal

final case class Signal(
    name: Name,
    offset: Offset,
    length: Length,
    factor: Factor,
    addend: Addend,
    min: Min,
    max: Max,
    unit: TheUnit,
    signing: Signing,
    endianness: Endianness,
    consumers: List[Consumer]
)

sealed trait Signing

case object Signed extends Signing

case object Unsigned extends Signing

object Signing {
  def fromString(s: String): Signing = s match {
    case "+" => Unsigned
    case "-" => Signed
  }
}

sealed trait Endianness

case object Big extends Endianness

case object Little extends Endianness

object Endianness {
  def fromString(s: String): Endianness = s match {
    case "0" => Little
    case "1" => Big
  }
}

object Signal {

  import fastparse._
  import SingleLineWhitespace._

  private def signalStart[_: P]: P[Unit] = P("SG_")

  private def name[_: P]: P[Name] = P(CharsWhile(nameChars, 1).!.map(Name) ~ ":")

  private def position[_: P]: P[(Offset, Length)] =
    P(integral.!.map(_.toInt).map(Offset) ~ "|" ~ integral.!.map(_.toInt).map(Length))

  private def bitInfo[_: P]: P[(Endianness, Signing)] =
    P("@" ~ CharIn("01").!.map(Endianness.fromString) ~ CharIn("+\\-").!.map(Signing.fromString))

  private def coding[_: P]: P[(Factor, Addend)] =
    P("(" ~ number.!.map(_.toDouble).map(Factor) ~ "," ~ number.!.map(_.toDouble).map(Addend) ~ ")")

  private def range[_: P]: P[(Min, Max)] =
    P("[" ~ number.!.map(_.toDouble).map(Min) ~ "," ~ number.!.map(_.toDouble).map(Max) ~ "]")

  private def unit[_: P]: P[TheUnit] = string.map(TheUnit)

  private def consumers[_: P]: P[Seq[Consumer]] =
    P(CharsWhile(nameChars, 1).!.map(Consumer).rep(sep = ","./))

  def signal[_: P]: P[Signal] =
    P(
        signalStart ~
        name ~
        position ~
        bitInfo ~
        coding ~
        range ~
        unit ~/
        consumers
    ).map {
      case (
          sName,
          (sOffset, sLength),
          (sEndianness, sSigning),
          (sFactor, sAddend),
          (sMin, sMax),
          sUnit,
          sConsumers
          ) =>
        Signal(
          sName,
          sOffset,
          sLength,
          sFactor,
          sAddend,
          sMin,
          sMax,
          sUnit,
          sSigning,
          sEndianness,
          sConsumers.toList
        )
    }

  def signals[_: P]: P[Seq[Signal]] = P(newLine.rep ~ (signal ~ newLine.rep).rep)

  def main(args: Array[String]): Unit = {
    val r = parse(
      s"""
         |
         |           SG_ SIG_1 : 0|8@0+ (-1.4,-2) [-15,0] "km/h" CON_1,CON_2
         |
         |SG_ SIG_2 : 8|16@0+ (-1.4,-2) [-15,0] "km/h"
         |
         |   SG_ SIG_3 : 24|30@0+ (-1.4,-2) [-15,0] "km/h" CON_3
         |
         |""".stripMargin,
      signals(_)
    )

    println(r)
  }
}
