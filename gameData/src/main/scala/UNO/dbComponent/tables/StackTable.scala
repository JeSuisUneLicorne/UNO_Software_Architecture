package UNO.dbComponent.tables

import slick.jdbc.PostgresProfile.api.* 

class StackTable(tag: Tag) extends Table[(String, String)](tag, "Stack") {
  def stackValue = column[String]("stack_value")
  def stackColor = column[String]("stack_color")
  override def * = (stackValue, stackColor)
}