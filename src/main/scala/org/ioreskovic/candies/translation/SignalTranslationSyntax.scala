package org.ioreskovic.candies.translation

import cats.data._
import cats.implicits._
import org.ioreskovic.candies.{DbcError, Signal}
import org.ioreskovic.candies.internal.{Signal => ISignal}

import scala.concurrent.duration.Duration

private[translation] trait SignalTranslationSyntax {
  implicit final def signalSyntax(iSignal: ISignal): SignalTranslation = SignalTranslation(iSignal)
}

private final case class SignalTranslation(iSignal: ISignal) extends AnyVal {
  def toApi(interval: Duration): ValidatedNel[DbcError, Signal] =
    Signal(
      iSignal.name.value,
      iSignal.mtype,
      iSignal.offset.value,
      iSignal.length.value,
      iSignal.signing,
      iSignal.endianness,
      iSignal.factor.value,
      iSignal.addend.value,
      iSignal.min.value,
      iSignal.max.value,
      iSignal.unit.value,
      interval,
      iSignal.consumers.map(_.value)
    ).validNel
}
