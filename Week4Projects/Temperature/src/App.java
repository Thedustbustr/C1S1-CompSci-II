import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
  private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

  public static void main(String[] args) throws Exception {
    userInput();
  }

  public static void userInput() throws Exception {
    try {
      System.out.println("Enter a temperature in fahrenheit: ");

      Temperature temperature = new Temperature(Double.parseDouble(reader.readLine()));
      System.out.println("Celsius: " + temperature.celsius() + "\nKelvin: " + temperature.kelvin());

    } catch (NumberFormatException e) {
      System.out.println("==================\nInvalid Input: Double Required");
      userInput();
    }
  }
}
