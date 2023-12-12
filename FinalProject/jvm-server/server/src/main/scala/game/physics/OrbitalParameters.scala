package game.physics

import io.circe.Encoder
import io.circe.*
import io.circe.generic.semiauto.*
import io.circe.syntax.*
import util.extensions.*
import util.math.Vector3

final case class OrbitalParameters(
    µ: Double,
    semiMajorAxis: Double,
    semiMinorAxis: Double,
    apoapsis: Double,
    periapsis: Double,
    eccentricity: Double,
    inclination: Double,
    argumentOfPeriapsis: Double,
    longitudeOfTheAscendingNode: Double,
    specificAngularMomentum: Vector3,
    orbitalPeriod: Double
)

object OrbitalParameters {
  implicit val encoder: Encoder[OrbitalParameters] = deriveEncoder

  def calculateOrbitalParameters(velocity: Vector3, position: Vector3, mass: Double, centralObject: PhysicsObject) = {
    val distanceFromObject = Vector3.distance(position, centralObject.position);

    /* µ = G(M + m) */
    val µ: Double = UniversalLaws.GRAVITATIONAL_CONSTANT * (centralObject.mass + mass);

    /* a = -µ/2ε */
    val semiMajorAxis = -((µ * distanceFromObject) / ((distanceFromObject * (velocity.magnitude * velocity.magnitude)) - (2 * µ)));

    /* ε = -µ/2a */
    val specificOrbitalEnergy = -µ / (2 * semiMajorAxis);

    /* hᵥ = rᵥ×vᵥ */
    val specificAngularMomentum = Vector3.cross(position, velocity);

    /* e = √1 + (2εh²/µ²) */
    val eccentricity = scala.math.sqrt(1 + ((2 * specificOrbitalEnergy * (specificAngularMomentum.magnitude * specificAngularMomentum.magnitude)) / (µ * µ)));

    /* b = a√1 - e² */
    val semiMinorAxis = semiMajorAxis * math.sqrt(1 - (eccentricity * eccentricity));

    /* 𝘢𝘱 = a * (1 + e) */
    val apoapsis = semiMajorAxis * (1 + eccentricity);

    /* 𝘱𝘦𝘳𝘪 = a * (1 - e) */
    val periapsis = semiMajorAxis * (1 - eccentricity);

    /* i = arccos(h𝓏/|hᵥ|) */
    val inclination = math.acos(specificAngularMomentum.z / specificAngularMomentum.magnitude);

    /* nᵥ = kᵥ×hᵥ = (-hᵧ,h𝓏, 0) */
    val vectorToAscendingNode = Vector3.cross(Vector3(0, 0, 1), specificAngularMomentum);

    /* Ω = { Ω = 0, nᵥ = 0; }u{ arccos(nₓ/|nᵥ|), nᵧ > 0; }u{ 2π - arccos(nₓ/|nᵥ|), nᵧ < 0; } */
    val longitudeOfTheAscendingNode = (vectorToAscendingNode.y >= 0) ? math.acos(vectorToAscendingNode.x / vectorToAscendingNode.magnitude) | (2 * math.Pi) - math.acos(vectorToAscendingNode.x / vectorToAscendingNode.magnitude);

    /* eᵥ = (vᵥ×hᵥ)/µ */
    val eccentricityVector = (Vector3.cross(velocity, specificAngularMomentum) / µ) - (position / position.magnitude);

    /* ω = arccos(nᵥ•eᵥ/|nᵥ||eᵥ|) */
    val argumentOfPeriapsis = math.acos(Vector3.dot(vectorToAscendingNode, eccentricityVector) / (vectorToAscendingNode.magnitude * eccentricityVector.magnitude));

    /* ϖ = Ω + ω */
    val longitudeOfPeriapsis = longitudeOfTheAscendingNode + argumentOfPeriapsis;

    /* T = 2π√a³/µ */
    val orbitalPeriod = 2 * Math.PI * math.sqrt((semiMajorAxis * semiMajorAxis * semiMajorAxis) / µ);

    OrbitalParameters(
      µ,
      semiMajorAxis,
      semiMinorAxis,
      apoapsis,
      periapsis,
      eccentricity,
      inclination,
      argumentOfPeriapsis,
      longitudeOfTheAscendingNode,
      specificAngularMomentum,
      orbitalPeriod
    );
  }
}

/*
https://en.wikipedia.org/wiki/Orbital_eccentricity
https://en.wikipedia.org/wiki/Semi-major_and_semi-minor_axes
https://en.wikipedia.org/wiki/Specific_orbital_energy
https://en.wikipedia.org/wiki/Specific_angular_momentum
https://en.wikipedia.org/wiki/Orbital_inclination
https://en.wikipedia.org/wiki/Longitude_of_the`_ascending_node
https://en.wikipedia.org/wiki/Argument_of_periapsis
https://en.wikipedia.org/wiki/Eccentric_anomaly

https://space.stackexchange.com/questions/8911/determining-orbital-position-at-a-future-point-in-time
 */
