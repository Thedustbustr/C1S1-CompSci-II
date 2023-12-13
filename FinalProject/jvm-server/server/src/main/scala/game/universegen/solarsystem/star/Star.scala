package game.universegen.solarsystem.star

import game.physics.*
import game.universegen.solarsystem.planet.PlanetGenerator
import io.circe.Encoder
import io.circe.generic.semiauto.*
import networking.*
import util.Color
import util.math.Vector3

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.ListBuffer

final case class Star(
    starClass: Char,
    starType: String,
    temperature: Int,
    luminosity: Double,

    /* NetworkObject */
    networkID: java.util.UUID,

    /* PhysicsObject */
    color: Color,
    var mass: Double,
    var position: Vector3,
    var velocity: Vector3,

    /*CelestialBody*/
    gravity: Double,
    radius: Double,
    density: Double,
    maximumGravitationalReach: Double,
    minimumSafeDistanceToGenerate: Double,
    satellites: ListBuffer[PhysicsObject] = ListBuffer.empty
) extends NetworkObject,
      PhysicsObject,
      CelestialBody {

  def Initialize(): Star = {
    this.satellites.addAll(PlanetGenerator.generatePlanets(this))

    this
  }

  /* ---------------------------------------------------------------------------- */

  def Unload(): Unit = {
    //
  }
}
