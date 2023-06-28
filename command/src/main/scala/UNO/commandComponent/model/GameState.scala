package command.commandComponent
package model

import command.commandComponent.model.cardComponent.cardBaseImp.Card
import command.commandComponent.model.PlayerComponent.playerBaseImp.Player
import command.commandComponent.model.stackComponent.stackBaseImp.Stack

case class GameState(playerList: List[Player], playStack: List[Card]):

  def getplayerList(): List[Player] =
    playerList

  def getstackCard(): Stack =
    var stackCard = Stack(List(new Card("", ""))).initStack()
    /*(1 to 100).foreach((i)=>{
      stackCard = stackCard.shuffleCards()
    })*/
    stackCard

  def getplayStack(): List[Card] =
    playStack
