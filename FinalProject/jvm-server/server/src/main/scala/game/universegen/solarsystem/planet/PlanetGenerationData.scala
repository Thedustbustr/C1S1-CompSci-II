package game.universegen.solarsystem.planet

import scala.collection.immutable.HashMap

object PlanetGenerationData {
  enum PlanetType { case Rocky, Gas }

  object PlanetType {
    import io.circe.*
    implicit val encoder: Encoder[PlanetType] =
      Encoder.instance {
        case Rocky => Json.fromString("Rocky")
        case Gas   => Json.fromString("Gas")
      }
  }

  final case class GasProperties(
      greenhouseEffect: Double
  )
  object Rocky {
    val GENERATION_DISTANCE_PROBABILITY = Array[Array[Int]](
      // Each index contains three values: (1) min distance (exclusive), (2) max distance (inclusive), (3) rocky planet probability
      Array(0, 1, 5), // 0% // 5
      Array(1, 2, 25), // 1%
      Array(2, 3, 50), // 2%
      Array(3, 4, 75), // 3%
      Array(4, 5, 75), // 4%
      Array(5, 6, 70), // 5%
      Array(6, 7, 60), // 6%
      Array(7, 8, 50), // 7%
      Array(8, 9, 50), // 8%
      Array(9, 10, 50), // 9%
      Array(10, 20, 40), // 10%
      Array(20, 30, 30), // 20%
      Array(30, 40, 20), // 30%
      Array(40, 50, 10), // 40%
      Array(50, 60, 5), // 50%
      Array(60, 70, 5), // 60%
      Array(70, 80, 5), // 70%
      Array(80, 90, 5), // 80%
      Array(90, 100, 5), // 90%
      Array(100, 100, 5) // 100%
    )

    // Each index contains 4 values: (1) min mass, (2) max mass (3) min probability (4) max probability [Class is index of array]
    val PLANET_MASSES = Array[Array[Int]]( // In Earth Masses * 1000
      Array(2, 30, 0, 7), // 7% --> Dwarf Planet
      Array(30, 500, 7, 17), // 10%
      Array(500, 750, 17, 37), // 20%
      Array(750, 1500, 37, 77), // 40% --> Earth Size
      Array(1500, 2500, 77, 97), // 20%
      Array(2500, 4000, 97, 99), // 2%
      Array(5000, 6000, 99, 100) // 1% --> Super Earth
    );

    val POSSIBLE_ATMOSPHERES = HashMap[String, (Array[Int], GasProperties)](
      "Nitrogen" -> (Array(25, 50), new GasProperties(0)), // 25%
      "Oxygen" -> (Array(50, 70), new GasProperties(0)), // 20%
      "Ozone" -> (Array(70, 80), new GasProperties(0)), // 10%
      "Argon" -> (Array(80, 90), new GasProperties(0)), // 10%
      "Methane" -> (Array(90, 95), new GasProperties(0)), // 5%
      "Ammonia" -> (Array(95, 100), new GasProperties(0)) // 5%
    )
  }

  object Gas {
    // Each index contains three values: (1) min distance (exclusive), (2) max distance (inclusive), (3) gas planet probability
    val GENERATION_DISTANCE_PROBABILITY = Array[Array[Int]](
      Array(0, 1, 0), // 0%
      Array(1, 2, 0), // 1%
      Array(2, 3, 0), // 2%
      Array(3, 4, 0), // 3%
      Array(4, 5, 0), // 4%
      Array(5, 6, 0), // 5%
      Array(6, 7, 0), // 6%
      Array(7, 8, 0), // 7%
      Array(8, 9, 0), // 8%
      Array(9, 10, 5), // 9%
      Array(10, 20, 20), // 10%
      Array(20, 30, 40), // 20%
      Array(30, 40, 60), // 30%
      Array(40, 50, 75), // 40%
      Array(50, 60, 75), // 50%
      Array(60, 70, 75), // 60%
      Array(70, 80, 75), // 70%
      Array(80, 90, 75), // 80%
      Array(90, 100, 60), // 90%
      Array(100, 100, 50) // 100%
    )

    // Each index contains 4 values: (1) min mass, (2) max mass (3) min probability (4) max probability [Class is index of array]
    val PLANET_MASSES = Array[Array[Int]]( // In Juipter Masses * 100
      Array(3, 15, 0, 7), // 7% --> Dwarf gas giant?
      Array(15, 50, 7, 17), // 10%
      Array(50, 75, 17, 37), // 20%
      Array(75, 150, 37, 77), // 40% --> Juipter Size
      Array(150, 300, 77, 97), // 20%
      Array(300, 700, 97, 99), // 2%
      Array(700, 1300, 99, 100) // 1% --> Pre Brown Dwarfs
    )

    val POSSIBLE_ATMOSPHERES = HashMap[String, (Array[Int], GasProperties)](
      "Hydrogen" -> (Array(0, 50), new GasProperties(0)), // 50%
      "Helium" -> (Array(50, 80), new GasProperties(0)), // 30%
      "Methane" -> (Array(80, 90), new GasProperties(0)), // 10%
      "Ammonia" -> (Array(90, 100), new GasProperties(0)) // 10%
    );
  }
}
