package scala.util

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import UNO.UnoGame.Controller
import UNO.util.{callFirstUnoEvent, callSecondUnoEvent, exitGameEvent, forgotCallUnoEvent, gameStatsEvent, instructionEvent, removeFalseCardEvent, removePlayerCardEvent, setPlayerCardEvent, toManyCardsEvent}

class EventSpec extends AnyWordSpec with Matchers {
  "Event" should {
    "instructionEvent" in {
      instructionEvent should be
      ("\nPossible instructions:\n" +
        "\tq = Quit\n" +
        "\ts = Take a new Card from Stack\n" +
        "\tr = Put a Card from Hand into GameBoard\n" +
        "\tu = Call UNO/ UNO-UNO\n" +
        "\n" + "_" * 50 + "\n")
    }

    "gameStartEvent" in {
      gameStatsEvent() should be
      ("\n" + "_" * 50 + "\n" +
        "\nUNO! " + "\n" +
        "\n" + "_" * 50 + "\n")
    }

    //TODO: fix Event Tests

    /*"gameStatsEvent" in {
      gameStatsEvent should be
      ("\n" + "_" * 50 + "\nPLAYER " + Controller.playerList(0).name.toUpperCase() +
        "\n\nHandcards: \t" + Controller.playerList(0).playerCards +
        "\n\n\nPlayStack: \t" + Controller.playStack2(0) + "\n" +
        "\nStackCard: \t" + Controller.stackCard + "\n\n")
    }*/
    "exitGameEvent" in {
      exitGameEvent should be
      ("\nGame exit\n")
    }

    "removeFalseCardEvent" in {
      removeFalseCardEvent should contain  //be
      ("\nWrong Card!\n")
    }

    /*"setPlayerCardEvent" in {
      setPlayerCardEvent should be
      ("\n--Handcards:\t" + Controller.playerList(1).playerCards)
    }*/

    "removePlayerCardEvent" in {
      removePlayerCardEvent(0) should contain //be
      ("\n\n--Handcards:\t" + Controller.playerList(1).playerCards + "\n")
    }

    "callFirstUnoEvent" in {
      val index = 0
      callFirstUnoEvent(index) should contain //be
      ("UNO\n" +
        removePlayerCardEvent(index) + "\n")
    }

    "callSecondUno" in {
      callSecondUnoEvent should contain //be
      ("\nUNO - UNO!\n" +
        "Player " + Controller.playerList(0).name.toUpperCase() + ":WON\n")
    }

    "toManyCardsEvent" in {
      toManyCardsEvent should contain //be
      ("\nTo many Cards\n" +
        "\n--Handcards:\t" + Controller.playerList(1).playerCards + "\n")
    }

    "forgotCallUnoEvent" in {
      forgotCallUnoEvent should contain //be
      ("\nYou have forgot to Call UNO\n" +
        "\n--Handcards:\t" + Controller.playerList(1).playerCards + "\n")
    }
  }
}