package game.physics

import util.math.Vector3
import util.Color
import scala.collection.mutable.ArrayBuffer
import io.circe.Encoder
import io.circe.generic.semiauto.*

enum PhysicsObjectType { case Virtual, Planet, Star }

object PhysicsObjectType {
  import io.circe.*
  implicit val encoder: Encoder[PhysicsObjectType] =
    Encoder.instance {
      case Planet  => Json.fromString("Planet")
      case Star    => Json.fromString("Star")
      case Virtual => Json.fromString("Virtual")
    }
}

final case class PhysicsObject(
    networkID: java.util.UUID,
    var locked: Boolean,
    typ: PhysicsObjectType,
    color: Color,
    var centralObject: Option[PhysicsObject] = None,
    var orbitalParameters: Option[OrbitalParameters] = None,
    var velocity: Vector3, // Is constant (Well at least magnitude)
    var position: Vector3,
    var mass: Double, // In kg
    radius: Double, // In m
    var gravity: Double, // In m/s/s
    minimumSafeDistanceToGenerate: Double, // In m
    maximumGravitationalReach: Double, // In m

    var satellites: Array[PhysicsObject]
) {

  /* ---------------------------------------------------------------------------- */

  lazy val scaledRadius: Double = this.radius / UniversalLaws.SCALE_FACTOR

  lazy val scaledMinimumSafeDistanceToGenerate: Double = this.minimumSafeDistanceToGenerate / UniversalLaws.SCALE_FACTOR

  lazy val scaledMaximumGravitationalReach: Double = this.maximumGravitationalReach / UniversalLaws.SCALE_FACTOR

  /* ---------------------------------------------------------------------------- */

  def scaledVelocity(): Vector3 = this.velocity / UniversalLaws.SCALE_FACTOR

  def scaledPosition(): Vector3 = this.position / UniversalLaws.SCALE_FACTOR

  def scaledMass(): Double = this.mass / UniversalLaws.SCALE_FACTOR

  /* ---------------------------------------------------------------------------- */

  def requestAllBodies(): Array[PhysicsObject] = {
    val celestialBodies = ArrayBuffer(this)

    this.satellites.map { satellite =>
      celestialBodies.addAll(satellite.requestAllBodies())
    }

    celestialBodies.toArray
  }
}

object PhysicsObject {
  private val derivedEncoder: Encoder[PhysicsObject] = deriveEncoder

  implicit val encoder: Encoder[PhysicsObject] = Encoder.instance { po =>
    val strippedPo = po.copy(centralObject = None)
    derivedEncoder(strippedPo)
  }
}
