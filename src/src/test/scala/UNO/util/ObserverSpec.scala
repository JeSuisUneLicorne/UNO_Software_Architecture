package scala.util

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import UNO.util._

class ObservableSpec extends AnyWordSpec with Matchers {
  "An Observable" should {
    val observable = new Observable
    val observer = new Observer {
      var updated: Boolean = false
      def isUpdated: Boolean = updated
      override def update: Boolean = {updated = true; updated}
    }
    
    "add an Observer" in {
      observable.add(observer) //ohne observable. gab es einen bug - TODO: recheck
      observable.subscribers should contain (observer) //ohne observable. gab es einen bug - TODO: recheck
    }
    
    "notify an Observer" in {
      observer.update should be(true)  //observer.isUpdated should be(false)
      observable.notifyObservers()
      observer.update should be(true) //observer.isUpdated should be(true)
    }

    "remove an Observer" in {
      observable.remove(observer) //ohne observable. gab es einen bug - TODO: recheck
      observable.subscribers should not contain (observer) //ohne observable. gab es einen bug - TODO: recheck
    }
  }
}
