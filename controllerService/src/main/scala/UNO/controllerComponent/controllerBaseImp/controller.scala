package UNO.controllerComponent

package controllerBaseImp

import com.google.inject.{Guice, Inject}
import net.codingwell.scalaguice.InjectorExtensions.ScalaInjector
import scala.swing.Publisher
import scala.util.{Success, Failure}

import UNO.GameState
import UNO.controllerComponent.UnoGameModule
import UNO.controllerComponent.*
import UNO.PlayerComponent.playerBaseImp.Player
import UNO.cardComponent.cardBaseImp.Card
import UNO.stackComponent.stackBaseImp.Stack
import UNO.controllerComponent.UndoManager
import UNO.fileIOComponent.FileIOTrait
import UNO.fileIOComponent.fileIOJsonImp.FileIO
import UNO.controllerComponent.GameStatus._
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import scala.concurrent.ExecutionContextExecutor
import akka.http.scaladsl.model.HttpMethods
import scala.concurrent.Future
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpRequest
import UNO.fileIOComponent.gameDataService.gameDataAPI
import akka.http.scaladsl.unmarshalling.Unmarshal
import play.api.libs.json.JsValue
import play.api.libs.json.Json

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
  val fileIo: FileIO = Guice.createInjector(new UnoGameModule).getInstance(classOf[FileIO])
  val gameDataServer = "http://localhost:8080/fileIO"

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

  def getCard(): Unit =
    stackCard = stackEmpty()
    undoManager.doStep(new SetCommand(this))
    publish(new updateStates)

  def removeCard(handindex: Int): Unit =
    stackCard = stackEmpty()
    undoManager.doStep(new RemoveCommand(handindex:Int, this))
    unoCall = false
    publish(new updateStates)

  def undoGet: Unit = undoRedoget(true)
  def redoGet: Unit = undoRedoget(false)

  def undoRedoget(value: Boolean): Unit =
    if(value == true)
      undoManager.undoStep()
      publish(new updateStates)
    else
      undoManager.redoStep()
      publish(new updateStates)

  // def undoGet: Unit =
  //   undoManager.undoStep()
  //   publish(new updateStates)

  // def redoGet: Unit =
  //   undoManager.redoStep()
  //   publish(new updateStates)

  // save before rest
  //override def save: Unit =
  //  fileIo.save(GameState(playerList, playStack2))
  override def save: Unit =
    implicit val system:ActorSystem[Any] = ActorSystem(Behaviors.empty, "my-system")
    val executionContext: ExecutionContextExecutor = system.executionContext
    given ExecutionContextExecutor = executionContext
    print("inside save (Controller)")
    val response: Future[HttpResponse] = Http().singleRequest(HttpRequest(
      method = HttpMethods.POST,
      uri = gameDataServer + "/save",
      entity = fileIo.save(gameState).toString()
    ))

  override def loadd: String = 
    implicit val system:ActorSystem[Any] = ActorSystem(Behaviors.empty, "my-system")
    val executionContext: ExecutionContextExecutor = system.executionContext
    given ExecutionContextExecutor = executionContext
    print("inside save (ControllerLOad)")
    val response: Future[HttpResponse] = Http().singleRequest(HttpRequest(
      uri = gameDataServer + "/load"
    ))
      response.onComplete {
        case Failure(_) => sys.error("No Json file")
        case Success(value) =>
          { 
          Unmarshal(value.entity).to[String].onComplete {
            case Failure(_) => sys.error("Couldnt unmarshal")
            case Success(value) => val json: JsValue = Json.parse(value)
              fileIo.load()
              //publish 
            }
          }
      }
