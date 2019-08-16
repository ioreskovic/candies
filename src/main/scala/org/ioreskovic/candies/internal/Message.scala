package org.ioreskovic.candies.internal

import org.ioreskovic.candies.internal.Message.Definition

object Message {

  final case class Id(value: Long) extends AnyVal

  final case class Name(value: String) extends AnyVal

  final case class Length(value: Int) extends AnyVal

  final case class Producer(value: String) extends AnyVal

  final case class Definition(id: Id, name: Name, length: Length, producer: Producer)

}

final case class Message(definition: Definition, signals: List[Signal])
