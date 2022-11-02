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

/**
 * This class models a restart game button in the cs300 spring 2022 p05 Treasure Hunt
 * adventure style game application
 */
public class RestartGameButton extends Button {

  /**
   * Creates a new RestartGameButton object labeled "Restart Game" at a
   * specific position on the screen
   * @param x - x-position to be assigned to this restart game button
   * @param y - y-position to be assigned to this restart game button
   */
  public RestartGameButton(int x, int y) {
    super("Restart Game", x, y);
  }

  @Override
  /**
   * Defines the behavior of this button when the mouse is pressed.
   * This button initializes the game if it is clicked (meaning if the mouse is over it)
   */
  public void mousePressed() {
    if(isMouseOver()){
      TreasureHunt treasureHunt = (TreasureHunt) processing;
      treasureHunt.initGame();
    }
  }
}
