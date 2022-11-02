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
 * Simulates a schedule that creates a relationship between available rooms and courses adhering
 * to their constrictions
 */
public class Schedule {
  private Room[] rooms; //An array of the Room objects available for exams
  private Course[] courses; //An array of the Course objects which require exam rooms
  private int[] assignments; //Arr where int(ex. 3) @ index N is the index of the room that
  // courses[N] has been assigned to.
  //Courses and Assignments are parallel. Course N is assigned to Room[ Assignments[N] ]

  /**
   * PUBLIC version that creates a room object, there are no assignemnts initialized
   * @param rooms array of room objects
   * @param courses array of course objects
   */
  public Schedule(Room[] rooms, Course[] courses){
    this.rooms = rooms;
    this.courses = courses;
    assignments = new int[courses.length];

    for(int i = 0; i < assignments.length; i++){ //Initialize w/ -1 to show unassigned. Theres
      // prolly a better method LMAO
      assignments[i] = -1;
    }
  }

  /**
   * Private version that creates a room object and can make unique assignments
   * @param rooms array of room objects
   * @param courses array of course objects
   * @param assignments
   */
  private Schedule(Room[] rooms, Course[] courses, int[] assignments){
    this.rooms = rooms;
    this.courses = courses;
    this.assignments = assignments;
  }


  /**
   * Gets the number of room objects available
   * @return the number of rooms available in this schedule
   */
  public int getNumRooms() {
    int totalRooms = 0;

    for(int i = 0; i < rooms.length; i++){
      if(rooms[i] != null) totalRooms++;
    }
    return totalRooms;
  }

  /**
   * Gets the specified available room object
   * @param roomIndex location of room in rooms array
   * @throws IndexOutOfBoundsException if the given index is invalid
   * @return the Room object at the given index in the rooms array
   */
  public Room getRoom(int roomIndex){
    if(roomIndex >= rooms.length || roomIndex <= -1)
      throw new IndexOutOfBoundsException("invalid input");

    return rooms[roomIndex];
  }


  /**
   * Gets the total number of course objects available
   * @return the number of courses in this schedule
   */
  public int getNumCourses(){
    int totalCourses = 0;

    for(int i = 0; i < courses.length; i++){
      if(courses[i] != null) totalCourses++;
    }
    return totalCourses;
  }

  /**
   * Gets the specified course object
   * @param courseIndex
   * @throws IndexOutOfBoundsException if the given index is invalid
   * @return the Course object at the given index in the courses array
   */
  public Course getCourse(int courseIndex){
    if(courseIndex >= courses.length || courseIndex <= -1)
      throw new IndexOutOfBoundsException("invalid input");

    return courses[courseIndex];
  }

  /**
   * Checks if a given index has been assigned a room
   * @param assignmentIndex
   * @return returns true if and only if the course at the given index has been assigned a room
   * otherwise it returns false;
   */
  public boolean isAssigned(int assignmentIndex){
    if(assignments[assignmentIndex] != -1)
      return true;
    else
      return false;
  }

  /**
   * Gets the assignment of a specific course which is its associated room in the rooms array
   * @param assignmentIndex location of room assignment in assignment index
   * @throws IllegalArgumentException if the course has not been assigned a room
   * @throws IndexOutOfBoundsException if the given course index is invalid
   * @return Room object assigned to the course at the given index;
   */
  public Room getAssignment(int assignmentIndex){
    if(assignmentIndex < 0 || assignmentIndex >= assignments.length)
      throw new IndexOutOfBoundsException("Invalid index given");

    if(assignments[assignmentIndex] == -1)
      throw new IllegalArgumentException("Course was not assigned room");

    Room returnedRoom = rooms[assignments[assignmentIndex]];

    return returnedRoom;
  }

  /**
   * Checks to see if the assignments array is filled out for eveyr course
   * @return true if all courses are assigned, false otherwise
   */
  public boolean isComplete(){
    for(int i = 0; i < assignments.length; i++){
      if(assignments[i] == -1) return false;
    }
    return true;
  }
  /**
   * Assigns a relationship between a course and room through altering a new assignments array
   * @param courseIndex index of course in the courses array
   * @param roomIndex index of room int the rooms array
   * @throws IndexOutOfBoundsException if given course/room index is invalid
   * @throws IllegalArgumentException if the given course has already been assigned a room, or if
   * the room does not have sufficient capacity
   * @return NEW schedule object with the course at the first argument index assigned to the room
   * at the second argument index
   */
  public Schedule assignCourse(int courseIndex, int roomIndex){
    if(courses[courseIndex] == null){
      throw new NullPointerException("The course in that index is null");
    }
    else if(rooms[roomIndex] == null){
      throw new NullPointerException("The room in that index is null");
    }

    if(courseIndex < 0 || courseIndex >= courses.length)
      throw new IndexOutOfBoundsException("Invalid course index given");
    if(roomIndex < 0 || roomIndex >= rooms.length)
      throw new IndexOutOfBoundsException("Invalid room index given");
    if(assignments[courseIndex] != -1){
      throw new IllegalArgumentException("Course alr assigned");}
    if(courses[courseIndex].getNumStudents() > rooms[roomIndex].getCapacity())
      throw new IllegalArgumentException("Room reached capacity already");

    int[] newAssignments = new int[assignments.length];
    for(int i = 0; i < assignments.length; i++){
      newAssignments[i] = assignments[i];
    }

    Room[] newRooms = new Room[rooms.length];
    for(int i = 0; i < rooms.length; i++){
      newRooms[i] = rooms[i];
    }

    Room reducedRoom = rooms[roomIndex].reduceCapacity(courses[courseIndex].getNumStudents());

    newRooms[roomIndex] = reducedRoom;
    newAssignments[courseIndex] = roomIndex;

    //Assignments arr where int @ assignments[N] is index of room in rooms[] that courses[N] has
    // been assigned to.
    //Courses and Assignments are parallel. Course N is assigned to Room[ Assignments[N] ]

    Schedule newSchedule = new Schedule(newRooms, courses, newAssignments);


    return newSchedule;
  }

  @Override
  /**
   * Overridden toString method that prints the counts of the schedule object. Specifically the
   * roomassignments per course
   */
  public String toString(){
    //{CS300: AG 125, CS200: HUM 3650, CS400: Unassigned}
    // ["CS300", "CS200", "CS400"], and the rooms had locations
    // ["SCI 180","HUM 3650", "AG 125"].
    // [2, 1, -1] is the assignments array
    // Note that CS400 has not yet been assigned a room so it's -1.
    String toString = "{";
    for(int i = 0; i < assignments.length-1; i++){
      if(assignments[i] == -1){
        toString+= courses[i].getName() + ": Unassigned, ";
      }
      else{
        toString+= courses[i].getName() + ": " + rooms[assignments[i]].getLocation() + ", ";
      }
    }
    int finalIndex = this.getNumCourses()-1;

    if(assignments[finalIndex] == -1)
      toString+= courses[finalIndex].getName() + ": Unassigned}";
    else
      toString+= courses[finalIndex].getName() + ": " + rooms[assignments[finalIndex]].getLocation() + "}";

    return toString;
  }
}
