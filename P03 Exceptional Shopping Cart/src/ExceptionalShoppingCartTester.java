//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Exceptional Shopping Cart - PO1 w/ Exceptions
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

import jdk.jshell.spi.ExecutionControlProvider;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;

public class ExceptionalShoppingCartTester {

    public static void main(String[] args) {
        if (runAllTests()){
            System.out.println("All tests passed");
        }
    }

    /**
     * Tests the Exception Handling of the LookUpMethods
     *
     * @return false if an unexpected exception occurs otherwise true
     */
    public static boolean testLookupMethods() {
        System.out.println("Running testLookupMethods");

        try{
            ExceptionalShoppingCart.lookupProductByName("Eggs");
            System.out.println("Valid input (Name) test passed");

            ExceptionalShoppingCart.lookupProductById(4390);
            System.out.println("Valid input (id) test passed");

        }catch (Exception e){
            System.out.println("Valid Input. No exception should be thrown");
            return false;
        }

        try{
            //Illegal Arguments Exception
            ExceptionalShoppingCart.lookupProductById(123);
            return false;
        }
        catch(IllegalArgumentException e){
            System.out.println("Non 4digit key error caught successfully");
        } catch(Exception e){
            System.out.println("Caught a general exception instead of IllegalArgument");
            return false;
        }

        try{
            //NoSuchElementException
            ExceptionalShoppingCart.lookupProductById(9999);
            return false;
        }catch(NoSuchElementException e){
            System.out.println("Nonregistered id error caught successfully");
        }catch(Exception e){
            System.out.println("Caught a general exception instead of NoSuchElementException");
            return false;
        }

        try{
            //NoSuchElementException
            ExceptionalShoppingCart.lookupProductByName("Seal"); //aw hell nah
            return false;
        }catch (NoSuchElementException e){
            System.out.println("Nonregistered name error caught successfully");
        }catch (Exception e){
            System.out.println("Caught a general exception instead of NoSuchElementException");
            return false;
        }


        System.out.println("-----------------");
        return true;
    }
    /**
     * Tests the Exception Handling of the AddItemToMarketCatalog method
     *
     * @return false if an unexpected exception occurs otherwise true
     */
    public static boolean testAddItemToMarketCatalog(){
        System.out.println("Running testAddItemToMarketCatalog");

        try{
            //normal input
            ExceptionalShoppingCart.addItemToMarketCatalog("1234", "Seal", "3.13");
            System.out.println("Valid input test passed");
        }catch (Exception e){
            System.out.println("Valid input. There should be no error");
            return false;
        }

        try{
            //Illegal Argument Exception - Key Not 4digits
            ExceptionalShoppingCart.addItemToMarketCatalog("123", "Sugar", "$3.13");
            return false;
        }catch(IllegalArgumentException e){
            System.out.println("Non 4digit key error caught successfully");
        }catch(Exception e){
            System.out.println("Caught a general exception instead of IllegalArgumentException");
            return false;
        }

        try{
            //Illegal Argument Exception - Empty Name
            ExceptionalShoppingCart.addItemToMarketCatalog("1234", "", "$3.13");
            return false;
        }catch(IllegalArgumentException e){
            System.out.println("Empty name error caught successfully");
        }catch(Exception e){
            System.out.println("Caught a general exception instead of IllegalArgumentException");
            return false;
        }

        try{
            //Illegal Argument Exception - Null Name
            ExceptionalShoppingCart.addItemToMarketCatalog("1234", null, "$3.13");
            return false;
        }catch(IllegalArgumentException e){
            System.out.println("Null name error caught successfully");
        }catch(Exception e){
            System.out.println("Caught a general exception instead of IllegalArgumentException");
            return false;
        }

        try{
            //Illegal Argument Exception - Non Parsable Double
            ExceptionalShoppingCart.addItemToMarketCatalog("1234", "Seal", "$:D");
            return false;
        }catch(IllegalArgumentException e){
            System.out.println("NonParsable Double error caught successfully");
        }catch(Exception e){
            System.out.println("Caught a general exception instead of IllegalArgumentException");
            return false;
        }

        try{
            //Illegal Argument Exception - Negative Price
            ExceptionalShoppingCart.addItemToMarketCatalog("1234", "Seal", "-$3.14");
            return false;
        }catch(IllegalArgumentException e){
            System.out.println("Negative Price error caught successfully");
        }catch(Exception e){
            System.out.println("Caught a general exception instead of IllegalArgumentException");
            return false;
        }

        try{
            //Illegal Argument Exception - Bad $ Placement
            ExceptionalShoppingCart.addItemToMarketCatalog("1234", "Seal", "3.1$4");
            System.out.println("Bad $ Test passed");
        }catch(IllegalArgumentException e){
            System.out.println("No error should be thrown from a bad $");
            return false;
        }catch(Exception e){
            System.out.println("Caught a general exception instead of IllegalArgumentException");
            return false;
        }

        System.out.println("-----------------");
        return true;
    }
    /**
     * Tests the Exception Handling of the saveCartSummary method
     *
     * @return false if an unexpected exception occurs otherwise true
     */
    public static boolean testSaveCartSummary(){
        System.out.println("Running testSaveCartSummary");
        File file = new File("Words.txt");
        String[] cart = new String[]{"Apple", "Apple", "Eggs", "Pizza", "Milk", null, null};

        try{
            //Normal Case
            ExceptionalShoppingCart.saveCartSummary(cart, 5, file);
            System.out.println("Valid input test passed");
        } catch (Exception e){
            System.out.println("Valid input. There should be no error");
            return false;
        }

        try{
            // IllegalArgumentException - Negative Size
            ExceptionalShoppingCart.saveCartSummary(cart, -1, file);
            return  false;
        }catch (IllegalArgumentException e){
            System.out.println("Negative Size Error caught successfully");
        }catch (Exception e){
            System.out.println("Caught a general exception instead of IllegalArgumentException");
            return false;
        }

        System.out.println("-----------------");
        return true;
    }

    /**
     * Tests the Exception Handling of the ParseCartSummary method
     *
     * @return false if an unexpected exception occurs otherwise true
     */

    public static boolean testParseCartSummary(){
        System.out.println("Running testParseCartSummary");

        String[] cart = new String[5];

        try{
            //Valid Test
            ExceptionalShoppingCart.parseCartSummaryLine("( 1 ) Eggs", cart, 0);

            System.out.println("Valid input test passed");
        }catch (DataFormatException e){
            System.out.println("Formatting error occured");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("General Exception thrown in valid input test");
            return false;
        }

        try{
            // IllegalArgumentException - Not found in market items
            ExceptionalShoppingCart.parseCartSummaryLine("( 1 ) Parrot", cart, 0);
            System.out.println("This shouldn't be printed - IllegalArgException Test");
            return false;
        }catch (IllegalArgumentException e){
            System.out.println("Not found in market items error caught successfully");
        }catch (Exception e){
            System.out.println("Caught a general exception instead of IllegalArgument");
            return false;
        }

        try{
            //Dataformat exception
            ExceptionalShoppingCart.parseCartSummaryLine("( 11 ) Apple", cart, 0);
            ExceptionalShoppingCart.parseCartSummaryLine("( -1 ) Apple", cart, 0);
            return false;
        }catch (DataFormatException e){
            System.out.println("Illegal NbOccurences error caught");
        }catch (Exception e){
            System.out.println("Caught a general exception instead of Dataformat");
            return false;
        }

        try{
            // IllegalStateException - Cart reached capacity
            cart = new String[5];
            ExceptionalShoppingCart.parseCartSummaryLine("( 6 ) Apple", cart, 0);

            return false;
        }catch (IllegalStateException e){
            System.out.println("Cart Capacity Reached error caught successfully");
        }catch (Exception e){
            System.out.println("Caught a general exception instead of IllegalStateExcepetion");
            return false;
        }

        System.out.println("-----------------");
        return true;
    }

    /**
     * Tests the Exception Handling of the LoadCartSumamry method
     *
     * @return false if an unexpected exception occurs otherwise true
     */

    public static boolean testLoadCartSummary(){
        System.out.println("Running testLoadCartSummary");

        File fileValid = new File("test_summaryValid.txt");
        try (PrintWriter printWriter = new PrintWriter(fileValid)) {
            printWriter.print(
                    "( 2 ) Apple\n" + "( 1 ) Milk\n" + "( 1 )\n" + "( one ) Chocolate\n"
                            + "( 3 ) Pizza\n" + "( 1 )Unknown\n" + "( 1 ) Grape\n");
        } catch (FileNotFoundException e) {
            return false;
        }

        String[] cart = new String[10];

        try{
            //Valid Test
            ExceptionalShoppingCart.loadCartSummary(fileValid,cart,0);
            System.out.println("Valid input test passed");

        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Error, Valid Input was given. No exception should be thrown");
            return false;
        }

        try{
            // IllegalArgumentException - Negative size
            ExceptionalShoppingCart.loadCartSummary(fileValid,cart,-1);
            return false;
        }catch (IllegalArgumentException e){
            System.out.println("Negative size error caught successfully");
        }catch (Exception e){
            System.out.println("Caught a general exception instead of IllegalArgument");
            return false;
        }


        File fileOverloaded = new File("test_summaryOverloaded.txt");
        try (PrintWriter printWriter = new PrintWriter(fileOverloaded)) {
            printWriter.print(
                    "( 6 ) Apple\n");
        } catch (FileNotFoundException e) {
            return false;
        }
        String[] cartOverloaded = new String[5];

        try{
            //Illegal State Exception - Cart Capacity Reached
            ExceptionalShoppingCart.loadCartSummary(fileOverloaded,cartOverloaded,0);

            return false;
        }catch (IllegalStateException e){
            System.out.println("Cart Capacity error caught successfully");
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Caught a general exception instead of IllegalStateException");
            return false;
        }

        System.out.println("-----------------");
        return true;
    }

    /**
     * Runs every testmethod. Makes the main method look pretty*~
     *
     * @return false if any test method returns false, otherwise true
     */

    public static boolean runAllTests() {
        if(testLookupMethods() == false){
            return false;
        }
        if(testAddItemToMarketCatalog() == false){
            return false;
        }
        if(testSaveCartSummary() == false){
            return false;
        }
        if(testParseCartSummary() == false){
            return false;
        }
        if(testLoadCartSummary() == false){
            return false;
        }

        return true;
    }
}

