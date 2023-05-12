package UNO.controllerComponent

package controllerBaseImp

import com.google.inject.{Guice, Inject}
import net.codingwell.scalaguice.InjectorExtensions.ScalaInjector
import scala.swing.Publisher
import scala.util.{Success, Failure}
import io.circe._
import io.circe.syntax._
import io.circe.generic.auto._

import UNO.GameState
import UNO.controllerComponent.UnoGameModule
import UNO.controllerComponent.*
import UNO.PlayerComponent.playerBaseImp.Player
import UNO.cardComponent.cardBaseImp.Card
import UNO.stackComponent.stackBaseImp.Stack
import command.commandComponent.UndoManager
import UNO.controllerComponent.GameStatus._
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import scala.concurrent.ExecutionContextExecutor
import akka.http.scaladsl.model.HttpMethods
import scala.concurrent.Future
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpRequest
import UNO.fileIOComponent.gameDataService.fileIOAPI
import akka.http.scaladsl.unmarshalling.Unmarshal
import play.api.libs.json.JsValue
import play.api.libs.json.Json
import scala.util.Try
import command.commandComponent.UndoManager
import command.commandComponent.Command
import controllerComponent.controllerBaseImp.SetCommand

class Controller @Inject() extends controllerInterface with Publisher:
  var playername1 = "1"
  var playername2 = "2"
  var stackCard = initStackCard()
  var playerList = initPlayerList()
  var playStack2 = initPlayStack()
  var colorSet = ""
  var unoCall = false
  var gameStatus: GameStatus = IDLE

  private val undoManager = new UndoManager
  var gameState: GameState = GameState(playerList, playStack2)
  //val injector = Guice.createInjector(new UnoGameModule)
  //val fileIo = injector.getInstance(classOf[FileIO])
  //def Controller = Guice.createInjector(new UnoGameModule).getInstance(classOf[controllerInterface])
  //val fileIo: FileIO = Guice.createInjector(new UnoGameModule).getInstance(classOf[FileIO])
  val gameDataServer = "http://localhost:8080/fileIO"
  val commandServer = "http://localhost:8081/command"

  def setDefault(): Unit =
    stackCard = initStackCard()
    playerList = initPlayerList()
    playStack2 = initPlayStack()
    publish(new updateStates)

  def initStackCard(): Stack =
    var stackCards =Stack(List(new Card("",""))).initStack()
    (1 to 100).foreach((i) => {
      stackCards = stackCards.shuffleCards()
    })
    stackCards

  def initPlayStack(): List[Card] =
    while stackCard.getCardFromStack().color == "black" do
      stackCard = stackCard.pullCards(List(stackCard.getCardFromStack()))
      stackCard = stackCard.removeCard()
    List(stackCard.getCardFromStack())

  def stackEmpty(): Stack =
    if stackCard.stackCards.length <= 5 then
      stackCard = stackCard.reversePullCards(playStack2).shuffleCards()
      (1 to 100).foreach((i) =>
        stackCard = stackCard.shuffleCards()
      )
    stackCard

  def initPlayerList(): List[Player] =
    def startHand(): List[Card] =
      var starthand = List(Card("",""))
      (1 to 7).foreach((i) =>
        starthand = stackCard.getCardFromStack() :: starthand
        stackCard = stackCard.removeCard()
      )
      starthand.init.reverse
    List(Player(playername1,startHand()),Player(playername2,startHand()))

//  def getCard(): Unit =
//    stackCard = stackEmpty()
//    undoManager.doStep(new SetCommand(this))
//    publish(new updateStates)

  def getCard(): Unit =
    stackCard = stackEmpty()
    implicit val system:ActorSystem[Any] = ActorSystem(Behaviors.empty, "my-system")
    val executionContext: ExecutionContextExecutor = system.executionContext
    given ExecutionContextExecutor = executionContext

    val response: Future[HttpResponse] = Http().singleRequest(HttpRequest(
      method = HttpMethods.POST,
      uri = commandServer + "/doStep",
      entity = doStepJson()
    ))

  def doStepJson(): String = {
    implicit val setCommandEncoder: Encoder[SetCommand] = Encoder[Command].contramap(identity)
    implicit val setCommandDecoder: Decoder[SetCommand] = Decoder[Command].map(_.asInstanceOf[SetCommand])
    
  }

    //undoManager.doStep(new SetCommand(this))
    publish(new updateStates)


  def removeCard(handindex: Int): Unit =
    stackCard = stackEmpty()
    //undoManager.doStep(new RemoveCommand(handindex:Int, this))
    unoCall = false
    publish(new updateStates)

  def undoGet: Unit = undoRedoget(true)
  def redoGet: Unit = undoRedoget(false)

  def undoRedoget(value: Boolean): Unit =
    if(value == true)
      //undoManager.undoStep()
      publish(new updateStates)
    else
      //undoManager.redoStep()
      publish(new updateStates)
  override def save: Unit =
    implicit val system:ActorSystem[Any] = ActorSystem(Behaviors.empty, "my-system")
    val executionContext: ExecutionContextExecutor = system.executionContext
    given ExecutionContextExecutor = executionContext

    val response: Future[HttpResponse] = Http().singleRequest(HttpRequest(
      method = HttpMethods.POST,
      uri = gameDataServer + "/save",
      entity = gameStateToJson()
    ))
    //response.onComplete{
    //  case Failure(fail) => sys.error("Save failed")
    //  case Success(value) => print("\n Send Value\n" + value)
    //}
  override def load: Unit =
    implicit val system:ActorSystem[Any] = ActorSystem(Behaviors.empty, "my-system")
    val executionContext: ExecutionContextExecutor = system.executionContext
    given ExecutionContextExecutor = executionContext
    val response: Future[HttpResponse] = Http().singleRequest(HttpRequest(
      uri = gameDataServer + "/load"
    ))
      response.onComplete {
        case Failure(_) => sys.error("No Json file")
        case Success(value) =>
          {
          Unmarshal(value.entity).to[String].onComplete {
        case Failure(_) => sys.error("Couldn`t unmarshal")
            case Success(value) => val json: JsValue = Json.parse(value)
              val gameState = loadJson(json)
                gameState match {
                  case Success(option) =>
                    option.match {
                      case Some(lists) =>
                        //print("playerlist before: " + playerList)
                        val(playerliste, playstackonthefield) = lists
                        playerList = playerliste
                        //print("new Playerlist :" + playerList)
                        playStack2 = playstackonthefield
                        gameStatus = LOADED
                        "load success"
                      case None=>
                        gameStatus = COULD_NOT_LOAD
                        "load not success"
                    }
                  case Failure(e) =>
                    gameStatus = COULD_NOT_LOAD
                    "load not success"
                }
              print(gameStatus)
              //print("updateStates: \n")
              publish(new updateStates)
              "load"
            }
        }
      }

  def loadJson(json: JsValue): Try[Option[(List[Player], List[Card])]] =
    var matchFieldOption: Option[(List[Player], List[Card])] = None
    Try {
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
  
  def returnList (newCard: Card, oldList: List[Card], index: Int, playerValueIndex: List[String], playerColorIndex: List[String]): List[Card] =
    if((playerValueIndex.size - index) < 1) then
      newCard:: oldList
    else
      newCard:: returnList(Card(playerValueIndex(index), playerColorIndex(index)),oldList, index + 1, playerValueIndex, playerColorIndex)

  def gameStateToJson(): String =
    print("Inside gameStatetoJson" + gameState.playerList)
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
    ).toString