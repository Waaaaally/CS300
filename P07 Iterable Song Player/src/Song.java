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
/**
 * This class models a song
 */
public class Song {
  private String songName; // name or title of the song
  private String artist; // artist of the song
  private String duration; // duration of the song


  /**
   * Creates a new Song given the song name, the name of the artist, and the duration of the song
   *
   * @param songName title of the song
   * @param artist   name of the artist who performed this song
   * @param duration duration of the song in the format mm:ss
   * @throws IllegalArgumentException with a descriptive error message if either of songName, or
   * artist, or duration is null or is blank, or if the duration is not formatted as mm:ss where
   * both mm and ss are in the 0 .. 59 range.
   */
  public Song(String songName, String artist, String duration) {
    if (songName == null || songName.isBlank())
      throw new IllegalArgumentException("song was invalid");
    if (artist == null || artist.isBlank())
      throw new IllegalArgumentException("artist was invalid");
    if (duration == null || duration.isBlank())
      throw new IllegalArgumentException("duration was invalid");

    boolean badDuration = false;
    int colonIndex = duration.indexOf(':');
    int length = duration.length();

    if(length != 4 && length != 5) badDuration = true;
    if(colonIndex == -1) badDuration = true;
    if(badDuration)
      throw new IllegalArgumentException("duration was invalid format");

    int minutes = Integer.parseInt(duration.substring(0, colonIndex));
    int seconds = Integer.parseInt(duration.substring(colonIndex + 1, length));

    if(minutes > 59 || minutes < 0) badDuration = true;
    if(seconds > 59 || seconds < 0) badDuration = true;
    if(badDuration)
      throw new IllegalArgumentException("duration was invalid format");

    this.songName = songName;
    this.artist = artist;
    this.duration = duration;
  }

  /**
   * Gets the title or name of this song
   *
   * @return the title or name of this song
   */
  public String getSongName() {
    return this.songName;
  }

  /**
   * Gets the name of the artist who performed this song
   *
   * @return the artist who performed this song
   */
  public String getArtist() {
    return this.artist;
  }

  /**
   * Gets the duration of this song
   *
   * @return the duration
   */
  public String getDuration() {
    return this.duration;
  }

  @Override
  /**
   * Returns a string representation of this song. This string should be formatted as follows.
   * "songName---artist---duration" where songName is the title of the song, artist is the name of
   * the artist, and duration is the duration of the song.
   * @return a string representation of this song.
   */
  public String toString() {
    String toString = (songName + "---" + artist + "---" + duration);
    return toString;
  }

  @Override
  /**
   * Returns true when this song's name and artist strings equals to the other song's name and
   * artist strings, and false otherwise. Note that this method takes an Object rather than a Song
   * argument, so that it Overrides Object.equals(Object). If an object that is not an instance of
   * Song is ever passed to this method, it should return false.
   */
  public boolean equals(Object other){
    if(!(other instanceof Song))
      return false;

    if(!this.songName.equalsIgnoreCase(((Song)other).getSongName())){
      //System.out.println("Song names didn't match");
      return false;
    }
    if(!this.artist.equalsIgnoreCase(((Song)other).getArtist())){
      //System.out.println("Artists didn't match");
      return false;
    }
    return true;
  }
}
