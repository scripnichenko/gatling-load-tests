name := "BBC Gatling Load Tests"

version := "0.0.1"

val test = project.in(file("."))
  .enablePlugins(GatlingPlugin)
    .settings(libraryDependencies += "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.0.3" % "test")
    .settings(libraryDependencies += "io.gatling" % "test-framework" % "1.0" % "test")

scalacOptions ++= Seq("-feature", "-language:postfixOps")

javaOptions in Gatling := "-Djsse.enableSNIExtension=false"


