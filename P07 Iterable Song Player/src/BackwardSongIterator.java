import java.util.Iterator;
import java.util.NoSuchElementException;

//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Song Player Utilizng Doubly Linked Lists
// Course:   CS 300 Spring 2022
//
// Author:   Pritish Das
// Email:    pdas26@wisc.edu
// Lecturer: Hobbes LeGault)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (N/A)
// Partner Email:   (N/A)
// Partner Lecturer's Name: (N/A)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: None
// Online Sources:  None
//
///////////////////////////////////////////////////////////////////////////////

public class BackwardSongIterator implements Iterator<Song> {
  private LinkedNode<Song> next; //reference to the next linked node in a list of nodes
  //but its actually like the previous ig? Technically we just implement thinking the tail is the
  //head ig lmao yk thats like some snake shit ong prolly who knows this snake weird af fr fr yk

  /**
   * Creates a new iterator which iterates through songs in back/tail to front/head order
   * @param last reference to the tail of a doubly linked list of songs
   */
  public BackwardSongIterator(LinkedNode<Song> last){
    next = last;
  }

  @Override
  /**
   * Checks whether there are more songs to return in the reverse order
   * @returns true if there are more songs to return in the reverse order
   */
  public boolean hasNext() {
    return next != null; //prolly the same cuz like its a tail that we start at and that should
    // be chilling for now and then itll may be null depending how we implement our next()
  }

  @Override
  /**
   *
   * @returns Returns the next song in the iteration
   * @throws NoSuchElementException - with a descriptive error message if there are no
   * more songs to return in the reverse order (meaning if this.hasNext() returns false)
   */
  public Song next() {
    if(!this.hasNext()) throw new NoSuchElementException("There is no previous node");

    Song theSongButPrevious = next.getData();
    next = next.getPrev(); //I think the same just works??? testing methods good

    return theSongButPrevious;
  }
}
