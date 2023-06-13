package UNO.dbComponent

trait DbInterface:
  def loadGameState: String
  def save(Json:String): Unit