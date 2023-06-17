package UNO.commandComponent

import command.commandComponent.commandAPI

import scala.util.Try
import scala.util.Success
import scala.util.Failure
import scala.io.StdIn.readLine

object uno {
  @main def main: Unit =
    Try(commandAPI) match
      case Success(v) => println("Command-Rest-Server is running")
      case Failure(v) => println("Command-Rest-Service could not be started! " + v.getMessage + v.getCause)
    readLine()
}