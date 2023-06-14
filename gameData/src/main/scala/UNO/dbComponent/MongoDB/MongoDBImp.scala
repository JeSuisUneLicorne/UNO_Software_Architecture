package UNO.dbComponent.MongoDB

import scala.concurrent._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import UNO.dbComponent.Slick.DbInterface

object MongoDBImp:
  def save(json: String): Unit =

    //MongoDBDOA.delete()

    val createFuture = Future {
    MongoDBDOA.create
    }

    val createResult = Await.result(createFuture, Duration.Inf)
    Thread.sleep(300)
    MongoDBDOA.update(json)

  def load(): String =
    MongoDBDOA.read
  