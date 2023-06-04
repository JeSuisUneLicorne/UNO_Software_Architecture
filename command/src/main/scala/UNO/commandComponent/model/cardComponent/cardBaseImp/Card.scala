package command.commandComponent.model.cardComponent.cardBaseImp

import command.commandComponent.model.cardComponent.CardInterface

case class Card(value: String, color: String) extends CardInterface:
  override def toString: String =
    "Card = %s || %s".format(value, color)
