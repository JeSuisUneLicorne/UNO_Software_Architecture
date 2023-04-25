package UNO.fileIOComponent.gameDataService

import java.io._
import play.api.libs.json.{JsValue, Json}
import scala.io.Source

object gameDataController {

  def load(): String = {
    val file = scala.io.Source.fromFile("gamestate.json")
    try file.mkString finally file.close()
  }

  def save(gameAsJson: String): Unit = {
    print("gamedatacontroller")
    val pw = new PrintWriter(new File("." + File.separator + "gamestatetest.json"))
    pw.write(gameAsJson)
    pw.close
  }
}