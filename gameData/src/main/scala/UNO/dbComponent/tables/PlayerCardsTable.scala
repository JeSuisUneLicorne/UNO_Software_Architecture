package UNO.dbComponent.tables

import slick.jdbc.PostgresProfile.api.* 

class PlayerCardsTable(tag: Tag) extends Table[(String,Int)](tag, "PlayerCards") {
  def name = column[String]("player",O.PrimaryKey)
  def value = column[Int]("value")
  override def * = (name, value)
}