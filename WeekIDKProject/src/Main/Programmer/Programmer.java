package Main.Programmer;

import java.util.Date;
import java.util.UUID;

import Main.Employee;
import lib.CallbackManager;

/**
 * A simple class to store data on a programmer
 */
public class Programmer extends Employee {
  private CallbackManager<Programmer> callbackManager;
  private UUID cbid;

  private Date dateOfLastCoffee;
  private Language favoriteLanguage;

  /**
   * It has been to long
   */
  public static final boolean hasItBeenTooLongSinceCoffeeHasBeenDrank = true;

  /** 
   * @return CallbackManager
   */
  public CallbackManager<Programmer> getCallbackManager() {
    return this.callbackManager;
  }

  /** 
   * @return UUID
   */
  public UUID getcbid() {
    return this.cbid;
  }

  /** 
   * @return Date
   */
  public Date getDateOfLastCoffee() {
    return this.dateOfLastCoffee;
  }

  /** 
   * @return Language
   */
  public Language getFavoriteLanguage() {
    return this.favoriteLanguage;
  }

  /**
   * Constructor
   * @param callbackManager The callback manager
   * @param cbid The callback uuid
   * @param dateOfLastCoffee The date since the last coffee was drank
   * @param favoriteLanguage The programmers favorite language
   * @param name The name of the employee
   * @param age The age of the employee
   * @param spotifyWrapped The spotify wrapped of the employee
   * @param socialSecurityNumber The social security number of the employee
   * @param nameOfChildhoodPet The name of the childhood pet of the employee
   * @param mothersMaidenName The name of the employee's mother's maiden name
   */
  public Programmer(CallbackManager<Programmer> callbackManager, UUID cbid, Date dateOfLastCoffee,
      Language favoriteLanguage, String name, int age, String spotifyWrapped, String socialSecurityNumber,
      String nameOfChildhoodPet, String mothersMaidenName) {

    super(name, age, spotifyWrapped, socialSecurityNumber, nameOfChildhoodPet, mothersMaidenName);

    this.callbackManager = callbackManager;
    this.cbid = cbid;

    this.dateOfLastCoffee = dateOfLastCoffee;
    this.favoriteLanguage = favoriteLanguage;
  }

  /**
   * Deep copy constructor
   * @param programmer The object to deep copy from
   */
  public Programmer(Programmer programmer) {
    super(programmer.name, programmer.age, programmer.spotifyWrapped, programmer.socialSecurityNumber,
        programmer.nameOfChildhoodPet, programmer.mothersMaidenName);

    this.callbackManager = new CallbackManager<Programmer>(programmer.callbackManager.getActiveCallbacks());
    this.cbid = programmer.getcbid();

    this.dateOfLastCoffee = (Date) programmer.getDateOfLastCoffee().clone();
    this.favoriteLanguage = programmer.favoriteLanguage;
  }

  /** 
  * Simulates processing really complicated data
  */
  public void ProcessSomeReallyComplexData() {
    Thread simulateReallyComplexProcess = new Thread(() -> {
      try {
        Errors.Logging.Log("Started new waiting thread", Errors.Threads.waiting);
        Thread.sleep(10000);
        this.callbackManager.executeCallback(cbid, this);
      } catch (Exception e) {
        Errors.Logging.Error("An erros has occured!", Errors.Threads.waiting, e);
      }
    });

    Errors.Logging.Log("Starting new waiting thread", Errors.Threads.main);
    simulateReallyComplexProcess.start();
  }

  /** 
   * Calculates the time since the last coffee was drank
   * @return String
   */
  public String CalculateTimeFromLastCoffee() {
    long differenceInMilliseconds = new Date().getTime() - this.dateOfLastCoffee.getTime();

    return differenceInMilliseconds / 60000d + " minutes!\nThats far too long!";
  }

  /** 
   * @return String
   */
  @Override
  public String getSpotifyWrapped() {
    return "Too much data";
  }

  /** 
   * To string
   * @return String
   */
  @Override
  public String toString() {
    return "Callback Manager: " + this.callbackManager + "\nCallback UUID: " + this.cbid + "\nDateOfLastCoffee: "
        + this.dateOfLastCoffee + "\nFavorite Language: " + this.favoriteLanguage + "\n" + super.toString();
  }

  /** 
   * Equals method
   * @param o Object to be compared
   * @return boolean
   */
  @Override
  public boolean equals(Object o) {
    if (o == this) { /* Check if the object is this class */
      return true;
    }

    if (!(o instanceof Programmer)) { /* Check if the object is type Assignment */
      return false;
    }

    /* Checking individual values */
    Programmer programmer = ((Programmer) o);
    if (programmer.callbackManager.equals(this.callbackManager) && programmer.cbid.equals(this.cbid)
        && programmer.dateOfLastCoffee.equals(dateOfLastCoffee)
        && programmer.favoriteLanguage == this.favoriteLanguage
        && programmer.name == this.name
        && programmer.age == this.age
        && programmer.spotifyWrapped == this.spotifyWrapped
        && programmer.socialSecurityNumber == this.socialSecurityNumber
        && programmer.nameOfChildhoodPet == this.nameOfChildhoodPet
        && programmer.mothersMaidenName == this.mothersMaidenName) {
      return true;
    }

    return false; /* Otherwise false */
  }
}
