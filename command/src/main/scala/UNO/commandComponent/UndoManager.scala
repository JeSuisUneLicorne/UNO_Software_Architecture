package command
package commandComponent

import play.api.libs.json.JsValue
import play.api.libs.json.Json
import command.commandComponent.controller.controllerInterface
import UNO.controllerComponent.controllerStuckImp.Controller
import com.google.inject.Guice
import command.commandComponent.SetCommand
import scala.util.Try
import command.commandComponent.model.PlayerComponent.playerBaseImp.Player
import command.commandComponent.model.cardComponent.cardBaseImp.Card
import command.commandComponent.model.stackComponent.stackBaseImp.Stack
import command.commandComponent.controller.UnoGameModule
import scala.util.matching.Regex


object UndoManager:
  private var undoStack: List[Command] = Nil
  private var redoStack: List[Command] = Nil

  val controller = Guice.createInjector(new UnoGameModule).getInstance(classOf[controllerInterface])

  def doStep(jsonCommand: String): String =
    val json: JsValue = Json.parse(jsonCommand)
    val playerList1 = (json \ "playerList1").as[String]
    val playerList2 = (json \ "playerList2").as[String]
    val stackCard = (json \ "stackCard").as[String]
    val command = (json \ "command").as[String]
    val handindex = (json \ "handindex").as[String]
    val player1Cards = parseCardList(playerList1)
    val player2Cards = parseCardList(playerList2)
    val player1 = Player("1", player1Cards)
    val player2 = Player("2", player2Cards)
    val players : List[Player] = List(player1, player2)
    val playStack = (json \ "playStack").as[String]
    val playStack2 = parseCardList(playStack)
    controller.playStack2 = playStack2
    controller.playerList = players
    val stackCards = parseCardList(stackCard)
    val newStack = Stack(stackCards)
    controller.stackCard = newStack
    val newController = checkCommand(controller, command, handindex.toInt)
    val jsonController : String = controllerToJson(newController)
    jsonController

  def checkCommand(controller: controllerInterface, command: String, handindex: Int) : controllerInterface =
    if (command == "set") {
      val newCommand = new SetCommand(controller)
      undoStack = newCommand :: undoStack
      newCommand.doStep()
    } else if (command == "remove") {
      val newCommand = new RemoveCommand(handindex, controller)
      undoStack = newCommand :: undoStack
      newCommand.doStep()
    } else {
      val newCommand = new SetCommand(controller)
      undoStack = newCommand :: undoStack
      newCommand.doStep()
    }

  def controllerToJson(controller: controllerInterface): String =
    Json.obj(
      "playerList1" -> controller.playerList(0).toString,
      "playerList2" -> controller.playerList(1).toString,
      "stackCard" -> controller.stackCard.toString,
      "playStack" -> controller.playStack2.toString
      ).toString

  def parseCardList(cardListString: String): List[Card] =
    var cardRegex = "".r
    if (cardListString.length < 25) {
      cardRegex = """Card = (\S+)\s*\|\|\s*(\S+)(?:[,\$])?""".r
    } else {
      cardRegex = """Card = (\S+)\s*\|\|\s*(\S+)(?:,\s*|\)$)""".r
    }
    cardRegex.findAllMatchIn(cardListString).map { matchResult =>
      val value = matchResult.group(1)
      val color = matchResult.group(2)
      Card(value, color)
    }.toList

  def redoStep(): String = unitfiedStep(false)
  def undoStep(): String = unitfiedStep(true)

  def unitfiedStep(checkValue: Boolean): String =
    if(checkValue == true)
      undoStack match
        case Nil => ""
        case head :: stack =>
          val undo : controllerInterface = head.undoStep()
          undoStack = stack
          redoStack = head :: redoStack
          controllerToJson(undo)
    else
      redoStack match
        case Nil => ""
        case head :: stack =>
          val redo : controllerInterface = head.redoStep()
          redoStack = stack
          undoStack = head :: undoStack
          controllerToJson(redo)

  //old stuff:
  // def undoStep(): Unit =
  //   undoStack match 
  //     case Nil =>
  //     case head :: stack =>
  //       head.undoStep()
  //       undoStack = stack
  //       redoStack = head :: redoStack

  // def redoStep(): Unit =
  //   redoStack match 
  //     case Nil =>
  //     case head :: stack =>
  //       head.redoStep()
  //       redoStack = stack
  //       undoStack = head :: undoStack