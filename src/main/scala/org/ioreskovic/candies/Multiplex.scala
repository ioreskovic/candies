package org.ioreskovic.candies

import org.ioreskovic.candies.Multiplex.{Discriminator, MuxGroup}

object Multiplex {
  type Discriminator = Int
  final case class MuxGroup(discriminator: Discriminator, signals: List[Signal])
}

final case class Multiplex(multiplexer: Signal, map: Map[Discriminator, MuxGroup])
