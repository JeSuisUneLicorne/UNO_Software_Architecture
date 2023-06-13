package UNO.dbComponent.tables

import slick.jdbc.PostgresProfile.api.* 

class PlayerCardsTable(tag: Tag) extends Table[(String, String, String)](tag, "PlayerCards") {
  def playerName = column[String]("player_name")
  def cardValue = column[String]("card_value")
  def cardColor = column[String]("card_color")
  override def * = (playerName, cardValue, cardColor)
}


/*
class PlayerCardsTable(tag: Tag) extends Table[(String,Int)](tag, "PlayerCards") {
  def id = column[Int]("id", O.PrimaryKey)
  def name = column[String]("player")
  def value = column[Int]("value")

  val playerTable = TableQuery[PlayerTable]

  def player = foreignKey("fk_playercards_player", name, playerTable)(_.name, onDelete = ForeignKeyAction.Cascade)

  override def * = (name, value)
}

*/