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
 * This class models a droppable object designed for the cs300 spring 2022 p05 Treasure Hunt
 * adventure style game application. It is a draggable object which triggers a next clue to
 * appear when dropped on a specific target.
 */
public class DroppableObject extends DraggableObject{
  private InteractiveObject target; // target of this droppable object

  /**
   * Creates a new Droppable object with specific name, x and y positions, message, target,
   * and sets its next clue.
   * @param name - name to be assigned to this droppable object
   * @param x - x-position of this droppable object
   * @param y - y-position this droppable object
   * @param message - message to be assigned to this droppable object
   * @param target - target where this droppable object should be dropped
   * @param nextClue - reference to an interactive object which will be activated when
   *                 this droppable object is dropped on its target.
   */
  public DroppableObject(String name, int x, int y, String message,
                         InteractiveObject target, InteractiveObject nextClue){
    super(name, x, y, message);
    this.target = target;
    setNextClue(nextClue);
  }

  /**
   * Checks whether this object is over another interactive object.
   * @param target - reference to another iteractive object. We assume that other is NOT null.
   * @return true if this droppable object and other overlap and false otherwise.
   */
  public boolean isOver(InteractiveObject target){
    int x1, x2, x3, x4, y1, y2, y3, y4;
    boolean xGood = false;
    boolean yGood = false;
    x1 = target.getX(); //Bottomleft & Topright points being initialized for both considered
    y1 = target.getY() + target.image.height; //rectangles

    x2 = target.getX() + target.image.width;
    y2 = target.getY();

    x3 = this.getX();
    y3 = this.getY() + this.image.height;

    x4 = this.getX() + this.image.width;
    y4 = this.getY();

    if((x1 < x4) && (x3 < x2)) xGood = true; //Considering the axis in regards to graphics
    if((y1 > y4) && (y3 > y2)) yGood = true; //for the logic implementation

    if(xGood & yGood){
      return true;
    }
    else return false;
  }

  @Override
  /**
   * Stops dragging this droppable object. Also, if this droppable object is over its target and
   * the target is activated, this method (1) deactivates both this object and its target,
   * (2) activates the next clue, and (3) prints the message of this draggable object to the console
   */
  public void mouseReleased(){
    if(isOver(target) && target.isActive()){
      this.deactivate();
      target.deactivate();
      activateNextClue();
      message();
    }
    stopDragging();
  }
}
