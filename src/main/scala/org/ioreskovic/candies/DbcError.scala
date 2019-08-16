package org.ioreskovic.candies

sealed trait DbcError
final case class MultipleMultiplexers(msgId: Long, msgName: String, multiplexers: List[String])
    extends DbcError
final case class MissingMultiplexer(msgId: Long, msgName: String) extends DbcError
