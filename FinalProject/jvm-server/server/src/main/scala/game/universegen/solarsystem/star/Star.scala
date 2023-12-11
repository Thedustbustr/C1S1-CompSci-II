package game.universegen.solarsystem.star

import util.math.Vector3
import util.Color
import game.physics.*
import scala.collection.mutable.ArrayBuffer
import game.universegen.solarsystem.planet.PlanetGenerator
import io.circe.Encoder
import io.circe.generic.semiauto.*

final case class Star(
    starClass: Char,
    starType: String,
    temperature: Int,
    luminosity: Double,
    physicsObject: PhysicsObject
) {
  export physicsObject.{*}

  def Initialize(): Star = {
    this.satellites = PlanetGenerator.generatePlanets(this).map(_.physicsObject)

    this
  }

  /* ---------------------------------------------------------------------------- */

  def Unload(): Unit = {
    //
  }
}

object Star {
  implicit val encoder: Encoder[Star] = deriveEncoder
}
