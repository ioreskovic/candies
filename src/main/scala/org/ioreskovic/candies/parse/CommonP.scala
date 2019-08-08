package org.ioreskovic.candies.parse

import fastparse.SingleLineWhitespace._
import fastparse._

private[parse] trait CommonP {
  private[parse] def nameChars(c: Char): Boolean = c.isLetterOrDigit || c == '-' || c == '_'

  private[parse] def digits[_: P]: P[Unit] = P(CharsWhileIn("0-9"))

  private[parse] def exponent[_: P]: P[Unit] = P(CharIn("eE") ~ CharIn("+\\-").? ~ digits)

  private[parse] def fractional[_: P]: P[Unit] = P("." ~ digits)

  private[parse] def integral[_: P]: P[Unit] = P("0" | CharIn("1-9") ~ digits.?)

  private[parse] def number[_: P]: P[Unit] =
    P(CharIn("+\\-").? ~ integral ~ fractional.? ~ exponent.?)

  private[parse] def stringChars(c: Char): Boolean = c != '\"' && c != '\\'

  private[parse] def space[_: P]: P[Unit] = P(CharsWhileIn(" \r\n", 0))

  private[parse] def hexDigit[_: P]: P[Unit] = P(CharIn("0-9a-fA-F"))

  private[parse] def unicodeEscape[_: P]: P[Unit] =
    P("u" ~ hexDigit ~ hexDigit ~ hexDigit ~ hexDigit)

  private[parse] def escape[_: P]: P[Unit] = P("\\" ~ (CharIn("\"/\\\\bfnrt") | unicodeEscape))

  private[parse] def strChars[_: P]: P[Unit] = P(CharsWhile(stringChars))

  private[parse] def string[_: P]: P[String] = P(space ~ "\"" ~/ (strChars | escape).rep.! ~ "\"")

  private[parse] def newLine[_: P]: P[Unit] = P(CharPred(c => c == '\r' | c == '\n'))

  private[parse] def nameString[_: P]: P[String] = P(CharsWhile(nameChars, 1).! ~ ":")

  private[parse] def nodeRefs[_: P]: P[List[String]] =
    P(CharsWhile(nameChars, 1).!.rep(sep = ","./)).map(_.toList)
}
