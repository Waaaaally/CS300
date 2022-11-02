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
 * This class models a draggable object. A draggable object is a clickable interactive object
 * which can follow the mouse moves when being dragged.
 */
public class DraggableObject extends InteractiveObject{
  private int oldMouseX;
  private int oldMouseY;
  boolean isDragging;

  /**
   * Creates a new Draggable object with a given name, x and y position,
   * and "Drag Me!" as a default message. When created a new draggable object is NOT dragging.
   * @param name - name to be assigned to this draggable object
   * @param x - x-position of this draggable object in the display window
   * @param y - y-position of this draggable object in the display window
   */
  DraggableObject(String name, int x, int y){
    super(name, x, y, "Drag Me!");
    isDragging = false;
  }
  /**
   * Creates a new Draggable object with a given name, x and y position,
   * and "Drag Me!" as a default message. When created a new draggable object is NOT dragging.
   * @param name - name to be assigned to this draggable object
   * @param x - x-position of this draggable object in the display window
   * @param y - y-position of this draggable object in the display window
   * @param message message to be displayed when this draggable object is clicked. We assume that
   *                message is VALID (meaning it is NOT null and NOT an empty string).
   */
  DraggableObject(String name, int x, int y, String message){
    super(name, x, y, message);
    isDragging = false;
  }

  /**
   * Checks whether this draggable object is being dragged.
   * @return true if this object is being dragged, false otherwise
   */
  public boolean isDragging(){
    return isDragging;
  }

  /**
   * Starts dragging this draggable object and updates the oldMouseX and oldMouseY
   * positions to the current position of the mouse.
   */
  public void startDragging(){
    isDragging = true;
    oldMouseX = processing.mouseX;
    oldMouseY = processing.mouseY;
  }

  /**
   * Stops dragging this draggable object
   */
  public void stopDragging(){
    isDragging = false;
  }

  @Override
  /**
   * Draws this draggable object to the display window.
   * If this object is dragging, this method sets its position to follow the mouse moves.
   * Then, it draws its image to the its current position.
   */
  public void draw(){
    if(isDragging()){
      move(processing.mouseX - oldMouseX, processing.mouseY - oldMouseY);
    }
    processing.image(image, getX(), getY());
    oldMouseX = processing.mouseX; //need to reset "current" position so object doesnt launch
    oldMouseY = processing.mouseY; //w/ the exponential increasing distance
    //not speaking form experience LMAO
  }

  /**
   * Starts dragging this object when it is clicked (meaning when the mouse is over it).
   */
  @Override
  public void mousePressed(){
    if(isMouseOver()){
      startDragging();
    }
  }

  @Override
  /**
   * Stops dragging this object if the mouse is released
   */
  public void mouseReleased(){
    stopDragging();
  }
}
