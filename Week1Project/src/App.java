import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * I haven't looked into Java lambdas too much so I used
 * https://www.geeksforgeeks.org/method-within-method-in-java/ to figure it out
 */

interface Invalid {
  float run() throws Exception;
}

enum Type {
  organism, days
}

public class App {
  public static void main(String[] args) throws Exception {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    System.out.println("Population size predictor\n===================");

    int organisms = GetIntCount(reader, Type.organism);
    float percentage = GetPrecentage(reader);
    int days = GetIntCount(reader, Type.days); // <--- This could be a float but im not sure its necessary

    float tmpOrganisms = organisms;
    for (int i = 0; i < days; i++) {
      tmpOrganisms = tmpOrganisms * (percentage + 1f);
    }

    organisms = Math.round(tmpOrganisms); // <--- The organisms should be a whole number... i'm not gonna have 10.2 oragnisms
    System.out
        .println("The population of the organisms after " + days + " days will be " + organisms);
  }

  private static int GetIntCount(BufferedReader reader, Type type) throws Exception {
    try {
      System.out.println(type == Type.organism ? "Enter the starting number of organisms: "
          : "Enter the amount of days the population will increase: ");

      return Integer.parseInt(reader.readLine());

    } catch (NumberFormatException e) {
      System.out.println("==================\nInvalid Input: Integer Required");
      return GetIntCount(reader, type);

    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  private static float GetPrecentage(BufferedReader reader) throws Exception {
    Invalid invalid = () -> { // <--- Don't want to have retype code :)
      System.out.println("==================\nInvalid Input: Precentage Required");
      return GetPrecentage(reader);
    };

    try {
      System.out.println("Enter the daily population increase (in percentage): ");
      String str = reader.readLine();
      if (str.charAt(str.length() - 1) != '%') {
        return invalid.run();
      }

      return (Float.parseFloat(str.substring(0, str.length() - 1)) / 100);

    } catch (NumberFormatException e) {
      return invalid.run();

    } catch (Exception e) {
      throw new Exception("Error Occured: " + e.getMessage());
    }
  }
}