package Main;

import java.util.Date;
import java.util.UUID;

import lib.Callback;
import lib.CallbackManager;

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

    Programmer exampleClass = new Programmer(exampleCallbackManger, cbid, new Date(), Language.java);
    exampleClass.ProcessSomeReallyComplexData();

    System.out.println("To String:\n" + exampleClass + "\n=======================");

    Programmer exampleClass1 = new Programmer(exampleCallbackManger, cbid, new Date(), Language.java);
    Programmer exampleClass2 = new Programmer(exampleCallbackManger, cbid, new Date(), Language.c);
    System.out.println("Do two class 1 and 2 equal each other? " + exampleClass1.equals(exampleClass2));

    Programmer exampleClass3 = new Programmer(exampleCallbackManger, cbid, new Date(), Language.java);
    Programmer exampleClass4 = new Programmer(exampleClass3);
    System.out.println("Do two class 3 and 4 equal each other? " + exampleClass3.equals(exampleClass4));
  }
}