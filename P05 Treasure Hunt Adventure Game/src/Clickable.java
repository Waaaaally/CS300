//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Treasure Hunt Game
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
//
///////////////////////////////////////////////////////////////////////////////
public interface Clickable {
  // draws this Clickable object to the screen
  public void draw();

  // defines the behavior of this Clickable object each time the mouse is pressed
  public void mousePressed();

  // defines the behavior of this Clickable object each time the mouse is released
  public void mouseReleased();

  // returns true if the mouse is over this clickable object
  public boolean isMouseOver();
}
