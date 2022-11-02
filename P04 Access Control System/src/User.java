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

public class User {
    private final String USERNAME; // The name of the user
    private String password; // The password of the user
    private boolean isAdmin; // Admin powers

    // Creates a new user with the given username, password, and admin status
    public User(String username, String password, boolean isAdmin){
        USERNAME = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    // Report whether the password is correct
    public boolean isValidLogin(String password){
        if(password != null && password.equals(this.password))
            return true;
        else
            return false;
    }

    // Return the name of the user
    public String getUsername(){
       return USERNAME;
    }

    // Report whether the user is an admin
    public boolean getIsAdmin(){
        return isAdmin;
    }

    // Set the new password
    public void setPassword(String password){
        this.password = password;
    }

    // Set the new admin status
    public void setIsAdmin(boolean isAdmin){
        this.isAdmin = isAdmin;
    }

}
