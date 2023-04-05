package UNO.model.PlayerComponent.playerBaseImp

import scala.util.{Failure, Success, Try}

import UNO.model.PlayerComponent.PlayerInterface
import UNO.model.cardComponent.cardBaseImp.Card

case class Player(name: String, playerCards: List[Card]) extends PlayerInterface:
  override def toString: String =
    name + "\n" + playerCards + "\n"

  def setPlayerCards(setCard: Card): Player =
      trySetPlayerCards(setCard) match
        case Some(player) => player
        case None => throw new Exception("Karten konnten nicht ersetzt werden!\n")      

  def trySetPlayerCards(setCard: Card): Option[Player] =
      Try(copy(playerCards = setCard :: playerCards)) match
        case Success(player) => Some(copy(playerCards = setCard :: playerCards))
        case Failure(_) => None

  def removePlayerCards(index: Int): Player =
    tryRemovePlayerCards(index) match
      case Some(player) => player
      case None => throw new Exception("Es konnte keine Karte ausgewählt werden!\n")

  def tryRemovePlayerCards(index: Int): Option[Player] =
    Try(playerCards diff List(playerCards(index))) match
      case Success(list) => Some(copy(playerCards = list))
      case Failure(_) => None
