import processing.core.PImage;
import java.io.File;
import java.util.Random;

//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Fountain Assignment
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
 *  This class models a fountain graphically shooting out water indefinitely
 */
public class Fountain {
    public static void main(String [] args){
        Utility.runApplication();
    }

    private static Random randGen;
    private static PImage fountainImage;
    private static int positionX, positionY;
    private static Droplet[] droplets;
    private static int startColor;
    private static int endColor;

    /**
     * Initializes all the static variables after declaration and runs the test methods
     */
    public static void setup(){
        testUpdateDroplet();
        testRemoveOldDroplets();

        randGen = new Random();
        positionX = Utility.width()/2;
        positionY = Utility.height()/2;
        startColor = Utility.color(23,141,235);
        endColor = Utility.color(23,200,255);

        // load the image of the fountain
        fountainImage = Utility.loadImage("images" + File.separator + "fountain.png");

        droplets = new Droplet[800];
    }

    /**
     * The program repeatedly calls upon the code in this method body to display images rapidly
     */
    public static void draw(){
        Utility.background(Utility.color(253,245,230));
        Utility.fill(Utility.color(23,141,235));

        Utility.image(fountainImage, positionX, positionY);

        createNewDroplets(10);

        for (int i = 0; i < droplets.length; i++){
            if(droplets[i] != null){
                updateDroplet(i);
            }
        }

        removeOldDroplets(80);
    }

    /**
     * Updates the droplet object's velocity, position, and age
     *
     * @param index Index of the droplet being updated in the droplets array
     */
    private static void updateDroplet(int index){
        float positionX = droplets[index].getPositionX();
        float positionY = droplets[index].getPositionY();
        float xVelocity = droplets[index].getVelocityX();
        float yVelocity = droplets[index].getVelocityY();
        int age = droplets[index]. getAge();

        //arbitrary transparency and circle size, just not overbearing
        Utility.fill(Utility.color(23, 141, 235), 100);
        Utility.circle(positionX, positionY, 5.0f);

        droplets[index].setVelocityY(yVelocity + 0.3f);
        droplets[index].setPositionX(positionX + xVelocity);
        droplets[index].setPositionY(positionY + yVelocity);
        droplets[index].setAge(age + 1);
    }

    /**
     *  Creates a droplet with randomized attributes
     *
     * @param dropletsToCreate The number of randomized droplets being created
     */
    private static void createNewDroplets(int dropletsToCreate){
        int dropletsCreated, indexCounter, age, transparency, lerp; //inclusive
        float offsetX, offsetY, velocityX, velocityY, randSize, randPosX, randPosY; //exclusive
        dropletsCreated = 0;
        indexCounter = 0;

        while(dropletsCreated < dropletsToCreate){
            //randomization in loop for each individual droplet
            offsetX = randGen.nextFloat() * 6 - 3; //range b/w [-3, 3)
            offsetY = randGen.nextFloat() * 6 - 3;
            randSize = randGen.nextFloat() * 7 + 4; //range b/w [4, 11)
            age = randGen.nextInt(41); //range b/w [0,40]
            transparency = randGen.nextInt(96) + 32; //range b/w [32, 127]
            velocityX = randGen.nextFloat() * 2 - 1; //range b/w [-1,1)
            velocityY = randGen.nextFloat() * 5 - 10; //range b/w [-5, -10)
            lerp = Utility.lerpColor(startColor, endColor, randGen.nextFloat());

            randPosX = positionX + offsetX;
            randPosY = positionY + offsetY;

            //giving droplet properties
            if(droplets[indexCounter] == null){
                droplets[indexCounter] = new Droplet(randPosX,randPosY, randSize, lerp);
                droplets[indexCounter].setVelocityX(velocityX);
                droplets[indexCounter].setVelocityY(velocityY);
                droplets[indexCounter].setTransparency(transparency);
                droplets[indexCounter].setAge(age);
                dropletsCreated++;
            }
            indexCounter++;
        }
    }

    /**
     * Removes droplet objects from the droplets array based on droplet age
     *
     * @param maxAge the upper age limit of droplets before being removed
     */
    private static void removeOldDroplets(int maxAge){

        for(int i = 0; i < droplets.length; i++){
            // >= if droplets are somehow initialized greater than maxAge
            if(droplets[i] != null && droplets[i].getAge() >= maxAge){
                droplets[i] = null;
            }
        }
    }

    /**
     * Uses the mouse's location when pressed to update the location of graphics displayed
     */
    public static void mousePressed(){
        positionX = Utility.mouseX();
        positionY = Utility.mouseY();
    }

    /**
     * Takes a screenshot of the display if a specified key is pressed on the keyboard
     * @param key the input of the pressed key
     */
    public static void keyPressed(char key){
        //System.out.println(key + " is being pressed");
        if(key == 's' || key == 'S') {
            Utility.save("screenshot.png");
        }
    }

    /**
     * This tester initializes the droplets array to hold at least three droplets.
     * Creates a single droplet at position (3,3) with velocity (-1,-2). Then checks
     * whether calling updateDroplet() on this droplet’s index correctly results in
     * changing its position to (2.0, 1.3).
     *
     * @return true when no defect is found, and false otherwise
     */
    private static boolean testUpdateDroplet() {
        //tested droplet setup
        float expectedPositionX = 2.0f;
        float expectedPositionY = 1.3f;
        droplets = new Droplet[3];
        Droplet testDroplet = new Droplet(3.0f, 3.0f, 5.0f, startColor);
        testDroplet.setVelocityX(-1.0f);
        testDroplet.setVelocityY(-2.0f);

        droplets[0] = testDroplet;
        updateDroplet(0);

        if(droplets[0].getPositionX() != expectedPositionX || droplets[0].getPositionY() != expectedPositionY){
            System.out.println("There was a problem with the updateDroplet() Method."
                    + " It did not return expected velocity values after being called");
            return false;
        }

        return true;
    }

    /**
     * This tester initializes the droplets array to hold at least three droplets.
     * Calls removeOldDroplets(6) on an array with three droplets (two of which have
     * ages over six and another that does not). Then checks whether the old droplets
     * were removed and the young droplet was left alone.
     *
     * @return true when no defect is found, and false otherwise
     */
    private static boolean testRemoveOldDroplets() {
        int removedDroplets = 0;
        int expectedRemovedDroplets = 2;
        droplets = new Droplet[3];

        droplets[0] = new Droplet();
        droplets[0].setAge(6);

        droplets[1] = new Droplet();
        droplets[1].setAge(3);

        droplets[2] = new Droplet();
        droplets[2].setAge(6);

        removeOldDroplets(6);

        for(int i = 0; i < droplets.length; i++){
            if(droplets[i] == null){
                removedDroplets++;
            }
        }

        if(removedDroplets != expectedRemovedDroplets){
            System.out.println("There was an error in removeOldDroplets(). It removed "
                    + removedDroplets + " droplets instead of " + expectedRemovedDroplets);
            return false;
        }

        return true;
    }
}

