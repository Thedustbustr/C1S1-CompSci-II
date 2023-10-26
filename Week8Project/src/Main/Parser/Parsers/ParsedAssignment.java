package Main.Parser.Parsers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import Main.Assignment;
import lib.CallbackManager;

/**
 * The PrasedAssignment class holds the parser and the parsed data for assignment objects
 */
public class ParsedAssignment extends ParsedData implements Runnable {
  private List<Assignment> assignments;
  private UUID cbid;

  /**
   * Constructor
   * @param cbid The callback UUID
   * @param url The URL of the API
   */
  public ParsedAssignment(UUID cbid, String url) {
    this.cbid = cbid;
    super.url = url;
  }

  /** 
   * Gets all assignments that were parsed
   * @return Assignment[]
   */
  public Assignment[] getAssignments() {
    Assignment[] a = new Assignment[assignments.size()];
    return assignments.toArray(a);
  }

  /**
  * Main parsing method (threaded)
  */
  @Override
  public void run() {
    try {
      Errors.Logging.Log("Parser thread started successfully!", Errors.Threads.parser);

      ArrayList<Assignment> threadSafeList = new ArrayList<Assignment>(); // Although my efforts its not thread safe (I tried it) 🥲

      Errors.Logging.Log("Hitting API Endpoint...", Errors.Threads.parser);

      URL url = new URL(super.url);
      Scanner s = new Scanner(url.openStream());
      String input = s.nextLine();

      Errors.Logging.Log("Data Recieved: " + input, Errors.Threads.parser);
      Errors.Logging.Log("Parsing...", Errors.Threads.parser);
      String[] assignments = input.split(",");
      for (int i = 0; i < assignments.length; i++) {
        String[] data = assignments[i].split(":");

        GregorianCalendar date = new GregorianCalendar();
        date.setTimeInMillis(Long.parseLong(data[1]));

        threadSafeList.add(new Assignment(data[0], date, Double.parseDouble(data[2])));
      }

      Errors.Logging.Log(
          "===============================\nParsed Data:\n" + threadSafeList + "\n===============================",
          Errors.Threads.parser);

      this.assignments = Collections.synchronizedList(threadSafeList);
      CallbackManager.INSTANCE.executeCallback(this.cbid, this);

      s.close();
      this.cbid = null;

    } catch (Exception e) {
      Errors.Logging.Error("An error has occured when reading and parsing API data!", Errors.Threads.parser, e);
    }
  }
}
