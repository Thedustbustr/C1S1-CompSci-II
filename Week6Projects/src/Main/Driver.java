package Main;

/**
 * Main class
 */
public class Driver {
  /** 
   * Main method
   * @param args default
   * @throws Exception Any exception
   */
  public static void main(String[] args) throws Exception {
    try {
      System.out.println("Circle (R=5): " + Area.area(5) + "\nRectangle (L=5,W=2): " + Area.area(5, 2) + "\nCylinder (R=5,H=2): " + Area.area(Math.PI, 5, 2));
    } catch (Exception e) {
      Errors.Logging.Error("An error has occured!", Errors.Threads.main, e);
    }
  }
}
