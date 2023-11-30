// The simplest possible sbt build file is just one line:
ThisBuild / scalaVersion := "3.2.2"

name := "cave-game-dedicated-server"
ThisBuild / organization := "gaming.actually"
ThisBuild / version := "0.1.0-SNAPSHOT"

lazy val server = (project in file("server"))
  .settings(
    libraryDependencies ++= Seq(
      "co.fs2" %% "fs2-core" % "3.6.1",
      "co.fs2" %% "fs2-io" % "3.6.1",
      "co.fs2" %% "fs2-scodec" % "3.6.1",
      "org.scodec" %% "scodec-core" % "2.1.0",
      "org.scodec" %% "scodec-bits" % "1.1.37"
    )
  )
