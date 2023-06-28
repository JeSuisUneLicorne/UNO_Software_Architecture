package gatling

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class LoadTest_Multi_Save_300 extends Simulation {

  private val httpProtocol = http
    .baseUrl("http://0.0.0.0:8080")
    .inferHtmlResources()
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("de,en-US;q=0.7,en;q=0.3")
    .upgradeInsecureRequestsHeader("1")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/113.0")


  private val scn = scenario("Game Scenario: Multi Save with 300 Users")
    .exec(
      http("save")
        .post("/fileIO/save")
    )
    .pause(2.seconds)
    .exec(
      http("save")
        .post("/fileIO/save")
    )

  setUp(scn.inject(atOnceUsers(300))).protocols(httpProtocol)
}