package org.ioreskovic.candies

case class Network(
    messages: Map[Message.Id, Message] = Map.empty,
    cycles: Map[Message.Id, Cycle] = Map.empty
) {
  def withMessage(msg: Message): Network =
    this.copy(messages = messages + (msg.definition.id -> msg))

  def withCycle(cyc: Cycle): Network =
    this.copy(cycles = cycles + (cyc.msgId -> cyc))

  def +(other: Network): Network =
    Network(this.messages ++ other.messages, this.cycles ++ other.cycles)
}

object Network {
  val empty: Network = Network()
}
