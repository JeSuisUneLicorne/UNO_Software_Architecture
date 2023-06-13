package UNO.dbComponent

import play.api.libs.json._
import scala.io.Source
import play.api.libs.json._
import scala.concurrent.Await
import scala.concurrent.duration._
import UNO.dbComponent.DOA.PlayerDOA
import UNO.dbComponent.DOA.PlayerCardsDOA
import UNO.dbComponent.DOA.StackDOA

object DbImp:
  def save(value: String): Unit =
    val jsString: JsValue = Json.parse(value)
    
    PlayerDOA.create
    PlayerCardsDOA.create
    StackDOA.create

    PlayerDOA.delete
    PlayerCardsDOA.delete
    StackDOA.delete

    val players_ = (jsString \ "playerListName").as[List[String]]
    println("\nplayers: " + players_)

    players_.foreach(e => {
      PlayerDOA.update(e)
    })

    val cardValues_ = (jsString \ "playerCardsValue1").as[List[String]]
    println("\ncardValues: " + cardValues_)
    val cardColors_ = (jsString \ "playerCardsColor1").as[List[String]]
    println("\ncardColors: " + cardColors_)

    cardValues_.zip(cardColors_).foreach{ case (value, color) =>
      PlayerCardsDOA.update(players_(0), value, color)
    }

    val stackValue_ = (jsString \ "playStackValue").as[String]
    println("\nstackValue: " + stackValue_)
    val stackColor_ = (jsString \ "playStackColor").as[String]
    println("\nstackColor: " + stackColor_)

    StackDOA.update(stackValue_, stackColor_)

  def load: Unit =
    ""