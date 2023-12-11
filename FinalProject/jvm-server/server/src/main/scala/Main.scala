import cats.effect.IOApp
import cats.effect.ExitCode
import cats.effect.IO
import game.universegen.StarSystemManager
import http.HttpServer
import scala.util.Random
object Main extends IOApp {
  def run(args: List[String]): IO[ExitCode] = {

    ////////////////////////////////

    ServerRuntime.init

    println(StarSystemManager.LoadedStarSystems)
    StarSystemManager.LoadedStarSystems.map { st =>
      st.physicsObject.satellites.map { s =>
        println(s"\n\n\n\nSattlite: $s")
      }
    }

    HttpServer.server.allocated.flatMap(_ => IO.never)
  }
}
