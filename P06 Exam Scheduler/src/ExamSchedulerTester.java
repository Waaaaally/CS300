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
// Persons: None
// Online Sources: None
//
///////////////////////////////////////////////////////////////////////////////

/**
 * Testing class for all the methods of Course, Room, Schedule, ExamScheduler
 */
public class ExamSchedulerTester {
  public static void main(String [] args){
    System.out.println(allTests());
  }

  /**
   * Tests methods of Room Object
   * @return return true if all work as expected, else false.
   */
  public static boolean testRoom(){
    Room testRoom = new Room("Union South", 350);

    try{
      Room fakeRoom = new Room("Wonderland", -1);
      return false;
    }
    catch(IllegalArgumentException e){
        //chilling sunglasses
    }
    catch(Exception e){
      System.out.println("The Room constructor gave the wrong exception when given a negative ");
      return false;
    }

    if(!testRoom.getLocation().equals("Union South")){
      System.out.println("getLocation did not work");
      return false;
    }

    if(testRoom.getCapacity() != 350){
      System.out.println("getCapacity did not work");
    }

    if(testRoom.reduceCapacity(50).getCapacity() != 300){
      System.out.println("Reduced capacity of 50 from 350 did not return 300");
      return false;
    }

    return true;
  }

  /**
   * Test methods of course object
   * @return return true if all work as expected, else false.
   */
  public static boolean testCourse(){ //tests the methods in Course Class
    Course testCourse = new Course("Sealology", 100);

    try{
      Course fakeCourse = new Course("cs100", -1);
      return false;
    }
    catch(IllegalArgumentException e){
      //chilling sunglasses
    }
    catch(Exception e){
      System.out.println("The Course constructor gave the wrong exception when given a negative");
      return false;
    }

    if(!testCourse.getName().equals("Sealology")){
      System.out.println("Course getName did not work");
      return false;
    }

    if(testCourse.getNumStudents() != 100){
      System.out.println("Course getNumStudents did not work");
      return false;
    }

    return true;
  }

  /**
   * Test accessor methods of Schedule object
   * @return return true if all work as expected, else false.
   */
  public static boolean testScheduleAccessors(){
    Room[] testRooms = new Room[3];
    testRooms[0] = new Room("Union South", 350);
    Course[] testCourses = new Course[3];
    testCourses[1] = new Course("Math240", 200 );

    Schedule testSchedule = new Schedule(testRooms, testCourses);

    if(testSchedule.getNumRooms() != 1){
      System.out.println("Schedule getNumRooms does not work");
      return false;
    }

    if(testSchedule.getNumCourses() != 1){
      System.out.println("Schedule getNumCourses does not work");
      return false;
    }

    if(!testSchedule.getRoom(0).equals(testRooms[0])){
      System.out.println("Schedule getRoom did not work");
      return false;
    }

    if(!testSchedule.getCourse(1).equals(testCourses[1])){
      System.out.println("Schedule getCourse did not work");
      return false;
    }

    try{
      testSchedule.getRoom(-1);
      System.out.println("This code shouldn't run in the getRoom tryblock");
      return false;
    }
    catch (IndexOutOfBoundsException e){
      //chilling sunglasses
    }
    catch (Exception e){
      System.out.println("getRoom did not give an IndexOutOfBoundsException");
      return false;
    }

    try{
      testSchedule.getCourse(-1);
      System.out.println("This code shouldn't run in the getCourse tryblock");
      return false;
    }
    catch (IndexOutOfBoundsException e){
      //chilling sunglasses
    }
    catch (Exception e){
      System.out.println("getCourse did not give an IndexOutOfBoundsException");
      return false;
    }

    //courses[1] is assigned to room[0]
    //assignments SHOULD have index of room in room[0]... value should be 0;

    testSchedule = testSchedule.assignCourse(1,0);

    if(testSchedule.isAssigned(1) == false){
      System.out.println("Schedule isAssigned method doesn't work");
      return false;
    }

    if(!testSchedule.getAssignment(1).equals(testSchedule.getRoom(0))){
      System.out.println("Schedule getAssignment doesn't work");
      return false;
    }
    //The assignmentIndexes just need to match

    return true;
  }

  /**
   * Test assign course method of Schedule Object
   * @return return true if all work as expected, else false.
   */
  public static  boolean testAssignCourse(){
    Course course1 = new Course("Course1", 50);
    Room goodRoom = new Room("GoodRoom", 100);
    Room badRoom = new Room("BadRoom", 10);
    Room otherRoom = new Room("OtherRoom", 60);
    Room[] rooms = {goodRoom, badRoom, otherRoom};
    Course[] courses = {course1};

    Schedule schedule = new Schedule(rooms, courses);

    try {
      schedule.assignCourse(0, 0);
    }catch (Exception e){
      System.out.println("An exception was thrown by Assign Course for a valid input");
      return false;
    }

    try{
      schedule.assignCourse(0, 1);
      System.out.println("This code shouldn't be run in the try block");
      return false;
    }catch(IllegalArgumentException e){
      //Chilling :Sunglasses:
    }catch (Exception e){
      System.out.println("An exception was thrown that's not IllegalArgument");
    }

    Schedule newSchedule = schedule.assignCourse(0,0);
    try{
      newSchedule.assignCourse(0,2);//assignning course thats been assigned
      System.out.println("This code shouldn't be run in the try block. 2nd Exception Test");
      return false;
    }catch(IllegalArgumentException e){
      //Chilling Sunglasses
    }
    catch (Exception e){
      System.out.println("An exception was thrown thats not IllegalArgument. 2nd Exception Test");
      return false;
    }

    return true;
  }

  /**
   * Test findSchedule method of ExamScheduler Object
   * @return return true if all work as expected, else false.
   */
  public static boolean testFindSchedule(){
    /*
    For example, if we want to assign the Courses CS200 (50 people), CS300 (110 people), CS400 (75 people)
    to rooms Room1 (cap 100), Room2 (cap 150), Room3 (cap 75), the list of ALL resulting possible valid
    schedules is: */

    Course cs200 = new Course("CS200", 15); //was 15
    Course cs300 = new Course("CS300", 90); //was 90
    Course cs400 = new Course("CS400", 70); //was 70

    Room room0 = new Room("Room 0", 75);
    Room room1 = new Room("Room 1", 100);
    Room room2 = new Room("Room 2", 15);

    Course[] courses = {cs200, cs300, cs400};
    Room[] rooms = {room0, room1, room2};

    Schedule schedule = ExamScheduler.findSchedule(rooms, courses);

    if(!schedule.toString().equals("{CS200: Room 2, CS300: Room 1, CS400: Room 0}")){
      System.out.println("Schedule have expected values/toString");
      return false;
    }

    Course randomCourse = new Course("course", 100);
    Course randomCourse1 = new Course("course2", 75);
    Room randoRoom = new Room("RandomRoom", 150);

    Course[] randomCourses = {randomCourse, randomCourse1};
    Room[] randomRoom = {randoRoom};

    try{
      Schedule doomedSchedule = ExamScheduler.findSchedule(randomRoom, randomCourses);
    }catch (IllegalStateException e){
      //Chilling
    }catch (Exception e){
      System.out.println("An exception that is not an IllegalState was thrown");
    }



    return true;
  }

  /**
   * Test findAllSchedule method of Exam Scheduler
   * @return return true if all work as expected, else false.
   */
  public static  boolean testFindAllSchedules(){
    Course cs200 = new Course("CS200", 50); //was 15
    Course cs300 = new Course("CS300", 110); //was 90
    Course cs400 = new Course("CS400", 75); //was 70

    Room room0 = new Room("Room 1", 100);
    Room room1 = new Room("Room 2", 150);
    Room room2 = new Room("Room 3", 75);

    Course[] courses = {cs200, cs300, cs400};
    Room[] rooms = {room0, room1, room2};

    ArrayList<Schedule> allSchedules = new ArrayList<Schedule>();

    allSchedules = ExamScheduler.findAllSchedules(rooms, courses);

    String expected1 = "{CS200: Room 1, CS300: Room 2, CS400: Room 3}";
    String expected2 = "{CS200: Room 3, CS300: Room 2, CS400: Room 1}";

    if(!allSchedules.get(0).toString().equals(expected1) ||
            !allSchedules.get(1).toString().equals(expected2)){
      System.out.println("allSchedules did not return the expected schedules");
      return false;
    }

    Course course1 = new Course("c1", 100); //was 15
    Course course2 = new Course("c2", 100); //was 15
    Course course3 = new Course("c3", 100); //was 15

    Room r0 = new Room("Room A", 50);
    Room r1 = new Room("Room B", 50);
    Room r2 = new Room("Room C", 50);

    Room[] invalidRooms = {r0, r1, r2};
    Course[] invalidCourses = {course1, course2, course3};

    allSchedules = ExamScheduler.findAllSchedules(invalidRooms, invalidCourses);

    if(allSchedules.size() != 0){
      System.out.println("All schedules returned unexpected amount of schedules when no valid");
      return false;
    }
    return true;
  }

  /**
   * Runs all the test methods for pretty mainmethod. waaw
   * @return return true if all work as expected, else false.
   */
  public static boolean allTests(){

    if(!testCourse()){
      System.out.println("TestCourse failed");
      return false;
    }
    if(!testRoom()) {
      System.out.println("TestRoomFailed");
      return false;
    }
    if(!testAssignCourse()){
      System.out.println("TestAssignCourse Failed");
      return false;
    }
    if(!testScheduleAccessors()){
      System.out.println("TestScheduleAccessors Failed");
      return false;
    }
    if(!testFindSchedule()){
      System.out.println("testFindSchedule Failed");
      return false;
    }
    if(!testFindAllSchedules()){
      System.out.println("testFindAllSchedules Failed");
      return false;
    }
    return true;
  }
}
