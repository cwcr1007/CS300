//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: SimpleBag
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
import java.util.Random;
import java.util.Scanner;

/**
 * This class models a SimpleBag object. It plays the role of a base class of Cleverbag.
 *
 */
public class SimpleBag {
  //Initializes a protected field, data, which is an array of Strings with capacity 80,000.
  protected String[] data = new String[80000];
  protected Random random;
  
  /**
   * Initializes a protected Random object, random, using the provided seed value.
   * 
   * @param seed    int type
   */
  public SimpleBag(int seed) {
    random = new Random(seed);
  }
  
  /**
   * Reads the text contents of the provided file, inserting each new space-separated word at the 
   * beginning of the data array. All strings currently in the array should be shifted to the right 
   * by one index to make room. That is, the string at index N should be moved to index N+1, and so 
   * forth. If you encounter any exceptions while reading the File, simply return from the method.
   * 
   * Complexity: O(N^2)
   * 
   * @param f   File object
   * 
   */
  public void loadData(File f) {
    try {
      Scanner s = new Scanner(f);
      s.next();
      while(s.hasNext() == true) {
        for(int i=data.length-1; i>0; i--) {
          data[i] = data[i-1]; 
        }
        data[0] = s.next();
      }
      s.close();
//      for(int i=0; i<15; i++) {
//        System.out.println(data[i]);
//      }
    }
    catch(Exception e){
      System.out.println(e.getMessage());
      return;
    }
    
  }
  
  /**
   * Counts the number of Strings (i.e. non-null) values in the data array and generates a random 
   * index between 0 and the number of Strings stored in this bag (exclusive). Removes and returns 
   * the String at that index. Fills gaps by moving all following strings to the left by one index. 
   * N -> N-1, etc. If the bag contains no strings, this method returns null.
   * 
   * Complexity: O(N)
   * 
   * @return the String at that index or null if bag contains no strings.
   */
  public String removeRandom() {
    int bound = 0;
    for(int i=0; i<data.length; i++) {
      if(data[i] != null) {
        bound++;
      }
    }
    //System.out.println("total: " + bound);
    
    if(bound ==0) {
      return null;
    }
    int idx = random.nextInt(bound);
    //System.out.println("index: " + idx);
    
    String output = data[idx];
    data[idx] = null;
    for (int i=idx; i<bound; i++) {
      data[i] = data[i+1];
    }
    return output;
  }
}
