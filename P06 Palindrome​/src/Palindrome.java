//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Palindrome
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

/**
 * This Palindrome class contain the following four static methods implemented recursively.
 * 
 */
public class Palindrome {
  
  /**
   * Recursively create a simple alphabet pattern, starting at the provided character, moving
   * backward to the beginning of the alphabet, and then forward again to the provided letter, 
   * separating each letter with a space.
   * 
   * @param start   char type
   * @return a String type
   * @throws IllegalArgumentException with a descriptive error message if anything other than 
   * a capital letter is provided as an argument
   */
  public static String mirrorA(char start) throws IllegalArgumentException{
    if (start < 65 || start > 90) {
      throw new IllegalArgumentException("Wrong input: not capital letter.");
    }
    if (start == 65) {
      return start + "";
    }
    return start + " " + mirrorA((char) (start-1)) + " " + start;
  }
  
  /**
   * 
   * @param start   char type
   * @param step    int type
   * @return
   * @throws IllegalArgumentException with a descriptive error message if receive other than 
   * capital letter input and ​strictly positive​ (not zero or negative) step sizes
   */
  public static String mirrorA(char start, int step)throws IllegalArgumentException{
    if (start < 65 || start > 90) {
      throw new IllegalArgumentException("Wrong input: not capital letter.");
    }
    if (step <= 0) {
      throw new IllegalArgumentException("Wrong input: zero or negative step sizes.");
    }
    
    if (start == 65) {
      return start + "";
    }
    if (start - step < 65) {
      return start + " " + start;
    }
    
    return start + " " + mirrorA((char) (start-step), step) + " " + start;
  }
  
  /**
   * Recursively create a simple alphabet pattern, starting the provided character, and moving 
   * forward to the end of the alphabet, and then backward again to the provided letter, separating 
   * each letter with a space.
   * 
   * @param start   char type
   * @return a String
   * @throws IllegalArgumentException with a descriptive error message if anything other than 
   * a capital letter is provided as an argument
   */
  public static String mirrorZ(char start) throws IllegalArgumentException{
    if (start < 65 || start > 90) {
      throw new IllegalArgumentException("Wrong input: not capital letter.");
    }
    if (start == 90) {
      return start + "";
    }
    return start + " " + mirrorZ((char) (start+1)) + " " + start;
  }
  
  /**
   * Recursively create an alphabet pattern, starting at the provided character, and moving forward 
   * and back to the end of the alphabet by steps of size ​step​.
   * 
   * @param start   char type
   * @param step    int type
   * @return a String
   * @throws IllegalArgumentException with a descriptive error message if receive other than 
   * capital letter input and ​strictly positive​ (not zero or negative) step sizes
   */
  public static String mirrorZ(char start, int step) throws IllegalArgumentException{
    if (start < 65 || start > 90) {
      throw new IllegalArgumentException("Wrong input: not capital letter.");
    }
    if (step <= 0) {
      throw new IllegalArgumentException("Wrong input: zero or negative step sizes.");
    }
    
    if (start == 90) {
      return start + "";
    }
    if (start + step > 90) {
      return start + " " + start;
    }
    
    return start + " " + mirrorZ((char) (start+step), step) + " " + start;
  }
  
}








