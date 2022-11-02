import java.rmi.server.ExportException;
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
 * This class implements unit test methods to check the correctness of Application, 
 * ApplicationIterator, ApplicationQueue and OpenPosition classes in the assignment.
 *
 */
public class OpenPositionTester {

    /**
     * This method tests and makes use of the Application constructor, getter methods,
     * toString() and compareTo() methods.
     * 
     * @return true when this test verifies the functionality, and false otherwise
     */
    public static boolean testApplication() {
        // create an Application with valid input
        try{
            Application validApp = new Application("Anton", "earlcups@gmail.com", 87);
        }catch(Exception e){
            System.out.println("Exception thrown when none expected in validApp test");
            return false;
        }

    	// create an Application with invalid input:
        // blank name
        try{
            Application application = new Application("", "earlcups@gmail.com", 87);
            System.out.println("Exception wasn't thrown when expected in blankName test");
            return false;
        }catch (IllegalArgumentException e){}
        catch(Exception e){
            System.out.println("Unexpected exception thrown in blankName test");
            return false;
        }
        // null name
        try{
            Application validApp = new Application(null, "earlcups@gmail.com", 87);
            System.out.println("Exception wasn't thrown when expected in nullName test");
            return false;
        }catch (IllegalArgumentException e){}
        catch(Exception e){
            System.out.println("Unexpected exception thrown in null test");
            return false;
        }
        // blank email
        try{
            Application application = new Application("Anton", "", 87);
            System.out.println("Exception wasn't thrown when expected in blankEmail test");
        }catch (IllegalArgumentException e){}
        catch(Exception e){
            System.out.println("Unexpected exception thrown in blankName test");
            return false;
        }
        // null email
        try{
            Application application = new Application("Anton", null, 87);
            System.out.println("Exception wasn't thrown when expected in nullEmail test");
            return false;
        }catch (IllegalArgumentException e){}
        catch(Exception e){
            System.out.println("Unexpected exception thrown in blankName test");
            return false;
        }
        // no @ email
        try{
            Application application = new Application("Anton", "earlcupsgmail.com", 87);
            System.out.println("Exception wasn't thrown when expected in no@ test");
            return false;
        }catch (IllegalArgumentException e){}
        catch(Exception e){
            System.out.println("Unexpected exception thrown in no @ test");
            return false;
        }
        // too many @ email
        try{
            Application application = new Application("Anton", "earl@cups@gmail.com", 87);
            System.out.println("Exception wasn't thrown when expected in @@@ test");
            return false;
        }catch (IllegalArgumentException e){}
        catch(Exception e){
            System.out.println("Unexpected exception thrown in too many @ test");
            return false;
        }
        // invalid score negative
        try{
            Application application = new Application("Anton", "earlcups@gmail.com", -1);
            System.out.println("Exception wasn't thrown when expected in invalidNegScore test");
            return false;
        }catch (IllegalArgumentException e){}
        catch(Exception e){
            System.out.println("Unexpected exception thrown in negative invalidScore test");
            return false;
        }
        // extra credit score
        try{
            Application application = new Application("Anton", "earlcups@gmail.com", 101);
            System.out.println("Exception wasn't thrown when expected in invalid+++Score test");
            return false;
        }catch (IllegalArgumentException e){}
        catch(Exception e){
            System.out.println("Unexpected exception thrown in ec invalidScore test");
            return false;
        }

        // verify getters
        try{
            Application application = new Application("Anton", "earlcups@gmail.com", 87);

            if(!application.getName().equals("Anton")){
                System.out.println("Name Getter Fail");
                return false;
            }
            if(!application.getEmail().equals("earlcups@gmail.com")){
                System.out.println("Email Getter Fail");
                return false;
            }
            if(application.getScore() != 87){
                System.out.println("Score Getter Fail");
                return false;
            }
        }catch (Exception e){
            System.out.println("Exception thrown when unexpected in Getters Test");
            return false;
        }
        // verify compareTo
        try{
            Application application = new Application("Anton", "earlcups@gmail.com", 87);
            Application application1 = new Application("Emry", "mrSiggle@gmail.com", 100);
            Application application2 = new Application("Drut", "theIncident@gmail.com", 100);

            if(application.compareTo(application1) > 0){
                System.out.println("CompareTo Bad w/ smaller against bigger");
                return false;
            }

            if(application1.compareTo(application) < 0){
                System.out.println("CompareTo Bad w/ bigger against smaller");
                return false;
            }

            if(application2.compareTo(application1) > 0){
                System.out.println("CompareTo Bad w/ equal");
                return false;
            }
        }catch (Exception e){
            System.out.println("Exception thrown when unexpected in compareTo Test");
            return false;
        }
        
        // verify toString
        try{
            Application application = new Application("Anton", "earlcups@gmail.com", 87);
            //name + ":" + email + ":" + score;
            if(!application.toString().equals("Anton:earlcups@gmail.com:87")){
                System.out.println("ToStringBad");
                return false;}
        }catch (Exception e){
            System.out.println("Exception thrown when unexpected in toString Test");
            return false;
        }
            
        return true;
    }

    /**
     * This method tests and makes use of the ApplicationIterator class.
     * 
     * @return true when this test verifies the functionality, and false otherwise
     */
    public static boolean testApplicationIterator() {
    	// create an ApplicationQueue with capacity at least 3
        ApplicationQueue applications = new ApplicationQueue(3);
        // and at least 3 Applications with different scores
        Application application = new Application("Anton", "earlCups@gmail.com", 87);
        Application application1 = new Application("Emry", "mrSiggle@gmail.com", 100);
        Application application2 = new Application("Drut", "theIncident@gmail.com", 58);
    	// add those Applications to the queue
        applications.enqueue(application);
        applications.enqueue(application1);
        applications.enqueue(application2);
    	// verify that iterating through the queue gives you the applications in order of
    	// INCREASING score
        int lastScore = -1;
        for(Application i : applications){
            if(lastScore > i.getScore())
                return false;
            lastScore = i.getScore();
        }
        return true;
    }
    
    /**
     * This method tests and makes use of the enqueue() and dequeue() methods
     * in the ApplicationQueue class.
     * 
     * @return true when this test verifies the functionality, and false otherwise
     */
    public static boolean testEnqueueDequeue() {
    	// create an ApplicationQueue with capacity 3 TODO
        ApplicationQueue applications = new ApplicationQueue(3);
    	// and at least 4 Applications with different scores
        Application application = new Application("Anton", "earlCups@gmail.com", 87);
        Application application1 = new Application("Emry", "mrSiggle@gmail.com", 100);
        Application application2 = new Application("Drut", "theIncident@gmail.com", 58);
        Application application3 = new Application("name", "theSkeld@gmail.com", 10);

        try{
            applications.enqueue(null);
            System.out.println("No exception was thrown when expected in NullEnqueue test");
            return false;
        }catch(NullPointerException e){}
        catch(Exception e){
            System.out.println("General Exception thrown instead of expected in NullEnqueue test");
            return false;
        }
        // enqueue one valid application
        try{
            applications.enqueue(application);
        } catch(Exception e){
            System.out.println("General Exception thrown in valid case");
            return false;
        }
        // enqueue two more valid applications
        try{
            applications.enqueue(application1);
            applications.enqueue(application2);
        } catch(Exception e){
            System.out.println("General Exception thrown in valid case");
            return false;
        }
        // enqueue one more application (exceeds capacity)
        try{
            applications.enqueue(application3);
            System.out.println("No exception was thrown when expected in fullQueue Enqueue test");
            return false;
        }catch(IllegalStateException e){}
        catch(Exception e){
            System.out.println("General Exception thrown instead of expected in fullQueue Enqueue");
            return false;
        }
    	// dequeue one application (should be lowest score)
        try{
            Application dequeued = applications.dequeue();
            if(dequeued != null && dequeued.compareTo(applications.peek()) > 0 ){
                System.out.println("Dequeued value was not the smallest in the list");
                return false;
            }
        } catch(Exception e){
            System.out.println("General Exception thrown in valid case for dequeue");
            return false;
        }
        // dequeue all applications
        try{
            applications.dequeue();
            applications.dequeue();
        }catch(Exception e){
            System.out.println("General Exception thrown in valid case for dequeue");
            return false;
        }
        // dequeue from an empty queue
        try{
            applications.dequeue();
        }catch(NoSuchElementException e){}
        catch(Exception e){
            System.out.println("General exception thrown instead of expected in emptyQueue " +
                    "dequeue");
            return false;
        }
        return true;
    }

    /**
     * This method tests and makes use of the common methods (isEmpty(), size(), peek())
     * in the ApplicationQueue class.
     * 
     * @return true when this test verifies the functionality, and false otherwise
     */
    public static boolean testCommonMethods() {
    	// create an ApplicationQueue with 0 capacity (should fail)
        try{
            ApplicationQueue hollowQueue = new ApplicationQueue(0);
            System.out.println("No exception thrown when expected in 0 capacity test");
            return false;
        }catch(IllegalArgumentException e){}
        catch (Exception e){
            System.out.println("General exception thrown instead of expected in 0 capacity test");
            return false;
        }
    	// create an ApplicationQueue with capacity 3
        ApplicationQueue applicationQueue;
        try{
            applicationQueue = new ApplicationQueue(3);
        } catch (Exception e){
            System.out.println("General exception thrown in valid case");
            return false;
        }
    	// and at least 3 Applications with different scores
        Application application = new Application("Anton", "earlCups@gmail.com", 50);
        Application application1 = new Application("Emry", "mrSiggle@gmail.com", 100);
        Application application2 = new Application("Drut", "theIncident@gmail.com", 0);
        // verify the methods' behaviors on an empty queue (isEmpty(), size(), peek())
        try{
            if(!applicationQueue.isEmpty()){
                System.out.println("isEmpty issue. Said queue was not empty with 0 things added");
                return false;
            }
            if(applicationQueue.size() != 0){
                System.out.println("size() issue. Said queue size was not 0 with 0 things added");
                return false;
            }
            applicationQueue.peek();
        }catch(NoSuchElementException e){}
        catch(Exception e){
            System.out.println("An exception that wasn't expected was thrown in emptyQueue common" +
                    " methods test");
            System.out.println(e.getMessage());
            return false;
        }
        // add one Application and verify the methods' behaviors
        try{
            applicationQueue.enqueue(application);
            if(applicationQueue.isEmpty()){
                System.out.println("isEmpty issue. Said queue was not empty with 1 things added");
                return false;
            }
            if(applicationQueue.size() != 1){
                System.out.println("size() issue. Said queue size was not 1 with 1 things added");
                return false;
            }
            if(!applicationQueue.peek().equals(application)){
                System.out.println("peek() issue. Didn't return the only thing added for compare");
            }
        }catch(Exception e){
            System.out.println("An exception that wasn't expected was thrown in 1item added Queue" +
                    " common methods test");
            return false;
        }
        // add the rest of the Applications and verify the methods' behaviors
        try{
            applicationQueue.enqueue(application1);
            applicationQueue.enqueue(application2);
            if(applicationQueue.isEmpty()){
                System.out.println("isEmpty issue. Said queue was not empty with 3 things total");
                return false;
            }
            if(applicationQueue.size() != 3){
                System.out.println("size() issue. Said queue size was not 3 with 3 things total");
                return false;
            }
            if(!applicationQueue.peek().equals(application2)){
                System.out.println("peek() issue. Didn't return the smallest one");
                return false;
            }
        }catch(Exception e){
            System.out.println("An exception that wasn't expected was thrown in 3item added Queue" +
                    " common methods test");
            return false;
        }
        return true;
    }

    /**
     * This method tests and makes use of OpenPosition class.
     * 
     * @return true when this test verifies the functionality, and false otherwise
     */
    public static boolean testOpenPosition() {
        // create an OpenPosition with 0 capacity (should fail)
        try{
            OpenPosition openPosition = new OpenPosition("imposter", 0);
            System.out.println("No exception thrown when expected in 0 capacity test");
            return false;
        }catch(IllegalArgumentException e){}
        catch(Exception e){
            System.out.println("General exception thrown with 0 capacity test");
            return false;
        }

    	// create an OpenPosition with capacity 3
        OpenPosition openPosition = new OpenPosition("imposter", 3);
        // and at least 5 Applications with different scores
        Application application1= new Application("Anton", "earlCups@gmail.com", 87);
        Application application2 = new Application("Emry", "mrSiggle@gmail.com", 100);
        Application application3 = new Application("Drut", "theIncident@gmail.com", 58);
        Application application4 = new Application("name", "words@gmail.com", 37);
        Application application5 = new Application("Tim", "screwTim@gmail.com", 0);

//         verify that the 3 MIDDLE-scoring Applications can be added
//         don't use the highest and lowest scoring applications YET

        try{
            if(!openPosition.add(application1)){
                System.out.println("1st mid application wasn't properly added");
                return false;
            }
            if(!openPosition.add(application3)){
                System.out.println("2nd mid application wasn't properly added");
                return false;
            }
            if(!openPosition.add(application4)){
                System.out.println("3rd mid application wasn't properly added");
                return false;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        // verify that getApplications returns the correct value for your input
        try{
//            openPosition.add(application1);
//            openPosition.add(application3);
//            openPosition.add(application4);
            String applicationString = openPosition.getApplications();
            String actual =
                    application4.toString()+"\n"+application3.toString()+"\n"+application1.toString()+"\n";

            if(!actual.equals(applicationString)){
                System.out.println("Get Applications Error");
                return false;
            }
        }catch (Exception e){
            System.out.println("Get Application issue, threw an error");
            System.out.println(e.getMessage());
            return false;
        }
        // verify that the result of getTotalScore is the sum of all 3 Application scores
        try{
            int expected =
                    application1.getScore() + application3.getScore() + application4.getScore();
            if(openPosition.getTotalScore() != expected){
                System.out.println("Issue with GetTotalScore");
                return false;
            }
        }catch (Exception e){
            System.out.println("Wrong score found");
            return false;
        }
        // verify that the lowest-scoring application is NOT added to the OpenPosition
        try {
            if(openPosition.add(application5)) {
                System.out.println("Error, Added smallest thing which should no add");
                return false;
            }
        }catch (Exception e) {
            System.out.println("Exception thrown when none expected");
            return false;
        }
        
        // verify that the highest-scoring application IS added to the OpenPosition
        try{
            if(!openPosition.add(application2)){
                System.out.println("add Error, should've been able to add highest one");
                return false;
            }
        }catch(Exception e){
            System.out.println("Exception thrown when none expected");
            return false;
        }
        // verify that getApplications has changed correctly
        try{
            String applicationString = openPosition.getApplications();
            String actual =
                    application3.toString()+"\n"+application1.toString()+"\n"+application2.toString()+"\n";

            if(!actual.equals(applicationString)){
                System.out.println("Get Applications Error");
                return false;
            }
        }catch (Exception e){
            System.out.println("Get Application issue, threw an error");
            System.out.println(e.getMessage());
            return false;
        }
        // verify that the result of getTotalScore has changed correctly
        try{
            int expected =
                    application1.getScore() + application2.getScore() + application3.getScore();
            if(openPosition.getTotalScore() != expected){
                System.out.println("Issue with GetTotalScore");
                return false;
            }
        }catch (Exception e){
            System.out.println("Wrong score found");
            return false;
        }

        return true;
    }
    
    /**
     * This method calls all the test methods defined and implemented in your OpenPositionTester class.
     * 
     * @return true if all the test methods defined in this class pass, and false otherwise.
     */
    public static boolean runAllTests() {
        return testApplication() && testApplicationIterator()
                && testEnqueueDequeue() && testCommonMethods()
                && testOpenPosition();
    }

    /**
     * Driver method defined in this OpenPositionTester class
     * 
     * @param args input arguments if any.
     */
    public static void main(String[] args) {
        System.out.print(runAllTests());
    }
}
