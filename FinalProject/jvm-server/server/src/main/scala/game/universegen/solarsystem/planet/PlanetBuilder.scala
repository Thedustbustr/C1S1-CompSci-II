package game.universegen.solarsystem.planet

import game.physics.PhysicsObject
import game.physics.*
import game.universegen.solarsystem.planet.PlanetGenerationData.GasProperties
import game.universegen.solarsystem.planet.PlanetGenerationData.PlanetType
import game.universegen.solarsystem.star.Star
import util.Color
import util.math.Vector3

import java.util.UUID
import scala.collection.immutable.HashMap
import scala.collection.mutable.ListBuffer
import scala.util.Random

class PlanetBuilder() {
  /* Inital Variable */
  var centralOrbitalBody: Option[Star] = None
  var position: Option[Vector3] = None
  var typ: Option[PlanetType] = None

  //  Dictionary<string, (int[], GasProperties)> AtmosphericProperties { get; private set; }

  def setCentralOrbitalBody(centralOrbitalBody: Star) = {
    this.centralOrbitalBody = Some(centralOrbitalBody)
    this
  }

  def setPostion(position: Vector3) = {
    this.position = Some(position)
    this
  }

  def setType(typ: PlanetType) = {
    this.typ = Some(typ)
    this
  }

  def build() = {
    val planet = for {
      cob <- centralOrbitalBody
      pos <- position
      t <- typ
    } yield {
      val planetClass: Int = calculateClass(t);
      val density = calcualteDensity(t);

      val orbitalPeriod = calcualteOrbitalPeriod(Vector3.distance(cob.position, pos), cob.mass);
      val velocity = Vector3(0, 0, math.sqrt((UniversalLaws.GRAVITATIONAL_CONSTANT * cob.mass) / pos.magnitude)); // <----- Prob gonna need to use magnitude if planets dont wanna start at the same spot
      val mass = calculateMass(t, planetClass);
      val radius = calculateRadius(mass, density);
      val gravity = calculateGravity(mass, radius);
      val orbitalParameters = OrbitalParameters.calculateOrbitalParameters(velocity, pos, mass, cob);
      val minimumSafeDistanceToGenerate = calculateMinimumSafeDistanceToGenerate(radius);
      val maximumGravitationalReach = calculateMaximumGravitationalReach(mass, orbitalParameters, cob);
      val color = Color(0, 0, 255);

      Planet(
        typ = t,
        density = density,
        orbitalPeriod = orbitalPeriod,
        centralOrbitalBody = cob,
        orbitalParameters = orbitalParameters,
        networkID = UUID.randomUUID(), // <-- tmp
        // locked = true,
        // typ = PhysicsObjectType.Planet,
        color = color,
        velocity = velocity,
        position = pos,
        mass = mass,
        radius = radius,
        gravity = gravity,
        minimumSafeDistanceToGenerate = minimumSafeDistanceToGenerate,
        maximumGravitationalReach = maximumGravitationalReach
      )
    }

    planet.getOrElse(
      throw new RuntimeException("Why no initalized?")
    )
  }

  /* //////////////////////////////////////////////////////////////////////////// */
  /* //////////////////////////////////////////////////////////////////////////// */

  /* Used the SOI equation: https://en.wikipedia.org/wiki/Sphere_of_influence_(astrodynamics) */
  private def calculateMaximumGravitationalReach(mass: Double, orbitalParameters: OrbitalParameters, centralOrbitalBody: PhysicsObject): Double = orbitalParameters.semiMajorAxis * math.pow(mass / centralOrbitalBody.mass, 2d / 5)

  private def calculateMinimumSafeDistanceToGenerate(radius: Double): Double = return radius * 8

  private def calculateRadius(mass: Double, density: Double): Double = math.pow(mass / ((4 / 3) * Math.PI * density), 1d / 3)

  private def calculateEscapeVelocity(mass: Double, radius: Double): Double = { // <---- Not correct, radius is distance from planet
    return math.sqrt((2 * UniversalLaws.GRAVITATIONAL_CONSTANT * mass) / radius);
  }

  private def calculateGravity(mass: Double, radius: Double): Double = UniversalLaws.GRAVITATIONAL_CONSTANT * ((mass) / math.pow((radius), 2))

  private def calcualteOrbitalPeriod(radius: Double, massCentral: Double): Double = math.sqrt(math.pow(radius, 3) * ((4 * math.pow(math.Pi, 2)) / (UniversalLaws.GRAVITATIONAL_CONSTANT * massCentral)))

// private def GenerateLineRenderer : LineRenderer = { //(GameObject model, Color color) {
//     val line : LineRenderer =model.AddComponent[LineRenderer]();
//     line.loop = true;

//     line.material = MaterialGenerator.GenerateMaterial(color, transparent: true, shader: "HDRP/Unlit");
//     line.generateLightingData = false;
//     line.startColor = color;
//     line.endColor = color / 2;
//     line.startWidth = 5;
//     line.endWidth = 5;

//     line.enabled = false;

//     return line;
// }

  private def calculateClass(typ: PlanetType): Int = {
    val random: Int = Random.between(1, 101)

    def loop(masses: Array[Array[Int]]) = {
      masses.zipWithIndex.find { arr =>
        random > arr._1(2) && random <= arr._1(3)
      }
    }

    val planetClass = typ match {
      case PlanetType.Rocky => loop(PlanetGenerationData.Rocky.PLANET_MASSES)
      case PlanetType.Gas   => loop(PlanetGenerationData.Gas.PLANET_MASSES)
    }

    /* If class is not set for whatever reason throws exception */
    planetClass
      .map(_._2)
      .getOrElse(
        throw new RuntimeException("OH GOD WRONG CLASS NO FIND\nPlanet Type: " + planetClass)
      )
    // if (planetClass == -1) {
    //     throw new ErrorHandling.CelestialClassInvalid(planetClass, ErrorHandling.CelestialBodyType.Planet);
    // }
  }

  private def calculateMass(typ: PlanetType, planetClass: Int): Double = {
    typ match {
      case PlanetType.Rocky =>
        val min = PlanetGenerationData.Rocky.PLANET_MASSES(planetClass)(0);
        val max = PlanetGenerationData.Rocky.PLANET_MASSES(planetClass)(1);

        ((Random.between(min, max + 1)) * UniversalLaws.EARTH_MASS_IN_KILOGRAMS) / 1000;
      case PlanetType.Gas =>
        val min = PlanetGenerationData.Gas.PLANET_MASSES(planetClass)(0);
        val max = PlanetGenerationData.Gas.PLANET_MASSES(planetClass)(1);

        ((Random.between(min, max + 1)) * UniversalLaws.JUPITER_MASS_IN_KILOGRAMS) / 100
    }
  }

  private def calcualteDensity(typ: PlanetType): Double = {
    /* Data from our solar system */
    typ match {
      case PlanetType.Rocky => (Random.between(100, 700 + 1)) / 100d
      case PlanetType.Gas   => (Random.between(75, 225 + 1)) / 100d
    }
  }
}

// private def calculateAtmosphere( typ:PlanetType):HashMap[String, (Array[Int], GasProperties)] =   {
//     Dictionary<string, (int[], GasProperties)> atmosphericProperties = new Dictionary<string, (int[], GasProperties)>();
//     Dictionary<string, (int[], GasProperties)> planetGasses = new Dictionary<string, (int[], GasProperties)>();
//     List[string] toRemove = new List[string]();

//     if (type == PlanetType.Rocky) {
//         planetGasses = PlanetGenerationData.Rocky.PossibleAtmospheres;
//     } else if (type == PlanetType.Gas) {
//         planetGasses = PlanetGenerationData.Gas.PossibleAtmospheres;
//     }

//     val random : int =UnityEngine.Random.Range(0, 100);
//     foreach (KeyValuePair<string, (int[], GasProperties)> gasProperties in planetGasses) {
//         if (atmosphericProperties.Count >= 3) {
//             break;
//         }

//         if (random > gasProperties.Value.Item1[0] && random <= gasProperties.Value.Item1[1]) {
//             if (atmosphericProperties.ContainsKey(gasProperties.Key)) {
//                 List[string] keyList = new List[string](planetGasses.Keys);
//                 val key : string =keyList[UnityEngine.Random.Range(0, (planetGasses.Count - 1))];

//                 atmosphericProperties.Add(key, planetGasses[key]);
//             } else {
//                 atmosphericProperties.Add(gasProperties.Key, gasProperties.Value);
//             }

//             toRemove.Add(gasProperties.Key);
//         }
//     }

//     foreach (string item in toRemove) {
//         planetGasses.Remove(item);
//     }

//     return atmosphericProperties;
// }
