package UNO.fileIOComponent.gameDataService

import scala.util.Try
import scala.util.Success
import scala.util.Failure
import scala.io.StdIn.readLine
import UNO.dbComponent.DOA.PlayerDOA
import UNO.dbComponent.DOA.PlayerCardsDOA

object uno {
  @main def main(): Unit =
    Try(fileIOAPI) match
      case Success(v) => println("fileIO-Rest-Server is running")
      case Failure(v) => println("fileIO-Rest-Service could not be started! " + v.getMessage + v.getCause)
    //readLine()
    //PlayerDOA.delete
    //PlayerDOA.create
    //PlayerDOA.update("Hans")
    //PlayerDOA.update("Wurst")
    PlayerCardsDOA.delete
    PlayerCardsDOA.create
    
    readLine()
}