public class Person implements Comparable<Person> {
  protected String firstName;
  protected String lastName;

  protected String address;
  protected String phone;

  public String getFirstName() {
    return this.firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public String getAddress() {
    return this.address;
  }

  public String getPhone() {
    return this.phone;
  }

  public Person() {
    this.firstName = "John/Jane";
    this.lastName = "Doe";

    this.address = null;
    this.phone = null;
  }

  public Person(String[] name, String address, String phone) {
    this.firstName = name[0];
    this.lastName = name[1];

    this.address = address;
    this.phone = phone;
  }

  @Override
  public int compareTo(Person o) {
    String fullName = this.firstName + " " + this.lastName;
    String otherFullName = o.firstName + " " + o.lastName;

    return fullName.compareTo(otherFullName);
  }
}
