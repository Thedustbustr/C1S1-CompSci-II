file:///E:/UtilityFolders/Documents/.Useful%20Stuff/2023/s1/Comp%20Sci%20II/Week8Project/src/Main/Assignment.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

action parameters:
offset: 2901
uri: file:///E:/UtilityFolders/Documents/.Useful%20Stuff/2023/s1/Comp%20Sci%20II/Week8Project/src/Main/Assignment.java
text:
```scala
package Main;

import java.util.Calendar;
import java.util.GregorianCalendar;

import Errors.Threads;

/**
 * The assignment class. Stores and maintains data for its assignment
 */
public class Assignment {
  private String name;
  private GregorianCalendar dueDate;
  private double grade;

  /**
  * Gets name
  * @return name of assignment
  */
  public String getName() {
    return name;
  }

  /**
  * Gets due date
  * @return the due date of the assignment
  */
  public GregorianCalendar getDueDate() {
    return dueDate;
  }

  /**
  * Gets grade
  * @return double value of grade
  */
  public double getGrade() {
    return grade;
  }

  /**
  * Gets name
  * @param name Name to set to
  */
  public void setName(String name) {
    this.name = name;
  }

  /**
  * Sets due date with GregorianCalendar class
  * @param dueDate The GregorianCalendar due date value
  */
  public void setDueDate(GregorianCalendar dueDate) {
    this.dueDate = dueDate;
  }

  /**
  * Sets sets due date 
  * @param year the year of the date
  * @param month the month of the date
  * @param dayOfMonth the day of the month
  * @param hourOfDay the hour of the day
  * @param minute the minute
  */
  public void setDueDate(int year, int month, int dayOfMonth, int hourOfDay, int minute) {
    this.dueDate = new GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute);
  }

  /**
  * Sets grade
  * @param grade the grade to set to
  */
  public void setGrade(double grade) {
    this.grade = grade;
  }

  /**
  * Constructor
  */
  public Assignment() {
    //
  }

  /**
  * Copy constructor
  * @param assignment the grade to set to
  */
  public Assignment(Assignment assignment) {
    this.name = assignment.getName();
    this.dueDate = assignment.getDueDate();
    this.grade = assignment.getGrade();
  }

  /**
   * Constructor
   * @param name the name of the assignment
   * @param due the due date of the assignment
   * @param grade the grade recieved on the assignment
   */
  public Assignment(String name, GregorianCalendar due, double grade) {
    this.name = name;
    this.dueDate = due;
    this.grade = grade;
  }

  /*
   * I was trying to find a better way to do switch statements between ranges and I came across this thread: https://stackoverflow.com/questions/10873590/using-switch-statement-with-a-range-of-value-in-each-case
   * I altered someone elses code to work better in this situation.
   */
  /**
  * Gets grade letter from grade double value
  * @param grade Double value of grade
  * @return grade letter
  */
  public static String gradeToLetter(double grade) {
    double[] thresholds = new double[] { 0, 55, 65, 70, 78.5, 81.5, 88.5, 91.5, 96.5, 100 };
    String strGrade;

    int n = 0;
    for (; grade >= (thresholds[n]); n++)
      ;@@
    switch (9 - n + 1) {
      case 1 -> strGrade = "A";
      case 2 -> strGrade = "A-";
      case 3 -> strGrade = "B+";
      case 4 -> strGrade = "B";
      case 5 -> strGrade = "B-";
      case 6 -> strGrade = "C+";
      case 7 -> strGrade = "C";
      case 8 -> strGrade = "D";
      case 9 -> strGrade = "F";
      default -> {
        Errors.Logging.Warning("Invalid grade value", Threads.main);
        return "N/A";
      }
    }

    return strGrade;
  }

  /**
  * Gets grade letter from grade double value
  * @return grade letter
  */
  public String getGradeLetter() {
    return gradeToLetter(this.grade);
  }

  /**
   * Sets grade from string
   * @param grade the grade letter to get double value from
   */
  public void setGrade(String grade) {
    System.out.println(grade);
    switch (grade) {
      case "A+" -> this.grade = 100;
      case "A" -> this.grade = 96;
      case "A-" -> this.grade = 91;
      case "B+" -> this.grade = 88;
      case "B" -> this.grade = 85;
      case "B-" -> this.grade = 81;
      case "C+" -> this.grade = 78;
      case "C" -> this.grade = 75;
      case "D" -> this.grade = 65;
      case "F" -> this.grade = 55;
      default -> Errors.Logging.Warning("Invalid grade value", Threads.main);
    }
  }

  /**
   * To string
   * @return String value
   */
  @Override
  public String toString() {
    return "Assignment: " + this.name + "\nDue: " + dueDate.get(Calendar.HOUR_OF_DAY) + ":"
        + dueDate.get(Calendar.MINUTE) + " " + dueDate.get(Calendar.MONTH) + "/" + dueDate.get(Calendar.DATE) + "/"
        + dueDate.get(Calendar.YEAR) + "\nGrade: " + getGradeLetter() + " [" + getGrade() + "]";
  }

  /**
  * Equals
  * @param o The object to be compared
  * @return Whether the two objects are equal
  */
  @Override
  public boolean equals(Object o) {
    if (o == this) { /* Check if the object is this class */
      return true;
    }

    if (!(o instanceof Assignment)) { /* Check if the object is type Assignment */
      return false;
    }

    /* Checking individual values */
    Assignment assignment = ((Assignment) o);
    if (assignment.name == this.name && assignment.dueDate.equals(this.dueDate) && assignment.grade == this.grade) {
      return true;
    }

    return false; /* Otherwise false */
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