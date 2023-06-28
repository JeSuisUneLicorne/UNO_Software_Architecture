package gatling

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class EnduranceTest extends Simulation {

  private val httpProtocol = http
    .baseUrl("http://0.0.0.0:8080")
    .inferHtmlResources()
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("de,en-US;q=0.7,en;q=0.3")
    .upgradeInsecureRequestsHeader("1")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/113.0")

  private val headers = Map(
    "Cache-Control" -> "no-cache",
    "Content-Type" -> "application/json",
    "Proxy-Connection" -> "keep-alive"
  )
  
  private val scn = scenario("EnduranceTest").repeat(300, "i") {
    exec(
      http("save")
        .post("/fileIO/save")
        .headers(headers)
    )
    .pause(1.seconds)
    .exec(
      http("load")
        .get("/fileIO/load")
        .headers(headers)
    )
  }
  setUp(scn.inject(atOnceUsers(100))).protocols(httpProtocol)
}
