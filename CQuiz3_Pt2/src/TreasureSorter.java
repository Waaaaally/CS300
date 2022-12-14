///////////////////////// CUMULATIVE QUIZ FILE HEADER //////////////////////////
// Full Name: Pritish Das
// Campus ID: 908 367 3922
// WiscEmail: pdas26@wisc.edu
//
////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////
//
// This file contains ONE class. You will need to complete the implementation
// this class with respect to the provided requirements in the 2DO tags for
// full credit.
//
// When creating new exception objects, including messages within these objects
// is optional.
////////////////////////////////////////////////////////////////////////////////

// MAKE SURE TO SAVE your source file before uploading it to gradescope.

import java.util.Arrays;

/**
 *  This class contains a single public method: sortHeapOfTreasure(), along with
 *  several helper methods and a test.  This public method uses the heap sort
 *  algorithm to sort treasure values in descending order, but skips the initial
 *  heapify step, since the treasure values are provided in max-heap order.
 *
 *  // 2DO #1-6 below ask you to finish implementing the following methods
 *  (only make edits below each of these numbered TODOs, as described for each):
 *  peekTreasure() - returns largest treasure value from the root position of the max-heap
 *  percolateDown() - recursively enforces heap ordering down from specified index
 *  removeTreasure() - removes treasure value from root position in heap, and returns new size
 *  sortHeapOfTreasure() - computes and returns a sorted array of treasure values
 *
 *  Additionally, the following helper and test methods are provided:
 *  swap() - swaps the values at two different positions within an array
 *  bestOf() - returns the largest of its three string/null arguments
 *  test() - returns true when this test passes and false otherwise
 *  main() - calls and prints out the value returned from test()
 */
public class TreasureSorter {

  /**
   * Returns treasure value from the root position in the max-heap (largest value)
   * We suppose that the provided heap array is NOT empty.
   *
   * @param heap a provided max-heap
   * @return treasure value from the root position in the max-heap
   */
  private static Double peekTreasure(Double[] heap) {
    // 2DO #1: return element from the root position within the zero-indexed max-heap
    return heap[0]; // default return statement added to avoid compiler errors. Feel free to change
    // it.
  }

  /**
   * Recursive helper method which implements percolateDown operation to restore the
   * heap order property if it is violated at a given index position of a provided max-heap.
   *
   * @param index position of the element to start the percolate-down operation from
   * @param heap a provided max-heap array
   * @param size of the heap
   */
  private static void percolateDown(int index, Double[] heap, int size) {
    if(index >= size) return; // cannot percolate further below bottom of heap
    // compute indexes of left and right children positions within heap
    int leftChildIndex = 2 * index + 1;
    int rightChildIndex = 2 * index + 2;

    // store values from index, left, and right child positions into separate variables
    Double indexValue = heap[index];
    Double rightChildValueOrNull = null;
    if(rightChildIndex<size) rightChildValueOrNull = heap[rightChildIndex];
    Double leftChildValueOrNull = null;
    if(leftChildIndex<size) leftChildValueOrNull = heap[leftChildIndex];

    // compute highest priority value between index and its left and right children
    Double best = bestOf(indexValue, rightChildValueOrNull, leftChildValueOrNull);

    if(best.equals(leftChildValueOrNull) ) {
      // 2DO #2: implement percolate down through left subtree
      Double temp = heap[leftChildIndex];
      heap[leftChildIndex] = heap[index];
      heap[index] = temp;
      percolateDown(leftChildIndex, heap, size);
    } else if( best.equals(rightChildValueOrNull) ) {
      // 2DO #3: implement percolate down through right subtree
      Double temp = heap[rightChildIndex];
      heap[rightChildIndex] = heap[index];
      heap[index] = temp;
      percolateDown(rightChildIndex, heap, size);
    }
  }

  /**
   * Removes treasure value from root position in heap, and then return heap's new size
   * We suppose that the provided heap array is NOT empty.
   *
   * @param heap a provided max-heap
   * @param size of heap before removeTreasure is called.
   * @return size is the new size of heap after removeTreasure is performed
   */
  private static int removeTreasure(Double[] heap, int size) {
    // 2DO #4: remove the root element from the provided heap, correct
    //          heap order violations, and then return the resulting heap size
    heap[0] = heap[size-1];
    heap[size - 1] = null;

    percolateDown(0, heap, size-1);
    return size -1;
  }

  /**
   * Sorts an array of treasure values into descending order using a max-heap.
   * This sort operation does not operate in-place.
   * @param heap a max-heap
   * @return reference to the sorted array
   */
  public static Double[] sortHeapOfTreasure(Double[] heap) {
    int heapSize = heap.length;
    // Sorted is an over sized array that will be filled with Treasure values
    // in sorted (descending) order.  When it is full, it will be returned.
    Double[] sorted = new Double[heap.length];
    int sortedSize = 0;

    while(heapSize > 0) {
      // 2DO #5: insert best element from heap into earliest free position in sorted array
      sorted[sortedSize] = heap[0];
      // 2DO #6: remove the best value from the heap
      heapSize = removeTreasure(heap, heapSize);
      sortedSize++;
    }
    return sorted;
  }

  //MAKE SURE TO SAVE your source file before uploading it to gradescope.


  /**
   * Swaps the elements at indexes i and j in the provided array
   * @param array in which elements are being moved
   * @param i the first index being changed in that array
   * @param j the second index being changed in that array
   */
  private static void swap(Double[] array, int i, int j) {
    Double temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

  /**
   * Returns the largest between the provided arguments.
   * Null arguments are ignored (considered smaller than doubles).
   * @param a, b, c are the doubles being compared
   * @return the largest of those doubles
   * @throws IllegalArgumentException when all doubles are null
   */
  private static Double bestOf(Double a, Double b, Double c) {
    if(a != null &&
            (b == null || a.compareTo(b) >= 0) &&
            (c == null || a.compareTo(c) >= 0))
      return a;
    else if(b != null &&
            (a == null || b.compareTo(a) >= 0) &&
            (c == null || b.compareTo(c) >= 0))
      return b;
    else if(c != null &&
            (a == null || c.compareTo(a) >= 0) &&
            (b == null || c.compareTo(b) >= 0))
      return c;
    else
      throw new IllegalArgumentException("None of these doubles are the best");
  }


  /**
   * One short and simple test for the sortHeapOfTreasure method.
   * @return true when this test passes, otherwise false
   */
  private static boolean test() {
    try {
      Double[] heapOfTreasure = new Double[] {
              98.6,
              32.0,
              64.0,
              1.6,
              3.1,
              9.8
      };
      Double[] sortedTreasure = new Double[] {
              98.6,
              64.0,
              32.0,
              9.8,
              3.1,
              1.6
      };

      if(!Arrays.deepEquals(sortHeapOfTreasure(heapOfTreasure), sortedTreasure)){
        return false;}

    }catch(Exception e) {
      return false;
    }

    return true;
  }

  /**
   * Driver for the test method above.
   * @param args is unused
   */
  public static void main(String[] args) {
    System.out.println("test(): "+test());
  }
}

