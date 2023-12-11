package game.universegen.solarsystem.planet

import game.universegen.solarsystem.star.*
import scala.util.Random
import scala.collection.mutable.ArrayBuffer
import game.physics.UniversalLaws
import cats.instances.boolean
import util.math.Vector3

object PlanetGenerator {
  def generatePlanets(star: Star): Array[Planet] = {
    val generatedPlanets = ArrayBuffer.empty[Planet];

    val minDistance: Double = star.scaledMinimumSafeDistanceToGenerate;
    val maxDistance: Double = star.scaledMaximumGravitationalReach;

    val planetaryGenerationThreshold: Double = maxDistance - minDistance;
    var currentDistance: Double = minDistance;

    var incrementDistance: Double = star.scaledRadius;
    while (currentDistance < maxDistance) {
      val distacePercentage: Double = (currentDistance / planetaryGenerationThreshold) * 100;

      incrementDistance = attemptGenerationOfPlanet(generatedPlanets, star, distacePercentage, currentDistance, incrementDistance)
      currentDistance += incrementDistance;
    }

    return generatedPlanets.toArray;
  }

  /* //////////////////////////////////////////////////////////////////////////// */
  /* //////////////////////////////////////////////////////////////////////////// */

  final case class GeneratedPlanet(planet: Planet, distance: Double)

  private def attemptGenerationOfPlanet(generatedPlanets: ArrayBuffer[Planet], star: Star, distacePercentage: Double, currentDistance: Double, incrementDistance: Double = 0): Double = {

    def generatePlanet(fn: (Star, Double, Double, Double) => Option[GeneratedPlanet]): Option[GeneratedPlanet] = {
      val generated = fn(star, distacePercentage, currentDistance, incrementDistance);

      generated.map { g =>
        generatedPlanets.addOne(g.planet)
        g
      }
    }

    val generated: Option[GeneratedPlanet] = {
      if (Random.nextBoolean) {
        generatePlanet(attemptGenerationOfRockyPlanet).orElse(generatePlanet(attemptGenerationOfGasPlanet))
      } else {
        generatePlanet(attemptGenerationOfGasPlanet).orElse(generatePlanet(attemptGenerationOfRockyPlanet))
      }
    }

    generated.map(_.distance).getOrElse(incrementDistance)
  }

  /* //////////////////////////////////////////////////////////////////////////// */
  /* //////////////////////////////////////////////////////////////////////////// */

  private def calculateAndAdjustGravitationalDistances(planet: Planet, incrementDistance: Double, typ: PlanetGenerationData.PlanetType): Double = {
    val maxDistance =
      typ match {
        case PlanetGenerationData.PlanetType.Rocky => math.sqrt((UniversalLaws.GRAVITATIONAL_CONSTANT * planet.mass) / 0.01) / UniversalLaws.SCALE_FACTOR
        case PlanetGenerationData.PlanetType.Gas   => math.sqrt((UniversalLaws.GRAVITATIONAL_CONSTANT * planet.mass) / 0.01) / UniversalLaws.SCALE_FACTOR
      }

    val scaledDistance =
      if (maxDistance > incrementDistance) {
        planet.scaledPosition().x + (maxDistance - incrementDistance) // ---> Prob gonna have to use maganitudes if planets are not generated in a straight line
      } else {
        planet.scaledPosition().x
      }

    scaledDistance + maxDistance
  }

  /* //////////////////////////////////////////////////////////////////////////// */

  private def attemptGenerationOfRockyPlanet(star: Star, distacePercentage: Double, currentDistance: Double, incrementDistance: Double): Option[GeneratedPlanet] = {
    if (canGenerateRockyPlanet(distacePercentage) == true) {
      val planet: Planet = PlanetBuilder()
        .setCentralOrbitalBody(star.physicsObject)
        .setPostion(Vector3(currentDistance * UniversalLaws.SCALE_FACTOR, 0, 0))
        .setType(PlanetGenerationData.PlanetType.Rocky)
        .build();

      val increment = calculateAndAdjustGravitationalDistances(planet, incrementDistance, PlanetGenerationData.PlanetType.Rocky);

      Some(GeneratedPlanet(planet, increment))
    } else {
      None
    }
  }

  private def canGenerateRockyPlanet(distacePercentage: Double): Boolean = {
    var probability: Int = 0;
    var i = 0
    var c = true

    while (c && i < PlanetGenerationData.Rocky.GENERATION_DISTANCE_PROBABILITY.size) {
      if (distacePercentage > PlanetGenerationData.Rocky.GENERATION_DISTANCE_PROBABILITY(i)(0) && distacePercentage <= PlanetGenerationData.Rocky.GENERATION_DISTANCE_PROBABILITY(i)(1)) {
        probability = PlanetGenerationData.Rocky.GENERATION_DISTANCE_PROBABILITY(i)(2);
        c = false
      }

      i = i + 1
    }

    Random.nextInt(100) < probability
  }

  // /* //////////////////////////////////////////////////////////////////////////// */

  private def attemptGenerationOfGasPlanet(star: Star, distacePercentage: Double, currentDistance: Double, incrementDistance: Double): Option[GeneratedPlanet] = {
    if (canGenerateGasPlanet(distacePercentage) == true) {
      val planet: Planet = PlanetBuilder()
        .setCentralOrbitalBody(star.physicsObject)
        .setPostion(Vector3(currentDistance * UniversalLaws.SCALE_FACTOR, 0, 0))
        .setType(PlanetGenerationData.PlanetType.Gas)
        .build();

      val increment = calculateAndAdjustGravitationalDistances(planet, incrementDistance, PlanetGenerationData.PlanetType.Gas);

      Some(GeneratedPlanet(planet, increment))
    } else {
      None
    }
  }

  private def canGenerateGasPlanet(distacePercentage: Double): Boolean = { // {
    var probability: Int = 0;
    var i = 0
    var c = true

    while (c && i < PlanetGenerationData.Gas.GENERATION_DISTANCE_PROBABILITY.size) {
      if (distacePercentage > PlanetGenerationData.Gas.GENERATION_DISTANCE_PROBABILITY(i)(0) && distacePercentage <= PlanetGenerationData.Gas.GENERATION_DISTANCE_PROBABILITY(i)(1)) {
        probability = PlanetGenerationData.Gas.GENERATION_DISTANCE_PROBABILITY(i)(2);
        c = false
      }

      i = i + 1
    }

    Random.nextInt(100) < probability
  }
}
