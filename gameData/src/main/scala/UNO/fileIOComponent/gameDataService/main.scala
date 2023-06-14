package UNO.fileIOComponent.gameDataService

import scala.util.Try
import scala.util.Success
import scala.util.Failure
import scala.io.StdIn.readLine
import UNO.dbComponent.Slick.DOA.PlayerDOA
import UNO.dbComponent.Slick.DOA.PlayerCardsDOA
import UNO.dbComponent.Slick.DOA.StackDOA

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
    //PlayerCardsDOA.delete
    //PlayerCardsDOA.create
    //PlayerCardsDOA.update("hans", 2, "green")
    //PlayerCardsDOA.update("hans", 3, "blue")
    //PlayerCardsDOA.update("hans", 4, "red")
    //PlayerCardsDOA.delete

    //StackDOA.delete
    //StackDOA.create

    //StackDOA.update(2, "red")
    //StackDOA.update(3, "blue")

    //PlayerDOA.delete


    /*
    //PlayerDOA.create
    val players:  List[String] = List("hans", "bert") 
    
    /*
    players.foreach(e => {
      PlayerDOA.update(e)
    })
    */

    val values: List[Int] = List(1,2,3,4,5,6)
    val colors: List[String] = List("red", "blue", "green", "red", "blue", "green")
    PlayerCardsDOA.create

    values.zip(colors).foreach{ case (value, color) =>
      PlayerCardsDOA.update("hans", value, color)
    }

    PlayerCardsDOA.delete

    */
    readLine()
}