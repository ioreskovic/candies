package com.rimac.telemetry.m2m.can

sealed trait Signing

case object Signed extends Signing

case object Unsigned extends Signing

object Signing {
  def fromString(s: String): Signing = s match {
    case "+" => Unsigned
    case "-" => Signed
  }
}
