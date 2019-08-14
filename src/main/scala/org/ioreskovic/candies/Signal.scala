package org.ioreskovic.candies

import Signal._

object Signal {

  final case class Name(value: String) extends AnyVal

  final case class Offset(value: Int) extends AnyVal

  final case class Length(value: Int) extends AnyVal

  final case class Factor(value: Double) extends AnyVal

  final case class Addend(value: Double) extends AnyVal

  final case class Min(value: Double) extends AnyVal

  final case class Max(value: Double) extends AnyVal

  final case class TheUnit(value: String) extends AnyVal

  final case class Consumer(value: String) extends AnyVal

  sealed trait Type
  final case object Regular extends Type
  final case object Multiplexer extends Type
  final case class Multiplexed(group: Int) extends Type

}

final case class Signal(
    name: Name,
    mtype: Type,
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
