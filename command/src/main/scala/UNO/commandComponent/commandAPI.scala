package command
package commandComponent

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import scala.io.StdIn
import scala.concurrent.ExecutionContextExecutor
import com.google.inject.Guice
import play.api.libs.json.JsValue
import scala.util.Success
import scala.util.Failure

object commandAPI {

    // needed to run the route
    implicit val system:ActorSystem[Any] = ActorSystem(Behaviors.empty, "my-system")
    // needed for the future flatMap/onComplete in the end
    val executionContext: ExecutionContextExecutor = system.executionContext
    given ExecutionContextExecutor = executionContext

    val routes =
      """
      Command-REST-Service
      Available routes:
      POST: /command/doStep
      GET: /command/undo
      GET: /command/redo
      """

    val route = concat (
      pathSingleSlash {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, routes))
      },
      path("command" / "doStep") {
        post {
          entity(as[String]) { command =>
            complete(HttpEntity(ContentTypes.`application/json`, UndoManager.doStep(command)))
          }
        }
      },
      path("command" / "undo") {
        get {
          complete(HttpEntity(ContentTypes.`application/json`, UndoManager.undoStep()))
          }
        },
      path("command" / "redo") {
        get {
          complete(HttpEntity(ContentTypes.`application/json`, UndoManager.redoStep()))
        }
      }
    )

    val bindingFuture = Http().newServerAt("0.0.0.0", 8081).bind(route)

    bindingFuture.onComplete{
      case Success(value) => {
        println(s"Server now online. Please navigate to http://localhost:8081/command\nPress RETURN to stop...")
        StdIn.readLine() // let it run until user presses return
        bindingFuture
          .flatMap(_.unbind()) // trigger unbinding from the port
          .onComplete(_ => system.terminate()) // and shutdown when done
      }
      case Failure(exception) => {
        println("Command-Rest-Server could not be started! Error: " + exception + "\n")
      }
    }
}

