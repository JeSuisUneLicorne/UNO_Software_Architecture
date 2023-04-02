name := "UNO"
version := "1"

scalaVersion := "3.2.2"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.15"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.15" % "test"

libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "3.0.0"

libraryDependencies += "com.google.inject" % "guice" % "5.1.0"

//libraryDependencies += "net.codingwell" %% "scala-guice" % "5.1.1"

libraryDependencies += ("net.codingwell" %% "scala-guice" % "5.1.1").cross(CrossVersion.for3Use2_13)

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.10.0-RC7"

libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.1.0"
