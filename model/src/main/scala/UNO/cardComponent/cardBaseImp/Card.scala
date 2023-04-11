package UNO.cardComponent.cardBaseImp

import UNO.cardComponent.CardInterface

case class Card(value: String, color: String) extends CardInterface:
  override def toString: String =
    "Card = %s || %s".format(value, color)
