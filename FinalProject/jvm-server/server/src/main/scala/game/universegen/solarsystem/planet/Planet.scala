package game.universegen.solarsystem.planet

import game.physics.*
import game.universegen.solarsystem.planet.PlanetGenerationData.PlanetType
import game.universegen.solarsystem.star.Star
import io.circe.Encoder
import io.circe.generic.semiauto.*
import networking.NetworkObject

import scala.collection.mutable.ListBuffer

final case class Planet(
    typ: PlanetType,
    orbitalPeriod: Double,
    centralOrbitalBody: CelestialBody,
    orbitalParameters: OrbitalParameters,
    // Dictionary<string, (int[], GasProperties)> AtmosphericProperties;

    /* NetworkObject */
    networkID: java.util.UUID,

    /* PhysicsObject */
    color: util.Color,
    var mass: Double,
    var position: util.math.Vector3,
    var velocity: util.math.Vector3,

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

  // Inf recur bad (stack ovr if comment VVVV)
  override def toString(): String = {
    val centralOrbitalBodyOverride =
      centralOrbitalBody match {
        case s: Star => s"${s.networkID}"
        case _       => "N/A"
      }

    s"""
    |type: $typ
    |orbitalPeriod: $orbitalPeriod 
    |centralOrbitalBody: $centralOrbitalBodyOverride
    |orbitalParameters: $orbitalParameters 
    |networkID: $networkID 
    |color: $color
    |mass: $mass 
    |position: $position
    |velocity: $velocity
    |gravity: $gravity 
    |maximumGravitationalReach: $maximumGravitationalReach 
    |minimumSafeDistanceToGenerate: $minimumSafeDistanceToGenerate 
    |radius: $radius 
    |satellites: $satellites
  """.stripMargin('|')
  }
}
