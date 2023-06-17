val ScalaVersion3 = "3.2.0"
val AkkaVersion = "2.7.0"
val AkkaHttpVersion = "10.5.1"

lazy val command = (project in file("."))
  .settings(
    name := "command",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := ScalaVersion3,
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
      "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
      "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
      "org.scala-lang.modules" %% "scala-swing" % "3.0.0",
      "io.spray" %% "spray-json" % "1.3.6",
      "org.slf4j" % "slf4j-log4j12" % "1.7.32" exclude("org.slf4j", "slf4j-log4j12"),
      "com.google.inject" % "guice" % "5.1.0",
      ("net.codingwell" %% "scala-guice" % "5.1.1").cross(CrossVersion.for3Use2_13),
      "com.typesafe.play" %% "play-json" % "2.10.0-RC7",
    )
  )
