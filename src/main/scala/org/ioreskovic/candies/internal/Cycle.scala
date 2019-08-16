package org.ioreskovic.candies.internal

import scala.concurrent.duration.Duration

final case class Cycle(msgId: Message.Id, duration: Duration)
