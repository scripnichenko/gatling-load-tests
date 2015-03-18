package bbc.sport

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import bbc.utils._

class LdpSport extends Simulation {

  val palUrls = csv("sport/pal-client.txt").random 
  val sportDataUrls = csv("sport/sport-data.txt").random

  val httpProtocol = http
    .baseURL(BaseUrls.url("data"))
    .acceptHeader("application/json-ld")
    .disableCaching

  val ldpSport = scenario("ldpSport")
    .feed(palUrls)
    .exec(http("Pal Client")
    .get("${palUrl}")
    .check(status.in(200, 201, 404)))
/*
    .exec(http("CPS Nav Builder")
    .get("/nav?api_key=aszYdyTIisgk9XEwAg9rlnSrjAlDhkWG")
    .check(status.in(200, 201, 404)))
    
    .feed(sportDataUrls)
    .exec(http("Sports Data") 
    .get("${sportDataUrl}")
    .header("Accept", "application/xml")
    .check(status.in(200, 201, 404)))
*/
  setUp(ldpSport.inject(
    rampUsersPerSec(10) to 100 during(10 seconds),
    constantUsersPerSec(100) during(30 minutes),
    rampUsersPerSec(100) to 250 during(5 minutes),
    constantUsersPerSec(250) during(60 minutes)
  ).protocols(httpProtocol))
}
