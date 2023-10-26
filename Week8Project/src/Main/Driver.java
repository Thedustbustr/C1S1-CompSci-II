package Main;

import java.util.UUID;

import Main.Parser.Parsers.ParsedAssignment;
import lib.Callback;

/**
 * Main class
 * Sorry if I hit the api alot, I wanted to see if I could get anything but an "F" average (which I did)
 */
public class Driver {
  /**
   * Max attempts before giving up on
   */
  private static final int MAX_ATTEMPTS = 25;

    /**
   * The number of attempts that have occured
   */
  private static int attempts = 0;

  /**
   * Should it even try to get something other than a "F" average
   */
  public static boolean tryToGetAnythingButAnF = false;

  /** 
   * Main method
   * @param args Main args
   * @throws Exception Just in case we get an error
   */
  public static void main(String[] args) throws Exception {
    program();
  }

  /** 
  * In this format due to "tryToGetAnythingButAnF" system
  */
  public static void program() {
    GradeBook gradeBook = new GradeBook("Cyrus", "Comp Sci");

    Callback<ParsedAssignment> cb = data -> {
      gradeBook.addAssignments(data.getAssignments());
      System.out.println(gradeBook.toString());

      attempts++;
      if (tryToGetAnythingButAnF == true && (gradeBook.courseGrade().equals("F")) && attempts < MAX_ATTEMPTS) {
        program();
      } else {
        return;
      }
    };

    UUID cbid = lib.CallbackManager.INSTANCE.registerCallback(cb);

    Errors.Logging.Log("Attempting startup of parser thread...", Errors.Threads.main);
    ParsedAssignment parsedAssignment = new ParsedAssignment(cbid, "https://roan-equinox-chauffeur.glitch.me/grades");
    Thread parserThread = new Thread(parsedAssignment);

    parserThread.start();
  }
}