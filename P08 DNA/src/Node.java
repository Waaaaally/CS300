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
/**
 * Class models a node in a linkedQueue data structure
 * @param <T>
 */
public class Node<T> {
  private T data; // The data contained in the Node
  private Node<T> next; // The Node following this one


  /**
   * Basic constructor; creates a node that contains the provided data and no linkages.
   * @param data the information to put inside the node
   */
  public Node(T data){
    this.data = data;
    next = null;
  }

  /**
   * A constructor that allows specification of the next node in the chain
   * @param data the information to put inside the Node
   * @param next the next node, must contain the same type of data as this node
   */
  public Node(T data,Node<T> next){
    this.data = data;
    this.next = next;
  }

  /**
   * Accessor method for this node's data
   * @return the data contained in this node
   */
  public T getData(){
    return data;
  }

  /**
   * Accessor method for the node following this one
   * @return the next node
   */
  public Node<T> getNext(){
    return next;
  }

  public void setNext(Node<T> next){
    this.next = next;
  }
}
