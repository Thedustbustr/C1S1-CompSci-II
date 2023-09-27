import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
  private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

  public static void main(String[] args) throws Exception {
    userInput();
  }

  public static void userInput() throws Exception {
    try {
      System.out.println("Enter a radius for the circle: ");
      Circle circle = new Circle(Double.parseDouble(reader.readLine()));

      System.out.println(
          "Area: " + circle.area() + "\nDiameter: " + circle.diameter() + "\nCircumference: " + circle.circumference());

    } catch (NumberFormatException e) {
      System.out.println("==================\nInvalid Input: Double Required");
      userInput();
    }
  }
}
