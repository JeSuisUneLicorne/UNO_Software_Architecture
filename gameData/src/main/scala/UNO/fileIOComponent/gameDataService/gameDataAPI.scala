package UNO.fileIOComponent
package gameDataService

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import scala.io.StdIn


//import UNO.fileIOComponent.fileIOJsonImp.FileIO
import scala.concurrent.ExecutionContextExecutor

object gameDataAPI {

    implicit val system: ActorSystem[Any] = ActorSystem(Behaviors.empty, "my-system")
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext: ExecutionContextExecutor = system.executionContext
    given ExecutionContextExecutor = executionContext

    val routes: String = "test"

    val route = concat (
      pathSingleSlash {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, routes))
      },
      path("fileIO" / "load") {
        get {
          complete(HttpEntity(ContentTypes.`application/json`, gameDataController.load()))
        }
      },
      path("fileIO" / "save") {
        post {
          entity(as[String]) { game =>
            gameDataController.save(game)
            complete("game saved")
          }
        }
      }
    )

    val bindingFuture = Http().newServerAt("localhost", 8081).bind(route)

    println(s"Server now online. Please navigate to http://localhost:8080/hello\nPress RETURN to stop...")
    //StdIn.readLine() // let it run until user presses return
    //bindingFuture
    //  .flatMap(_.unbind()) // trigger unbinding from the port
    //  .onComplete(_ => system.terminate()) // and shutdown when done
}


