package UNO.dbComponent.DOA

import slick.jdbc.PostgresProfile.api.*
import slick.jdbc.JdbcBackend.Database
import slick.lifted.TableQuery
import slick.jdbc.PostgresProfile.api.*

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.{Duration, DurationInt}
import scala.concurrent.{Await, Future}
import scala.io.StdIn
import scala.util.{Failure, Success, Try}
import UNO.dbComponent.tables.PlayerTable
import UNO.dbComponent.tables.PlayerCardsTable

object PlayerCardsDOA:
  val connectIP = sys.env.getOrElse("POSTGRES_IP", "localhost").toString
  val connectPort = sys.env.getOrElse("POSTGRES_PORT", 5432).toString.toInt
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
    //database.run(fieldTable.schema.create)
    val running = Future(Await.result(database.run(DBIO.seq(
      playerCardsTable.schema.createIfNotExists,
    )), Duration.Inf))
    running.onComplete{
      case Success(_) => println("Connection to DB & Creation of PlayerCardsTable successful!")
      case Failure(e) => println("Error: " + e)
    }
  
  def update(name: String): Unit =
    playerCardsTable.schema.createIfNotExists
    val insertAction = playerCardsTable returning playerCardsTable.map(_.name)
    += (name)
    val insertResult = database.run(insertAction)
    insertResult.onComplete {
      case Success(_) =>
        // Fetch the updated data from the database
        val queryAction = playerTable.filter(_.name === name).result
        val queryResult = database.run(queryAction)
        queryResult.onComplete {
          case Success(data) => println("Updated data: " + data)
          case Failure(e) => println("Error while querying updated data: " + e)
        }
      case Failure(e) => println("Error: " + e)
    }

  def read: String =
    ""
  
  def delete: Unit =
    val deleteAction = playerCardsTable.delete
    val resultFuture = database.run(deleteAction)

    resultFuture.onComplete {
      case Success(numRowsDeleted) => println(s"Deleted ${numRowsDeleted} rows from Table PlayerCards")
      case Failure(u) => println(s"Error during delete: ${u}")
    }