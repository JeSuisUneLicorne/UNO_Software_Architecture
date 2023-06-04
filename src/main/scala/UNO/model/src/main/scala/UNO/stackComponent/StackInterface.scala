package UNO

package stackComponent

import UNO.cardComponent.cardBaseImp.Card
import UNO.stackComponent.stackBaseImp.Stack
import UNO.cardComponent.cardBaseImp.Card

trait StackInterface:
  def stackCards: List[Card]
  def initStack(): Stack
  def shuffleCards(): Stack
  def removeCard(): Stack
  def pullCards(playerStack: List[Card]): Stack
  def reversePullCards(playerStack: List[Card]): Stack
  def getCardFromStack(): Card
