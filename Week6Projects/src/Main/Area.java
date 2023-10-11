package Main;


/**
 * The area class is simply to hold the area override methods
 */
public final class Area {
  /** 
   * @param radius Radius of circle
   * @return Area as double
   */
  public static double area(double radius) {
    return Math.PI * radius * 2;
  }

  /** 
  * @param length Length of rectangle
  * @param width With of rectangle
  * @return Area as double
  */
  public static double area(double length, double width) {
    return length * width;
  }

  /** 
  * @param π Pi constant
  * @param radius radius of cylinder
  * @param height height of cylinder
  * @return Area as double
  */
  public static double area(double π, double radius, double height) {
    return π * radius * height;
  }
}
