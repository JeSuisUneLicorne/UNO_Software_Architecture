package UNO.model.fileIOComponent

import scala.util.Try

import UNO.model.GameState
import UNO.model.PlayerComponent.playerBaseImp.Player
import UNO.model.cardComponent.cardBaseImp.Card

trait FileIOTrait:
  def load:Try[Option[(List[Player],List[Card])]] //def load: GameState

  def save(gameState:GameState): Unit
