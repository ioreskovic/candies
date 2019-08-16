package org.ioreskovic.candies.translation

import cats.data._
import cats.implicits._
import org.ioreskovic.candies.{DbcError, Network}
import org.ioreskovic.candies.internal.{Cycle, Network => INetwork}

import scala.concurrent.duration._

private[translation] trait NetworkTranslationSyntax {
  implicit final def networkSyntax(iNetwork: INetwork): NetworkTranslation =
    NetworkTranslation(iNetwork)
}

private final case class NetworkTranslation(iNetwork: INetwork) extends AnyVal {

  def toApi: ValidatedNel[DbcError, Network] = {
    import syntax.message._

    iNetwork.messages.values.toList
      .traverse(
        im =>
          im.toApi(iNetwork.cycles.getOrElse(im.definition.id, Cycle(im.definition.id, 1.second)))
      )
      .map(Network)
  }
}
