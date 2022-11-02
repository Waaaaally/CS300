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
 * Simulates a room that has a location and capacity
 */
public class Room {
  private String location; //The building AND room number, e.g. "Noland 168"
  private int capacity; //The max number of people who can eb in the room at a time

  /**
   * Creates a room object with a location and capacity
   * @param location - Name of where the room is
   * @param capacity - How many students can be held in the room
   */
  public Room(String location, int capacity){
    if(capacity < 0) throw new IllegalArgumentException("The room cannot hold negative people.");

    this.location = location;
    this.capacity = capacity;
  }

  /**
   * Gets the location of the current room object
   * @return the location of the room
   */
  public String getLocation() {
    return location;
  }

  /**
   * Gets the capacity of the current room object
   * @return capacity of room
   */
  public int getCapacity(){
    return capacity;
  }

  /**
   * Returns a NEW Room object with the same location as this one, but with  a capacity less than
   * this one's by the argument's amount.
   * @param reduction
   * @throws IllegalArgumentException if the argument is greater than the given room's capacity.
   * @return
   */
  public Room reduceCapacity(int reduction){
    if(reduction > capacity) throw new IllegalArgumentException("Reduction can't be more than cap");

    Room reducedRoom = new Room(location, capacity - reduction);
    return reducedRoom;
  }
}
