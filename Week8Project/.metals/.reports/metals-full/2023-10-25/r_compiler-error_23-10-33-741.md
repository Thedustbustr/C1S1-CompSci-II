file:///E:/UtilityFolders/Documents/.Useful%20Stuff/2023/s1/Comp%20Sci%20II/Week8Project/src/Main/Driver.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

action parameters:
offset: 162
uri: file:///E:/UtilityFolders/Documents/.Useful%20Stuff/2023/s1/Comp%20Sci%20II/Week8Project/src/Main/Driver.java
text:
```scala
package Main;

import java.util.UUID;

import Main.Parser.Parsers.ParsedAssignment;
import lib.Callbacks.Callback;

/**
 * Main class
 */
public class Driver {
  @@final int MAX_ATTEMPTS = 25;
  private int attempts;

  public static void main(String[] args) throws Exception {

  }

  public static void iDontWantToFail() {
    GradeBook gradeBook = new GradeBook("Cyrus", "Comp Sci");

    Callback<ParsedAssignment> cb = data -> {
      gradeBook.addAssignments(data.getAssignments());
      System.out.println(gradeBook.toString());
    };

    UUID cbid = lib.Callbacks.CallbackManager.INSTANCE.registerCallback(cb);

    Errors.Logging.Log("Attempting startup of parser thread...", Errors.Threads.main);
    ParsedAssignment parsedAssignment = new ParsedAssignment(cbid, "https://roan-equinox-chauffeur.glitch.me/grades");
    Thread parserThread = new Thread(parsedAssignment);

    parserThread.start();

    if (gradeBook.courseGrade().equals("F") || attempts > MAX_ATTEMPTS) {
      iDontWantToFail();
    } else {
      return;
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