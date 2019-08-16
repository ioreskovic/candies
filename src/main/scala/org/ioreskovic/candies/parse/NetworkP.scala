package org.ioreskovic.candies.parse

import fastparse.MultiLineWhitespace._
import fastparse._
import org.ioreskovic.candies.internal.Network

private[parse] trait NetworkP {
  private def nMessage[_: P](net: Network): P[Network] = message.message.map(net.withMessage)

  private def nCycle[_: P](net: Network): P[Network] = cycle.cycle.map(net.withCycle)

  def network[_: P](net: Network): P[Network] = P(nMessage(net) | nCycle(net))

}
