package UNO.dbComponent.Slick

trait DOAInterface:
  def create: Unit
  def read: String
  def update(input:String): Unit
  def delete: Unit