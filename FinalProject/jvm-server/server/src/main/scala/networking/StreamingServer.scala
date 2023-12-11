package networking

import cats.effect.IOApp
import cats.effect.IO
import cats.effect.Concurrent
import fs2.io.net.Network
import cats.effect.ExitCode
import com.comcast.ip4s._
import networking.codecs.eventStreamDecoder
import fs2.interop.scodec.StreamDecoder
import networking.events.Event

object Main extends IOApp {
  val Port = port"5000"

  def run(args: List[String]): IO[ExitCode] = {
    Console.println("Awaiting Connections")

    def incoming: IO[Unit] = {
      Network[IO]
        .server(port = Some(Port), address = Some(host"0.0.0.0"))
        .map { client =>
          client.reads
            //.debug("BYTEME " + _.toString, Console.out.println)
            .through(eventStreamDecoder.toPipeByte)
             .debug(_.toString(), Console.out.println)
            .handleErrorWith { err =>
              err.printStackTrace(Console.err); fs2.Stream.empty
            } // handle errors of client sockets
            .drain
        }
        .parJoin(100)
        .compile
        .drain
    } 

    // def outgoing: IO[Unit] = {
    //   Network[IO]
    //     .server(port = Some(Port), address = Some(host"0.0.0.0"))
    //     .map { client =>
    //       client.write()
    //         // .debug("BYTEME " + _.toString, Console.out.println)
    //         .through(eventStreamDecoder.toPipeByte)
    //         .debug(_.toString(), Console.out.println)
    //         .handleErrorWith { err =>
    //           err.printStackTrace(Console.err); fs2.Stream.empty
    //         } // handle errors of client sockets
    //         .drain
    //     }
    //     .parJoin(100)
    //     .compile
    //     .drain
    // }

    // outgoing >> 
    incoming >> IO.pure(ExitCode.Success)
  }
}
