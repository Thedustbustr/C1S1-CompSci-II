package Main;

/**
 * A simple class to store data on an employee
 */
public abstract class Employee implements EmployeeData {
  protected String name;
  protected int age;
  protected String spotifyWrapped;
  protected String socialSecurityNumber;
  protected String nameOfChildhoodPet;
  protected String mothersMaidenName;

  /**
   * Constructor
   * @param name The name of the employee
   * @param age The age of the employee
   * @param spotifyWrapped The spotify wrapped of the employee
   * @param socialSecurityNumber The social security number of the employee
   * @param nameOfChildhoodPet The name of the childhood pet of the employee
   * @param mothersMaidenName The name of the employee's mother's maiden name
   */
  public Employee(String name, int age, String spotifyWrapped, String socialSecurityNumber,
      String nameOfChildhoodPet, String mothersMaidenName) {

    this.name = name;
    this.age = age;
    this.spotifyWrapped = spotifyWrapped;
    this.socialSecurityNumber = socialSecurityNumber;
    this.nameOfChildhoodPet = nameOfChildhoodPet;
    this.mothersMaidenName = mothersMaidenName;
  }

  /** 
   * Gets name
   * @return String
   */
  public String getName() {
    return this.name;
  }

  /** 
   * Gets age
   * @return int
   */
  public int getAge() {
    return this.age;
  }

  /** 
   * Gets spotify wrapped
   * @return String
   */
  public String getSpotifyWrapped() {
    return this.spotifyWrapped;
  }

  /** 
   * Gets social security number
   * @return String
   */
  public String getSocialSecurityNumber() {
    return this.socialSecurityNumber;
  }

  /** 
   * Gets name of childhood pet
   * @return String
   */
  public String getNameOfChildhoodPet() {
    return this.nameOfChildhoodPet;
  }

  /** 
   * Gets mothers maiden name
   * @return String
   */
  public String getMothersMaidenName() {
    return this.mothersMaidenName;
  }

  /** 
   * To string
   * @return String
   */
  @Override
  public String toString() {
    return "Name: " + this.name + "\nAge: " + this.age + "\nSpotify Wrapped: " + this.spotifyWrapped
        + "\nName Of Childhood Pet: " + this.nameOfChildhoodPet + "\nMothers Maiden Name: " + this.mothersMaidenName;
  }
}
