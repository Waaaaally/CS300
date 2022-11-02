import java.util.ArrayList;

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
// Persons: Mingrui Liu - Provided intuition and guidance on findScheduleHelper's control flow
// through exception handling
// Online Sources: None
//
///////////////////////////////////////////////////////////////////////////////

/**
 * Simulates an exam scheduler that creates a schedule based on input courses and rooms
 */
public class ExamScheduler {

  /**
   * Finds a possible schedule given an array of rooms and coures
   * This method should contain only a call to the helper method, providing an empty starting
   * Schedule.
   * @param rooms array that has rooms with unique capacities
   * @param courses array of courses with unique amounts of students
   * @return returns a valid Schedule for the given set of rooms and
   * courses
   * @throws IllegalStateException if no such schedule exists
   */
  public static Schedule findSchedule(Room[] rooms, Course[] courses) {
    Schedule schedule = new Schedule(rooms, courses);

    schedule = findScheduleHelper(schedule, 0);

    return schedule;
  }

  /**
   * recursive method that assigns all unassigned courses in a Schedule beginning from the index
   * provided as an argument
   *
   * @param schedule schedule to be filled
   * @param index    starting point
   */
  private static Schedule findScheduleHelper(Schedule schedule, int index) {
    Schedule newSchedule;

    if (index == schedule.getNumCourses()) {
      if (!schedule.isComplete())
        throw new IllegalStateException("Invalid Schedule");
      return schedule; //would only get correct schedule
    }
    else if (schedule.isAssigned(index)) {
      newSchedule = findScheduleHelper(schedule, index + 1);
    }
    else if (!schedule.isAssigned(index)) {
      for (int roomIndex = 0; roomIndex < schedule.getNumRooms(); roomIndex++) {
//        System.out.println("-----");
//        capacity = schedule.getRoom(roomIndex).getCapacity();
//        courseStudents = schedule.getCourse(index).getNumStudents();
//        System.out.println(courseStudents + " is totalStudents in course " + index);
//        System.out.println(capacity + " is the capacity of room " + roomIndex);

        try {
//          System.out.println("Trying to assign course " + index + " to roomIndex: " + roomIndex);
          newSchedule = schedule.assignCourse(index, roomIndex); //dont lose old schedule
//          System.out.println("Assigned course " + index + " to roomIndex: " + roomIndex);

          newSchedule = findScheduleHelper(newSchedule, index + 1); //new call w/ new sched and
          // new index
          return newSchedule;
        } catch (IllegalArgumentException e) { //same as below
        } catch (IllegalStateException e) { //catch exception, do nothing, go back to loop to try
          // next
        }
      }
      throw new IllegalStateException(); //control flow back into the forLoop
    }
    return null;
  }

  /**
   * Finds every possible schedule given an array of coures and rooms
   * (If none can be created, this ArrayList is empty.) This method
   * should contain only a call to the helper method, providing an empty starting Schedule.
   * @param rooms array of Room objects with unique capacities
   * @param courses array of Course objects with unique total of students
   * @return an ArrayList containing all possible Schedules for the given set of rooms and
   * courses.
   */
  public static ArrayList<Schedule> findAllSchedules(Room[] rooms, Course[] courses) {
    Schedule schedule = new Schedule(rooms, courses);
    ArrayList<Schedule> allSchedules = new ArrayList<Schedule>();

    allSchedules = findAllSchedulesHelper(schedule, 0);

    return allSchedules;
  }

  /**
   * Helper method of findAllSchedules that does the finding of all schedules... recursively
   * @param schedule empty schedule being passed originally into recursion weeew
   * @param courseIndex starting point of courses to work through
   * @return A completed (or empty) array list with valid schedules!
   */
  private static ArrayList<Schedule> findAllSchedulesHelper(Schedule schedule, int courseIndex) {
    ArrayList<Schedule> allSchedules = new ArrayList<Schedule>();

    /*
      findAllSchedulesHelper(Schedule, int) - a private, recursive method that assigns all unassigned
      courses in a Schedule in ALL POSSIBLE ways, beginning from the index provided as an argument:
     */

     //If the provided index is equal to the number of courses, check to see if the Schedule is
    //    complete. If so, add it to an ArrayList of possible schedules and return the ArrayList.
    if (courseIndex == schedule.getNumCourses()) {
      if(schedule.isComplete()) {
        allSchedules.add(schedule);
        return allSchedules;
      }
    }
    /*  ○ If the provided index corresponds to a course that has already been assigned to a room,
          recursively add all possible valid schedules from the following indexes to an ArrayList of
          Schedules and return this ArrayList.*/
    if (schedule.isAssigned(courseIndex)) {
      if (courseIndex < schedule.getNumCourses()) {

        allSchedules.addAll(findAllSchedulesHelper(schedule, courseIndex + 1)); //next course
        return allSchedules;
      }
    }
    /* ○ If the provided index corresponds to a course that has NOT already been assigned to a
         room, iteratively try to assign it to each possible valid Room and recursively add all
         possible valid schedules from the following indexes to an ArrayList of Schedules and
         return this ArrayList.*/
    else if (!schedule.isAssigned(courseIndex)) {

      for (int roomIndex = 0; roomIndex < schedule.getNumRooms(); roomIndex++) { //next rooms
        try {
          Schedule newSchedule = schedule.assignCourse(courseIndex, roomIndex); //iterates w/ inc Rooms
          //and similar principle in other algo to not lose our sched
          allSchedules.addAll(findAllSchedulesHelper(newSchedule, courseIndex + 1));
          //next course with new sched. like other algorithm
        }
        catch (IllegalArgumentException e){}
      }
    }

    return allSchedules;
  }

}
