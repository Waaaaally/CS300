//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Recursive Exam Scheduler
// Course:   CS 300 Spring 2022
//
// Author:   Pritish Das
// Email:    pdas26@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: N/A
// Partner Email: N/A
// Partner Lecturer's Name: N/A
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: None
// Online Sources: None
//
///////////////////////////////////////////////////////////////////////////////

/**
 * Simulates a course that has a name and # of students enrolled
 */
public class Course {
  private String name; //the name of the course, e.g. "CS300"
  private int numStudents; //the number of students enrolled in the course, e.g. 250

  /**
   * Constructs a course object with a name and number of students
   * @param name name of the course
   * @param numStudents total # of students enrolled
   */
  public Course(String name, int numStudents){
    if(numStudents < 0) throw new IllegalArgumentException("# of Students can't be negative");

    this.name = name;
    this.numStudents = numStudents;
  }

  /**
   * gets name of course
   * @return name of current course object
   */
  public String getName(){
    return name;
  }

  /**
   * gets number of students enrolled in course
   * @return total number of students in current course object
   */
  public int getNumStudents(){
    return numStudents;
  }
}
