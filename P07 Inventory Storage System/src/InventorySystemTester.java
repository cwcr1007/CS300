//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: InventorySystemTester
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
 * This class test the correctness of LindedBox and InventoryLis class and includes the main method.
 *
 */
public class InventorySystemTester {

  /**
   * Checks the correctness of LinkedBox class
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLinkedBox() {
    Box.restartNextInventoryNumber();
    Box a = new Box(Color.BLUE);
    Box b = new Box(Color.YELLOW);
    LinkedBox la = new LinkedBox(a);
    LinkedBox lb = new LinkedBox(b);

    if (!la.getBox().equals(a)) {
      System.out.println("Error in LinkedBox");
      return false;
    }

    la.setNext​(lb);
    if (!la.getNext().equals(lb)) {
      System.out.println("Error in LinkedBox");
      return false;
    }
    return true;
  }

  /**
   * checks for the correctness of the InventoryList.clear() method
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testClear() {
    Box.restartNextInventoryNumber();
    InventoryList list = new InventoryList();
    list.addBlue​(new Box(Color.BLUE)); // adds BLUE 1 
    list.clear();
    
    if(list.isEmpty()) {
      return true;
    }
    else {
      System.out.println("Error in Clear()");
      return false;
    }
  }
  
  /**
   * checks for the correctness of the InventoryList.addYellow(), InventoryList.addBlue(), 
   * and InventoryList.addBrown() methods
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddBoxes() {
    Box.restartNextInventoryNumber();
    InventoryList list = new InventoryList();

    list.addBlue​(new Box(Color.BLUE)); // adds BLUE 1 
    list.addYellow​(new Box(Color.YELLOW)); // adds YELLOW 2 at the head of the list
    list.addBlue​(new Box(Color.BLUE)); // adds BLUE 3 
    list.addYellow​(new Box(Color.YELLOW)); // adds YELLOW 4
    list.addYellow​(new Box(Color.YELLOW)); // adds YELLOW 5 at the head of the list
    list.addBrown​(new Box(Color.BROWN)); // adds BROWN 6 at the head of the list
    
    if(list.size() == 6 && list.getYellowCount() == 3 && list.getBlueCount() == 2 &&
        list.getBrownCount() == 1 && list.toString() == "YELLOW 5 -> YELLOW 4 -> YELLOW 2 -> "
            + "BLUE 3 -> BLUE 1 -> BROWN 6 -> END") {
      return true;
    }
    else {
      System.out.println("Error in AddBoxes");
      return false; 
    }
  }

  /**
   * checks for the correctness of the InventoryList.removeBox(), InventoryList.removeYellow(), 
   * and InventoryList.remove Brown() methods
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRemoveBoxes() {
    Box.restartNextInventoryNumber();
    InventoryList list = new InventoryList();

    list.addBlue​(new Box(Color.BLUE)); // adds BLUE 1 
    list.addYellow​(new Box(Color.YELLOW)); // adds YELLOW 2 at the head of the list
    list.addBlue​(new Box(Color.BLUE)); // adds BLUE 3 
    list.addYellow​(new Box(Color.YELLOW)); // adds YELLOW 4
    list.addYellow​(new Box(Color.YELLOW)); // adds YELLOW 5 at the head of the list
    list.addBrown​(new Box(Color.BROWN)); // adds BROWN 6 at the head of the list
    
    list.removeBrown();
    list.removeYellow();
    list.removeBox​(4);
    if(list.size() == 3 && list.getYellowCount() == 1 && list.getBlueCount() == 2 &&
        list.getBrownCount() == 0 && list.get​(0).getInventoryNumber() == 2 && 
        list.get​(2).getInventoryNumber() == 1) {
      return true;
    }
    else {
      System.out.println("Error in RemoveBoxes");
      return false; 
    }
  }
  
  /**
   * checks for the correctness of the InventoryList.get() method 
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetBoxes() {
    Box.restartNextInventoryNumber();
    InventoryList list = new InventoryList();
    
    Box a = new Box(Color.BLUE);
    Box b = new Box(Color.YELLOW);
    Box c = new Box(Color.BROWN);
    
    list.addBlue​(a); // adds BLUE 1 
    list.addYellow​(b); // adds YELLOW 2 at the head of the list
    list.addBrown​(c); // adds BROWN 6 at the head of the list
    
    if(list.get​(0).equals(b) && list.get​(2).equals(c)) {
      return true;
    }
    else {
      System.out.println("Error in GetBoxes");
      return false;
    }
  }
  
  /**
   * checks a test suite method to run all your test methods
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean runAllTests() {
    Box.restartNextInventoryNumber();
    return testLinkedBox() && testLinkedBox() && testRemoveBoxes() && testClear() && testGetBoxes();
  }

/**
  public static void main(String[] args) {
    System.out.println(testLinkedBox());
    testAddBoxes();
  }
*/  
  public static void main(String[] args) {
    System.out.println(runAllTests());
  }
}





