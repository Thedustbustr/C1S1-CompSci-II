import java.util.UUID;

public class Driver {
  public static void main(String[] args) throws Exception {
    Customer customer = new Customer(new String[] { "John", "Doe" }, "340 Dekalb Pike, Blue Bell, PA 19422",
        "555-555-5555", UUID.randomUUID(), true);
    Customer customer2 = new Customer(new String[] { "Jane", "Doe" }, "340 Dekalb Pike, Blue Bell, PA 19422",
        "555-555-5555", UUID.randomUUID(), false);

    System.out.println("Customer comparison: " + customer.compareTo(customer2));
  }
}
