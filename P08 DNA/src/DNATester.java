//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    DNA with Linked Queues
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

import java.util.NoSuchElementException;
/**
 * Test methods to verify your implementation of the methods for P08.
 */
public class DNATester {

  /**
   * Tests the enequeue and deqeuue methods of the LinkedQueue class
   * Returns true if all tests are passed
   */
  public static boolean testEnqueueDequeue() {
    LinkedQueue<String> queue = new LinkedQueue<String>();
    try {
      queue.enqueue("A");
      queue.enqueue("B");
      queue.enqueue("C");
      String actual = queue.toString().replaceAll(" ", "");

      if (!actual.equals("ABC")) {
        System.out.println("Something wrong with enqueue method w/ valid input");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Exception thrown on valid input in enqueue test");
      return false;
    }

    try {
      String removed = queue.dequeue();

      if (!queue.toString().trim().equals("B C") && !removed.equals("A")) {
        System.out.println("Something wrong with dequeue method w/ valid queue. Either value " +
                "returned or the resulting queue post dequeue");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Exception thrown on a valid input in dequeue test");
    }
    LinkedQueue<String> emptyQueue = new LinkedQueue<String>();
    try {

      emptyQueue.dequeue();
      System.out.println("EmptyQueue.dequeue threw no exceptions when one was expected");
      return false;
    } catch (NoSuchElementException e) {
    } catch (Exception e) {
      System.out.println("Caught an exception other than NoSuchElement when dequeing an empty " +
              "queue");
      return false;
    }

    LinkedQueue<String> nullQueue = null;
    try {
      nullQueue.enqueue("A");
      System.out.println("Exception wasn't throw in nullQueue.dequeue when expected");
      return false;
    } catch (NullPointerException e) {
    } catch (Exception e) {
      System.out.println("Exception other than NPE was thrown in nullQueue.queue");
      return false;
    }

    return true;
  }

  /**
   * Tests the QueueSize component of The LinkedQueue class and isEmpty.
   * @return true if all the tests pass
   */
  public static boolean testQueueSize(){
    try{
    DNA emptyDNA = new DNA("");
    LinkedQueue<Character> emptyQueue = new LinkedQueue<>();

    if(emptyQueue.size() != 0){
      System.out.println("The size of an assumed empty queue was " + emptyQueue.size() + "instead" +
              " of 0");
      return false;
    }
    if(!emptyQueue.isEmpty()){
      System.out.println("is empty test failed on empty queue");
      return false;
    }
    }catch(Exception e){
      System.out.println("Exception caught on valid input in testQueueSize emptyQueue");
      return false;
    }

    try{
    DNA smallQueue = new DNA("");
    LinkedQueue<Character> smallSequence = new LinkedQueue<>();
    smallSequence.enqueue('A');
    smallSequence.enqueue('B');
    smallSequence.enqueue('C');

    if(smallSequence.size() != 3){
      System.out.println("The size of an assumed 3 queue was " + smallSequence.size() + "instead " +
              "of 3");
      return false;
    }
    if(smallSequence.isEmpty()){
      System.out.println("IsEmpty Test failed on smallSequenece queue");
      return false;
    }
    }catch (Exception e){
      System.out.println("Exception returned on a valid input in smallQueueSizeTest");
      return false;
    }

    try{
      DNA smallQueue = new DNA("");
      LinkedQueue<Character> changingSequence = new LinkedQueue<>();
      changingSequence.enqueue('A');
      changingSequence.dequeue();

      if(!changingSequence.isEmpty()){
        System.out.println(changingSequence.n);
        System.out.println("not expected found in isEmptyTest queue->dequeue");
        return false;
      }
    }catch (Exception e){
      System.out.println("Excpetion thrown when not expected in isEmptyTest queue->dequeue");
      return false;
    }
    return true;
  }

  /**
   * Tests the transcribeDNA() method
   * @return true if and only if the method works correctly
   */
  public static boolean testTranscribeDNA() {
    DNA testDNA = new DNA("GGAGTCAGTTAAGCGACCGGGACATACTGTCTTGGTAATCTCCGAGCTAGAAAGTCTCTG");
    String mRNA = "CCUCAGUCAAUUCGCUGGCCCUGUAUGACAGAACCAUUAGAGGCUCGAUCUUUCAGAGAC";
    //System.out.println(testDNA.transcribeDNA().toString());
    if (testDNA.transcribeDNA().toString().replaceAll(" ", "").equals(mRNA)) {
      return true;
    }
    return false;
  }
  
  /**
   * Tests the translateDNA() method
   * @return true if and only if the method works correctly
   */
  public static boolean testTranslateDNA() {
    DNA testDNA = new DNA("GGAGTCAGTTAAGCGACCGGGACATACTGTCTTGGTAATCTCCGAGCTAGAAAGTCTCTG");
    //System.out.println(testDNA.translateDNA().toString());
    if (!testDNA.translateDNA().toString().replaceAll(" ", "").equals("PQSIRWPCMTEPLEARSFRD")) {
      return false;
    }

    testDNA = new DNA("CCGGCCCTCCGGTGGATCCAA");
    LinkedQueue<Character> mRNAQueue = testDNA.transcribeDNA();
    LinkedQueue<String> aminoQueue = testDNA.mRNATranslate(mRNAQueue);
    //System.out.println(mRNAQueue); Checked if GUU was still left afterwards. It is, Sunglasses.


    if(!testDNA.translateDNA().toString().trim().equals("G R E A T")){
      System.out.println(testDNA.translateDNA());
      System.out.println("TranslateDNA does not work for dna sequences w/ a stop codon");
      return false;
    }

    return true;
  }

  /**
   * Tests the mRNATranslate method
   * @return true only if all tests are passed
   */
  public static boolean testMRNATranslate(){
    String acids =       "GGAGTCAGTTAAGCGACCGGGACATACTGTCTTGGTAATCTCCGAGCTAGAAAGTCTCTG";
    String translation = "CCUCAGUCAAUUCGCUGGCCCUGUAUGACAGAACCAUUAGAGGCUCGAUCUUUCAGAGAC";
    DNA testDNA= new DNA(acids);
    //GGAGTCAGTTAAGCGACCGGGACATACTGTCTTGGTAATCTCCGAGCTAGAAAGTCTCTG orig dna queue
    //CCUCAGUCAAUUCGCUGGCCCUGUAUGACAGAACCAUUAGAGGCUCGAUCUUUCAGAGAC translateion
    LinkedQueue<Character> transcription = new LinkedQueue<Character>();
    for(int i = 0; i < translation.length(); i++){
      transcription.enqueue(translation.charAt(i));
    }
    try{
      LinkedQueue<String> translate = testDNA.mRNATranslate(transcription);
      String actual = translate.toString().replaceAll(" ", "");
      if (!actual.equals("PQSIRWPCMTEPLEARSFRD")) {
        System.out.println("mRNATranslate no work on valid case");
        return false;
    }
    }catch (Exception e) {
      System.out.println("Exception caught on valid case for mRNATranslate");
      return false;
    }
    try{
      LinkedQueue<String> nullTranslation = testDNA.mRNATranslate(null);
      System.out.println("null arg for mRNAtranslate test did not throw an exception when " +
              "expected");
      return false;
    }
    catch (NullPointerException e){}
    catch (Exception e){
      System.out.println("Some other exception other than null pointer was thrown in a null arg " +
              "test for mRNA translate");
      return false;
    }
    LinkedQueue<String> smallTranslation;
    try{
      LinkedQueue<Character> smallTranscription = new LinkedQueue<Character>();
      smallTranscription.enqueue('B');
      smallTranscription.enqueue('F');
      smallTranslation = testDNA.mRNATranslate(smallTranscription);

      if(!smallTranslation.toString().replaceAll(" ", "").equals("")){
        System.out.println("Small Translation error. <3 acid test.");
        return false;
      }
    }catch (Exception e){
      System.out.println("Exception caught when not expected (<3 queue test)");
      return false;
    }

//    try{
//      LinkedQueue<String> fakeTranslation;
//      LinkedQueue<Character> fakeTranscription = new LinkedQueue<>();
//      fakeTranscription.enqueue('A');
//      fakeTranscription.enqueue('B');
//      fakeTranscription.enqueue('C');
//      fakeTranscription.enqueue('D');
//      fakeTranscription.enqueue('E');
//      fakeTranscription.enqueue('F');
//      fakeTranslation = testDNA.mRNATranslate(fakeTranscription);
//
//      String actual = fakeTranslation.toString().replaceAll(" ","");
//      if(!actual.equals("")){
//        System.out.println("Something wrong with fakeTranscription case test");
//        return false;
//      }
//    }catch (Exception e){
//      System.out.println("FakeTranslation test error. Exception thrown when none expected");
//      return false;
//    }


    return true;
  }

  /**
   * Tests the correctness of all testermethods and the methods the testermethods tests. heh
   * @return true if ALL tests pass.
   */
  public static boolean testAll(){
    if(!testEnqueueDequeue()){
      System.out.println("EnqueueDequeue Issue");
      return false;
    }
    if(!testQueueSize()){
      System.out.println("QueueSize Issue");
      return false;
    }
    if(!testTranscribeDNA()){
      System.out.println("TranscribeDNA Issue");
      return false;
    }
    if(!testMRNATranslate()){
      System.out.println("mRNA Translate Issue");
      return false;
    }
    if(!testTranslateDNA()){
      System.out.println("TranslateDNA Issue");
      return false;
    }
    return true;
  }
  /**
   * Main method - use this to run your test methods and output the results (ungraded)
   * @param args unused
   */
  public static void main(String[] args) {
    System.out.println(testAll());
  }
}
