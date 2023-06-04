package command
package commandComponent

import play.api.libs.json.JsValue
import play.api.libs.json.Json
import command.commandComponent.controller.controllerInterface
//import UNO.controllerComponent.controllerBaseImp
import UNO.controllerComponent.controllerStuckImp.Controller
import com.google.inject.Guice
import command.commandComponent.SetCommand
//import UNO.controllerService.controllerComponent.UnoGameModule
//import UNO.controllerComponent.UnoGameModule
import scala.util.Try
import command.commandComponent.model.PlayerComponent.playerBaseImp.Player
import command.commandComponent.model.cardComponent.cardBaseImp.Card
import command.commandComponent.model.stackComponent.stackBaseImp.Stack
import command.commandComponent.controller.UnoGameModule
//import UNO.PlayerComponent.playerBaseImp.Player
//import UNO.cardComponent.cardBaseImp.Card
import scala.util.matching.Regex
//import UNO.stackComponent.stackBaseImp.Stack

object UndoManager:
  private var undoStack: List[Command] = Nil
  private var redoStack: List[Command] = Nil

  val controller = Guice.createInjector(new UnoGameModule).getInstance(classOf[controllerInterface])
  //val controller = this

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
    controller.playerList = players
    val cardRegex: Regex = """Card = (\S+) \|\| (\S+)""".r
    val stackCards: List[Card] = cardRegex
      .findAllMatchIn(stackCard)
      .map(m => Card(m.group(1), m.group(2)))
      .toList
    
    // dazwischen , eingefügt 
    val newStack = Stack(stackCards)
    controller.stackCard = newStack
    //print("controllerstacccccccccck" + newStack)
    // HIER ein komma zu viel
    val newController = checkCommand(controller, command, handindex.toInt)
    val jsonController : String = controllerToJson(newController)
    jsonController

  def checkCommand(controller: controllerInterface, command: String, handindex: Int) : controllerInterface =
    if (command == "set") {
      val newCommand = new SetCommand(controller)
      undoStack = newCommand :: undoStack
      newCommand.doStep()
    } else {
      val newCommand = new RemoveCommand(handindex, controller)
      undoStack = newCommand :: undoStack
      newCommand.doStep()
    }

  // check dropright, can be deleted i guess
  def controllerToJson(controller: controllerInterface): String =
    Json.obj(
      "playerList1" -> controller.playerList(0).toString.dropRight(1),
      "playerList2" -> controller.playerList(1).toString.dropRight(1),
      "stackCard" -> controller.stackCard.toString.dropRight(1)
      ).toString

  def parseCardList(cardListString: String): List[Card] =
    val cardRegex: Regex = """Card = (\S+) \|\| (\S+)""".r
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