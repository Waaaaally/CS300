import java.util.Iterator;
import java.util.NoSuchElementException;

//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Open Position w/ Priority Queue & Heaps
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
 * Array-based heap implementation of a priority queue containing Applications. Guarantees the
 * min-heap invariant, so that the Application at the root should have the lowest score, and
 * children always have a higher or equal score as their parent. The root of a non-empty queue
 * is always at index 0 of this array-heap.
 */
public class ApplicationQueue implements PriorityQueueADT<Application>, Iterable<Application> {
	private Application[] queue; // array min-heap of applications representing this priority
	// queue
	private int size;            // size of this priority queue

	/**
	 * Creates a new empty ApplicationQueue with the given capacity
	 * 
	 * @param capacity Capacity of this ApplicationQueue
	 * @throws IllegalArgumentException with a descriptive error message if the capacity is not a
	 *                                  positive integer
	 */
	public ApplicationQueue(int capacity) {
		//verify the capacity
		if(capacity < 1)
			throw new IllegalArgumentException("Capacity cannot be a nonpositive int");

		//initialize fields appropriately
		queue = new Application[capacity];
		size = 0;
	}

	/**
	 * Checks whether this ApplicationQueue is empty
	 * 
	 * @return {@code true} if this ApplicationQueue is empty
	 */
	@Override
	public boolean isEmpty() {
		return (size == 0);  // TODO fix - I think this is right?
	}

	/**
	 * Returns the size of this ApplicationQueue
	 * 
	 * @return the size of this ApplicationQueue
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Adds the given Application to this ApplicationQueue and use the percolateUp() method to
	 * maintain min-heap invariant of ApplicationQueue. Application should be compared using 
	 * the Application.compareTo() method.
	 * 
	 * 
	 * @param o Application to add to this ApplicationQueue
	 * @throws NullPointerException if the given Application is null
	 * @throws IllegalStateException with a descriptive error message if this ApplicationQueue is full
	 */
	@Override
	public void enqueue(Application o) {
		// verify the application
		if(o == null)
			throw new NullPointerException("Application provided can't be Null");
		// verify that the queue is not full
		if(queue[queue.length - 1] != null)
			throw new IllegalStateException("Queue is full"); //todo fine?

		// TODO if allowed, add the application to the queue and percolate to restore the heap condition
		queue[size] = o;
		percolateUp(size);
		size++;
	}

	/**
	 * Removes and returns the Application at the root of this ApplicationQueue, i.e. the Application
	 * with the lowest score.
	 * 
	 * @return the Application in this ApplicationQueue with the smallest score
	 * @throws NoSuchElementException with a descriptive error message if this ApplicationQueue is
	 *                                empty
	 */
	@Override
	public Application dequeue() {
		// verify that the queue is not empty
		if(isEmpty())
			throw new NoSuchElementException("Cannot dequeue empty queue");

		// save the lowest-scoring application
		Application lowestApplication = queue[0];
		// replace the root of the heap and percolate to restore the heap condition
		queue[0] = queue[size-1];
		queue[size-1] = null; //todo double check
		size--;
		percolateDown(0);
		// return the lowest-scoring application
		return lowestApplication;
	}

	/**
	 * An implementation of percolateDown() method. Restores the min-heap invariant of a given
	 * subtree by percolating its root down the tree. If the element at the given index does not
	 * violate the min-heap invariant (it is due before its children), then this method does not
	 * modify the heap. Otherwise, if there is a heap violation, then swap the element with the
	 * correct child and continue percolating the element down the heap.
	 * 
	 * This method may be implemented recursively OR iteratively.
	 * 
	 * @param index index of the element in the heap to percolate downwards
	 * @throws IndexOutOfBoundsException if index is out of bounds - do not catch the exception
	 */
	private void percolateDown(int index) {
		// TODO implement the min-heap percolate down algorithm to modify the heap in place
		if (index >= queue.length || index < 0)
			throw new IndexOutOfBoundsException("IndexOut Of Bounds");

		Application value = queue[index];
		int leftIndex = index * 2 + 1, rightIndex = index * 2 + 2, smallestChild = 0, swapIndex = 0;
		boolean leftChildExists = leftIndex < size, rightChildExists = rightIndex < size;
		boolean swapLeft = false, swapRight = false;

		if(leftChildExists) {
			swapLeft = value.compareTo(queue[leftIndex]) > 0;//value bigger, should swap
			if(rightChildExists) //right child can only exist if theres a left child.
				swapRight = value.compareTo(queue[rightIndex]) > 0;//value bigger, should swap
		}


		while(leftChildExists && (swapLeft || swapRight)){//internal node & something to swap
			//we know left child exists at this point. NOT A LEAF
			int smallerIndex = index;//arbitrary

			if(!rightChildExists) //only index is the left
				smallerIndex = leftIndex;
			else if(leftChildExists && rightChildExists)
				smallerIndex = queue[leftIndex].compareTo(queue[rightIndex]) < 0 ? leftIndex : rightIndex;

			swap(index, smallerIndex);

			//update for loop conditionals
			index = smallerIndex;
			leftIndex = index * 2 + 1; rightIndex = index * 2 + 2;
			leftChildExists = leftIndex < size; rightChildExists = rightIndex < size;
			if(leftChildExists) {
				swapLeft = value.compareTo(queue[leftIndex]) > 0;//value bigger, should swap
				if(rightChildExists) //right child can only exist if theres a left child.
					swapRight = value.compareTo(queue[rightIndex]) > 0;//value bigger, should swap
			}
		}

	}

	/**
	 * Swapping helper method bc I've rewritten percolate down like 4 times.
	 * @param i index
	 * @param j other index
	 */
	private void swap(int i, int j) {
		Application temp = queue[i];
		queue[i] = queue[j];
		queue[j] = temp;
	}

	/**
	 * An implementation of percolateUp() method. Restores the min-heap invariant of the tree
	 * by percolating a leaf up the tree. If the element at the given index does not violate the
	 * min-heap invariant (it occurs after its parent), then this method does not modify the heap.
	 * Otherwise, if there is a heap violation, swap the element with its parent and continue
	 * percolating the element up the heap.
	 * 
	 * This method may be implemented recursively OR iteratively.
	 * 
	 * Feel free to add private helper methods if you need them.
	 * 
	 * @param i index of the element in the heap to percolate upwards
	 * @throws IndexOutOfBoundsException if index is out of bounds - do not catch the exception
	 */
	private void percolateUp(int index) {
		// implement the min-heap percolate up algorithm to modify the heap in place
		if(index >= queue.length || index < 0)
			throw new IndexOutOfBoundsException("Index Out Of Bounds");

		Application value = queue[index];
		int parentIndex = (index-1)/2;
		Application parent = queue[parentIndex];

		while(index > 0 && value.compareTo(parent) < 0){ //becomes root or is smaller than parent
			//swap value & parent.
			Application temp = queue[parentIndex];
			queue[parentIndex] = queue[index];
			queue[index] = temp;

			//reset indexes n values for next comparison
			index = parentIndex;
			parentIndex = (index-1)/2;
			parent = queue[parentIndex];
		}
	}

	/**
	 * Returns the Application at the root of this ApplicationQueue, i.e. the Application with
	 * the lowest score.
	 * 
	 * @return the Application in this ApplicationQueue with the smallest score
	 * @throws NoSuchElementException if this ApplicationQueue is empty
	 */
	@Override
	public Application peek() {
		//verify that the queue is not empty
		if(this.isEmpty())
			throw new NoSuchElementException("This ApplicationQueue is empty");
		//return the lowest-scoring application
		return queue[0];  //TODO I think right?
	}

	/**
	 * Returns a deep copy of this ApplicationQueue containing all of its elements in the same order.
	 * This method does not return the deepest copy, meaning that you do not need to duplicate 
	 * applications. Only the instance of the heap (including the array and its size) will be duplicated.
	 * 
	 * @return a deep copy of this ApplicationQueue. The returned new application queue has the same
	 *         length and size as this queue.
	 */
	public ApplicationQueue deepCopy() {
		// implement this method according to its Javadoc comment
		ApplicationQueue deepCopy = new ApplicationQueue(size);
		for(int i = 0; i < size; i++)
			deepCopy.enqueue(queue[i]);
		return deepCopy;
	}

	/**
	 * Returns a String representing this ApplicationQueue, where each element (application) of the
	 * queue is listed on a separate line, in order from the lowest score to the highest score.
	 * 
	 * This implementation is provided.
	 * 
	 * @see Application#toString()
	 * @see ApplicationIterator
	 * @return a String representing this ApplicationQueue
	 */
	@Override
	public String toString() {
		StringBuilder val = new StringBuilder();

		for (Application a : this) {
			val.append(a).append("\n");
		}

		return val.toString();
	}
	/**
	 * Returns an Iterator for this ApplicationQueue which proceeds from the lowest-scored to the
	 * highest-scored Application in the queue.
	 * 
	 * This implementation is provided.
	 * 
	 * @see ApplicationIterator
	 * @return an Iterator for this ApplicationQueue
	 */
	@Override
	public Iterator<Application> iterator() {
		return new ApplicationIterator(this);
	}
}




