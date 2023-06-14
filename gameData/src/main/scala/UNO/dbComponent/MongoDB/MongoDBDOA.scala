package UNO.dbComponent.MongoDB

import play.api.libs.json.{JsArray, JsValue, Json}
import org.mongodb.scala.*
import org.mongodb.scala.model.Updates.set
import org.mongodb.scala.model.Filters.*
import org.mongodb.scala.result.{DeleteResult, InsertOneResult, UpdateResult}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success, Try}
import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration

object MongoDBDOA {

  //val database_pw = sys.env.getOrElse("MONGO_INITDB_ROOT_PASSWORD", "mongo").toString
  //val database_username = sys.env.getOrElse("MONGO_INITDB_ROOT_USERNAME", "root").toString
  val uri: String = s"mongodb://localhost:27017/?authSource=admin"
  val client: MongoClient = MongoClient(uri)
  val db: MongoDatabase = client.getDatabase("uno")

  val gameCollection: MongoCollection[Document] = db.getCollection("game")

  def create: Unit =
    val gameDoc: Document = Document("_id " -> "gameDoc", "game" -> "")
    print("\nCreate MongoDBDOA")
    observerInsertion(gameCollection.insertOne(gameDoc))

  def update(input: String) =
    println("\nUpdate MongoDBDOA" + input)
    observerUpdate(gameCollection.updateOne(equal("_id", "gameDocument"), set("game", input)))


  private def observerInsertion(insertObservable: SingleObservable[InsertOneResult]): Unit =
    insertObservable.subscribe(new Observer[InsertOneResult] {
      override def onNext(result: InsertOneResult): Unit = println(s"inserted: $result")
      
      override def onError(e: Throwable): Unit = println(s"insert onError: $e")
    
      override def onComplete(): Unit = println("completed insert")
    })

}
