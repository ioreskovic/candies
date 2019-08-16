package org.ioreskovic.candies.translation

import cats.data._
import cats.implicits._
import org.ioreskovic.candies.Multiplex.{Discriminator, MuxGroup}
import org.ioreskovic.candies.Signal.Interval
import org.ioreskovic.candies._
import org.ioreskovic.candies.internal.Message.Definition
import org.ioreskovic.candies.internal.{Cycle, Message => IMessage, Signal => ISignal}

private[translation] trait MessageTranslationSyntax {
  implicit final def messageSyntax(iMessage: IMessage): MessageTranslation =
    MessageTranslation(iMessage)
}

private final case class MessageTranslation(iMessage: IMessage) extends AnyVal {
  def toApi(cycle: Cycle): ValidatedNel[DbcError, Message] = {
    import syntax.signal._

    val signalsByType = iMessage.signals.foldLeft(Signals.empty)(_ + _)

    (
      signalsByType.regular.traverse(_.toApi(cycle.duration)),
      multiplex(signalsByType, cycle.duration, iMessage.definition)
    ).mapN {
      case (regular, multiplex) =>
        Message(
          iMessage.definition.id.value,
          iMessage.definition.name.value,
          iMessage.definition.length.value,
          iMessage.definition.producer.value,
          regular,
          multiplex
        )
    }
  }

  private def multiplex(
      signals: Signals,
      interval: Interval,
      msgDef: Definition
  ): ValidatedNel[DbcError, Option[Multiplex]] = {
    import syntax.signal._

    if (signals.multiplexer.length > 1) {
      MultipleMultiplexers(
        msgDef.id.value,
        msgDef.name.value,
        signals.multiplexer.map(_.name.value)
      ).invalidNel
    } else if (signals.multiplexer.isEmpty && signals.multiplexed.nonEmpty) {
      MissingMultiplexer(msgDef.id.value, msgDef.name.value).invalidNel
    } else if (signals.multiplexer.nonEmpty) {
      (
        signals.multiplexer.head.toApi(interval),
        signals.multiplexed.traverse(_.toApi(interval))
      ).mapN {
        case (multiplexer, multiplexed) =>
          val groups = multiplexed
            .foldLeft(Map.empty[Discriminator, List[Signal]].withDefaultValue(Nil)) {
              case (groups, signal) =>
                signal.signalType match {
                  case Multiplexed(d) => groups + (d -> (signal :: groups(d)))
                  case _ => groups
                }
            }
            .map[Discriminator, MuxGroup] { case (d, ss) => (d, MuxGroup(d, ss)) }

          Multiplex(multiplexer, groups).some
      }

    } else {
      None.validNel
    }
  }
}

private object Signals {
  val empty: Signals = Signals(Nil, Nil, Nil)
}

private final case class Signals(
    regular: List[ISignal],
    multiplexer: List[ISignal],
    multiplexed: List[ISignal]
) {
  def +(s: ISignal): Signals = s.mtype match {
    case Regular => Signals(s :: regular, multiplexer, multiplexed)
    case Multiplexer => Signals(regular, s :: multiplexer, multiplexed)
    case Multiplexed(_) => Signals(regular, multiplexer, s :: multiplexed)
  }
}
