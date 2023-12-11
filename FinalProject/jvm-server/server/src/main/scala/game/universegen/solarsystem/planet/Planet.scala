package game.universegen.solarsystem.planet

import game.physics.*
import io.circe.Encoder
import io.circe.generic.semiauto.*

final case class Planet(
    density: Double,
    orbitalPeriod: Double,
    // Dictionary<string, (int[], GasProperties)> AtmosphericProperties;

    physicsObject: PhysicsObject
) {
  export physicsObject.{*}
}

object Planet {
  implicit val encoder: Encoder[Planet] = deriveEncoder
}
