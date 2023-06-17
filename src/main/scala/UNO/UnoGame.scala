package UNO

import scala.io.StdIn.readLine
import com.google.inject.Guice
import util.{State, gameStatsEvent, instructionEvent}
import controllerComponent.controllerInterface
import UNO.ui.TUI
import UNO.ui.gui.SwingGui
import controllerComponent.UnoGameModule
import scala.util.Try
import UNO.fileIOComponent.gameDataService.fileIOAPI
import command.commandComponent.commandAPI
import scala.util.Success
import scala.util.Failure

object UnoGame:
  val Controller = Guice.createInjector(new UnoGameModule).getInstance(classOf[controllerInterface])
  //val Controller = Guice.createInjector().getInstance(classOf[controllerInterface])
  val tui = new TUI(Controller)
  var UIType: Boolean = true //if System.getenv("UI_TYPE").equals("UNO.gui") then true else false

  @main def main(): Unit =

    fileIOAPI.main()
    commandAPI.main()

    Try(fileIOAPI) match
      case Success(v) => println("FileIo Rest Server is running!")
      case Failure(v) => println("FileIO Server couldn't be started! " + v.getMessage + v.getCause)

    Try(commandAPI) match
    case Success(v) => println("Command Rest Server is running!")
    case Failure(v) => println("Command Server couldn't be started! " + v.getMessage + v.getCause)

    print(State.handle(instructionEvent()))
    print(State.handle(gameStatsEvent()))
    
    //if UIType == true then
      //val gui = new SwingGui(Controller)
    var input1: String = ""
    while input1 != "q" 
    do
      input1 = readLine("\nInstruction: ")
      print(tui.processInputLine(input1))
      if (input1 == "q")
        System.exit(0)
