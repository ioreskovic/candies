package org.ioreskovic.candies

sealed trait Endianness

case object Big extends Endianness

case object Little extends Endianness

object Endianness {
  def fromString(s: String): Endianness = s match {
    case "0" => Little
    case "1" => Big
  }
}
