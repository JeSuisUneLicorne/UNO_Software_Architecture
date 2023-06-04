package command
package commandComponent

import command.commandComponent.controller.controllerInterface
//import util.Command
import command.commandComponent.Command

case class SetCommand(controller: controllerInterface) extends Command:
  override def doStep(): controllerInterface =
    controller.playerList = List(controller.playerList(1), controller.playerList(0).setPlayerCards(controller.stackCard.getCardFromStack()))
    controller.stackCard = controller.stackCard.removeCard()
    controller

  override def undoStep(): controllerInterface =
    controller.playerList = List(controller.playerList(1).removePlayerCards(0), controller.playerList(0))
    controller.stackCard = controller.stackCard.pullCards(List(controller.playerList(1).playerCards(0)))
    controller

  override def redoStep(): controllerInterface =
    controller.playerList = List(controller.playerList(1), controller.playerList(0).setPlayerCards(controller.stackCard.getCardFromStack()))
    controller.stackCard = controller.stackCard.removeCard()
    controller
