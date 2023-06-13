package UNO.controllerComponent

package controllerBaseImp

import com.google.inject.{Guice, Inject}
import net.codingwell.scalaguice.InjectorExtensions.ScalaInjector
import scala.swing.Publisher
import scala.util.{Success, Failure}

import UNO.model._
//import UNO.controllerComponent.UnoGameModule
import UNO.controllerComponent.*
import UNO.model.PlayerComponent.playerBaseImp.Player
import UNO.model.cardComponent.cardBaseImp.Card
import UNO.model.stackComponent.stackBaseImp.Stack
import UNO.controllerComponent.GameStatus._
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import scala.concurrent.ExecutionContextExecutor
import akka.http.scaladsl.model.HttpMethods
import scala.concurrent.Future
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpRequest
//import UNO.fileIOComponent.gameDataService.fileIOAPI
import akka.http.scaladsl.unmarshalling.Unmarshal
import play.api.libs.json.JsValue
import play.api.libs.json.Json
import scala.util.Try
import scala.util.matching.Regex

case class Controller @Inject()() extends controllerInterface with Publisher:
  var playername1 = "1"
  var playername2 = "2"
  var stackCard = initStackCard()
  var playerList = initPlayerList()
  var playStack2 = initPlayStack()
  var colorSet = ""
  var unoCall = false
  var gameStatus: GameStatus = IDLE

  //private val undoManager = new UndoManager
  var gameState: GameState = GameState(playerList, playStack2)
  //val injector = Guice.createInjector(new UnoGameModule)
  //val fileIo = injector.getInstance(classOf[FileIO])
//  def Controller: controllerInterface = Guice.createInjector(new UnoGameModule).getInstance(classOf[controllerInterface])
  //val fileIo: FileIO = Guice.createInjector(new UnoGameModule).getInstance(classOf[FileIO])

  // For Docker:
  //val gameDataServer = "http://gameData:8080/fileIO"
  val commandServer = "http://command:8081/command"

  // For local development
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
    implicit val system:ActorSystem[Any] = ActorSystem(Behaviors.empty, "my-system")
    val executionContext: ExecutionContextExecutor = system.executionContext
    given ExecutionContextExecutor = executionContext
    val response: Future[HttpResponse] = Http().singleRequest(HttpRequest(
      method = HttpMethods.POST,
      uri = commandServer + "/doStep",
      entity = doSetCommandJson(this)
      ))

    response.onComplete{
      case Failure(_) => sys.error("No Json")
      case Success(value) => {
        Unmarshal(value.entity).to[String].onComplete{
          case Failure(_) => sys.error("Unmarshaling failed")
          case Success(value) => {
            val json: JsValue = Json.parse(value)
                val playerList1 = (json \ "playerList1").as[String]
                val playerList2 = (json \ "playerList2").as[String]
                val stackCard = (json \ "stackCard").as[String]
                val player1Cards = parseCardList(playerList1)
                val player2Cards = parseCardList(playerList2)
                val player1 = Player("1", player1Cards)
                val player2 = Player("2", player2Cards)
                val players : List[Player] = List(player1, player2)
                
                val cardRegex: Regex = """Card = (\S+) \|\| (\S+)""".r
                val stackCards: List[Card] = cardRegex
                  .findAllMatchIn(stackCard)
                  .map(m => Card(m.group(1), m.group(2)))
                  .toList
                val newStack = Stack(stackCards)
                
                this.playerList = players
                this.stackCard = newStack
                publish(new updateStates)
          }
        }
      }
    }

  def parseCardList(cardListString: String): List[Card] =
    val cardRegex: Regex = """Card = (\S+) \|\| (\S+)""".r
    cardRegex.findAllMatchIn(cardListString).map { matchResult =>
      val value = matchResult.group(1)
      val color = matchResult.group(2)
      Card(value, color)
    }.toList

  def doSetCommandJson(controller: controllerInterface): String =
    Json.obj(
      "playerList1" -> controller.playerList(0).toString,
      "playerList2" -> controller.playerList(1).toString,
      "stackCard" -> controller.stackCard.toString,
      "command" -> "set",
      "handindex" -> 1.toString
      ).toString

  def removeCard(handindex: Int): Unit =
    stackCard = stackEmpty()
    implicit val system:ActorSystem[Any] = ActorSystem(Behaviors.empty, "my-system")
    val executionContext: ExecutionContextExecutor = system.executionContext
    given ExecutionContextExecutor = executionContext
    val response: Future[HttpResponse] = Http().singleRequest(HttpRequest(
      method = HttpMethods.POST,
      uri = commandServer + "/doStep",
      entity = doRemoveJson(this, handindex)
      ))
    response.onComplete{
      case Failure(_) => sys.error("No Json")
      case Success(value) => {
        Unmarshal(value.entity).to[String].onComplete{
          case Failure(_) => sys.error("Unmarshaling failed")
          case Success(value) => {
            val json: JsValue = Json.parse(value)
            val playerList1 = (json \ "playerList1").as[String]
            val playerList2 = (json \ "playerList2").as[String]
            val stackCard = (json \ "stackCard").as[String]
            val player1Cards = parseCardList(playerList1)
            val player2Cards = parseCardList(playerList2)
            val player1 = Player("1", player1Cards)
            val player2 = Player("2", player2Cards)
            val players : List[Player] = List(player1, player2)
            
            val cardRegex: Regex = """Card = (\S+) \|\| (\S+)""".r
            val stackCards: List[Card] = cardRegex
              .findAllMatchIn(stackCard)
              .map(m => Card(m.group(1), m.group(2)))
              .toList
            val newStack = Stack(stackCards)
            
            this.playerList = players
            this.stackCard = newStack
            publish(new updateStates)
          }
        }
      }
    }

    
    //old removeCard
    //stackCard = stackEmpty()
    //undoManager.doStep(new RemoveCommand(handindex:Int, this))
    //unoCall = false
    //publish(new updateStates)


  def doRemoveJson(controller: controllerInterface, handindex: Int): String =
    Json.obj(
      "playerList1" -> controller.playerList(0).toString,
      "playerList2" -> controller.playerList(1).toString,
      "stackCard" -> controller.stackCard.toString,
      "command" -> "remove",
      "handindex" -> handindex.toString
      ).toString

  def undoGet: Unit = undoRedoget(true)
  def redoGet: Unit = undoRedoget(false)

  def undoRedoget(value: Boolean): Unit =
    if(value == true)
      implicit val system:ActorSystem[Any] = ActorSystem(Behaviors.empty, "my-system")
      val executionContext: ExecutionContextExecutor = system.executionContext
      given ExecutionContextExecutor = executionContext
      val response: Future[HttpResponse] = Http().singleRequest(HttpRequest(
        method = HttpMethods.GET,
        uri = commandServer + "/undo",
        ))
      response.onComplete{
        case Failure(_) => sys.error("No Json")
        case Success(value) => {
          Unmarshal(value.entity).to[String].onComplete{
            case Failure(_) => sys.error("Unmarshaling failed")
            case Success(value) => {
              val json: JsValue = Json.parse(value)
              val playerList1 = (json \ "playerList1").as[String]
              val playerList2 = (json \ "playerList2").as[String]
              val stackCard = (json \ "stackCard").as[String]
              val player1Cards = parseCardList(playerList1)
              val player2Cards = parseCardList(playerList2)
              val player1 = Player("1", player1Cards)
              val player2 = Player("2", player2Cards)
              val players : List[Player] = List(player1, player2)
              
              val cardRegex: Regex = """Card = (\S+) \|\| (\S+)""".r
              val stackCards: List[Card] = cardRegex
                .findAllMatchIn(stackCard)
                .map(m => Card(m.group(1), m.group(2)))
                .toList
              val newStack = Stack(stackCards)
              
              this.playerList = players
              this.stackCard = newStack
              publish(new updateStates)
            }
          }
        }
      }

      // OLD UNDO
      //undoManager.undoStep()
      //publish(new updateStates)
    else
      implicit val system:ActorSystem[Any] = ActorSystem(Behaviors.empty, "my-system")
      val executionContext: ExecutionContextExecutor = system.executionContext
      given ExecutionContextExecutor = executionContext
      val response: Future[HttpResponse] = Http().singleRequest(HttpRequest(
        method = HttpMethods.GET,
        uri = commandServer + "/redo",
        ))
      response.onComplete{
        case Failure(_) => sys.error("No Json")
        case Success(value) => {
          Unmarshal(value.entity).to[String].onComplete{
            case Failure(_) => sys.error("Unmarshaling failed")
            case Success(value) => {
              val json: JsValue = Json.parse(value)
              val playerList1 = (json \ "playerList1").as[String]
              val playerList2 = (json \ "playerList2").as[String]
              val stackCard = (json \ "stackCard").as[String]
              val player1Cards = parseCardList(playerList1)
              val player2Cards = parseCardList(playerList2)
              val player1 = Player("1", player1Cards)
              val player2 = Player("2", player2Cards)
              val players : List[Player] = List(player1, player2)
              
              val cardRegex: Regex = """Card = (\S+) \|\| (\S+)""".r
              val stackCards: List[Card] = cardRegex
                .findAllMatchIn(stackCard)
                .map(m => Card(m.group(1), m.group(2)))
                .toList
              val newStack = Stack(stackCards)
              
              this.playerList = players
              this.stackCard = newStack
              publish(new updateStates)
            }
          }
        }
      }

      
      // OLD REDO
      //undoManager.redoStep()
      //publish(new updateStates)

  def save: Unit =
    implicit val system:ActorSystem[Any] = ActorSystem(Behaviors.empty, "my-system")
    val executionContext: ExecutionContextExecutor = system.executionContext
    given ExecutionContextExecutor = executionContext
    print(gameStateToJson())

    val response: Future[HttpResponse] = Http().singleRequest(HttpRequest(
      method = HttpMethods.POST,
      uri = gameDataServer + "/save",
      entity = gameStateToJson()
    ))

  def load: Unit =
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
                        val(playerliste, playstackonthefield) = lists
                        playerList = playerliste
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