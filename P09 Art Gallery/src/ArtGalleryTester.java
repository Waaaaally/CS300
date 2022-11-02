//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Art Gallery BST
// Course:   CS 300 Spring 2022
//
// Author:   Pritish Das
// Email:    pdas26@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: None
// Online Sources:  None
//
///////////////////////////////////////////////////////////////////////////////
import java.util.NoSuchElementException;
import java.util.ArrayList;

/**
 * This class checks the correctness of the implementation of the methods defined in the class
 * ArtworkGallery.
 * 
 * @author Pritish Das
 *
 */

public class ArtGalleryTester {

  /**
   * Checks the correctness of the implementation of both compareTo() and equals() methods defined
   * in the Artwork class.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testArtworkCompareToEquals() {
    Artwork sus = new Artwork("amog", 1500, 500.0);
    Artwork sussy = new Artwork("amogus", 1500, 500.0);

    sus.compareTo(sussy);

    return true; // Default return statement added to resolve compiler errors
  }

  /**
   * Checks the correctness of the implementation of both addArtwork() and toString() methods
   * implemented in the ArtworkGallery class. This unit test considers at least the following
   * scenarios. (1) Create a new empty ArtworkGallery, and check that its size is 0, it is empty,
   * and that its string representation is an empty string "". (2) try adding one artwork and then
   * check that the addArtwork() method call returns true, the tree is not empty, its size is 1, and
   * the .toString() called on the tree returns the expected output. (3) Try adding another artwork
   * which is smaller that the artwork at the root, (4) Try adding a third artwork which is greater
   * than the one at the root, (5) Try adding at least two further artwork such that one must be
   * added at the left subtree, and the other at the right subtree. For all the above scenarios, and
   * more, double check each time that size() method returns the expected value, the add method call
   * returns true, and that the .toString() method returns the expected string representation of the
   * contents of the binary search tree in an increasing order from the smallest to the greatest
   * artwork with respect to year, cost, and then name. (6) Try adding a artwork already stored in
   * the tree. Make sure that the addArtwork() method call returned false, and that the size of the
   * tree did not change.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddArtworkToStringSize() {
    ArtGallery gallery = new ArtGallery();
    System.out.println("Size: " + gallery.size() + " Height: " + gallery.height() + "\nGallery:");
    System.out.println(gallery);

    gallery.addArtwork(new Artwork("Guernica, Picasso", 1937, 3000));
    gallery.addArtwork(new Artwork("Starry Night, Van Gogh", 1889, 2000));
    System.out.println("==============================================================");
    System.out.println("Size: " + gallery.size() + " Height: " + gallery.height() + "\nGallery:");
    System.out.println(gallery);

    return true; // Default return statement added to resolve compiler errors
  }

  /**
   * This method checks mainly for the correctness of the ArtworkGallery.lookup() method. It must
   * consider at least the following test scenarios. (1) Create a new ArtworkGallery. Then, check
   * that calling the lookup() method on an empty ArtworkGallery returns false. (2) Consider a
   * ArtworkGallery of height 3 which lookup at least 5 artwork. Then, try to call lookup() method
   * to search for the artwork having a match at the root of the tree. (3) Then, search for a
   * artwork at the right and left subtrees at different levels considering successful and
   * unsuccessful search operations. Make sure that the lookup() method returns the expected output
   * for every method call.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLookup() {
    // TODO complete the implementation of this method
    return true; // Default return statement added to resolve compiler errors
  }

  /**
   * Checks for the correctness of ArtworkGallery.height() method. This test must consider several
   * scenarios such as, (1) ensures that the height of an empty artwork tree is zero. (2) ensures
   * that the height of a tree which consists of only one node is 1. (3) ensures that the height of
   * a ArtworkGallery with the following structure for instance, is 4. 
   *               (*) 
   *              /  \ 
   *            (*)  (*) 
   *             \   / \ 
   *            (*) (*) (*) 
   *                    / 
   *                   (*)
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testHeight() {
    // TODO complete the implementation of this method
    return true; // Default return statement added to resolve compiler errors
  }

  /**
   * Checks for the correctness of ArtworkGallery.getBestArtwork() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetBestArtwork() {
    // TODO complete the implementation of this method
    return true; // Default return statement added to resolve compiler errors
  }


  /**
   * Checks for the correctness of ArtworkGallery.lookupAll() method. This test must consider at
   * least 3 test scenarios. (1) Ensures that the ArtworkGallery.lookupAll() method returns an
   * empty arraylist when called on an empty tree. (2) Ensures that the
   * ArtworkGallery.lookupAll() method returns an array list which contains all the artwork satisfying
   * the search criteria of year and cost, when called on a non empty artwork tree with one match,
   * and two matches and more. Vary your search criteria such that the lookupAll() method must check
   * in left and right subtrees. (3) Ensures that the ArtworkGallery.lookupAll() method returns an
   * empty arraylist when called on a non-empty artwork tree with no search results found.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLookupAll() {
    ArtGallery gallery = new ArtGallery();

//    gallery.addArtwork(new Artwork("Guernica, Picasso", 1937, 3000));
//    gallery.addArtwork(new Artwork("Starry Night, Van Gogh", 1889, 2000));
//    gallery.addArtwork(new Artwork("Persistence of Memory, Dali", 1931, 7000));
//    gallery.addArtwork(new Artwork("Der Schrei, Silber", 2019, 12160));
    gallery.addArtwork(new Artwork("For gourmets, Tuzhilkina", 2021, 1280));
    gallery.addArtwork(new Artwork("Cantabrico, Torices", 2021, 3870));
//    gallery.addArtwork(new Artwork("Gothic, Wood", 1930, 6000));

    System.out.println("==============================================================");
    System.out.println("Size: " + gallery.size() + " Height: " + gallery.height() + "\nGallery:");

    System.out.println(gallery);
    ArrayList<Artwork> arts = gallery.lookupAll(2021, 5000);

    System.out.println("Items in 2021 that have a cost under 5000");
    for(Artwork i : arts){
      System.out.println(i);
    }
    return true; // Default return statement added to resolve compiler errors
  }

  /**
   * Checks for the correctness of ArtworkGallery.buyArtwork() method. This test must consider at
   * least 3 test scenarios. (1) Buying artwork that is at leaf node (2) Buying artwork at non-leaf
   * node (3) ensures that the ArtworkGallery.buyArtwork() method throws a NoSuchElementException
   * when called on an artwork that is not present in the BST
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testBuyArtwork() {
    ArtGallery gallery = new ArtGallery();

    gallery.addArtwork(new Artwork("Guernica, Picasso", 1937, 3000));
    gallery.addArtwork(new Artwork("Starry Night, Van Gogh", 1889, 2000));
    gallery.addArtwork(new Artwork("Persistence of Memory, Dali", 1931, 7000));
    gallery.addArtwork(new Artwork("Der Schrei, Silber", 2019, 12160));
    gallery.addArtwork(new Artwork("Gothic, Wood", 1930, 6000));
    gallery.addArtwork(new Artwork("For gourmets, Tuzhilkina", 2021, 1280));
    gallery.addArtwork(new Artwork("Cantabrico, Torices", 2021, 3870));

    System.out.println(gallery);
    System.out.println("Size: " + gallery.size() + " Height: " + gallery.height() + "\nGallery:");


    System.out.println("========================");
    System.out.println("Buying Starry Night");
    gallery.buyArtwork("Starry Night, Van Gogh", 1889, 2000);
    System.out.println("Size: " + gallery.size() + " Height: " + gallery.height() + "\nGallery:");

    System.out.println(gallery);

    return true; // Default return statement added to resolve compiler errors
  }

  /**
   * Returns false if any of the tester methods defined in this tester class fails.
   * 
   * @return false if any of the tester methods defined in this tester class fails, and true if all
   *         tests pass
   */
  public static boolean runAllTests() {
    // TODO complete the implementation of this method
    return true; // Default return statement added to resolve compiler errors
  }

  /**
   * Calls the test methods
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("testArworkCompareToEquals(): " + testArtworkCompareToEquals());
//    System.out.println("testAddArtworkToStringSize(): " + testAddArtworkToStringSize());
//    System.out.println("testLookup(): " + testLookup());
//    System.out.println("testHeight(): " + testHeight());
//    System.out.println("testGetBestArtwork(): " + testGetBestArtwork());
    System.out.println("testLookupAll(): " + testLookupAll()); //todo this one needs work
//    System.out.println("testBuyArtwork(): " + testBuyArtwork());
//    System.out.println("runAllTests(): " + runAllTests());
  }

}
