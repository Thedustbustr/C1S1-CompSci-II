import cats.effect.ExitCode
import cats.effect.IO
import cats.effect.IOApp
import game.universegen.StarSystemManager
import http.HttpServer

import scala.util.Random
object Main extends IOApp {
  def run(args: List[String]): IO[ExitCode] = {
    println("Starting Server...")
    println("Generating Data...")
    ServerRuntime.init
    println("Data Generated")

    // println(StarSystemManager.LoadedStarSystems)
    // StarSystemManager.LoadedStarSystems.map { st =>
    //   st.satellites.map { s =>
    //     println(s"\n\n\n\nSattlite: $s")
    //   }
    // }

    for {
      _ <- HttpServer.server.allocated
      _ <- IO.println("Server Started!\n\n=========================\nListening on: http://localhost:8080\n=========================\n\nUse ctrl+c to exit")
      _ <- IO.never
    } yield ExitCode.Success
  }
}
