package UNO.fileIOComponent
package gameDataService

import java.io._
import play.api.libs.json.{JsValue, Json}
import scala.io.Source
import UNO.dbComponent.Slick.DbImp
import UNO.dbComponent.MongoDB.MongoDBImp

object fileIOJsonImp {

  def load(): String = {
    
    //MongoDBImp.load()
    // Slick-DB
     DbImp.load()
    
    
    //val file = scala.io.Source.fromFile("gamestate.json").mkString
    //file
  }

  def save(gameAsJson: String): Unit = {

    //MongoDBImp.save(gameAsJson)
    
    // Slick-DB 
     DbImp.save(gameAsJson)
    
    
    //DbInterface.saveGameState(gameAsJson)
    //val pw = PrintWriter(File("gamestate.json"))
    //pw.write(gameAsJson)
    //pw.close
  } 
}