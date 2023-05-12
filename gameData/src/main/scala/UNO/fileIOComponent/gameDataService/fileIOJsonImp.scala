package UNO.fileIOComponent.gameDataService

import java.io._
import play.api.libs.json.{JsValue, Json}
import scala.io.Source

object fileIOJsonImp {

  def load(): String = {
    val file = scala.io.Source.fromFile("gamestate.json").mkString
    file
  }

  def save(gameAsJson: String): Unit = {
    val pw = PrintWriter(File("gamestate.json"))
    pw.write(gameAsJson)
    pw.close
  } 
}