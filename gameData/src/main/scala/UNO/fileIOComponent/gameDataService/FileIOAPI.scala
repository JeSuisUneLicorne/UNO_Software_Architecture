package UNO.fileIOComponent
package gameDataService

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import scala.io.StdIn
import scala.concurrent.ExecutionContextExecutor
import com.google.inject.Guice
import scala.util.Success
import scala.util.Failure
import UNO.dbComponent.DbImp

object fileIOAPI {

    implicit val system: ActorSystem[Any] = ActorSystem(Behaviors.empty, "my-system")
    // needed for the future flatMap/onComplete in the end
    val executionContext: ExecutionContextExecutor = system.executionContext
    given ExecutionContextExecutor = executionContext
    
    val routes: String = 
      """
      FileIO-REST-Service
      Available routes:
      GET:  /fileIO/load
      POST: /fileIO/save
      """

    val route = concat (
      pathSingleSlash {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, routes))
      },
      path("fileIO" / "load") {
        get {
          complete(HttpEntity(ContentTypes.`application/json`, fileIOJsonImp.load()))
        }
      },
      path("fileIO" / "save") {
        post {
          entity(as[String]) { game =>
            fileIOJsonImp.save(game)
            complete("game saved")
          }
        }
      }
    )

    val bindingFuture = Http().newServerAt("0.0.0.0", 8080).bind(route)

    bindingFuture.onComplete{
      case Success(value) => {
        println(s"Server now online. Please navigate to http://localhost:8080/fileIO\nPress RETURN to stop...")
        StdIn.readLine() // let it run until user presses return
        bindingFuture
          .flatMap(_.unbind()) // trigger unbinding from the port
          .onComplete(_ => system.terminate()) // and shutdown when done
      }
      case Failure(exception) => {
        println("fileIO-Rest-Server could not be started! Error: " + exception + "\n")
      }
    }
}