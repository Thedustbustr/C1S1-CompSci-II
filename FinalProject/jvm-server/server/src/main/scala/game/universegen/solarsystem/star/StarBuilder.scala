package game.universegen.solarsystem.star

import game.physics.UniversalLaws
import game.physics.*
import util.Color
import util.extensions.*
import util.math.Vector3

import java.util.UUID
import scala.util.Random

enum StarType(val name: String) {
  case Star extends StarType("Star")
  case WhiteDwarf extends StarType("White Dwarf")
  case NeutronStar extends StarType("Neutron Star")
  case BlackHole extends StarType("Black Hole")
}

object StarType {
  import io.circe.*
  implicit val encoder: Encoder[StarType] =
    Encoder.instance {
      case Star        => Json.fromString("Star")
      case WhiteDwarf  => Json.fromString("WhiteDwarf")
      case NeutronStar => Json.fromString("NeutronStar")
      case BlackHole   => Json.fromString("BlackHole")
    }
}

object StarBuilder {
  def build(): Star = {
    val starClass = calculateClass();
    val displayStarClass = StarGenerationData.Key(starClass);

    val temperature = calculateStarTemperature(StarGenerationData.Temperature.min(starClass), StarGenerationData.Temperature.max(starClass));
    val radius = caluculateStarRadius(StarGenerationData.Radius.min(starClass), StarGenerationData.Radius.max(starClass));

    val luminosity = caluculateStarLuminosity(temperature, radius);
    val mass = calculateStarMass(luminosity);

    val minimumSafeDistanceToGenerate = calculateMinimumSafeDistanceToGenerate(radius);
    val maximumGravitationalReach = calculateMaximumGravitationalReach(mass);

    val characteristics = calculateStarDeath(StarGenerationData.DeathProbability(starClass), mass, radius);
    val starType = "Class " + displayStarClass + " " + characteristics._1.name;
    val postRadius = characteristics._2;

    val gravity = calculateGravity(mass, radius);
    val position = Vector3(0, 0, 0);
    val velocity = Vector3(0, 0, 0);

    val color = calculateColor(temperature);

    // this.gameObject = CalculateProperties(
    //     luminosity, radius, mass, color,
    //     /* Sphere */
    //     SphereGenerator.GenerateSphere(radius / UniversalLaws.ScaleFactor, position, starType, /* Material --> */ MaterialGenerator.GenerateMaterial(color, emissive: true))
    // );

    Star(
      starClass = displayStarClass,
      starType = starType,
      temperature = temperature,
      luminosity = luminosity,
      networkID = UUID.randomUUID(), // <-- tmp
      // locked = true,
      // typ = PhysicsObjectType.Star,
      color = color,
      velocity = velocity, // Is constant (Well at least magnitude)
      position = position,
      mass = mass, // In kg
      radius = radius, // In m
      gravity = gravity, // In m/s/s
      minimumSafeDistanceToGenerate = minimumSafeDistanceToGenerate, // In m
      maximumGravitationalReach = maximumGravitationalReach // In m
    ).Initialize()
  }

  /* //////////////////////////////////////////////////////////////////////////// */
  /* //////////////////////////////////////////////////////////////////////////// */

  private def calculateClass(): Int = {
    var starClass = -1;
    (Random.between(1, 101)) match {
      case n if (n <= StarGenerationData.Probability(0)) => 0
      case n if (n <= StarGenerationData.Probability(1)) => 1
      case n if (n <= StarGenerationData.Probability(2)) => 2
      case n if (n <= StarGenerationData.Probability(3)) => 3
      case n if (n <= StarGenerationData.Probability(4)) => 4
      case n if (n <= StarGenerationData.Probability(5)) => 5
      case n                                             => 6
    }
  }

  /* Used a motified version of the SOI equation: https://en.wikipedia.org/wiki/Sphere_of_influence_(astrodynamics) */
  private def calculateMaximumGravitationalReach(mass: Double): Double = math.pow(mass, 2f / 5)

  private def calculateMinimumSafeDistanceToGenerate(radius: Double): Double = radius * 8

  private def calculateStarTemperature(min: Int, max: Int): Int = Random.between(min, max + 1)

  private def caluculateStarRadius(min: Int, max: Int): Double = (Random.between(min, max + 1) * UniversalLaws.SOLAR_RADII_IN_METERS) / 100d

  private def caluculateStarLuminosity(temperature: Int, radius: Double): Double = ((4 * math.Pi) * math.pow(radius, 2) * UniversalLaws.STEPHAN_BOLTZMANN_CONSTANT * math.pow(temperature, 4))

  private def calculateStarMass(luminosity: Double): Double = math.pow(math.pow((luminosity / UniversalLaws.BOLOMETRIC_MAGNITUDE_IN_WATTS), 2), (1d / 7)) * UniversalLaws.SOLAR_MASS_IN_KILOGRAMS

  private def calculateGravity(mass: Double, radius: Double): Double = UniversalLaws.GRAVITATIONAL_CONSTANT * ((mass) / math.pow((radius), 2))

  // private GameObject CalculateProperties(double luminosity, double radius, double mass, Color color, GameObject model) {
  //     Light light = model.AddComponent<Light>();
  //     light.color = new Color((color.r / 255), (color.g / 255), (color.b / 255), color.a);
  //     light.bounceIntensity = 0;

  //     HDAdditionalLightData lightData = model.AddHDLight(HDLightTypeAndShape.Point);
  //     lightData.EnableColorTemperature(false);
  //     lightData.fadeDistance = 40000;
  //     lightData.intensity = (float)1e10;
  //     lightData.shapeRadius = 10000;
  //     lightData.range = 1000000;

  //     return model;
  // }

  private def calculateColor(temperature: Int): Color = {
    def getGradient(colorIndex: Int) = {
      val previousIndexWeight = (temperature - StarGenerationData.ColorMap.colorPos(colorIndex - 1)) / (StarGenerationData.ColorMap.colorPos(colorIndex) - StarGenerationData.ColorMap.colorPos(colorIndex - 1)).floatValue()
      val currentIndexWeight = 1 - previousIndexWeight;

      val r = math.round((StarGenerationData.ColorMap.colors(colorIndex).r * currentIndexWeight) + (StarGenerationData.ColorMap.colors(colorIndex - 1).r * previousIndexWeight));
      val g = math.round((StarGenerationData.ColorMap.colors(colorIndex).g * currentIndexWeight) + (StarGenerationData.ColorMap.colors(colorIndex - 1).g * previousIndexWeight));
      val b = math.round((StarGenerationData.ColorMap.colors(colorIndex).b * currentIndexWeight) + (StarGenerationData.ColorMap.colors(colorIndex - 1).b * previousIndexWeight));

      Some(Color(r, g, b))
    }

    val maybeColor = StarGenerationData.ColorMap.colorPos.zipWithIndex.foldLeft(Option.empty[Color]) { case (color, (currentColorPosition, i)) =>
      /* If we found the color already, break (keep color and continue looping; no changes) */
      if (color.isDefined) color
      /* If the temperatures are equivalent to the threshold, set to threshold */
      else if (currentColorPosition == temperature) Some(StarGenerationData.ColorMap.colors(i))
      /* If the temperature is still less than the current threshold, not found, keep looping */
      else if (currentColorPosition - temperature < 0) None
      /* Temperature must be greater than previous threshold, if it is the first threshold (coldest temperture) use it for the star temperature, othersise get the gradient between the previous and current threshold */
      else (i == 0) ? Some(StarGenerationData.ColorMap.colors(0)) | getGradient(i)
    }

    val hottestTemperature = StarGenerationData.ColorMap.colors(StarGenerationData.ColorMap.colors.length - 1)
    maybeColor.getOrElse(hottestTemperature)
  }

  private def calculateStarDeath(probability: Int, mass: Double, radius: Double): (StarType, Double) = {
    def calculateWhiteDwarfSize(radius: Double): Double = {
      radius * 0.009; // Earth radius divided by the Suns
    }

    def calculateNeutronStarSize(mass: Double): Double = {
      mass * 10; // The size roughly is 10 times the mass in solar masses
    }

    def calculateBlackHoleSize(mass: Double): Double = {
      ((2 * UniversalLaws.GRAVITATIONAL_CONSTANT * (mass)) / math.pow(UniversalLaws.LIGHT_SPEED, 2)); // Schwarzschild radius
    }

    /* //////////////////////////////////////////////////////////////////////////// */

    if (Random.between(1, 101) > probability) {
      (StarType.Star, radius)
    } else {
      mass match {
        case m if (m <= 10) =>
          (StarType.WhiteDwarf, calculateWhiteDwarfSize(radius))
        case m if (m <= 15 && Random.nextBoolean) =>
          (StarType.WhiteDwarf, calculateWhiteDwarfSize(radius))
        case m if (m <= 20) =>
          (StarType.NeutronStar, calculateNeutronStarSize(mass))
        case m if (m <= 25 && Random.nextBoolean) =>
          (StarType.NeutronStar, calculateNeutronStarSize(mass))
        case _ =>
          (StarType.BlackHole, calculateBlackHoleSize(mass))
      }
    }
  }
}
