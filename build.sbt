val scalaVersion3 = "3.2.0"
val AkkaVersion = "2.7.0"
val AkkaHttpVersion = "10.5.1"

lazy val commonSettings = Seq(
  scalaVersion := scalaVersion3,
  name := "UNO",
  version := "0.1.0-SNAPSHOT",
  libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.15",
  libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.15" % "test",
  libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "3.0.0",
  libraryDependencies += ("net.codingwell" %% "scala-guice" % "5.1.1").cross(CrossVersion.for3Use2_13),
  libraryDependencies += "com.typesafe.play" %% "play-json" % "2.10.0-RC7",
  libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.1.0",
  libraryDependencies += "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  libraryDependencies += "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  libraryDependencies += "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
  libraryDependencies += "org.slf4j" % "slf4j-log4j12" % "1.7.32" exclude("org.slf4j", "slf4j-log4j12")
)

lazy val root = project
  .in(file("."))
  .settings(
    name := "UNO",
    version := "0.1.0-SNAPSHOT",
    commonSettings
  )

lazy val gameData = (project in file("gameData"))
  .settings(
    name := "gameData",
    version := "0.1.0-SNAPSHOT",
    commonSettings
  )

lazy val command = (project in file("command"))
  .settings(
    name := "command",
    version := "0.1.0-SNAPSHOT",
    commonSettings
  )

//lazy val model = (project in file("model"))
//  .settings(
//    name := "model",
//    version := "0.1.0-SNAPSHOT",
//    commonSettings
//  )

//lazy val controllerService = (project in file("controllerService"))
//  .dependsOn(model, gameData)
//  .aggregate(model, gameData)
//  .settings(
//    name := "controllerService",
//    version := "0.1.0-SNAPSHOT",
//    commonSettings
//  )

