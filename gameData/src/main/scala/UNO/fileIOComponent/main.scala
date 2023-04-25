package UNO.fileIOComponent

import com.google.inject.Guice
import scala.io.StdIn.readLine
import scala.collection.immutable.LazyList.cons
import gameDataService.gameDataAPI
import scala.util.{Try,Success,Failure}

object UNO {
  @main def main: Unit = {
    Try(gameDataAPI) match
      case Success(v) => println("Persistance Rest Server is running!")
      case Failure(v) => println("Persistance Server couldn't be started! " + v.getMessage + v.getCause)
  }
}