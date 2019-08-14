package org.ioreskovic.candies

import scala.concurrent.duration.Duration

final case class Cycle(msgId: Message.Id, duration: Duration)
