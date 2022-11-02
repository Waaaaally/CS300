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
 * A application handler of an open position using priority queue. Only saves a new Application
 * when the queue is not full, or when it can replace older, lower-scored ones with its higher
 * scores.
 */
public class OpenPosition {
	private String positionName;
    private ApplicationQueue applications; // the priority queue of all applications
    private int capacity;                  // the number of vacancies
    
    /**
     * Creates a new open position with the given capacity
     * 
     * @param capacity the number of vacancies of this position
     * @throws IllegalArgumentException with a descriptive error message if the capacity is not a
     *                                  positive integer
     */
    public OpenPosition(String positionName, int capacity) {
    	// verify the value of capacity
        if(capacity < 1)
            throw new IllegalArgumentException("Capacity cannot be 0 or negative");

        this.positionName = positionName;
        this.capacity = capacity;
        applications = new ApplicationQueue(capacity);
    }
    
    public String getPositionName() { return this.positionName; }
    
    /**
     * Tries to add the given Application to the priority queue of this position.  return
     * False when the new Application has a lower score than the lowest-scored Application
     * in the queue.
     * 
     * @return Whether the given Application was added successfully
     */
    public boolean add(Application application) {
        int comparison = 0;
        if(!applications.isEmpty())
            comparison = application.compareTo(applications.peek());

    	//  if the queue is full, determine whether this application has a higher score than
        //  the current lowest-scoring application; if not, do not add it
        if(applications.size() == capacity){
            if(comparison > 0){
                applications.dequeue();
                applications.enqueue(application);
                return true;
            }
        }
        // if there is room in the queue OR this application has a higher score than the current
        // lowest-scoring application, add it to the queue
        else if(applications.size() < capacity || comparison > 0){
            applications.enqueue(application);
            return true;
        }
        return false;
    }
    
    /**
     * Returns the list of Applications in the priority queue.
     * 
     * @return The list of Applications in the priority queue, in increasing order of the
     * scores.
     */
    public String getApplications() {
        return applications.toString();
    }
    
    /**
     * Returns the total score of Applications in the priority queue.
     * 
     * @return The total score of Applications in the priority queue.
     */
    public int getTotalScore() {
        // calculate the total score of all applications currently in the queue
        int totalScore = 0;

    	for(Application i : applications){
            totalScore += i.getScore();
        }
        return totalScore;
    }
    
    
}
