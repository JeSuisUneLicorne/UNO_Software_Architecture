/*
package UNO.fileIOComponent
package fileIOXmlImp

import java.io.{File, PrintWriter}
import scala.xml.{Elem, PrettyPrinter}
import scala.util.{Failure, Success, Try}
import play.api.libs.json.{JsValue, Json}
import scala.io.Source

import UNO.GameState
import UNO.PlayerComponent.playerBaseImp.Player
import UNO.cardComponent.cardBaseImp.Card
import UNO.fileIOComponent.FileIOTrait
import UNO.cardComponent.cardBaseImp.Card

class FileIO extends FileIOTrait:

  // was before:
  // override def load: UNO.GameState =
  //   val file = scala.xml.XML.loadFile("gamestate.xml")
  //   UNO.GameState(setPlayerList(file), setPlayStack(file))
  //   tryload match
  //     case Some(file: Elem) => UNO.GameState(setPlayerList(file), setPlayStack(file))
  //     case None => throw new Exception("Spielstand konnte nicht geladen werden.\n")

  // def tryload: Option[Elem] =
  //   Try(scala.xml.XML.loadFile("gamestate.xml")) match
  //     case Success(file) => Some(file)
  //     case Failure(_) => None

  override def load: Try[Option[(List[Player], List[Card])]] =
    var matchFieldOption: Option[(List[Player], List[Card])] = None
    Try {
      val file = scala.xml.XML.loadFile("gamestate.xml")
      matchFieldOption = Some((List[Player](), List[Card]()))
      matchFieldOption match
        case Some((playList,playStack2)) =>
          var newplaylist = playList
          var newplaystack2 = playStack2
          newplaylist = setPlayerList(file)
          newplaystack2= setPlayStack(file)
          matchFieldOption = Some((newplaylist,newplaystack2))
        case None=>
      matchFieldOption
    }

  def returnList (newCard: Card, oldList: List[Card], index: Int, playerValueIndex: List[String], playerColorIndex: List[String]): List[Card] =
    if((playerValueIndex.size-index) < 1) then
      newCard:: oldList
    else
      newCard:: returnList(Card(playerValueIndex(index), playerColorIndex(index)),oldList, index + 1, playerValueIndex, playerColorIndex)

  def setPlayerList (file: Elem) : List[Player] =
    val playerName = List((file \\ "gamestate" \ "playerName2").text, (file \\ "gamestate" \ "playerName1").text)
    val playerValue1 = (file \\ "gamestate" \ "playerCardValue1").text.split(" ").map(_.trim).toList.reverse.init
    val playerValue2 = (file \\ "gamestate" \ "playerCardValue2").text.split(" ").map(_.trim).toList.reverse.init
    val playerColor1 = (file \\ "gamestate" \ "playerCardColor1").text.split(" ").map(_.trim).toList.reverse.init
    val playerColor2 = (file \\ "gamestate" \ "playerCardColor2").text.split(" ").map(_.trim).toList.reverse.init
    val cards1 =  returnList(Card(playerValue1(0), playerColor1(0)),List():List[Card], 1, playerValue1, playerColor1)//List(Card(playerValue1(0), playerColor1(0))) (1 to playerValue1.size - 1).foreach(i => cards1 = Card(playerValue1(i), playerColor1(i)) :: cards1)
    val cards2 =  returnList(Card(playerValue2(0), playerColor2(0)),List():List[Card], 1, playerValue2, playerColor2)//List(Card(playerValue2(0), playerColor2(0))) (1 to playerValue2.size - 1).foreach(i =>  cards2 = Card(playerValue2(i), playerColor2(i)) :: cards2)
    List(Player(playerName(1), cards1), Player(playerName(0), cards2))

  def setPlayStack (file: Elem) : List[Card] = 
    List(Card((file \\ "gamestate" \ "playStack" \ "playStackValue").text.trim,
      (file \\ "gamestate" \ "playStack" \ "playStackColor").text.trim))

  override def save(gameState: GameState): Unit =
    val pw = new PrintWriter(new File("gamestate.xml"))
    val prettyPrinter = new PrettyPrinter(200,4)
    pw.write(prettyPrinter.format(gameStateToXml(gameState)))     //val xml = && pw.write(xml) -> test!
    pw.close()

  def gameStateToXml(gameState: GameState): Elem =
    <gamestate>
      {player1Name(gameState)}
      {player2Name(gameState)}
      {player1CardValue(gameState)}
      {player1CardColor(gameState)}
      {player2CardValue(gameState)}
      {player2CardColor(gameState)}
      {playerStack(gameState)}
    </gamestate>

  //PlayerList
  def player1Name (gameState: GameState): Elem =
    <playerName1>
      { gameState.playerList(0).name}
    </playerName1>

  def player1CardValue (gameState: GameState): Elem =
    <playerCardValue1>
      {gameState.playerList(0).playerCards.map(x => x.value + " ")}
    </playerCardValue1>

  def player1CardColor (gameState: GameState): Elem =
    <playerCardColor1>
      {gameState.playerList(0).playerCards.map(x => x.color + " ")}
    </playerCardColor1>

  def player2Name (gameState: GameState): Elem =
    <playerName2>
      {gameState.playerList(1).name}
    </playerName2>

  def player2CardValue (gameState: GameState): Elem =
    <playerCardValue2>
      {gameState.playerList(1).playerCards.map(x => x.value + " ")}
    </playerCardValue2>

  def player2CardColor (gameState: GameState): Elem =
    <playerCardColor2>
      {gameState.playerList(1).playerCards.map(x => x.color + " ")}
    </playerCardColor2>

  def playerStack (gameState: GameState): Elem =
    <playStack>
      <playStackColor>
        {gameState.playStack(0).color}
      </playStackColor>
      <playStackValue>
        {gameState.playStack(0).value}
      </playStackValue>
    </playStack>
*/