package command.commandComponent.model

package stackComponent

import command.commandComponent.model.cardComponent.cardBaseImp.Card
import command.commandComponent.model.stackComponent.stackBaseImp.Stack

trait StackInterface:
  def stackCards: List[Card]
  def initStack(): Stack
  def shuffleCards(): Stack
  def removeCard(): Stack
  def pullCards(playerStack: List[Card]): Stack
  def reversePullCards(playerStack: List[Card]): Stack
  def getCardFromStack(): Card
