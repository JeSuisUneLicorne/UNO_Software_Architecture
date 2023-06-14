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
import UNO.dbComponent.tables.StackTable

object StackDOA:
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

  val stackTable = TableQuery(new StackTable(_))

  def create: Unit =
    /*
    val dropAction =  stackTable.schema.dropIfExists
    val resultFuture = database.run(dropAction)
    resultFuture.onComplete {
      case Success(_) => println("PlayerCards table deleted successfully!")
      case Failure(e) => println("Error during table deletion: " + e)
    }
    */
    val running = Future(Await.result(database.run(DBIO.seq(
      stackTable.schema.createIfNotExists,
    )), Duration.Inf))
    running.onComplete{
      case Success(_) => println("Connection to DB & Creation of StackTable successful!")
      case Failure(e) => println("Error: " + e)
    }

  def update(value: String, color: String): Unit =
    stackTable.schema.createIfNotExists
    val insertAction = stackTable returning stackTable.map(_.value)
    += (value, color)
    val insertResult = database.run(insertAction)

    insertResult.onComplete {
      case Success(_) =>
        // Fetch the updated data from the database
        val queryAction = stackTable.filter(_.stackValue === value).result
        val queryResult = database.run(queryAction)
        queryResult.onComplete {
          case Success(data) => println("Updated data: " + data)
          case Failure(e) => println("Error while querying updated data: " + e)
        }
      case Failure(e) => println("Error: " + e)
    }

  def read: Future[Seq[(String, String)]] =
    val queryAction = stackTable.result
    database.run(queryAction)

  def delete: Unit =
    val deleteAction = stackTable.delete
    val resultFuture = database.run(deleteAction)

    resultFuture.onComplete {
      case Success(numRowsDeleted) => println(s"Deleted ${numRowsDeleted} rows from Table Stack")
      case Failure(u) => println(s"Error during delete: ${u}")
    }
