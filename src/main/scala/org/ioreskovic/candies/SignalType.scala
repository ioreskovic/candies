package org.ioreskovic.candies

sealed trait SignalType
case object Regular extends SignalType
case object Multiplexer extends SignalType
final case class Multiplexed(group: Int) extends SignalType
