//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: CleverBag
// Course: CS 300 Spring 2021
//
// Author: Bob Dai
// Email: zdai38@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////
import java.io.File;
import java.util.Scanner;

/**
 * This class models a CleverBag object. It inherit from the SimpleBag class and contain the 
 * following methods.
 *
 */
public class CleverBag extends SimpleBag {
  // Initializes the private integer field , size, which will track the current number of 
  // initialized Strings in the parent class’ data array.
  private int size = 0;
  
  /**
   * Calls the super class’ constructor with appropriate arguments. 
   * 
   * @param seed    int type
   */
  public CleverBag(int seed) {
    super(seed);
  }
  
  /**
   * Reads the contents of the file as in the parent class, but instead inserts the new words at 
   * the end of the array and then updates the size field accordingly. If you encounter any 
   * exceptions while reading the File, simply return from the method.
   * 
   * Complexity: O(N)
   * 
   * @param f   File object
   */
  @Override
  public void loadData(File f) {
    try {
      Scanner s = new Scanner(f);
      s.next();
      while(s.hasNext() == true) {
        data[size] = s.next();
        size++;
      }
      s.close();
    }
    catch(Exception e){
      System.out.println(e.getMessage());
      return;
    }
  }
  
  /**
   * Generates a random integer between 0 and the current size. Removes and returns the String at 
   * that index. Fills gaps by moving the last String into the gap and decrementing size. If the 
   * bag contains no strings, this method returns null.
   * 
   * Complexity: O(1)
   * 
   * @return String the String at that index.
   */
  @Override
  public String removeRandom() {
    if (size == 0) {
      return null;
    }
    int idx = random.nextInt(size);    
    String output = data[idx];
    data[idx] = null;
    data[idx] = data[size-1];
    data[size-1] = null;
    size--;
    return output;
  }

  
}
