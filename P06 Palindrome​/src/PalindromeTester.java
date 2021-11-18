//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: PalindromeTester
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
 * This class test the correctness of Palindrom class and includes the main method. 
 *
 */
public class PalindromeTester {
  /**
   * Checks the correctness of Palindrome.MirrorA() method
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testMirrorA(){
    try {
      // System.out.println(Palindrome.mirrorA('E'));
      if (Palindrome.mirrorA('E').equals("E D C B A B C D E")) {
        return true;
      }
      else {
        System.out.println("Wrong: MirrorA");
        return false;
      }
    }
    catch(IllegalArgumentException e1) {
      e1.getMessage();
    }    
    return false;
  }
  
  /**
   * Checks the correctness of Palindrome.MirrorAStep() method
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testMirrorAStep() {
    try {
      if (Palindrome.mirrorA('E', 1).equals("E D C B A B C D E") &&
          Palindrome.mirrorA('E', 2).equals("E C A C E") &&
          Palindrome.mirrorA('E', 3).equals("E B B E")) {
        return true;
      }
      else {
        System.out.println("Wrong: MirrorAStep");
        return false;
      }
    }
    catch(IllegalArgumentException e1) {
      e1.getMessage();
    }    
    return false;
  }
  
  /**
   * Checks the correctness of Palindrome.MirrorZ() method
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testMirrorZ() {
    try {
      if (Palindrome.mirrorZ('V').equals("V W X Y Z Y X W V")) {
        return true;
      }
      else {
        System.out.println("Wrong: MirrorZ");
        return false;
      }
    }
    catch(IllegalArgumentException e1) {
      e1.getMessage();
    }    
    return false;
  }
  
  /**
   * Checks the correctness of Palindrome.MirrorZStep() method
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testMirrorZStep() {
    try {
      if (Palindrome.mirrorZ('V', 1).equals("V W X Y Z Y X W V") &&
          Palindrome.mirrorZ('V', 2).equals("V X Z X V") &&
          Palindrome.mirrorZ('V', 3).equals("V Y Y V")) {
        return true;
      }
      else {
        System.out.println("Wrong: MirrorZStep");
        return false;
      }
    }
    catch(IllegalArgumentException e1) {
      e1.getMessage();
    }    
    return false;
  }
  
  /**
   * This method must call ALL of your test methods and return true if and only if all methods 
   * return true. If you add additional methods besides the four listed above, be sure to call them 
   * here.
   * 
   * @param args
   */
  public static boolean runAllTests() {
    return testMirrorA() && testMirrorAStep() && testMirrorZ() && testMirrorZStep();
  }
  
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    System.out.println(runAllTests());
  }

}
