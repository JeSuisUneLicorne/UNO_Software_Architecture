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


object PlayerDOA:
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

  val playerTable = TableQuery(new PlayerTable(_))

  def create: Unit =
    //database.run(fieldTable.schema.create)
    val running = Future(Await.result(database.run(DBIO.seq(
      playerTable.schema.createIfNotExists,
    )), Duration.Inf))
    running.onComplete{
      case Success(_) => println("Connection to DB & Creation of PlayerTable successful!")
      case Failure(e) => println("Error: " + e)
    }
  
  def update(name: String): Unit =
    playerTable.schema.createIfNotExists
    val insertAction = playerTable returning playerTable.map(_.name)
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

  def read: Future[Seq[String]] =
    val queryAction = playerTable.result
    database.run(queryAction)

  def delete: Unit =
    val deleteAction = playerTable.delete
    val resultFuture = database.run(deleteAction)

    resultFuture.onComplete {
      case Success(numRowsDeleted) => println(s"Deleted ${numRowsDeleted} rows from Table Player")
      case Failure(u) => println(s"Error during delete: ${u}")
    }
