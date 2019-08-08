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

}

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
