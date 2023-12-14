package me.thedustbuster.util.math;

/**
 * Vector3 math
 * @param x x component
 * @param y y component
 * @param z z component
 */
public record Vector3(double x, double y, double z) {

  /** 
   * @param addend2
   * @return Vector3
   */
  public Vector3 add(Vector3 addend2) {
    return new Vector3(this.x + addend2.x(), this.y + addend2.y(), this.z + addend2.z());
  }

  /** 
   * @param minuend
   * @return Vector3
   */
  public Vector3 subtract(Vector3 minuend) {
    return new Vector3(this.x - minuend.x(), this.y - minuend.y(), this.z - minuend.z());
  }

  /** 
   * @param multiplier
   * @return Vector3
   */
  public Vector3 multiply(double multiplier) {
    return new Vector3(this.x * multiplier, this.y * multiplier, this.z * multiplier);
  }

  /** 
   * @param divisor
   * @return Vector3
   */
  public Vector3 divide(double divisor) {
    return new Vector3(this.x / divisor, this.y / divisor, this.z / divisor);
  }

  /** 
   * @return Vector3
   */
  public Vector3 normalize() {
    double mag = magnitude();
    return new Vector3(this.x / mag, this.y / mag, this.z / mag);
  }

  /** 
   * @return double
   */
  public double sqrMagnitude() {
    return magnitude() * magnitude();
  }

  /** 
   * @param scale
   * @return Vector3
   */
  public Vector3 scaled(double scale) {
    return new Vector3(this.x / scale, this.y / scale, this.z / scale);
  }

  /** 
   * @return double
   */
  public double magnitude() {
    return Math.sqrt((this.x * this.x) + (this.y * this.y) + (this.z * this.z));
  }

  /** 
   * @param multiplicand
   * @param multiplier
   * @return Vector3
   */
  public static Vector3 cross(Vector3 multiplicand, Vector3 multiplier) {
    double x = (multiplicand.y() * multiplier.z()) - (multiplicand.z() * multiplier.y());
    double y = (multiplicand.z() * multiplier.x()) - (multiplicand.x() * multiplier.z());
    double z = (multiplicand.x() * multiplier.y()) - (multiplicand.y() * multiplier.x());

    return new Vector3(x, y, z);
  }

  /** 
   * @param multiplicand
   * @param multiplier
   * @return double
   */
  public static double dot(Vector3 multiplicand, Vector3 multiplier) {
    return (multiplicand.x() * multiplier.x()) + (multiplicand.y() * multiplier.y())
        + (multiplicand.z() + multiplier.z());
  }

  /** 
   * @param point1
   * @param point2
   * @return double
   */
  public static double distance(Vector3 point1, Vector3 point2) {
    return Math.sqrt(
        ((point2.x() - point1.x()) * (point2.x() - point1.x())) +
            ((point2.y() - point1.y()) * (point2.y() - point1.y())) +
            ((point2.z() - point1.z()) * (point2.z() - point1.z())));
  }

  /** 
   * @return String
   */
  @Override
  public String toString() {
    return "x: " + this.x + " y: " + this.y + " z: " + this.z;
  }
}
