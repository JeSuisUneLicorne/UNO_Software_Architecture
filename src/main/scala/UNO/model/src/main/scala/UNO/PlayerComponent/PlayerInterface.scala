package UNO

package PlayerComponent

import UNO.PlayerComponent.playerBaseImp.Player
import UNO.cardComponent.cardBaseImp.Card
import UNO.cardComponent.cardBaseImp.Card

trait PlayerInterface:
  def name: String
  def playerCards: List[Card]
  def tryRemovePlayerCards(index: Int): Option[Player]
  def removePlayerCards(index: Int): Player
  def setPlayerCards(setCard: Card): Player
  def toString: String
