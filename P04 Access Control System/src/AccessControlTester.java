//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Access Control System
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

public class AccessControlTester {
    public static void main(String [] args){
        runAllTests();
    }

    /**
     * Checks the correctness of AccessControl constructor and accessor/mutator methods in User
     * class. Also checks isValidLogin()
     * @return true if method calls return expected else false
     */
    public static boolean testUserConstructorAndMethods(){
        System.out.println("Testing UserConstructor and Methods");
        User testUser = new User("Testing", "Password", false);

        if(testUser.isValidLogin("password") == true){
            System.out.println("isValidLogin() Error. False psswrd test failed");
            return false;
        }

        if(testUser.isValidLogin(null) != false){
            System.out.println("isValidLogin() Error. Null input test failed");
            return false;
        }

        if(testUser.isValidLogin("Password") != true){
            System.out.println("isValidLogin() Error. Valid case test failed.");
            return false;
        }

        if(!testUser.getUsername().equals("Testing")){
            System.out.println("getUsername() Error. Returned incorrect string");
            return false;
        }

        if(testUser.getIsAdmin() != false){
            System.out.println("getIsAdmin() Error. Returned incorrect bool");
            return false;
        }

        testUser.setPassword("password");
        if(testUser.isValidLogin("Password") != false){
            System.out.println("setPassword() Error. Failed to update password");
            return false;
        }

        testUser.setIsAdmin(true);
        if(testUser.getIsAdmin() != true){
            System.out.println("getIsAdmin() Error. Failed to update admin status");
            return false;
        }

        return true;
    }

    /**
     * Checks the correctness of AccessControl.isValidLogin() method when
     * called with incorrect username or not matching (username, password) pair
     * @return true if method calls return expected else false
     */
    public static boolean testAccessControlIsValidLoginNotValidUser(){
        System.out.println("Now testing AccessControl is valid login");
        AccessControl terminal = new AccessControl();
        //should be at least one default user now

        if(AccessControl.isValidLogin("admin", "root") != true){
            System.out.println("isValidLogin Error. Valid case test failed");
            return false;
        }

        if(AccessControl.isValidLogin("Admin", "root") != false){
            System.out.println("isValidLogin Error. Incorrect username test failed");
            return false;
        }

        if(AccessControl.isValidLogin("admin", "ROOT") != false){
            System.out.println("isValidLogin Error. Incorrect password test failed");
            return false;
        }

        if(AccessControl.isValidLogin(null, "root") != false){
            System.out.println("isValidLogin Error. Null username test failed");
            return false;
        }

        if(AccessControl.isValidLogin("admin", null) != false){
            System.out.println("isValidLogin Error. Null password test failed");
            return false;
        }

        return true;
    }

    /**
     * Creates a new AccessControl object and does not log in an admin.
     * This test must fail if addUser(String username) does not return false or
     * if a user was added to the list of user after the method returns.
     * @return true if method calls return expected else false
     */
    public static boolean testAddUserWithNoAdminPowers() {
        System.out.println("Now testing add/removeUser w/o admin powers");

        AccessControl terminal = new AccessControl();
        terminal.addUser("tester");

        terminal.setCurrentUser("tester"); //not admin

        if(terminal.addUser("nonAdmin") != false){
            System.out.println("addUser() error. NonAdmin useage test case failed");
            return false;
        }

        return true;
    }

    /**
     * Checks the correctness of addUser and removeUser methods when the
     * current user has admin powers
     * @return true if method calls return expected else false
     */
    public static boolean testAddRemoveUserWithAdminPowers(){
        System.out.println("Now testing add/removeUser w/ admin powers");
        AccessControl terminal = new AccessControl();
        terminal.setCurrentUser("admin");

        if(terminal.addUser("random") != true){
            System.out.println("addUser() error. Valid test case failed");
            return false;
        }

        if(terminal.addUser(null) != false){
            System.out.println("addUser() error. Null input test case failed");
            return false;
        }

        if(terminal.removeUser("random") != true){
            //bc users added and removed are part of a static var, the calls above
            // should "still matter"
            System.out.println("removeUser() error. Valid test case failed");
            return false;
        }

        if(terminal.removeUser(null) != false){
            System.out.println("removeUser() error. Null input test case failed");
        }

        return true;
    }

    /**
     * Calls every test method
     *
     * @return true if every testmethodcall returns expected(true) else false
     */
    public static boolean runAllTests(){
        if(testAccessControlIsValidLoginNotValidUser() == false)
            return false;
        if(testUserConstructorAndMethods() == false)
            return false;
        if(testAddUserWithNoAdminPowers() == false)
            return false;
        if(testAddRemoveUserWithAdminPowers() == false)
            return false;

        System.out.println("All test cases passed");
        return true;
    }
}