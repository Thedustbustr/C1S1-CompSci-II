package me.thedustbuster.util.math;

public record Vector3(double x, double y, double z) {
  public Vector3 add(Vector3 addend2) {
    return new Vector3(this.x + addend2.x(), this.y + addend2.y(), this.z + addend2.z());
  }

  public Vector3 subtract(Vector3 minuend) {
    return new Vector3(this.x - minuend.x(), this.y - minuend.y(), this.z - minuend.z());
  }

  public Vector3 multiply(double multiplier) {
    return new Vector3(this.x * multiplier, this.y * multiplier, this.z * multiplier);
  }

  public Vector3 divide(double divisor) {
    return new Vector3(this.x / divisor, this.y / divisor, this.z / divisor);
  }

  public Vector3 normalize() {
    double mag = magnitude();
    return new Vector3(this.x / mag, this.y / mag, this.z / mag);
  }

  public double sqrMagnitude() {
    return magnitude() * magnitude();
  }

  public Vector3 scaled(double scale) {
    return new Vector3(this.x / scale, this.y / scale, this.z / scale);
  }

  public double magnitude() {
    return Math.sqrt((this.x * this.x) + (this.y * this.y) + (this.z * this.z));
  }

  public static Vector3 cross(Vector3 multiplicand, Vector3 multiplier) {
    double x = (multiplicand.y() * multiplier.z()) - (multiplicand.z() * multiplier.y());
    double y = (multiplicand.z() * multiplier.x()) - (multiplicand.x() * multiplier.z());
    double z = (multiplicand.x() * multiplier.y()) - (multiplicand.y() * multiplier.x());

    return new Vector3(x, y, z);
  }

  public static double dot(Vector3 multiplicand, Vector3 multiplier) {
    return (multiplicand.x() * multiplier.x()) + (multiplicand.y() * multiplier.y())
        + (multiplicand.z() + multiplier.z());
  }

  public static double distance(Vector3 point1, Vector3 point2) {
    return Math.sqrt(
        ((point2.x() - point1.x()) * (point2.x() - point1.x())) +
            ((point2.y() - point1.y()) * (point2.y() - point1.y())) +
            ((point2.z() - point1.z()) * (point2.z() - point1.z())));
  }

  @Override
  public String toString() {
    return "x: " + this.x + " y: " + this.y + " z: " + this.z;
  }
}
