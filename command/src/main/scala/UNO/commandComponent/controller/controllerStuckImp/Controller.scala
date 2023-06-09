package UNO

package controllerComponent.controllerStuckImp

import command.commandComponent.controller.controllerInterface
import command.commandComponent.model.PlayerComponent.playerBaseImp.Player
import command.commandComponent.model.cardComponent.cardBaseImp.Card
import command.commandComponent.model.stackComponent.stackBaseImp.Stack


abstract class Controller extends controllerInterface:
  var playername1 = "Saitama"  //Konstantin: here all val -> override val -> not working
  var playername2 = "Genos"
  var stackCard: Stack = Stack(List(new Card("",""))).initStack()
  var playerList: List[Player] = List(Player("Testy",List(Card("","red"))))
  var playStack2: List[Card] = List(Card("","blue"))
  var colorSet: String = "yellow"
  var unoCall: Boolean = false

  override def initPlayStack(): List[Card] = List(Card("","blue"))

  override def initPlayerList(): List[Player] = List(Player("Testy",List(Card("","red"))))

  override def getCard(): Unit = {}

  override def removeCard(handindex: Int): Unit = {}

  override def undoGet: Unit = {}

  override def redoGet: Unit = {}

  override def stackEmpty(): Stack = stackCard

  override def save: Unit = {}

  override def load: Unit //override def load: Unit = {}

  override def setDefault(): Unit = {}

  override def initStackCard() : Stack = Stack(List(new Card("",""))).initStack()
