package game.physics

import scala.collection.mutable.ListBuffer

trait CelestialBody {
  val gravity: Double
  val radius: Double
  val minimumSafeDistanceToGenerate: Double
  val maximumGravitationalReach: Double

  val satellites: ListBuffer[PhysicsObject]

  /* ---------------------------------------------------------------------------- */

  lazy val scaledRadius: Double = this.radius / UniversalLaws.SCALE_FACTOR

  lazy val scaledMinimumSafeDistanceToGenerate: Double = this.minimumSafeDistanceToGenerate / UniversalLaws.SCALE_FACTOR

  lazy val scaledMaximumGravitationalReach: Double = this.maximumGravitationalReach / UniversalLaws.SCALE_FACTOR
}
