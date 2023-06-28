package UNO.fileIOComponent
package gameDataService

import java.io._
import play.api.libs.json.{JsValue, Json}
import scala.io.Source
import UNO.dbComponent.Slick.DbImp
import UNO.dbComponent.MongoDB.MongoDBImp

object fileIOJsonImp {

  def loadDB(): String = {
    //Slick-DB:
    DbImp.load()

    //Mongo-DB:
    //MongoDBImp.load()
  }

  //normal load
  def load(): String = {
    val file = scala.io.Source.fromFile("gamestate.json").mkString
    file
  }

  def saveDB(gameAsJson: String): Unit = {
    //Slick-DB:
    DbImp.save(gameAsJson)
    
    //Mongo-DB:  
    //MongoDBImp.save(gameAsJson)
  } 

  //normal save
  def save(gameAsJson: String): Unit = {
    val pw = PrintWriter(File("gamestate.json"))
    pw.write(gameAsJson)
    pw.close
  } 
}