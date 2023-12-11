package util.math

import io.circe.Encoder
import io.circe.generic.semiauto.*

final case class Vector3(x: Double, y: Double, z: Double) {
  def +(addend2: Vector3) = Vector3(this.x + addend2.x, this.y + addend2.y, this.z + addend2.z)

  def -(minuend: Vector3) = Vector3(this.x - minuend.x, this.y - minuend.y, this.z - minuend.z)

  def *(multiplier: Double) = Vector3(this.x * multiplier, this.y * multiplier, this.z * multiplier)

  def /(divisor: Double) = Vector3(this.x / divisor, this.y / divisor, this.z / divisor)

  def normalize = {
    val mag = this.magnitude
    Vector3(this.x / mag, this.y / mag, this.z / mag)
  }

  def sqrMagnitude = this.magnitude * this.magnitude

  def scaled(scale: Double) = Vector3(this.x / scale, this.y / scale, this.z / scale)

  def magnitude = math.sqrt((this.x * this.x) + (this.y * this.y) + (this.z * this.z))
}

object Vector3 {
  def cross(multiplicand: Vector3, multiplier: Vector3) = {
    val x = (multiplicand.y * multiplier.z) - (multiplicand.z * multiplier.y);
    val y = (multiplicand.z * multiplier.x) - (multiplicand.x * multiplier.z);
    val z = (multiplicand.x * multiplier.y) - (multiplicand.y * multiplier.x);

    Vector3(x, y, z);
  }

  def dot(multiplicand: Vector3, multiplier: Vector3) = (multiplicand.x * multiplier.x) + (multiplicand.y * multiplier.y) + (multiplicand.z + multiplier.z)

  def distance(point1: Vector3, point2: Vector3) = math.sqrt(((point2.x - point1.x) * (point2.x - point1.x)) + ((point2.y - point1.y) * (point2.y - point1.y)) + ((point2.z - point1.z) * (point2.z - point1.z)));

  implicit val encoder: Encoder[Vector3] = deriveEncoder
}
