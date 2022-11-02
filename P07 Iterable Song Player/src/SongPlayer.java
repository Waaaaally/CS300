import javax.swing.*;
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

public class SongPlayer implements Iterable<Song> {
  private int size; //size of the list
  private LinkedNode<Song> head; //head of this doubly linked list
  private LinkedNode<Song> tail; //tail of this doubly linked list
  private boolean playingBackward; //true if this song player is reading the list backward


  /**
   * Adds a Song as Last Song
   *
   * @param oneSong the song that is going to be added to the tail of this doubly linked list of
   *                songs
   */
  public void addLast(Song oneSong) {
    if (oneSong == null) throw new NullPointerException("Song cannot be null");
    LinkedNode<Song> songLast = new LinkedNode<Song>(tail, oneSong, null); //first create new
    // node to SOON be the new tail.

    if (isEmpty()) {
      head = songLast;
      tail = songLast;
    } else {
      tail.setNext(songLast); //tail hasn't changed yet. Set "Old tail's" next to our new tail.
      tail = songLast; //update tail.
    }
    size++;
  }

  /**
   * add Song as First Song
   *
   * @param oneSong the song that is going to be added to the head of this doubly linked list of
   *                songs
   * @throws NullPointerException with a descriptive message if the passed oneSong is null
   */
  public void addFirst(Song oneSong) {
    if (oneSong == null) throw new NullPointerException("Song cannot be null");
    LinkedNode<Song> songFirst = new LinkedNode<Song>(null, oneSong, head); //soon2be head

    if (isEmpty()) {
      head = songFirst;
      tail = songFirst;
    } else {
      head.setPrev(songFirst);
      head = songFirst;
    }
    size++;
  }

  /**
   * adds Song at a given position/order within this collection of songs
   *
   * @param index   the given index where the new song will be added
   * @param oneSong the song that is going to be added
   * @throws NullPointerException      with a descriptive error message if the passed oneSong is null
   * @throws IndexOutOfBoundsException with a descriptive error message if index is out of the
   *                                   0 .. size() range
   */
  public void add(int index, Song oneSong) {
    if (oneSong == null) throw new NullPointerException("Song cannot be null");
    if (index < 0 || index > (size)) throw new IndexOutOfBoundsException("Illegal index given");

    LinkedNode<Song> songNodePrevious = head;
    LinkedNode<Song> songNodeNext = head;

    //PROBABLY ABSOLUTELY GARBAGE IMPLEMENTAITON BUT HERE WEE FOOKKKIN GOOOOOOOOO
    if (index == 0) {
      LinkedNode<Song> songNode = new LinkedNode<Song>(null, oneSong, head);
      head.setPrev(songNode);

      head = songNode;
    } else if (index == size) {
      LinkedNode<Song> songNode = new LinkedNode<Song>(tail, oneSong, null);
      tail.setNext(songNode);

      tail = songNode;
    } else {
      for (int i = 0; i < index - 1; i++) {
        songNodePrevious = songNodePrevious.getNext();
      }

      songNodeNext = songNodePrevious.getNext(); // PREVIOUS-NODE NEXT-NODE -> PREVIOUS-NODE
      // INPUT-NODE NEXT-NODE. THE NODE ISNT INPUT YET SO THE NEXT AFTER "PREVIOUS" CORRELATES TO
      // GETNEXT NOT GETNEXTNEXT.
      LinkedNode<Song> songNode = new LinkedNode<Song>(songNodePrevious, oneSong, songNodeNext);

      songNodePrevious.setNext(songNode); //set up other links after the establishment of newNode
      songNodeNext.setPrev(songNode);
    }
    size++;
  }

  /**
   * Returns the first Song in this list.
   *
   * @return the Song at the head of this list
   * @throws java.util.NoSuchElementException if the list is empty
   */
  public Song getFirst() {
    if (isEmpty()) throw new NoSuchElementException("List is empty");

    return head.getData();
  }

  /**
   * Returns the last Song in this list.
   *
   * @return the Song at the tail of this list
   * @throws java.util.NoSuchElementException if the list is empty
   */
  public Song getLast() {
    if (isEmpty()) throw new NoSuchElementException("List is empty");

    return tail.getData();
  }

  /**
   * Returns the song at the specified position in this list.
   *
   * @param index index of the song to return
   * @return the song at the specified position in this list
   * @throws IndexOutOfBoundsException if index is out of the 0 .. size()-1 range
   */
  public Song get(int index) {
    if (index < 0 || index > (size - 1)) throw new IndexOutOfBoundsException("Invalid index");

    LinkedNode<Song> start = head;
    for (int i = 0; i < index; i++) {
      start = start.getNext();
    }
    return start.getData();
  }

  /**
   * Removes and returns the first song from this list.
   *
   * @return the first song from this list
   * @throws java.util.NoSuchElementException if the list is empty
   */
  public Song removeFirst() {
    if (isEmpty()) throw new NoSuchElementException("List is empty");
    Song toBeRemoved = head.getData();

    if(size == 1){
      head = null;
      tail = null;
    }
    else {
      head = head.getNext(); //in a list of one. it's fucked
      head.setPrev(null); //null error here? happens bc head is null.
    }
    size--;

    return toBeRemoved;
  }

  /**
   * Removes and returns the last song from this list.
   *
   * @return the last song from this list
   * @throws java.util.NoSuchElementException if the list is empty
   */
  public Song removeLast() {
    if (isEmpty()) throw new NoSuchElementException("List is empty");
    Song toBeRemoved = tail.getData();

    if(size == 1){
      head = null;
      tail = null;
    }
    else {
      tail = tail.getPrev();
      tail.setNext(null);
    }
    size--;

    return toBeRemoved;
  }

  /**
   * Removes the song at the specified position in this list and returns the song that was removed
   * from the list. The order of precedence of the other songs in the list should not be modified.
   *
   * @param index the index of the song to be removed
   * @return the song previously at the specified position
   * @throws IndexOutOfBoundsException if index is out of the 0 .. size()-1 range
   */
  public Song remove(int index) {
    if (index < 0 || index > (size - 1)) throw new IndexOutOfBoundsException("Illegal Index Given");
    Song toBeRemoved;
    //3 Scenarios

    if (index == 0) {
      toBeRemoved = removeFirst();
    } else if (index == (size - 1)) {
      toBeRemoved = removeLast();
    } else {
      LinkedNode<Song> parsingNode = head; //node to be removed
      for (int i = 0; i < index; i++) {
        parsingNode = parsingNode.getNext();
      }
      toBeRemoved = parsingNode.getData();
      parsingNode.getNext().setPrev(parsingNode.getPrev());//set next nodes prevs b4 parsing node's
      parsingNode.getPrev().setNext(parsingNode.getNext());
      size--;
    }
    return toBeRemoved;
  }

  /**
   * Returns true if this list contains a match with the specified song. More formally, returns true
   * if and only if this list contains at least one song e such that Objects.equals(o, e).
   *
   * @param o song whose presence in this list is to be tested
   * @return true if this list contains the specified song
   */
  public boolean contains(Song o) {
    for (Song i : this) {
      if (i.equals(o))
        return true;
    }
    return false;
  }

  /**
   * Removes all of the songs from this list. The list will be empty after this call returns.
   */
  public void clear() {
    //just get rid of the head?
    head = null;
    tail = null;
    size = 0;
  }

  /**
   * Returns true if this list is empty.
   *
   * @return true if this list is empty
   */
  public boolean isEmpty() {
    return (head == null && tail == null && size == 0);
  }

  /**
   * Returns the number of songs in this list.
   *
   * @return the number of songs in this list
   */
  public int size() {
    return size;
  }

  /**
   * Returns an iterator to iterate through the songs in this list with respect to current
   * playing direction of this song player (either in the forward or in the backward direction)
   *
   * @return an Iterator to traverse the list of songs in this SongPlayer with respect to the
   * current playing direction specified by the playingBackward data field.
   */
  public Iterator<Song> iterator() {
    if (playingBackward)
      return new BackwardSongIterator(tail);
    else
      return new ForwardSongIterator(head);
  }

  /**
   * Mutator of the playingDirection of this song player. It switches the playing direction by
   * setting playingBackward to its opposite value.
   */
  public void switchPlayingDirection() {
    playingBackward = !playingBackward;
  }

  /**
   * Plays the songs in this song player in the current playing direction.
   * This method MUST be implemented using an enhanced for-each loop.
   *
   * @return a String representation of the songs in this song player. String representations
   * of each song are separated by a newline.
   * If this song player is empty, this method returns an empty string.
   */
  public String play(){
    String playlist = "";

    if (!playingBackward) {
      for (Song i : this) {
        playlist += i + "\n";
      }
    }
    else{
      for (Song i : this) {
        playlist += i + "\n";
      }
    }
    return playlist;
  }

}
