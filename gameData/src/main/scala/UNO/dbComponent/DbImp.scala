package UNO.dbComponent

import play.api.libs.json._
import scala.io.Source
import play.api.libs.json._
import scala.concurrent.Await
import scala.concurrent.duration._
import UNO.dbComponent.DOA.PlayerDOA

object DbImp: 
  def saveGameState(value: String): Unit =
    val jsString: JsValue = Json.parse(value)

    PlayerDOA.create

    PlayerDOA.delete

    val players_ = (jsString \ "Player").as[List[String]]
    players_.foreach(e => {
      PlayerDOA.update(e)
    })