package UNO.fileIOComponent.fileIOJsonImp

import play.api.libs.json.{JsValue, Json}
import scala.util.{Failure, Success, Try}
import scala.io.Source
import java.io._

import UNO.GameState
import UNO.PlayerComponent.playerBaseImp.Player
import UNO.cardComponent.cardBaseImp.Card
import UNO.fileIOComponent.FileIOTrait

class FileIO extends FileIOTrait:

  // was before:
  // override def load: UNO.GameState =
  //   val file: String = Source.fromFile("gamestate.json").getLines.mkString
  //   val json: JsValue = Json.parse(file)
  //   UNO.GameState(setPlayerList(json), setPlayStack(json))
  //   tryload match
  //     case Some(json: JsValue) => UNO.GameState(setPlayerList(json), setPlayStack(json))
  //     case None => throw new Exception("Spielstand konnte nicht geladen werden.\n")

  // def tryload: Option[JsValue] =
  //   Try(Source.fromFile("gamestate.json").getLines.mkString) match
  //     case Success(file) => Some(Json.parse(file))
  //     case Failure(_) => None

  override def load: Try[Option[(List[Player], List[Card])]] =
    var matchFieldOption: Option[(List[Player], List[Card])] = None
    Try {
      val file: String = Source.fromFile("gamestate.json").getLines.mkString
      val json: JsValue = Json.parse(file)
      matchFieldOption = Some((List[Player](), List[Card]()))
      matchFieldOption match 
        case Some((playList,playStack2))=>
          var newplaylist = playList
          var newplaystack2 = playStack2
          newplaylist = setPlayerList(json)
          newplaystack2= setPlayStack(json)
          matchFieldOption = Some((newplaylist,newplaystack2))
        case None =>
      matchFieldOption
    }
    
  def returnList (newCard: Card, oldList: List[Card], index: Int, playerValueIndex: List[String], playerColorIndex: List[String]): List[Card] =
    if((playerValueIndex.size - index) < 1) then
      newCard:: oldList
    else
      newCard:: returnList(Card(playerValueIndex(index), playerColorIndex(index)),oldList, index + 1, playerValueIndex, playerColorIndex)

  def setPlayerList (json: JsValue) : List[Player] =
    val playerName = (json \ "gameState" \ "playerListName").as[List[String]]
    val playerValue1 = (json \ "gameState" \ "playerCardsValue1").as[List[String]]
    val playerValue2 = (json \ "gameState" \ "playerCardsValue2").as[List[String]]
    val playerColor1 = (json \ "gameState" \ "playerCardsColor1").as[List[String]]
    val playerColor2 = (json \ "gameState" \ "playerCardsColor2").as[List[String]]
    val cards1 = returnList(Card(playerValue1(0), playerColor1(0)), List(): List[Card], 1, playerValue1, playerColor1) //before: var ... and: List(Card(playerValue1(0), playerColor1(0))) (1 to playerValue1.size - 1).foreach(i => cards1 = Card(playerValue1(i), playerColor1(i)) :: cards1)
    val cards2 = returnList(Card(playerValue2(0), playerColor2(0)), List(): List[Card], 1, playerValue2, playerColor2) // before: var ... and: List(Card(playerValue2(0), playerColor2(0))) (1 to playerValue2.size - 1).foreach(i => cards2 = Card(playerValue2(i), playerColor2(i)) :: cards2)
    List(Player(playerName(0), cards1.reverse), Player(playerName(1), cards2.reverse))

  def setPlayStack (json: JsValue) : List[Card] =
    List(Card((json \ "gameState" \ "playStackValue").as[String],
      (json \ "gameState" \ "playStackColor").as[String]))

  override def save(gameState: GameState): String =
    val pw = new PrintWriter(new File("gamestate.json"))
    pw.write(Json.prettyPrint(gameStateToJson(gameState)))
    pw.close
    val JsonString = Json.prettyPrint(gameStateToJson(gameState))
    JsonString

  def gameStateToJson(gameState: GameState) =
    Json.obj(
      "gameState" -> Json.obj(
        "playerListName" -> gameState.playerList.map(x => x.name),
        "playerCardsValue1" -> gameState.playerList(0).playerCards.map(x => x.value),
        "playerCardsColor1" -> gameState.playerList(0).playerCards.map(x => x.color),
        "playerCardsValue2" -> gameState.playerList(1).playerCards.map(x => x.value),
        "playerCardsColor2" -> gameState.playerList(1).playerCards.map(x => x.color),
        "playStackValue" -> gameState.playStack(0).value,
        "playStackColor" -> gameState.playStack(0).color
      )
    )
  