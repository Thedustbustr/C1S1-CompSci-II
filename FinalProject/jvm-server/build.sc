import mill._, scalalib._

val http4sVersion = "0.23.24"
val circeVersion = "0.14.6"

object server extends ScalaModule {
  def scalaVersion = "3.3.1"
  def ivyDeps = Agg(
      ivy"co.fs2::fs2-core:3.6.1",
      ivy"co.fs2::fs2-io:3.6.1",
      ivy"co.fs2::fs2-scodec:3.6.1",
      ivy"org.scodec::scodec-core:2.1.0",
      ivy"org.scodec::scodec-bits:1.1.37",
      ivy"org.http4s::http4s-ember-server:$http4sVersion",
      ivy"org.http4s::http4s-dsl:$http4sVersion",
      ivy"org.http4s::http4s-circe:$http4sVersion",
      ivy"io.circe::circe-core:$circeVersion",
      ivy"io.circe::circe-generic:$circeVersion",
      ivy"ch.qos.logback:logback-core:1.4.14",
      ivy"ch.qos.logback:logback-classic:1.4.14"
  )
}
