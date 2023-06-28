package UNO.dbComponent.Slick

import play.api.libs.json._
import scala.io.Source
import play.api.libs.json._
import scala.concurrent.Await
import scala.concurrent.duration._
import UNO.dbComponent.Slick.DOA.PlayerDOA
import UNO.dbComponent.Slick.DOA.PlayerCardsDOA
import UNO.dbComponent.Slick.DOA.StackDOA

object DbImp:
  def save(value: String): Unit =
    val jsString: JsValue = Json.parse(value)

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

  def load(): String =
    val players = Await.result(PlayerDOA.read, 10.seconds).toList.reverse
    val stack: List[String] = Await.result(StackDOA.read, 10.seconds).map {
      case (value, color) => List(value, color) 
    }.flatten.toList

    val player1Cards = Await.result(PlayerCardsDOA.read(players(0)), 10.seconds).toList
    val player2Cards = Await.result(PlayerCardsDOA.read(players(1)), 10.seconds).toList
    toJson(players, stack, player1Cards, player2Cards).toString

  def toJson(player: List[String], stack: List[String], player1Cards: List[(String, String)], player2Cards: List[(String, String)]) =
    Json.obj(
      "gameState" -> Json.obj(
        "playerListName" -> player.map(x => x),
        "playerCardsValue1" -> player1Cards.map(_._1),
        "playerCardsColor1" -> player1Cards.map(_._2),
        "playerCardsValue2" -> player2Cards.map(_._1),
        "playerCardsColor2" -> player2Cards.map(_._2),
        "playStackValue" -> stack(0),
        "playStackColor" -> stack(1)
      )
    )