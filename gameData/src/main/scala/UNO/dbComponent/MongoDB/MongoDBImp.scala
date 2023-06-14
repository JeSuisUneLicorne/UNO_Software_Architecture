package UNO.dbComponent.MongoDB

import scala.concurrent._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object MongoDBImp {
  def save(json: String): Unit =

    val createFuture = Future {
    MongoDBDOA.create
    }

    val createResult = Await.result(createFuture, 10.seconds)
    MongoDBDOA.update(json)
}