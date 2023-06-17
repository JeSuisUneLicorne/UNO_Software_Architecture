package UNO.fileIOComponent

import UNO.GameState

import scala.util.Try
import UNO.GameState
import UNO.PlayerComponent.playerBaseImp.Player
import UNO.cardComponent.cardBaseImp.Card
import UNO.cardComponent.cardBaseImp.Card

trait FileIOTrait:
  def load:Try[Option[(List[Player],List[Card])]] //def load: UNO.GameState

  def save(gameState:GameState): Unit