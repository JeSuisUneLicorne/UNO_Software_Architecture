package UNO.dbComponent.Slick.tables

import slick.jdbc.PostgresProfile.api.* 

class PlayerCardsTable(tag: Tag) extends Table[(String, String, String)](tag, "PlayerCards") {
  def playerName = column[String]("player_name")
  def cardValue = column[String]("card_value")
  def cardColor = column[String]("card_color")
  override def * = (playerName, cardValue, cardColor)
}