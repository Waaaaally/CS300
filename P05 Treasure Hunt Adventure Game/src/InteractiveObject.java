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
import processing.core.PApplet;
import processing.core.PImage;

import java.io.File;
import java.util.NoSuchElementException;

public class InteractiveObject implements Clickable{
  // reference to the PApplet where this object will be drawn
  protected static TreasureHunt processing;
  private final String NAME; // name of this interactive object
  protected PImage image; // reference to the image of this object
  private int x; // x-position of this object on screen
  private int y; // y-position of this object on screen
  // Note that the position (x,y) of the interactive object is the position
  // of the upper-left corner of its image (and NOT the center of the image).
  private boolean isActive; // tells whether or not this object is active
  private boolean wasClicked; // tells whether this object was already clicked
  private String message; // message to be displayed when this object is clicked
  private InteractiveObject nextClue; // Object to be activated when this object is clicked the
  // first time, if any

  /**
   * Creates a new interactive object with no next clue (nextClue == null) and sets its image,
   * name, x and y positions, its message, and activation status. When created, an interactive
   * object must be active, and not clicked yet. [Details on how to set the image of this
   * interactive object are provided in the write-up.]
   * @param name - name to be assigned to this interactive object. We assume that name is VALID
   * @param x - x-position to be assigned to this interactive object
   * @param y - y-position to be assigned to this interactive object
   * @param message - message to be displayed on the console each time this interactive object is
   *                  clicked. We assume that message is VALID
   */
  public InteractiveObject(String name, int x, int y, String message){
    image = processing.loadImage("images" + File.separator + name + ".png");
    NAME = name;
    this.x = x;
    this.y = y;
    this.message = message;
    isActive = true;
    wasClicked = false;
  }

  /**
   * Creates a new interactive object with no next clue (nextClue == null) and sets its image,
   * name, x and y positions, its message, and activation status. When created, an interactive
   * object must be active, and not clicked yet. [Details on how to set the image of this
   * interactive object are provided in the write-up.]
   * @param name - name to be assigned to this interactive object. We assume that name is VALID
   * @param x - x-position to be assigned to this interactive object
   * @param y - y-position to be assigned to this interactive object
   * @param message - message to be displayed on the console each time this interactive object is
   *                  clicked. We assume that message is VALID
   * @param nextClue - a reference to a non-null InteractiveObject which represents
   *                 the next clue associated to this interactive object.
   */
  public InteractiveObject(String name, int x, int y, String message, InteractiveObject nextClue){
    this(name, x, y, message);
    this.nextClue = nextClue;
    this.nextClue.deactivate();
  }

  /**
   * Gets the x-position of this interactive object
   * @return the x-position of this interactive object
   */
  public int getX(){
    return x;
  }

  /**
   * Gets the y-position of this interactive object
   * @return the y-position of this interactive object
   */
  public int getY(){
    return y;
  }

  /**
   * Gets the message of this interactive object.
   * @return the message to be displayed each time this interactive object is clicked.
   */
  public String message(){
    return message;
  }

  /**
   * Checks whether the name of this interactive object equals to the name passed as input parameter. We consider a case-sensitive comparison.
   * @param name - name to compare to
   * @return true if the name of this interactive object equals the provided name, false otherwise.
   */
  public boolean hasName(String name){
    if(NAME.equals(name)) return true;
    return false;
  }

  /**
   * Deactivates this interactive object, meaning setting its isActive data field to false.
   */
  public void deactivate(){
    this.isActive = false;
  }
  /**
   * Activates this interactive object, meaning setting its isActive data field to true.
   */
  public void activate(){
    isActive = true;
  }

  /**
   * Activates the next clue of this interactive object and adds it to the treasure hunt application
   * @throws java.util.NoSuchElementException - with a descriptive error message if the nextClue
   * of this interactive object is null
   */
  protected void activateNextClue(){
    if(nextClue == null){
      throw new NoSuchElementException("The nextClue of this object is Null");
    }
    processing.add(nextClue);
    nextClue.activate();
  }
  /**
   * Checks whether this interactive object is active. This should be a getter of the
   * isActive data field.
   * @return true if this interactive object is active and false otherwise.
   */
  public boolean isActive(){
    return this.isActive;
  }

  /**
   * Sets the next clue associated to this interactive object
   * @param nextClue - the next clue to be assigned to this interactive object
   */
  public void setNextClue(InteractiveObject nextClue){
    this.nextClue = nextClue;
  }

  /**
   * Sets the PApplet object of the treasure hunt application where all the interactive objects
   * will be drawn. Note that a graphic application has ONLY one display window of type PApplet
   * where all graphic objects interact. In p05, the TreasureHunt class is of type PApplet.
   * @param processing - represents the reference to the TreasureHunt PApplet object where
   *                     all the interactive objects will be drawn.
   */
  public static void setProcessing(TreasureHunt processing){
    InteractiveObject.processing = processing;
  }

  /**
   * Moves the current x and y positions of this interactive object with dx and dy, respectively
   * @param dx - move to be added to the x position of this interactive object
   * @param dy - move to be added to the y position of this interactive object
   */
  public void move(int dx, int dy){
    x += dx;
    y += dy;
  }

  @Override
  /**
   * Draws this interactive object to the display window at positions x and y.
   */
  public void draw() {
    processing.image(image, x, y);
  }

  @Override
  /**
   * This method operates each time the mouse is pressed. This interactive object responds to the
   * mouse clicks as follows. If the mouse is clicked (meaning the mouse is over it) two operations
   * will be performed as follows:
   * (1) The message of this interactive object must be displayed to the console.
   * (2) If this interactive object has a next clue and was not clicked,
   * its next clue will be activated and its wasClicked setting will be updated.
   */
  public void mousePressed() {
    if(isMouseOver()){
      System.out.println(message());
      if(wasClicked == false && nextClue != null){
        activateNextClue();
        wasClicked = true;
      }
    }
  }

  @Override
  /**
   * This method operates each time the mouse is released.
   * It implements a default behavior for an interactive object which is DO NOTHING
   * (meaning that the InteractiveObject.mouseReleased has a blank implementation).
   */
  public void mouseReleased() {
  }

  @Override
  /**
   * Checks whether the mouse is over the image of this interactive object
   */
  public boolean isMouseOver() {
    int height = image.height;
    int width = image.width;
    //int mouseX = processing.mouseX;
    //int mouseY = processing.mouseY;
    boolean xGood = false;
    boolean yGood = false;

    if(processing.mouseX >= x && processing.mouseX <= x + width) xGood = true;
    if(processing.mouseY >= y && processing.mouseY <= y + height) yGood = true;

    if(xGood && yGood)
      return true;
    else
      return false;
  }
}
