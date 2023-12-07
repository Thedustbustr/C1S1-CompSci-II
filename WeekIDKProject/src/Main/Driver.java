package Main;

import java.util.Date;
import java.util.UUID;

import Main.Programmer.Programmer;
import lib.Callback;
import lib.CallbackManager;
import Main.Programmer.Language;

/**
 * Main class
 */
public class Driver {
  /** 
   * Main method
   * @param args Main args
   * @throws Exception Just in case we get an error
   */
  public static void main(String[] args) throws Exception {
    Callback<Programmer> cb = data -> {
      System.out.println(data.CalculateTimeFromLastCoffee());
    };

    CallbackManager<Programmer> exampleCallbackManger = new CallbackManager<Programmer>();
    UUID cbid = exampleCallbackManger.registerCallback(cb);

    Programmer exampleClass = new Programmer(exampleCallbackManger, cbid, new Date(), Language.java, "John", 34,
        "This was kinda a joke idk what to put here",
        "I probably shouldn’t accidentally leaks someone social security number", "Fluffy", "Doe");
    exampleClass.ProcessSomeReallyComplexData();

    System.out.println("To String:\n" + exampleClass + "\n=======================");

    Programmer exampleClass1 = new Programmer(exampleCallbackManger, cbid, new Date(), Language.java, "Jane", 32,
        "This was kinda a joke idk what to put here",
        "I probably shouldn’t accidentally leaks someone social security number", "Ginger", "Berger");
    Programmer exampleClass2 = new Programmer(exampleCallbackManger, cbid, new Date(), Language.c, "Bob", 21,
        "This was kinda a joke idk what to put here",
        "I probably shouldn’t accidentally leaks someone social security number", "Wensday", "Bennett");
    System.out.println("Do two class 1 and 2 equal each other? " + exampleClass1.equals(exampleClass2));

    Programmer exampleClass3 = new Programmer(exampleCallbackManger, cbid, new Date(), Language.java, "John", 48,
        "This was kinda a joke idk what to put here",
        "I probably shouldn’t accidentally leaks someone social security number", "Kiri", "Groves");
    Programmer exampleClass4 = new Programmer(exampleClass3);
    System.out.println("Do two class 3 and 4 equal each other? " + exampleClass3.equals(exampleClass4));

    System.out.println("What is the programmers spotify wrapped? " + exampleClass4.getSpotifyWrapped());
  }
}