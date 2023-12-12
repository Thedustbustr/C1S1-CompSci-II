package game.physics

import io.circe.Encoder
import io.circe.generic.semiauto.*
import util.Color
import util.math.Vector3

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.ListBuffer

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

trait PhysicsObject {
  var velocity: Vector3
  var position: Vector3
  var mass: Double

  val color: Color

  /* ---------------------------------------------------------------------------- */

  def scaledVelocity(): Vector3 = this.velocity / UniversalLaws.SCALE_FACTOR

  def scaledPosition(): Vector3 = this.position / UniversalLaws.SCALE_FACTOR

  def scaledMass(): Double = this.mass / UniversalLaws.SCALE_FACTOR
}
