package UNO.dbComponent.Slick

trait DbInterface:
  def load(): String
  def save(Json:String): Unit