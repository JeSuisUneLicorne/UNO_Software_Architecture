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
    println("\n" + jsString)

    PlayerDOA.create
    PlayerCardsDOA.create
    StackDOA.create

    PlayerDOA.delete
    PlayerCardsDOA.delete
    StackDOA.delete

    val players_ = (jsString \ "gameState" \ "playerListName").as[List[String]]

    players_.foreach(e => {
      PlayerDOA.update(e.toString)
    })

    val cardValues_ = (jsString \ "gameState" \ "playerCardsValue1").as[List[String]]
    val cardColors_ = (jsString \ "gameState" \ "playerCardsColor1").as[List[String]]
    cardValues_.zip(cardColors_).foreach{ case (value, color) =>
      PlayerCardsDOA.update(players_(0), value, color)
    }

    val cardValues2_ = (jsString \ "gameState" \ "playerCardsValue2").as[List[String]]
    val cardColors2_ = (jsString \ "gameState" \ "playerCardsColor2").as[List[String]]
    cardValues_.zip(cardColors_).foreach{ case (value, color) =>
      PlayerCardsDOA.update(players_(1), value, color)
    }

    val stackValue_ = (jsString \ "gameState" \ "playStackValue").as[String]
    val stackColor_ = (jsString \ "gameState" \ "playStackColor").as[String]
    StackDOA.update(stackValue_, stackColor_)

  def load: Unit =
    ""