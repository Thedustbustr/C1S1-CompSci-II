public class Circle {
  final static private double π = 3.14159;
  private double radius;

  public void setRadius(double radius) {
    this.radius = radius;
  }

  public double getRadius() {
    return this.radius;
  }

  public Circle() {
    this.radius = 0.0;
  }

  public Circle(double radius) {
    this.radius = radius;
  }

  public double area() {
    return π * this.radius * this.radius;
  }

  public double diameter() {
    return this.radius * 2;
  }

  public double circumference() {
    return 2 * π * this.radius;
  }
}
