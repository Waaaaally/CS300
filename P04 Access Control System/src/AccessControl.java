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

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class AccessControl {
    public static void main(String[] args){
    }

    private static ArrayList<User> users; // An ArrayList of valid users
    private User currentUser; // Who is currently logged in, if anyone?
    private static final String DEFAULT_PASSWORD = "changeme";
    // Default password given to new users or when we reset a password

    /**
     * A no-argument constructor
     */
    public AccessControl(){
        if(users == null){
            users = new ArrayList<User>();
            users.add(new User("admin", "root", true));
        }
        currentUser = null;
    }


    /**
     * Report whether a given username/password pair is a valid login
     * @param username username of current user object
     * @param password password associated with current username
     * @return true if username/password pair match else false
     */
    public static boolean isValidLogin(String username, String password){
        if(username == null || password == null)
            return false;

        for(User i : users){
            if(i.getUsername().equals(username) && i.isValidLogin(password))
                return true;
        }
        return false;
    }


    /**
     * Change the password of the current user
     * @param newPassword new password to use from old one
     */
    public void changePassword(String newPassword){
        if(newPassword != null)
            currentUser.setPassword(newPassword);
    }

    /**
     *  Log out the current user
     */
    public void logout(){
        currentUser = null;
    }

    /**
     * A mutator that you can use to write tests without simulating user input.
     * It sets the current user to the user from the users list whose username matches the string
     * provided as input to the method (exact match case sensitive).
     * @param username specified username in users to change to
     */
    public void setCurrentUser(String username){
        for(User user : users){ //parsing all usernames
            if(user.getUsername().equals(username))
                currentUser = user;
        }
    }

    //START OF ADMIN METHODS

    /**
     * Create a new user with the default password and isAdmin==false & add it to the user ArrayList
     *
     * @param username specified username to add to all users
     * @throws IllegalArgumentException throws an IllegalArgumentException
     *  with a descriptive error message if username is null or if its length is less than 5 ( <
     *  5), or if a user with the same username is already in the list of users (usernames must be
     *  unique)
     * @return Return true if the current user has Admin power and the new user was successfully
     * added. Return false if the current user is null or does not have Admin power
     */
    public boolean addUser(String username){
        if(username == null)
            return false;

        if(currentUser == null || currentUser.getIsAdmin() == false)
            return false;

        boolean nameUsed = false; //finding if username has been used
        for(User i : users){
            if(i.getUsername().equals(username))
                nameUsed = true;
        }

        if(username == null || username.length() < 5 || nameUsed)
            throw new IllegalArgumentException("Username was null, already in use, or too short");

        users.add(new User(username, DEFAULT_PASSWORD, false ));
        return true;
    }

    /**
     * Create a new user, specify their admin status, and add it to the list of users.
     *
     * @param username specified username to add to all users
     * @throws IllegalArgumentException throws an IllegalArgumentException
     *  with a descriptive error message if username is null or if its length is less than 5 ( <
     *  5), or if a user with the same username is already in the list of users (usernames must be
     *  unique)
     * @return Return true if the current user has Admin power and the new user was successfully
     * added. Return false if the current user is null or does not have Admin power
     */
    public boolean addUser(String username, boolean isAdmin){
        if(username == null)
            return false;

        if(currentUser == null || currentUser.getIsAdmin() == false)
            return false;

        boolean nameUsed = false; //finding if username has been used
        for(User i : users){
            if(i.getUsername().equals(username))
                nameUsed = true;
        }
        if(username == null || username.length() < 5 || nameUsed)
            throw new IllegalArgumentException("Username was null, already in use, or too short");

        users.add(new User(username, DEFAULT_PASSWORD, isAdmin ));
        return true;
    }

    /**
     * Remove a user given their unique username
     *
     * @param username specified username to remove
     * @throws NoSuchElementException throws an IllegalArgumentException
     *  with a descriptive error message if username is null or if its length is less than 5 ( <
     *  5), or if a user with the same username is already in the list of users (usernames must be
     *  unique)
     * @return Return true if the current user has Admin power and the new user was successfully
     * added. Return false if the current user is null or does not have Admin power
     */
    public boolean removeUser(String username){
        if(username == null)
            return false;

        if(currentUser == null || currentUser.getIsAdmin() == false)
            return false;

        boolean nameUsed = false; //finding if username has been used
        for(User i : users) {
            if (i.getUsername().equals(username)){
                nameUsed = true;
                i = null;
            }

        }
        if(nameUsed == false)
            throw new NoSuchElementException("No username match found to remove");

        return true;
    }

    /**
     * Give a user admin power
     *
     * @param username specified username to grant admin power
     * @throws NoSuchElementException throws an IllegalArgumentException
     *  with a descriptive error message if username is null or if its length is less than 5 ( <
     *  5), or if a user with the same username is already in the list of users (usernames must be
     *  unique)
     * @return Return true if the current user has Admin power and the new user was successfully
     * added. Return false if the current user is null or does not have Admin power
     */
    public boolean giveAdmin(String username){
        if(username == null)
            return false;

        if(currentUser == null || currentUser.getIsAdmin() == false)
            return false;

        boolean nameUsed = false; //finding if username has been used
        for(User i : users) {
            if (i.getUsername().equals(username)){
                nameUsed = true;
                i.setIsAdmin(true);
            }
        }
        if(nameUsed == false)
            throw new NoSuchElementException("No username match found to give admin");

        return true;
    }

    /**
     * Remove the admin power of a user given their username
     *
     * @param username specified username to remove admin power from
     * @throws NoSuchElementException throws an IllegalArgumentException
     *  with a descriptive error message if username is null or if its length is less than 5 ( <
     *  5), or if a user with the same username is already in the list of users (usernames must be
     *  unique)
     * @return Return true if the current user has Admin power and the new user was successfully
     * added. Return false if the current user is null or does not have Admin power
     */
    public boolean takeAdmin(String username){
        if(username == null)
            return false;

        if(currentUser == null || currentUser.getIsAdmin() == false)
            return false;

        boolean nameUsed = false; //finding if username has been used
        for(User i : users) {
            if (i.getUsername().equals(username)){
                nameUsed = true;
                i.setIsAdmin(false);
            }
        }
        if(nameUsed == false)
            throw new NoSuchElementException("No username match found to take admin");

        return true;
    }

    /**
     * Reset the password of a user given their username (default)
     *
     * @param username specified username to reset to default password
     * @throws NoSuchElementException throws an IllegalArgumentException
     *  with a descriptive error message if username is null or if its length is less than 5 ( <
     *  5), or if a user with the same username is already in the list of users (usernames must be
     *  unique)
     * @return Return true if the current user has Admin power and the new user was successfully
     * added. Return false if the current user is null or does not have Admin power
     */
    public boolean resetPassword(String username){
        if(username == null)
            return false;

        if(currentUser == null || currentUser.getIsAdmin() == false)
            return false;

        boolean nameUsed = false; //finding if username has been used
        for(User i : users) {
            if (i.getUsername().equals(username)){
                nameUsed = true;
                i.setPassword(DEFAULT_PASSWORD);
            }
        }
        if(nameUsed == false)
            throw new NoSuchElementException("No username match found to reset password");

        return true;
    }
}
