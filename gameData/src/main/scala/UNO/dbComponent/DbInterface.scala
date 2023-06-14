package UNO.dbComponent

trait DbInterface:
  def load(): String
  def save(Json:String): Unit