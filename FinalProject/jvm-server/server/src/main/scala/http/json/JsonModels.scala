package http.json

import game.physics.CelestialBody
import game.physics.OrbitalParameters
import game.physics.PhysicsObject
import game.universegen.solarsystem.planet.Planet
import game.universegen.solarsystem.planet.PlanetGenerationData.PlanetType
import game.universegen.solarsystem.star.Star
import io.circe.*
import io.circe.generic.semiauto.*
import networking.NetworkObject
import util.Color
import util.extensions.*
import util.math.Vector3

import scala.collection.mutable.ListBuffer

implicit val physicsObjectEncoder: Encoder[JsonPhysicsObject] = deriveEncoder
implicit val jsonSatelliteEncoder: Encoder[JsonSatellite] = {
  val planetEncoder: Encoder[JsonPlanet] = deriveEncoder
  val starEncoder: Encoder[JsonStar] = deriveEncoder

  Encoder.instance {
    case p: JsonPlanet => planetEncoder(p)
    case s: JsonStar   => starEncoder(s)
  }
}
/* ---------------------------------------------------------------------------- */

def toJsonPhysicsObject(p: PhysicsObject) = JsonPhysicsObject(p.velocity, p.position, p.mass, p.color)

def filterByNetworkId(list: List[JsonSatellite], id: java.util.UUID): List[JsonSatellite] = {
  list.filter { p =>
    p match {
      case p: JsonPlanet => (p.networkID == id)
      case s: JsonStar   => (s.networkID == id)
    }
  }
}

def toJsonSatellite(p: PhysicsObject): JsonSatellite = {
  p match {
    case p: Planet => toJsonPlanet(p)
    case s: Star   => toJsonStar(s)
  }
}

def toJsonStar(s: Star): JsonStar = JsonStar(
  starClass = s.starClass,
  starType = s.starType,
  temperature = s.temperature,
  luminosity = s.luminosity,

  /* NetworkObject */
  networkID = s.networkID,

  /* PhysicsObject */
  color = s.color,
  mass = s.mass,
  position = s.position,
  velocity = s.velocity,

  /*CelestialBody*/
  radius = s.radius,
  gravity = s.gravity,
  density = s.density,
  maximumGravitationalReach = s.maximumGravitationalReach,
  minimumSafeDistanceToGenerate = s.minimumSafeDistanceToGenerate,
  satellites = s.satellites.toList.map(toJsonSatellite)
)

def toJsonPlanet(p: Planet): JsonPlanet = JsonPlanet(
  typ = p.typ,
  orbitalPeriod = p.orbitalPeriod,
  orbitalParameters = p.orbitalParameters,
  // Dictionary<string, (int[], GasProperties)> AtmosphericProperties;

  /* NetworkObject */
  networkID = p.networkID,

  /* PhysicsObject */
  color = p.color,
  mass = p.mass,
  position = p.position,
  velocity = p.velocity,

  /*CelestialBody*/
  radius = p.radius,
  gravity = p.gravity,
  density = p.density,
  maximumGravitationalReach = p.maximumGravitationalReach,
  minimumSafeDistanceToGenerate = p.minimumSafeDistanceToGenerate,
  satellites = p.satellites.toList.map(toJsonSatellite)
)

/* ---------------------------------------------------------------------------- */

type JsonSatellite = JsonPlanet | JsonStar

final case class JsonPhysicsObject(
    velocity: Vector3,
    position: Vector3,
    mass: Double,
    color: Color
)

/* ---------------------------------------------------------------------------- */

final case class JsonStar(
    starClass: Char,
    starType: String,
    temperature: Int,
    luminosity: Double,

    /* NetworkObject */
    networkID: java.util.UUID,

    /* PhysicsObject */
    color: util.Color,
    mass: Double,
    position: util.math.Vector3,
    velocity: util.math.Vector3,

    /*CelestialBody*/
    radius: Double,
    gravity: Double,
    density: Double,
    maximumGravitationalReach: Double,
    minimumSafeDistanceToGenerate: Double,
    satellites: List[JsonSatellite]
)

final case class JsonPlanet(
    typ: PlanetType,
    orbitalPeriod: Double,
    orbitalParameters: OrbitalParameters,
    // Dictionary<string, (int[], GasProperties)> AtmosphericProperties;

    /* NetworkObject */
    networkID: java.util.UUID,

    /* PhysicsObject */
    color: util.Color,
    mass: Double,
    position: util.math.Vector3,
    velocity: util.math.Vector3,

    /*CelestialBody*/
    radius: Double,
    gravity: Double,
    density: Double,
    maximumGravitationalReach: Double,
    minimumSafeDistanceToGenerate: Double,
    satellites: List[JsonSatellite]
)
