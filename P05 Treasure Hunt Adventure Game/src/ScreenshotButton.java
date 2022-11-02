import processing.core.PApplet;

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
public class ScreenshotButton extends Button {
  /**
   * Creates a new ScreenshotButton object labeled "Take a screenshot" at a specific
   * position on the screen
   * @param x - x-position to be assigned to this screenshot button
   * @param y - y-position to be assigned to this screenshot button
   */
  public ScreenshotButton(int x, int y) {
    super("Take a screenshot", x, y);
  }

  @Override
  /**
   * Defines the behavior of this button when the mouse is pressed. When it is clicked,
   * this button takes a screenshot of the game screen. This method calls the PApplet.save() method
   * to save the screenshot with the filename "screenshot.png"
   */
  public void mousePressed() {
    if (isMouseOver()) {
      processing.save("screenshot.png");
    }
  }
}

