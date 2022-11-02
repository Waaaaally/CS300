//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    DNA with Linked Queues
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
// Persons:         None
// Online Sources:  None
//
///////////////////////////////////////////////////////////////////////////////

import java.util.NoSuchElementException;

/**
 * A generic implementation of a linked queue
 * @param <T> Generic Data for the queue
 */
public class LinkedQueue<T> implements QueueADT<T>{
  public Node<T> back; //The node at the back of the queue, added MOST recently
  public Node<T> front; //The node at the front of the queue, added LEAST recently
  public int n; //The number of elements in the queue

  @Override
  /**
   * Adds the given data to this queue; every addition to a queue is made at the back
   * @data - the data to add
   */
  public void enqueue(T data) {
    Node<T> queueAdd = new Node<T>(data);

    if (data != null) {
      if (isEmpty()) {
        front = queueAdd;
        back = queueAdd;
      } else {
        back.setNext(queueAdd);
        back = queueAdd;
      }
      n++;
    }
  }

  @Override
  /**
   * Removes and returns the item from this queue that was least recently added
   * @returns the item from this queue that was least recently added
   * @throws NoSuchElementException- if this queue is empty
   */
  public T dequeue() {
    Node<T> toBeRemoved;
    if (isEmpty())
      throw new NoSuchElementException("Can't dequeue empty queue");

    toBeRemoved = front;

    if(front.getNext() != null){
      front = front.getNext();
    }
    n--;

    return toBeRemoved.getData();
    }

  @Override
  /**
   * Returns the item least recently added to this queue without removing it
   * @returns the item least recently added to this queue
   * @throws NoSuchElementException if the queue is empty
   */
  public T peek(){
    if(isEmpty()) throw new NoSuchElementException("Can't peek in an empty queue");
    return front.getData();
  }

  @Override
  /**
   * Checks whether the queue contains any elements
   * @returns true if this queue is empty; false otherwise
   */
  public boolean isEmpty(){
    return (n == 0); //May have to refine w/ head/tail null?
  }

  @Override
  /**
   * Returns the number of items in this queue
   * @returns the number of items in this queue
   */
  public int size() {
    return n;
  }

  @Override
  /**
   * Returns a string representation of this queue, beginning at the front (least recently added)
   * of the queue and ending at the back (most recently added). An empty queue is represented as an
   * empty string; otherwise items in the queue are represented using their data separated by spaces
   * @returns the sequence of items in FIFO order, separated by spaces
   */
  public String toString(){
    String toString = "";
    Node<T> saveTHEFront = front;

    for(int i = 0; i < n; i++){
      if(front != null){
        toString += front.getData() + " ";
        front = front.getNext();
      }
    }
    front = saveTHEFront;

    return toString;
  }
}
