package UNO.controllerComponent

package controllerBaseImp

import com.google.inject.{Guice, Inject}
import net.codingwell.scalaguice.InjectorExtensions.ScalaInjector
import scala.swing.Publisher
import scala.util.{Success, Failure}

import UNO.GameState
import UNO.UnoGameModule
import UNO.controllerComponent.*
import UNO.PlayerComponent.playerBaseImp.Player
import UNO.cardComponent.cardBaseImp.Card
import UNO.stackComponent.stackBaseImp.Stack
import UNO.util.UndoManager
import UNO.fileIOComponent.FileIOTrait
import UNO.fileIOComponent.fileIOJsonImp.FileIO
import UNO.controllerComponent.GameStatus._

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
  def Controller = Guice.createInjector(new UnoGameModule).getInstance(classOf[controllerInterface])
  val fileIo: FileIO = Guice.createInjector(new UnoGameModule).getInstance(classOf[FileIO])


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

  override def save: Unit =
    fileIo.save(GameState(playerList, playStack2))

  // override def load: Unit =
  //   gameState = fileIo.load
  //   playerList = gameState.playerList
  //   stackCard = gameState.getstackCard()
  //   playStack2 = gameState.playStack
  //   publish(new updateStates)

  override def load:String=
    val gameState = fileIo.load
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
    publish(new updateStates)
    "load"