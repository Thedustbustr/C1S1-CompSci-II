package Main;

import java.util.GregorianCalendar;

/**
 * Main class
 */
public class Driver {
  /** 
   * Main method
   * @param args default
   */
  public static void main(String[] args) {
    Assignment assignment1 = new Assignment("Test Assignment 1", new GregorianCalendar(), 0);
    assignment1.setGrade(98.7);

    Assignment assignment2 = new Assignment();

    assignment2.setDueDate(1970, 6, 26, 23, 59);
    assignment2.setName("Test Assignment 2");
    assignment2.setGrade("B");

    System.out.println(assignment1.getName() + " Class:\n" + assignment1 + "\n====================");
    System.out.println(assignment2.getName() + " Class:\n" + assignment2 + "\n====================");

    System.out.println("Average Grade: " + (assignment1.getGrade() + assignment2.getGrade()) / 2d);

    Assignment assignment3 = new Assignment(assignment2);

    System.out.println("\n\nDoes my copy constructor work? " + assignment2.equals(assignment3));

    System.out.println("\n\nDo these objects equal each other? " + assignment2.equals(assignment1));

    System.out.println("\n\nWhats 84.2% as a grade letter? " + Assignment.gradeToLetter(84.2d));
  }
}
