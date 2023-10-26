package Main;

import java.util.ArrayList;

/**
 * The gradebook for a specific student
 */
public class GradeBook {
  String studentName;
  String courseName;

  ArrayList<Assignment> assignments = new ArrayList<Assignment>();

  /**
   * Constructor
   * @param studentName The students name
   * @param courseName The courses name
   */
  public GradeBook(String studentName, String courseName) {
    this.studentName = studentName;
    this.courseName = courseName;
  }

  
  /** 
   * Adds a single assignment to the grade book
   * @param assignment The assignment to add
   */
  public void addAssignments(Assignment assignment) {
    this.assignments.add(assignment);
  }

  
  /** 
   * Adds multiple assignments to the grade book
   * @param assignment The array of assignments to add
   */
  public void addAssignments(Assignment[] assignment) {
    for (Assignment a : assignment) {
      this.assignments.add(a);
    }
  }

  
  /** 
   * Returns the average grade of the student's course
   * @return String The average grade
   */
  public String courseGrade() {
    double n = 0;
    for (Assignment a : this.assignments) {
      n += a.getGrade();
    }

    return Assignment.gradeToLetter(n / this.assignments.size());
  }

  
  /** 
   * To string
   * @return String
   */
  @Override
  public String toString() {
    return "Student Name: " + studentName + "\nCourse Name: " + courseName + "\nFinal Grade: " + this.courseGrade();
  }
}
