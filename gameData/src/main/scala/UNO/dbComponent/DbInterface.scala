package UNO.dbComponent

trait dbInterface:
  def loadGameState: String
  def saveGameState(Json:String): Unit