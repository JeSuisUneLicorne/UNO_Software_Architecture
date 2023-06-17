package UNO.dbComponent.Slick.DOA

import slick.jdbc.PostgresProfile.api.*
import slick.jdbc.JdbcBackend.Database
import slick.lifted.TableQuery
import slick.jdbc.PostgresProfile.api.*

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.{Duration, DurationInt}
import scala.concurrent.{Await, Future}
import scala.io.StdIn
import scala.util.{Failure, Success, Try}
import UNO.dbComponent.Slick.tables.PlayerTable
import UNO.dbComponent.Slick.tables.PlayerCardsTable

object PlayerCardsDOA:
  val connectIP = sys.env.getOrElse("POSTGRES_IP", "localhost").toString
  val connectPort = sys.env.getOrElse("POSTGRES_PORT", 5433).toString.toInt
  val database_user = sys.env.getOrElse("POSTGRES_USER", "postgres").toString
  val database_pw = sys.env.getOrElse("POSTGRES_PASSWORD", "postgres").toString
  val database_name = sys.env.getOrElse("POSTGRES_DB", "postgres").toString

    val database =
    Database.forURL(
      url = "jdbc:postgresql://" + connectIP + ":" + connectPort + "/" + database_name + "?serverTimezone=UTC",
      user = database_user,
      password = database_pw,
      driver = "org.postgresql.Driver")

  val playerCardsTable = TableQuery(new PlayerCardsTable(_))

  def create: Unit =
    /* Drop Table Schema, if it exists
    
    val dropAction =  playerCardsTable.schema.dropIfExists
    val resultFuture = database.run(dropAction)
    resultFuture.onComplete {
      case Success(_) => println("PlayerCards table deleted successfully!")
      case Failure(e) => println("Error during table deletion: " + e)
    }
    */

    
    val running = Future(Await.result(database.run(DBIO.seq(
      playerCardsTable.schema.createIfNotExists,
    )), Duration.Inf))
    running.onComplete{
      case Success(_) => println("Connection to DB & Creation of PlayerCardsTable successful!")
      case Failure(e) => println("Error: " + e)
    }

  def update(name: String, value: String, color: String): Unit =
    playerCardsTable.schema.createIfNotExists
    val insertAction = playerCardsTable returning playerCardsTable.map(_.playerName)
    += (name, value, color)
    val insertResult = database.run(insertAction)
    insertResult.onComplete {
      case Success(_) =>
        // Fetch the updated data from the database
        val queryAction = playerCardsTable.filter(_.playerName === name).result
        val queryResult = database.run(queryAction)
        queryResult.onComplete {
          case Success(data) => println("Updated data: " + data)
          case Failure(e) => println("Error while querying updated data: " + e)
        }
      case Failure(e) => println("Error: " + e)
    }

  def read(playerName: String): Future[Seq[(String, String)]] = {
    val query = playerCardsTable
      .filter(_.playerName === playerName)
      .map(card => (card.cardValue, card.cardColor))
      .result

    database.run(query)
  }

  def delete: Unit =
    val deleteAction = playerCardsTable.delete
    val resultFuture = database.run(deleteAction)

    resultFuture.onComplete {
      case Success(numRowsDeleted) => println(s"Deleted ${numRowsDeleted} rows from Table PlayerCards")
      case Failure(u) => println(s"Error during delete: ${u}")
    }