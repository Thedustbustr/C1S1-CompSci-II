import java.util.UUID;

public class Customer extends Person {
  private UUID customerID;
  private boolean mailingList;

  public UUID getCustomerID() {
    return this.customerID;
  }

  public boolean onMailingList() {
    return this.mailingList;
  }

  public Customer() {
    this.customerID = UUID.randomUUID();
    this.mailingList = false;
  }

  public Customer(String[] name, String address, String phone, UUID customerID, boolean mailingList) {
    super(name, address, phone);

    this.customerID = customerID;
    this.mailingList = mailingList;
  }
}
