import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
  public static void main(String[] args) throws Exception {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    System.out.println("Enter your grade here: ");
    double grade = Double.parseDouble(reader.readLine());
    System.out.print("You have a(n): ");

    if (grade >= 93d) {
      System.out.print("A");
    } else if (grade >= 90d && grade < 93d) {
      System.out.print("A-");
    } else if (grade >= 87d && grade < 90d) {
      System.out.print("B+");
    } else if (grade >= 83d && grade < 87d) {
      System.out.print("B");
    } else if (grade >= 80d && grade > 83d) {
      System.out.print("B-");
    } else if (grade >= 77d && grade < 80d) {
      System.out.print("C+");
    } else if (grade >= 70d && grade < 77d) {
      System.out.print("C");
    } else if (grade >= 60d && grade < 70d) {
      System.out.print("D");
    } else {
      System.out.print("F");
    }
  }
}
