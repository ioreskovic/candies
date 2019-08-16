package org.ioreskovic.candies

import org.ioreskovic.candies.Signal._

import scala.concurrent.duration.Duration

object Signal {

  type Name = String

  type Offset = Int

  type Length = Int

  type Factor = Double

  type Addend = Double

  type Min = Double

  type Max = Double

  type TheUnit = String

  type Consumer = String

  type Interval = Duration

}

case class Signal(
    name: Name,
    signalType: SignalType,
    offset: Offset,
    length: Length,
    signing: Signing,
    endianness: Endianness,
    factor: Factor,
    addend: Addend,
    min: Min,
    max: Max,
    unit: TheUnit,
    interval: Interval,
    consumers: List[Consumer]
)
