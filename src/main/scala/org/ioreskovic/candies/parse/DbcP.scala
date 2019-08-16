package org.ioreskovic.candies.parse

import fastparse.MultiLineWhitespace._
import fastparse.Parsed.Success
import fastparse._
import org.ioreskovic.candies.internal.Network

private[parse] trait DbcP {
  private def interested[_: P](n: Network): P[Network] = network.network(n)

  private def all[_: P](n: Network): P[Network] =
    interested(n).rep.map(_.reduce(_ + _))

  def dbc[_: P]: P[Network] = all(Network.empty)
}
