import java.util.Iterator;
import java.util.NoSuchElementException;

//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Open Position w/ Priority Queue & Heaps
// Course:   CS 300 Spring 2022
//
// Author:   Pritish Das
// Email:    pdas26@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    N/A
// Partner Email:   N/A
// Partner Lecturer's Name: N/A
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         None
// Online Sources:  None
//
///////////////////////////////////////////////////////////////////////////////

/**
 * Implements an iterator for Applications, which returns the Applications in order from earliest to
 * latest based on their order in a priority queue.
 */
public class ApplicationIterator implements Iterator<Application> {
  private ApplicationQueue queue; // a copy of the priority queue of applications to iterate over


  /**
   * Creates a new ApplicationIterator which iterates over the elements of the given ApplicationQueue
   * in order from lowest-scored application to the highest-scored application.
   * 
   * @param queue the ApplicationQueue to iterate over
   */
  public ApplicationIterator(ApplicationQueue queue) {
    this.queue = queue.deepCopy(); // we are going to work on a deep copy of the provided queue
                                   // as input parameter. 
  }

  /**
   * Returns true if the iteration has more elements.
   * 
   * @return {@code true} if the iteration has more elements
   */
  @Override
  public boolean hasNext() {
    return (!queue.isEmpty()); //todo I think works
  }

  /**
   * Returns the next element in the iteration.
   * 
   * @return the next element in the iteration.
   * @throws NoSuchElementException with a descriptive error message if the iteration has no more
   *                                elements
   */
  @Override
  public Application next() {
    if (!hasNext())
      throw new NoSuchElementException("No more elements in this iteration");
    return queue.dequeue();  // TODO
  }
}
