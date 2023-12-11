// The simplest possible sbt build file is just one line:
ThisBuild / scalaVersion := "3.3.1"

name := "cave-game-dedicated-server"
ThisBuild / organization := "gaming.actually"
ThisBuild / version := "0.1.0-SNAPSHOT"

val http4sVersion = "0.23.24"
val circeVersion = "0.14.1"

lazy val server = (project in file("server"))
  .settings(
    libraryDependencies ++= Seq(
      "co.fs2" %% "fs2-core" % "3.6.1",
      "co.fs2" %% "fs2-io" % "3.6.1",
      "co.fs2" %% "fs2-scodec" % "3.6.1",
      "org.scodec" %% "scodec-core" % "2.1.0",
      "org.scodec" %% "scodec-bits" % "1.1.37",
      "org.http4s" %% "http4s-ember-server" % http4sVersion,
      "org.http4s" %% "http4s-dsl" % http4sVersion,
      "org.http4s" %% "http4s-circe" % http4sVersion,
      "io.circe" %% "circe-core" % circeVersion,
      "io.circe" %% "circe-generic" % circeVersion,
      "ch.qos.logback" % "logback-core" % "1.4.14",
      "ch.qos.logback" % "logback-classic" % "1.4.14"
    )
  )
