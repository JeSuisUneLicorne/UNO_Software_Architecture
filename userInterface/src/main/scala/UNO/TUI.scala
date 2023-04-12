package UNO

import UNO.controllerComponent.controllerBaseImp.updateStates
import UNO.controllerComponent.controllerInterface
import UNO.util.*

import scala.swing.Reactor
import scala.util.{Failure, Success, Try}

enum Letter(val letter_string: String):
  case U extends Letter("u")
  case R extends Letter("r")

class TUI (controller: controllerInterface) extends Reactor:
  listenTo(controller)

  def processInputLine(input: String): String =
    val is: Array[String] = input.split(" ")
    is(0) match
      case "s" =>
        controller.getCard()
        State.handle(setPlayerCardEvent())
      case "r" =>
        case_r(is)
      case "u" =>
        case_u(is)
      case "q" =>
        State.handle(exitGameEvent())
      case "undo" =>
        controller.undoGet
        "undo"
      case "redo" =>
        controller.redoGet
        "redo"
      case "load" =>
        controller.load
        "Loading Game!"
      case "save" =>
        controller.save
        "Saved Game!"
      case _ =>
        "Wrong command!"

  reactions += {
    case event: updateStates => printGameStats
  }

  def printGameStats: Unit = 
    print(State.handle(gameStatsEvent()))

  def case_u(is: Array[String]): String = unifiedcases(Letter.U.letter_string) (is)
  
  def case_r(is: Array[String]): String = unifiedcases(Letter.R.letter_string) (is)

  def unifiedcases(value:String)(is:Array[String]):String=
    value match {
      case "u"=>
        controller.unoCall = true
        if controller.playerList(0).playerCards.size.equals(2) then
          controller.removeCard(is(1).toInt)
          State.handle(callFirstUnoEvent(is(1).toInt), is(1).toInt)
        else if controller.playerList(0).playerCards.size.equals(1) then
          State.handle(callSecondUnoEvent())
        else
          controller.getCard()
          controller.playerList = controller.playerList.reverse
          controller.getCard()
          State.handle(toManyCardsEvent())
      case "r"=>
        if controller.playerList(0).playerCards(is(1).toInt).color.equals("black") then
          controller.colorSet = is(2)
        if Strategy.handle(removeCardEvent(is(1).toInt), is(1).toInt) && controller.playerList(0).playerCards.size >= 3 then
          controller.removeCard(is(1).toInt)
          State.handle(removePlayerCardEvent(is(1).toInt), is(1).toInt)
        else if !Strategy.handle(removeCardEvent(is(1).toInt), is(1).toInt) && controller.playerList(0).playerCards.size >= 3 then
          State.handle(removeFalseCardEvent())
        else
          controller.removeCard(is(1).toInt)
          controller.getCard()
          controller.playerList = controller.playerList.reverse
          controller.getCard()
          State.handle(forgotCallUnoEvent())
    }

    // was before
    /*val is: Array[String] = input.split(" ")
    is(0) match
      case "s" => 
        controller.getCard()
        State.handle(setPlayerCardEvent())
      case "r" => 
        if controller.playerList(0).playerCards(is(1).toInt).color.equals("black") then
          controller.colorSet = is(2)
        if Strategy.handle(removeCardEvent(is(1).toInt),is(1).toInt) && controller.playerList(0).playerCards.size >= 3 then
          controller.removeCard(is(1).toInt)
          State.handle(removePlayerCardEvent(is(1).toInt),is(1).toInt)
        else if !Strategy.handle(removeCardEvent(is(1).toInt),is(1).toInt) && controller.playerList(0).playerCards.size >= 3 then
          State.handle(removeFalseCardEvent())
        else 
          controller.removeCard(is(1).toInt)
          controller.getCard()
          controller.playerList = controller.playerList.reverse
          controller.getCard()
          State.handle(forgotCallUnoEvent())
      case "u" => 
        controller.unoCall = true
        if(controller.playerList(0).playerCards.size.equals(2)) then
          controller.removeCard(is(1).toInt)
          State.handle(callFirstUnoEvent(is(1).toInt),is(1).toInt)
        else if(controller.playerList(0).playerCards.size.equals(1)) then
          State.handle(callSecondUnoEvent())
        else 
          controller.getCard()
          controller.playerList = controller.playerList.reverse
          controller.getCard()
          State.handle(toManyCardsEvent())
      case "q" => 
        State.handle(exitGameEvent())
      case "undo" => 
        controller.undoGet
        "undo"
      case "redo" => 
        controller.redoGet
        "redo"
      case "load" => 
        controller.load
        "Loading Game!"
      case "save" => 
        controller.save
        "Saved Game!"
      case _ => 
        "Wrong command!"*/
