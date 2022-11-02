import java.util.Iterator;
import java.util.NoSuchElementException;

//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Song Player Utilizng Doubly Linked Lists
// Course:   CS 300 Spring 2022
//
// Author:   Pritish Das
// Email:    pdas26@wisc.edu
// Lecturer: Hobbes LeGault
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

public class ForwardSongIterator implements Iterator<Song> {
  private LinkedNode<Song> next; //reference to the next linked node in a list of nodes.

  /**
   * Creates a new iterator which iterates through songs in front/head to back/tail order
   * @param first - reference to the head of a doubly linked list of songs
   */
  public ForwardSongIterator(LinkedNode<Song> first){
    next = first; //reference to the head of a doubly linked list of songs
  }

  @Override
  /**
   * Checks whether there are more songs to return
   * @return true if there are more songs to return
   */
  public boolean hasNext() {
    return next != null; //if there's a next, return true;
  }

  @Override
  /**
   * Returns the next song in the iteration
   * @throws NoSuchElementException if there are no more songs to return in the reverse order
   * (meaning if this.hasNext() returns false)
   */
  public Song next() {
    if(!this.hasNext()) throw new NoSuchElementException("There is no next node");

    Song theSong = next.getData();
    next = next.getNext(); //we will keep our list intact o7. No garbage collection today

    return theSong;
  }
}
