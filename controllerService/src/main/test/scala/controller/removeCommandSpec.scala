

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import com.google.inject.Guice

import UNO.UnoGame.Controller
import UNO.UnoGameModule
import UNO.controller.controllerComponent.controllerBaseImp.RemoveCommand
import UNO.controller.controllerComponent.controllerInterface
import UNO.model.cardComponent.cardBaseImp.Card
import UNO.model.stackComponent.stackBaseImp.Stack
import UNO.util.UndoManager

class RemoveCommandSpec extends AnyWordSpec with Matchers {
  val undoManager = new UndoManager
  val controller = Guice.createInjector(new UnoGameModule).getInstance(classOf[controllerInterface])

  controller.playStack2 = List(Card("",""))
  "RemoveCommand" should {
    "doStep" in {
      undoManager.doStep(new RemoveCommand(0, controller))
      controller.playStack2.size should be(2)
    }
    "undoStep" in {
      undoManager.undoStep()
      controller.playStack2.size should be(1)
    }
    "redoStep" in {
      undoManager.redoStep()
      controller.playStack2.size should be(2)
    }

  }
}