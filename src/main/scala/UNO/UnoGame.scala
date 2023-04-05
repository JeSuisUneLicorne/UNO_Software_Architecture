package UNO

import scala.io.StdIn.readLine
import com.google.inject.Guice

import util.{State, gameStatsEvent, instructionEvent}
import aview.gui.SwingGui
import controller.controllerComponent.controllerInterface
import UNO.aview.TUI

object UnoGame:
  val Controller = Guice.createInjector(new UnoGameModule).getInstance(classOf[controllerInterface])
  val tui = new TUI(Controller)
  var UIType: Boolean = true //if System.getenv("UI_TYPE").equals("gui") then true else false

  @main def main(): Unit =
    print(State.handle(instructionEvent()))
    print(State.handle(gameStatsEvent()))

    if UIType == true then
      val gui = new SwingGui(Controller)
    var input1: String = ""
    while input1 != "q" 
    do
      input1 = readLine("\nInstruction: ")
      print(tui.processInputLine(input1))
      if (input1 == "q")
        System.exit(0)
