package com.rimac.telemetry.m2m.can

import fastparse._
import SingleLineWhitespace._

trait Defaults {
  def nameChars(c: Char): Boolean = c.isLetterOrDigit || c == '-' || c == '_'

  def digits[_: P]: P[Unit] = P(CharsWhileIn("0-9"))

  def exponent[_: P]: P[Unit] = P(CharIn("eE") ~ CharIn("+\\-").? ~ digits)

  def fractional[_: P]: P[Unit] = P("." ~ digits)

  def integral[_: P]: P[Unit] = P("0" | CharIn("1-9") ~ digits.?)

  def number[_: P]: P[Unit] = P(CharIn("+\\-").? ~ integral ~ fractional.? ~ exponent.?)

  def stringChars(c: Char): Boolean = c != '\"' && c != '\\'

  def space[_: P]: P[Unit] = P(CharsWhileIn(" \r\n", 0))

  def hexDigit[_: P]: P[Unit] = P(CharIn("0-9a-fA-F"))

  def unicodeEscape[_: P]: P[Unit] = P("u" ~ hexDigit ~ hexDigit ~ hexDigit ~ hexDigit)

  def escape[_: P]: P[Unit] = P("\\" ~ (CharIn("\"/\\\\bfnrt") | unicodeEscape))

  def strChars[_: P]: P[Unit] = P(CharsWhile(stringChars))

  def string[_: P]: P[String] = P(space ~ "\"" ~/ (strChars | escape).rep.! ~ "\"")
}
