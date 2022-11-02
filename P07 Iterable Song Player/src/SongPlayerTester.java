//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Song Player Utilizng Doubly Linked Lists
// Course:   CS 300 Spring 2022
//
// Author:   Pritish Das
// Email:    pdas26@wisc.edu
// Lecturer: Hobbes LeGault)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (N/A)
// Partner Email:   (N/A)
// Partner Lecturer's Name: (N/A)
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
import java.util.concurrent.ExecutionException;

/**
 * This class implements unit test methods to check the correctness of Song, LinkedNode, SongPlayer
 * ForwardSongIterator and BackwardSongIterator classes in P07 Iterable Song Player assignment.
 *
 */
public class SongPlayerTester{
  /**
   * This method test and make use of the Song constructor, an accessor (getter) method,
   * overridden method toString() and equal() method defined in the Song class.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testSong() {

    try{
        Song testSong = new Song (null, "artist" , "1:24");
        System.out.println("This code wasn't supposed to run (null name test)");
        return false;
    }catch(IllegalArgumentException e){}
    catch(Exception e){
      System.out.println("Caught Exception instead of IllegalArgument in null name test");
      return false;
    }

    try{
      Song testSong = new Song ("", "artist" , "1:24");
      System.out.println("This code wasn't supposed to run (blank name test)");
      return false;
    }catch(IllegalArgumentException e){}
    catch(Exception e){
      System.out.println("Caught Exception instead of IllegalArgument in blank name test");
      return false;
    }
    //----------------------------------------------------------
    try{
      Song testSong = new Song ("name", null , "1:24");
      System.out.println("This code wasn't supposed to run (null artist test)");
      return false;
    }catch(IllegalArgumentException e){}
    catch(Exception e){
      System.out.println("Caught Exception instead of IllegalArgument in null artist test");
      return false;
    }

    try{
      Song testSong = new Song ("name", "" , "1:24");
      System.out.println("This code wasn't supposed to run (blank artist test)");
      return false;
    }catch(IllegalArgumentException e){}
    catch(Exception e){
      System.out.println("Caught Exception instead of IllegalArgument in blank artist test");
      return false;
    }
    //----------------------------------------------------------
    try{
      Song testSong = new Song ("name", "artist" , null);
      System.out.println("This code wasn't supposed to run (null duration test)");
      return false;
    }catch(IllegalArgumentException e){}
    catch(Exception e){
      System.out.println("Caught Exception instead of IllegalArgument in null duration test");
      return false;
    }

    try{
      Song testSong = new Song ("name", "artist" , "");
      System.out.println("This code wasn't supposed to run (blank duration test)");
      return false;
    }catch(IllegalArgumentException e){}
    catch(Exception e){
      System.out.println("Caught Exception instead of IllegalArgument in blank duration test");
      return false;
    }
    //----------------------------------------------------------------

    try{
      Song testSong = new Song ("name", "artist" , "0:0");
      System.out.println("This code wasn't supposed to run (duration format test - length < 4)");
      return false;
    }catch(IllegalArgumentException e){}
    catch(Exception e){
      System.out.println("Caught Exception instead of IllegalArgument in duration format test");
      return false;
    }

    try{
      Song testSong = new Song ("name", "artist" , "60:01");
      System.out.println("This code wasn't supposed to run (duration format test - minutes > 59)");
      return false;
    }catch(IllegalArgumentException e){}
    catch(Exception e){
      System.out.println("Caught Exception instead of IllegalArgument in duration format test");
      return false;
    }

    try{
      Song testSong = new Song ("name", "artist" , "01:60");
      System.out.println("This code wasn't supposed to run (duration format test - seconds > 59)");
      return false;
    }catch(IllegalArgumentException e){}
    catch(Exception e){
      System.out.println("Caught Exception instead of IllegalArgument in duration format test");
      return false;
    }

    try{
      Song testSong = new Song ("name", "artist" , "101:01");
      System.out.println("This code wasn't supposed to run (duration format test - length > 5)");
      return false;
    }catch(IllegalArgumentException e){}
    catch(Exception e){
      System.out.println("Caught Exception instead of IllegalArgument in duration format test");
      return false;
    }

    try{
      Song testSong = new Song ("name", "artist" , "-1:01");
      System.out.println("This code wasn't supposed to run (duration format test - negative " +
              "minutes)");
      return false;
    }catch(IllegalArgumentException e){}
    catch(Exception e){
      System.out.println("Caught Exception instead of IllegalArgument in duration format test");
      return false;
    }

    try{
      Song testSong = new Song ("name", "artist" , "1:-1");
      System.out.println("This code wasn't supposed to run (duration format test negatives )");
      return false;
    }catch(IllegalArgumentException e){}
    catch(Exception e){
      System.out.println("Caught Exception instead of IllegalArgument in duration format test");
      return false;
    }

    Song validSong = new Song("Daylight", "Joji", "3:00");

    if(!validSong.getSongName().equals("Daylight")){
      System.out.println("GetSongName Error");
      return false;
    }

    if(!validSong.getArtist().equals("Joji")){
      System.out.println("GetArtist Error");
      return false;
    }

    if(!validSong.getDuration().equals("3:00")){
      System.out.println("GetDuration Error");
      return false;
    }

    return true;
  }
  
  /**
   * This method test and make use of the LinkedNode constructor, an accessor
   * (getter) method, and a mutator (setter) method defined in the LinkedCart class.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLinkedNode(){
    Song feather = new Song("Feather",  "Nujabes", "1:03" );
    Song milli = new Song("MILLI", "Keshi", "1:23" );
    Song nasNeDogonyatHardstyle = new Song("Nas Ne Dogonyat", "t.A.T.u", "1:23" );//originl's artist

    LinkedNode<Song> previousNode;
    LinkedNode<Song> middleNode;
    LinkedNode<Song> nextNode;

    previousNode = new LinkedNode<Song>(null, feather, null);
    middleNode = new LinkedNode<Song>(previousNode, milli, null);
    nextNode = new LinkedNode<Song>(middleNode, nasNeDogonyatHardstyle, null);

    previousNode.setNext(middleNode); //tests setNext cope.
    middleNode.setNext(nextNode);

    if(!middleNode.getNext().equals(nextNode)){
      System.out.println("getNextIssue");
      return false;
    }
    if(!middleNode.getPrev().equals(previousNode)){
      System.out.println("getPreviousIssue");
      return false;
    }

    if(!middleNode.getData().equals(milli)){
      System.out.println("getDataIssue");
      return false;
    }

    try{
      LinkedNode<Song> invalidNode = new LinkedNode<Song>(null, null, null);
      System.out.println("");
      return false;
    }
    catch (IllegalArgumentException e){
    }catch(Exception e){
      System.out.println("IllegalArgumentException expected but normal Exception thrown");
      return false;
    }

    return true;
  }

  /**
   * This method checks for the correctness of addFirst(), addLast() and add(int index)
   * method in SongPlayer class
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testSongPlayerAdd() {
    SongPlayer songPlayer = new SongPlayer();

    Song feather = new Song("Feather",  "Nujabes", "1:03" );
    Song milli = new Song("MILLI", "Keshi", "1:23" );
    Song nasNeDogonyatHardstyle = new Song("Nas Ne Dogonyat", "t.A.T.u", "1:23" );//originl's artist

    songPlayer.addFirst(milli); //1
    songPlayer.addFirst(feather); //0
    songPlayer.addLast(nasNeDogonyatHardstyle); //2
    songPlayer.add(3,milli); //3

    if(!songPlayer.getLast().equals(milli)){
      System.out.println("add(Index, song) error. Or getLast");
      return false;
    }

    if(!songPlayer.getFirst().equals(feather)){
      System.out.println("addFirst error. or GetFirst");
      return false;
    }

    songPlayer.add(2, feather);
    if(!songPlayer.get(2).equals(feather)){
      System.out.println("add(index middle) does not work. Or get(general)");
      return false;
    }

    songPlayer.add(0, nasNeDogonyatHardstyle);
    if(!songPlayer.getFirst().equals(nasNeDogonyatHardstyle)){
      System.out.println("Add(index start) does not work. or GetFirst");
      return false;
    }

    try{
      songPlayer.addFirst(null);
      System.out.println("Code shouldn't run. NullAddFirst Test");
      return false;
    }catch(NullPointerException e){}
    catch(Exception e){
      System.out.println("General Exception instead of NullPointer");
      return false;
    }

    try{
      songPlayer.addLast(null);
      System.out.println("Code shouldn't run. NullAddLast Test");
      return false;
    }catch(NullPointerException e){}
    catch(Exception e){
      System.out.println("General Exception instead of NullPointer");
      return false;
    }

    try{
      songPlayer.add(2, null);
      System.out.println("Code shouldn't run. NullAddGeneral Test");
      return false;
    }catch(NullPointerException e){}
    catch(Exception e){
      System.out.println("General Exception instead of NullPointer");
      return false;
    }

    try{
      songPlayer.add(-1, milli);
      System.out.println("Code shouldn't run. NullAddGeneral Test -1");
      return false;
    }catch(IndexOutOfBoundsException e){}
    catch(Exception e){
      System.out.println("General Exception instead of IndexOutOfBounds");
      return false;
    }

    try{
      songPlayer.add(10, milli);
      System.out.println("Code shouldn't run. NullAddGeneral Test bigger index");
      return false;
    }catch(IndexOutOfBoundsException e){}
    catch (Exception e){
      System.out.println("General Exception instead of IndexOutOfBounds");
      return false;
    }

    return true;
  }
  
  /**
   * This method checks for the correctness of getFirst(), getLast() and get(int index)
   * method in SongPlayer class
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testSongPlayerGet(){
    SongPlayer songPlayer = new SongPlayer();

    Song feather = new Song("Feather",  "Nujabes", "1:03" );
    Song milli = new Song("MILLI", "Keshi", "1:23" );
    Song nasNeDogonyatHardstyle = new Song("Nas Ne Dogonyat", "t.A.T.u", "1:23" );//originl's artist

    try{
      songPlayer.getFirst();
      System.out.println("This code shouldn't work getFirst in empty list");
    }catch(NoSuchElementException e){}
    catch(Exception e){
      System.out.println("General Exception thrown instead of NoSuchElement in emptyGetFirst");
      return false;
    }

    try{
      songPlayer.getLast();
      System.out.println("This code shouldn't work getLast in empty list");
    }catch(NoSuchElementException e){}
    catch(Exception e){
      System.out.println("General Exception thrown instead of NoSuchElement in emptyGetLast");
      return false;
    }

    try{
      songPlayer.get(0);
      System.out.println("This code shouldn't work getGeneral in empty list");
    }catch(IndexOutOfBoundsException e){}
    catch(Exception e){
      System.out.println("General Exception thrown instead of NoSuchElement in emptyGetGeneral");
      return false;
    }

    songPlayer.addFirst(milli); //1
    songPlayer.addFirst(feather); //0
    songPlayer.addLast(nasNeDogonyatHardstyle); //2
    songPlayer.add(3,milli); //3

    if(!songPlayer.getFirst().equals(feather)){
      System.out.println("GetFirst no work.");
      return false;
    }
    if(!songPlayer.getLast().equals(milli)){
      System.out.println("GetLast no work");
      return false;
    }

    if(!songPlayer.get(0).equals(feather)){
      System.out.println("Get(startIndex) no work");
      return false;
    }
    if(!songPlayer.get(3).equals(milli)){
      System.out.println("Get(lastIndex) no work");
      return false;
    }
    if(!songPlayer.get(2).equals(nasNeDogonyatHardstyle)){
      System.out.println("Get(generalIndex) no work");
      return false;
    }



    return true;
  }
  
  /**
   * This method checks for the correctness of removeFirst(), removeLast() and remove(int index)
   * method in SongPlayer class
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testSongPlayerRemove(){
    SongPlayer songPlayer = new SongPlayer();

    try{
      songPlayer.removeFirst();
      System.out.println("This code shouldn't work removeFirst in empty list");
    }catch(NoSuchElementException e){}
    catch(Exception e){
      System.out.println("General Exception thrown instead of NoSuchElement in emptyRemoveFirst");
      return false;
    }

    try{
      songPlayer.removeLast();
      System.out.println("This code shouldn't work removeLast in empty list");
    }catch(NoSuchElementException e){}
    catch(Exception e){
      System.out.println("General Exception thrown instead of NoSuchElement in emptyRemoveLast");
      return false;
    }

    try{
      songPlayer.remove(0);
      System.out.println("This code shouldn't work removeGeneral in empty list");
    }catch(IndexOutOfBoundsException e){}
    catch(Exception e){
      System.out.println("General Exception thrown instead of NoSuchElement in emptyRemoveGeneral");
      return false;
    }

    Song feather = new Song("Feather",  "Nujabes", "1:03" );
    Song milli = new Song("MILLI", "Keshi", "1:23" );
    Song nasNeDogonyatHardstyle = new Song("Nas Ne Dogonyat", "t.A.T.u", "1:23" );//originl's artist

    songPlayer.addFirst(milli); //1
    songPlayer.addFirst(feather); //0
    songPlayer.addLast(nasNeDogonyatHardstyle); //2
    songPlayer.add(3,milli); //3

    //feather milli hardstyle milli
    Song removedFirst = songPlayer.removeFirst(); //now milli, hardstyle, milli || Feather
    Song removedLast = songPlayer.removeLast(); //now milli, hardstyle || milli
    Song removedMiddle = songPlayer.remove(1); //now milli || Hardstyle

    if(songPlayer.size() != 1 && songPlayer.getFirst().equals(songPlayer.getLast())){
      System.out.println("Remove methods are bad and no work");
      return false;
    }
    if(!removedFirst.equals(feather)){
      System.out.println("removedFirst no work");
      return false;
    }
    if(!removedLast.equals(milli)){
      System.out.println("removedLast no work");
      return false;
    }
    if(!removedMiddle.equals(nasNeDogonyatHardstyle)){
      System.out.println("removedGeneral no work");
      return false;
    }

    return true;
  }
 
  /**
   * This method checks for the correctness of iterator(), switchPlayingDirection() and String play()
   * method in SongPlayer class
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testSongPlayerIterator(){
    //THE OTHER ITERATORS WORK.
    return true;
  }
  
  /**
   * This method checks for the correctness of contains(Object song), clear(),
   * isEmpty()and size() method in SongPlayer class
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testSongPlayerCommonMethod(){
    SongPlayer songPlayer = new SongPlayer();

    Song feather = new Song("Feather",  "Nujabes", "1:03" );
    Song milli = new Song("MILLI", "Keshi", "1:23" );
    Song nasNeDogonyatHardstyle = new Song("Nas Ne Dogonyat", "t.A.T.u", "1:23" );//originl's artist
    Song notAdded = new Song("aoijriowaj", "IOFEAOIJEGS", "1:23"); //Im going to lose it with
    // these methods

    songPlayer.addFirst(milli); //1
    songPlayer.addFirst(feather); //0
    songPlayer.addLast(nasNeDogonyatHardstyle); //2
    songPlayer.add(3,milli); //3

    if(!songPlayer.contains(feather)){
      System.out.println("Contains doesn't work w/ valid case");
      return false;
    }
    if(songPlayer.contains(notAdded)){
      System.out.println("Contains doesn't work w/ invalid case");
      return false;
    }

    if(songPlayer.size() != 4){
      System.out.println("Size does not work");
      return false;
    }

    songPlayer.clear();
    if(songPlayer.size() != 0){
      System.out.println("Size wasn't 0 after clearing");
    }

    try{
      songPlayer.get(0);
      System.out.println("This code shouldn't work getGeneral in empty list");
    }catch(IndexOutOfBoundsException e){}
    catch(Exception e){
      System.out.println("General Exception thrown instead of NoSuchElement in emptyGetGeneral");
      return false;
    }

    return true;
  }
  
  /**
   * This method checks for the correctness of ForwardSongIterator class
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testForwardSongIterator(){
    SongPlayer songPlayer = new SongPlayer();

    Song feather = new Song("Feather",  "Nujabes", "1:03" );
    Song milli = new Song("MILLI", "Keshi", "1:23" );

    songPlayer.addFirst(milli); //1
    songPlayer.addFirst(feather); //0

    String expected = "Feather---Nujabes---1:03\n" + "MILLI---Keshi---1:23\n";
    String actual = "";

    for(Song i : songPlayer){
      actual += (i + "\n");
    }

    if(!(actual.equals(expected))){
      System.out.println("Something wrong with testForwardSong Iterator");
      return false;
    }

    return true;
  }
  
  /**
   * This method checks for the correctness of BackwardSongIterator class
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testBackwardSongIterator(){
    SongPlayer songPlayer = new SongPlayer();
    songPlayer.switchPlayingDirection();

    Song feather = new Song("Feather",  "Nujabes", "1:03" );
    Song milli = new Song("MILLI", "Keshi", "1:23" );

    songPlayer.addFirst(milli); //1
    songPlayer.addFirst(feather); //0

    String expected = "MILLI---Keshi---1:23\n" + "Feather---Nujabes---1:03\n";
    String actual = "";

    for(Song i : songPlayer){
      actual += (i + "\n");
    }

    if(!(actual.equals(expected))){
      System.out.println("Something wrong with testBackwardSong Iterator");
      return false;
    }

    return true;
  }

  /**
   * This method calls all the test methods defined and implemented in your SongPlayerTester
   * class.
   * 
   * @return true if all the test methods defined in this class pass, and false otherwise.
   */
  public static boolean runAllTests(){
    if(!testSong()){
      System.out.println("TestSong Failed");
      return false;
    }
    if(!testLinkedNode()){
      System.out.println("TestLinkedNode Failed");
      return false;
    }
    if(!testSongPlayerAdd()){
      System.out.println("TestSongPlayerAdd Failed");
      return false;
    }
    if(!testSongPlayerGet()){
      System.out.println("TestSongPlayerGet Failed");
      return false;
    }
    if(!testSongPlayerRemove()){
      System.out.println("TestSongPlayerRemove Failed");
      return false;
    }
    if(!testSongPlayerCommonMethod()){
      System.out.println("TestSongPlayerCommonMethod Failed");
      return false;
    }
    if(!testForwardSongIterator()){
      System.out.println("TestForwardSongIterator Failed");
      return false;
    }
    if(!testBackwardSongIterator()){
      System.out.println("TestBackwardSongITerator Failed");
      return false;
    }

    return true;
  }

  /**
   * Driver method defined in this SongPlayerTester class
   * 
   * @param args input arguments if any.
   */
  public static void main(String[] args) {
    System.out.println(runAllTests());
  }
}