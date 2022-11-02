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

/**
 * This class uses a linked queue to implement DNA transcription. DNA transcription
 * is performed by first transcribing a string of DNA characters to their mRNA complements,
 * and then translating those mRNA characters in groups of three (called "codons") to corresponding
 * amino acids, which finally fold up into proteins.
 */
public class DNA {
  protected LinkedQueue<Character> DNA; //The queue containing the original DNA sequence
  protected static String[][] mRNAtoProteinMap =
          {{"UUU", "F"}, {"UUC", "F"}, {"UUA", "L"}, {"UUG", "L"}, {"UCU", "S"}, {"UCC", "S"},
                  {"UCA", "S"}, {"UCG", "S"}, {"UAU", "Y"}, {"UAC", "Y"}, {"UAA", "STOP"}, {"UAG", "STOP"},
                  {"UGU", "C"}, {"UGC", "C"}, {"UGA", "STOP"}, {"UGG", "W"}, {"CUU", "L"}, {"CUC", "L"},
                  {"CUA", "L"}, {"CUG", "L"}, {"CCU", "P"}, {"CCC", "P"}, {"CCA", "P"}, {"CCG", "P"},
                  {"CAU", "H"}, {"CAC", "H"}, {"CAA", "Q"}, {"CAG", "Q"}, {"CGU", "R"}, {"CGC", "R"},
                  {"CGA", "R"}, {"CGG", "R"}, {"AUU", "I"}, {"AUC", "I"}, {"AUA", "I"}, {"AUG", "M"},
                  {"ACU", "T"}, {"ACC", "T"}, {"ACA", "T"}, {"ACG", "T"}, {"AAU", "N"}, {"AAC", "N"},
                  {"AAA", "K"}, {"AAG", "K"}, {"AGU", "S"}, {"AGC", "S"}, {"AGA", "R"}, {"AGG", "R"},
                  {"GUU", "V"}, {"GUC", "V"}, {"GUA", "V"}, {"GUG", "V"}, {"GCU", "A"}, {"GCC", "A"},
                  {"GCA", "A"}, {"GCG", "A"}, {"GAU", "D"}, {"GAC", "D"}, {"GAA", "E"}, {"GAG", "E"},
                  {"GGU", "G"}, {"GGC", "G"}, {"GGA", "G"}, {"GGG", "G"}};
  //A two-dimensional array containing the mRNA codons in index 0 and the corresponding amino acid
  // (or STOP) in index 1

  /**
   * Creates the DNA queue from the provided String. Each Node contains a single Character
   * from the sequence.
   * @param sequence a String containing the original DNA sequence
   * @throws NullPointerException if sequence is Null
   */
  public DNA (String sequence){
    DNA = new LinkedQueue<Character>();
    if(sequence == null) throw new NullPointerException("Sequence cannot be Null");
    for(int i = 0; i < sequence.length(); i++){
      DNA.enqueue(sequence.charAt(i));
    }
  }

  /**
   * Creates and returns a new queue of mRNA characters from the stored DNA.
   * The transcription is done one character at a time, as (A->U, T->A, C->G, G->C).
   * @return the queue containing the mRNA sequence corresponding to the stored DNA sequence
   */
  public LinkedQueue<Character> transcribeDNA(){
    LinkedQueue<Character> mRNA = new LinkedQueue<Character>();
    int length = DNA.size();

    for(int i = 0; i < length; i++){
      char letter = DNA.dequeue();

      switch(letter){
        case 'A':
          mRNA.enqueue('U');
          DNA.enqueue(letter);
          break;
        case 'T':
          mRNA.enqueue('A');
          DNA.enqueue(letter);
          break;
        case 'C':
          mRNA.enqueue('G');
          DNA.enqueue(letter);
          break;
        case 'G':
          mRNA.enqueue('C');
          DNA.enqueue(letter);
          break;
        default:
          DNA.enqueue(letter);
      }
    }
    return  mRNA;
  }

  /**
   * Creates and returns a new queue of amino acids from a provided queue of mRNA characters.
   * The translation is done three characters at a time, according to the static mRNAtoProteinMap
   * provided above. Translation ends either when you run out of mRNA characters OR when a STOP
   * codon is reached (i.e. the three-character sequence corresponds to the word STOP in the map,
   * rather than a single amino acid character).
   * @param mRNA a queue containing the mRNA sequence corresponding to the stored DNA sequence
   * @return the queue containing the amino acid sequence corresponding to the provided mRNA
   * sequence
   */
  public LinkedQueue<String> mRNATranslate(LinkedQueue<Character> mRNA){

    LinkedQueue<String> translate = new LinkedQueue<String>();
    int size = mRNA.size(); //initializing
    String mRNAvals = "";
    String translation = "";

    while(size >= 3){

      for(int i = 0; i < 3; i++){
        mRNAvals += mRNA.dequeue(); //Get Codon
      }
      size = mRNA.size(); //updating the size after dequeue

      translation = mRNAtoProteinHelper(mRNAvals); //Compare Codon to table provided
      if(translation.equals("STOP")){
        size = -1;
      } else
        translate.enqueue(translation);

      mRNAvals = ""; //resetting the string for next cycle
    }

    return translate;
  }

  /**
   * Helper method to translate codons
   * @param mRNA the 3length codon
   * @return the corresponding acid
   */
  private String mRNAtoProteinHelper(String mRNA){
    String letter = "";
    for(int i = 0; i < mRNAtoProteinMap.length; i++){
        if(mRNAtoProteinMap[i][0].equals(mRNA))
          letter = mRNAtoProteinMap[i][1];
    }
    return letter;
  }

  /**
   * A shortcut method that translates the stored DNA sequence to a queue of amino acids
   * using the other two methods in this class
   * @returns the queue containing the amino acid sequence corresponding to the stored DNA sequence,
   * via its mRNA transcription
   */
  public LinkedQueue<String> translateDNA(){
    LinkedQueue<String> translate = new LinkedQueue<String>();
    translate = mRNATranslate(transcribeDNA());

    return translate;
  }

}
