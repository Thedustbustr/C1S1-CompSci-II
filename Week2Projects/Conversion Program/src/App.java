import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
  private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

  public static void main(String[] args) throws Exception {
    System.out.println("Enter a distance in meters: ");
    double measurement = Double.parseDouble(reader.readLine());

    MainMenu("Choose conversion of " + measurement + " meters", measurement);
  }

  public static void MainMenu(String message, double conversion) throws Exception {
    System.out.println(message);
    System.out.println(" 1. Convert to kilometers\n 2. Convert to inches\n 3. Convert to feet\n 4. Quit the program");

    int option = Integer.parseInt(reader.readLine());

    switch (option) {
      case 1:
        showKilometers(conversion);
      case 2:
        showInches(conversion);
      case 3:
        showFeet(conversion);
      case 4:
        Quit();
    }
  }

  public static void showKilometers(double n) throws Exception {
    MainMenu(n + " meters is " + n * 0.001 + "km", n);
  }

  public static void showInches(double n) throws Exception {
    MainMenu(n + " meters is " + n * 3.281 * 12 + "in", n);
  }

  public static void showFeet(double n) throws Exception {
    MainMenu(n + " meters is " + n * 3.281 + "ft", n);
  }

  public static void Quit() {
    System.exit(0);
  }
}
