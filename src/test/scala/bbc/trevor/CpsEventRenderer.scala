package bbc.trevor

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class CpsEventRenderer extends Simulation {

    val httpProtocol = http
        .baseURL("https://newsapps-trevor-dev-utils.int.cloud.bbc.co.uk")

    val renderer = csv("trevor/cps-event-renderer.csv").circular

    val scn = scenario("CPS Event Renderer")
        .feed(renderer)
        .exec(http("CPS Event Renderer")
        .get("${content}")
        .check(status.is(200))
    ) 

    setUp(scn.inject(
        rampUsersPerSec(10) to(250) during(2 minutes),
        constantUsersPerSec(250) during(18 minutes)
    ).protocols(httpProtocol))

}
