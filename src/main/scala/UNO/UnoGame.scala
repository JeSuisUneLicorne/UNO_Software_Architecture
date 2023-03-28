package UNO

import scala.io.StdIn.readLine
import com.google.inject.Guice
import util.{State, gameStatsEvent, instructionEvent}

import aview.gui.SwingGui
import controller.controllerComponent.controllerInterface
import UNO.aview.TUI

object UnoGame:
  val injector = Guice.createInjector(new UnoGameModule)
  val controller = injector.getInstance(classOf[controllerInterface])
  val tui = new TUI(controller)
  var UIType: Boolean = if System.getenv("UI_TYPE").equals("gui") then true else false

  @main 
  def main(): Unit =
    print(State.handle(instructionEvent()))
    print(State.handle(gameStatsEvent()))

    if UIType.equals(true) then
      val gui = new SwingGui(controller)
      var input1: String = ""

      if input1 == "q" then
        System.exit(0)
      while 
        input1 != "q"
      do
        input1 = readLine("\nInstruction: ")
        print(tui.processInputLine(input1))
    // else
    //   tui.processInputLine(args(0))
