package http

import cats.data.OptionT
import cats.effect._
import cats.syntax.all._
import com.comcast.ip4s.ipv4
import com.comcast.ip4s.port
import game.physics.*
import game.universegen.StarSystemManager
import http.json.*
import io.circe.*
import io.circe.syntax.*
import org.http4s._
import org.http4s.circe.*
import org.http4s.dsl.io._
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.implicits._
import org.http4s.server.middleware.ErrorAction
import org.http4s.server.middleware.ErrorHandling

import java.util.UUID

object HttpServer {

  implicit val uuidEncoder: Encoder[UUID] = Encoder.encodeString.contramap(_.toString())

  val routes = HttpRoutes.of[IO] {
    case GET -> Root / "stars"                                                         => showAllStars
    case GET -> Root / "stars" / UUIDVar(starId)                                       => showStar(starId)
    case GET -> Root / "stars" / UUIDVar(starId) / "satellites"                        => showAllSatellitesOfStar(starId)
    case GET -> Root / "stars" / UUIDVar(starId) / "satellites" / UUIDVar(satelliteId) => showSatellite(starId, satelliteId)
    case GET -> Root => Ok("hi")
    case _ => IO(Response(Status.NotFound))
  }

  val withErrorLogging = ErrorHandling.Recover.total(
    ErrorAction.log(
      routes,
      messageFailureLogAction = (t, msg) =>
        OptionT.liftF {
          IO.println(msg) >>
            IO.println(t)
        },
      serviceErrorLogAction = (t, msg) =>
        OptionT.liftF {
          IO.println(msg) >>
            IO.println(t)
        }
    )
  )

  val getRoot = Request[IO](Method.GET, uri"/")
  val serviceIO = routes.orNotFound

  val server = EmberServerBuilder
    .default[IO]
    .withHost(ipv4"0.0.0.0")
    .withPort(port"8080")
    .withHttpApp(withErrorLogging.orNotFound)
    .build

  private def showAllStars: IO[Response[IO]] = {
    Ok(StarSystemManager.LoadedStarSystems.map(toJsonStar).asJson)
  }

  private def showStar(id: UUID): IO[Response[IO]] = {
    Ok(StarSystemManager.LoadedStarSystems.find(_.networkID == id).map(toJsonSatellite).asJson)
  }

  private def showAllSatellitesOfStar(id: UUID): IO[Response[IO]] = {
    Ok(StarSystemManager.LoadedStarSystems.find(_.networkID == id).map(_.satellites.map(toJsonSatellite)).asJson)
  }

  private def showSatellite(starId: UUID, satelliteId: UUID): IO[Response[IO]] = {
    val found =
      for {
        star <- StarSystemManager.LoadedStarSystems.find(_.networkID == starId)
        sat <- star.satellites.toList.map(toJsonSatellite).find { p =>
          p match {
            case p: JsonPlanet => (p.networkID == satelliteId)
            case s: JsonStar   => (s.networkID == satelliteId)
          }
        }
      } yield Ok(sat.asJson)

    found.getOrElse(NotFound())
  }
}
