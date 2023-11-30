package util.math

final case class Vector3(x: Double, y: Double, z: Double) {
  def +(addend2: Vector3) = Vector3(this.x + addend2.x, this.y + addend2.y, this.z + addend2.z)

  def -(minuend: Vector3) = Vector3(this.x - minuend.x, this.y - minuend.y, this.z - minuend.z)

  def *(multiplier: Double) = Vector3(this.x * multiplier, this.y * multiplier, this.z * multiplier)

  def /(divisor: Double) = Vector3(this.x / divisor, this.y / divisor, this.z / divisor)
}

object Vector3 {
  def normalize(v: Vector3) = {
    val mag = magnitude(v)
    Vector3(v.x / mag, v.y / mag, v.z / mag)
  }

  def sqrMagnitude(v: Vector3) = magnitude(v) * magnitude(v)

  def scaled(v: Vector3, scale: Double) = Vector3(v.x / scale, v.y / scale, v.z / scale)

  def magnitude(v: Vector3) = Math.sqrt((v.x * v.x) + (v.y * v.y) + (v.z * v.z))

  def cross(multiplicand: Vector3, multiplier: Vector3) = {
    val x = (multiplicand.y * multiplier.z) - (multiplicand.z * multiplier.y);
    val y = (multiplicand.z * multiplier.x) - (multiplicand.x * multiplier.z);
    val z = (multiplicand.x * multiplier.y) - (multiplicand.y * multiplier.x);

    Vector3(x, y, z);
  }

  def dot(multiplicand: Vector3, multiplier: Vector3) = (multiplicand.x * multiplier.x) + (multiplicand.y * multiplier.y) + (multiplicand.z + multiplier.z)

  def distance(point1: Vector3, point2: Vector3) = Math.sqrt(((point2.x - point1.x) * (point2.x - point1.x)) + ((point2.y - point1.y) * (point2.y - point1.y)) + ((point2.z - point1.z) * (point2.z - point1.z)));
}
