package org.ioreskovic.candies.parse

import fastparse.MultiLineWhitespace._
import fastparse._
import org.ioreskovic.candies.Network

private[parse] trait NetworkP {
  private def nMessage[_: P](net: Network): P[Network] = message.message.map(net.withMessage)

  private def nCycle[_: P](net: Network): P[Network] = cycle.cycle.map(net.withCycle)

  private def nNetwork[_: P](net: Network): P[Network] =
    P(nMessage(net) | nCycle(net)).rep.map(_.reduce(_ + _))

  def network[_: P]: P[Network] = nNetwork(Network.empty)

}
