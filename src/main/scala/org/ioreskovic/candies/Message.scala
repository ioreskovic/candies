package org.ioreskovic.candies

import org.ioreskovic.candies.Message._

object Message {

  type Id = Long

  type Name = String

  type Length = Int

  type Producer = String

}

final case class Message(
    id: Id,
    name: Name,
    length: Length,
    producer: Producer,
    signals: List[Signal],
    multiplex: Option[Multiplex]
)
