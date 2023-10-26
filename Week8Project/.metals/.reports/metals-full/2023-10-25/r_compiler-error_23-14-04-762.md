file:///E:/UtilityFolders/Documents/.Useful%20Stuff/2023/s1/Comp%20Sci%20II/Week8Project/src/Main/Parser/Parsers/ParsedAssignment.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

action parameters:
offset: 1686
uri: file:///E:/UtilityFolders/Documents/.Useful%20Stuff/2023/s1/Comp%20Sci%20II/Week8Project/src/Main/Parser/Parsers/ParsedAssignment.java
text:
```scala
package Main.Parser.Parsers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import Main.Assignment;
import lib.Callbacks.CallbackManager;

public class ParsedAssignment extends ParsedData implements Runnable {
  private List<Assignment> assignments;
  private UUID cbid;

  public ParsedAssignment(UUID cbid, String url) {
    this.cbid = cbid;
    super.url = url;
  }

  public Assignment[] getAssignments() {
    //Assignment[] a = new Assignment[assignments.size()];
    //return assignments.toArray(a);

    return new Assignment[] { new Assignment(null, null, 0) };
  }

  @Override
  public void run() {
    try {
      Errors.Logging.Log("Parser thread started successfully!", Errors.Threads.parser);

      ArrayList<Assignment> threadSafeList = new ArrayList<Assignment>();

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

     @@ Errors.Logging.Log("===============================\nParsed Data:\n" + threadSafeList + "\n===============================", Errors.Threads.parser);

      this.assignments = Collections.synchronizedList(threadSafeList);
      CallbackManager.INSTANCE.executeCallback(this.cbid, this);

      s.close();
      this.cbid = null;

    } catch (Exception e) {
      Errors.Logging.Error("An error has occured when reading and parsing API data!", Errors.Threads.parser, e);
    }
  }
}

```



#### Error stacktrace:

```
scala.collection.Iterator$$anon$19.next(Iterator.scala:973)
	scala.collection.Iterator$$anon$19.next(Iterator.scala:971)
	scala.collection.mutable.MutationTracker$CheckedIterator.next(MutationTracker.scala:76)
	scala.collection.IterableOps.head(Iterable.scala:222)
	scala.collection.IterableOps.head$(Iterable.scala:222)
	scala.collection.AbstractIterable.head(Iterable.scala:933)
	dotty.tools.dotc.interactive.InteractiveDriver.run(InteractiveDriver.scala:168)
	scala.meta.internal.pc.MetalsDriver.run(MetalsDriver.scala:45)
	scala.meta.internal.pc.HoverProvider$.hover(HoverProvider.scala:34)
	scala.meta.internal.pc.ScalaPresentationCompiler.hover$$anonfun$1(ScalaPresentationCompiler.scala:329)
```
#### Short summary: 

java.util.NoSuchElementException: next on empty iterator