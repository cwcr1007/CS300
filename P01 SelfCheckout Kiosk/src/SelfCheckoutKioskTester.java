//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Self Checkout Kiosk Tester
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

import java.util.Arrays;

/**
 * SelfCheckoutKioskTester class implement all the method to check the correctness of 
 * operations related to scanning an item, removing an item from the bagging area, 
 * displaying the checkout summary, getting the total/subtotal of your cart, and more
 * in SelfCheckoutKiosk class.
 */
public class SelfCheckoutKioskTester {
  /**
   * Checks whether SelfCheckoutKisok.getItemName() and SelfCheckoutKisok.getItemPrice() method work
   * as expected.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testItemNameAndPriceGetterMethods() {
    // consider all identifiers values as input arguments
    // GROCERY_ITEMS array is a perfect size array. So, its elements are stored
    // in the range of indexes from 0 .. GROCERY_ITEMS.length -1
    for (int i = 0; i < SelfCheckoutKiosk.GROCERY_ITEMS.length; i++) {
      // check first for the correctness of the getItemName(i) method
      if (!SelfCheckoutKiosk.getItemName(i).equals(SelfCheckoutKiosk.GROCERY_ITEMS[i][0])) {
        System.out.println("Problem detected: Called your getItemName() method with "
            + "input value " + i + ". But it did not return the expected output.");
        return false;
      }
      // Check for the correctness of the getItemPrice(i) method
      // Notice that GROCERY_ITEMS[i][1] is of type String starting with "$" followed by
      // the double price value.
      double expectedPriceOutput =
          Double.valueOf(SelfCheckoutKiosk.GROCERY_ITEMS[i][1].substring(1));
      // Note that we do not use the == operator to check whether two floating-point numbers
      // (double or float) in java are equal. Two variables a and b of type double are equal
      // if the absolute value of their difference is less or equal to a small threshold epsilon.
      // For instance, if Math.abs(a - b) <= 0.001, then a equals b
      if (Math.abs((SelfCheckoutKiosk.getItemPrice(i) - expectedPriceOutput)) > 0.001) {
        // We recommend that you print a descriptive error message before
        // returning false
        System.out.println("Problem detected: getItemPrice() does not get the correct price");
        return false;
      }
    }
    return true; // No defect detected -> The implementation passes this test
  }

  /**
   * Checks the correctness of SelfCheckoutKiosk.addItemToBaggingArea() method
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddItemToBaggingArea() {
    // Create an empty bagging area
    String[] items = new String[10];
    int size = 0;
    // Define the test scenarios:
    // (1) Add one item to an empty bagging area
    // try to add an apple (id: 0) to the bagging area
    size = SelfCheckoutKiosk.addItemToBaggingArea(0, items, size);
    if (size != 1) {
      System.out.println("Problem detected: Tried to add one item to an empty, "
          + "bagging area. The returned size must be 1. But your addItemToBaggingArea "
          + "method returned a different output.");
      return false;
    }
    if (!items[0].equals(SelfCheckoutKiosk.getItemName(0))) {
      // notice here the importance of checking for the correctness of your getItemName()
      // method before calling it above
      System.out.println("Problem detected: Tried to add only one item to an empty, "
          + "bagging area. But that item was not appropriately added to the contents "
          + "of the items array.");
      return false;
    }
    // (2) Consider a non-empty bagging area
    items = new String[] {"Milk", "Chocolate", "Onion", null, null, null, null};
    size = 3;
    size = SelfCheckoutKiosk.addItemToBaggingArea(10, items, size);
    if (size != 4) {
      System.out.println("Problem detected: Tried to add only one item to an non-empty, "
          + "bagging area. The size must be incremented after the method returns. But "
          + "it was not the case");
      return false;
    }
    if (!items[3].equals(SelfCheckoutKiosk.getItemName(10))) {
      System.out.println("Problem detected: Tried to add one item to an non-empty, "
          + "bagging area. But that item was not appropriately added to the contents "
          + "of the items array.");
      return false;
    }
    // (3) Consider adding an item to a full bagging area
    items = new String[] {"Pizza", "Eggs", "Apples"};
    size = 3;
    size = SelfCheckoutKiosk.addItemToBaggingArea(2, items, size);
    // TODO Complete the implementation of this test scenario
    // Check that the returned size is correct (must be 3), and that no
    // changes have been made to the content of items array {"Pizza", "Eggs", "Apples"}
    if (size != 3) {
      System.out.println("Problem detected: Tried to add one item to an non-empty, "
          + "full bagging area. The size must remain the same after the method returns. "
          + "But it was not the case");
      return false;
    }
    return true; // No defects detected by this unit test
  }

  /**
   * Checks the correctness of SelfCheckoutKiosk.count() method
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testCount() {
    // Create an empty bagging area
    String[] items = new String[10];
    int size = 0;
    int count;
    // Try to consider different test scenarios: (1) a bagging area (defined by
    // the items array and its size) which contains 0 occurrences of the item
    // Count for apple in an empty array
    count = SelfCheckoutKiosk.count("apple", items, size);
    if (count != 0) {
      System.out.println("Problem detected: Tried to count item in an empty bagging area."
          + "The count must be 0 after the method returns. But it was not the case.");
      return false;
    }
    // (2) a bagging area which contains at least 4 items and only one occurrence
    // of the item to count
    items = new String[] {"Milk", "Chocolate", "Onion", "Apple", null, null, null};
    size = 4;
    count = SelfCheckoutKiosk.count("apple", items, size);
    if (count != 1) {
      System.out.println("Problem detected: Tried to count item in an non-empty bagging area."
          + "The count must be 1 after the method returns. But it was not the case.");
      return false;
    }

    // (3) a bagging area which contains at least 5 items and 2 occurrences of the item to count.
    // There are 2 chocolate in the items
    items = new String[] {"Milk", "Chocolate", "Onion", "Apple", "Chocolate", null, null};
    size = 5;
    count = SelfCheckoutKiosk.count("Chocolate", items, size);
    if (count != 2) {
      System.out.println("Problem detected: Tried to count item in an non-empty bagging area."
          + "The count must be 2 after the method returns. But it was not the case.");
      return false;
    }
    return true; // No defects detected by this unit test
  }

  /**
   * Checks the correctness of SelfCheckoutKiosk.testIndexOf() method
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testIndexOf() {
    // Create an bagging area and size
    String[] items = new String[] {"Milk", "Chocolate", "Onion", "Apple", "Chocolate", null, null};
    int size = 5;
    int index;
    // The cases 1: where the items array contains at least one match with the item to find
    index = SelfCheckoutKiosk.indexOf("Apple", items, size);
    if (index != 3) {
      System.out.println("Problem detected: Tried to find the index of the first occurence of item"
          + "in an non-empty bagging area and -1 if the item not found."
          + "The count must be 3 after the method returns. But it was not the case.");
      return false;
    }

    // The case 2: when the item was not stored in the array and the expected output is -1
    index = SelfCheckoutKiosk.indexOf("Beef", items, size);
    if (index != -1) {
      System.out.println("Problem detected: Tried to find the index of the first occurence of item"
          + "in an non-empty bagging area and -1 if the item not found."
          + "The index must be -1 after the method returns. But it was not the case.");
      return false;
    }

    return true; // No defects detected by this unit test
  }

  /**
   * Checks the correctness of SelfCheckoutKiosk.testRemove() method. When only one attempt to
   * remove an item stored in the bagging area is made, only one occurrence of that item is removed
   * from the array of items, that the returned size is correct, and that the items array contains
   * all the other items.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRemove() {
    // Create an bagging area, size and itemToRemove
    String[] items = new String[] {"eggs", "banana", "eggs", "Milk", "Potato", null, null, null};
    int size = 5;
    String itemToRemove = new String("apple");
    String[] correct = items.clone();
    // Case 1: when an attempt is made to remove an item that is not in the bagging
    size = SelfCheckoutKiosk.remove(itemToRemove, items, size);
    if (size != 5) {
      System.out.println("Problem detected: try to remove a item that is not in the bagging area"
          + "The index must be 5 after the method returns. But it was " + size);
      return false;
    }
    // checking for the correctness of your Remove() that the items array
    // contains all the other items
    if (!Arrays.equals(items, correct)) {
      System.out.println("Problem detected: try to remove a item that is not in the bagging area. "
          + "But that items was not appropriately contains all the other to items");
      return false;
    }

    // Case 2: if an attempt is made to remove an item from an empty bagging
    // area (the input size == 0)
    items = new String[] {null, null, null};
    size = 0;
    itemToRemove = new String("banana");
    correct = items.clone();
    size = SelfCheckoutKiosk.remove(itemToRemove, items, size);
    if (size != 0) {
      System.out.println("Problem detected: try to remove a item in an empty bagging area. "
          + "The size must be 0 after the method returns. But it was " + size);
      return false;
    }

    if (!Arrays.equals(items, correct)) {
      System.out.println("Problem detected: try to remove a item in an empty bagging area "
          + "But that items was not appropriately contains all the other to items");
      return false;
    }

    // Case 3: when an attempt is made to remove an item that has multiple
    // occurrences in the bagging area
    items = new String[] {"eggs", "banana", "eggs", "Milk", "Potato", null, null, null};
    size = 5;
    itemToRemove = new String("eggs");
    correct = new String[] {"banana", "eggs", "Milk", "Potato", null, null, null, null};
    size = SelfCheckoutKiosk.remove(itemToRemove, items, size);
    if (size != 4) {
      System.out.println("Problem detected: try to remove a item in an bagging area has multiple "
          + "occurance of item. " + "The size must be 4 after the method returns. But it was "
          + size);
      return false;
    }
    if (!Arrays.equals(items, correct)) {
      System.out.println("Problem detected: try to remove a item in an bagging area has multiple "
          + "occurance of item. "
          + "But that items was not appropriately contains all the other to items");
      return false;
    }
    return true;// No defects detected by this unit test
  }

  /**
   * Checks whether getSubTotalPrice method returns the correct output
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetSubTotalPrice() {
    // Create bagging area,  size, and an empty array
    String[] items = new String[] {"eggs", "Milk"};
    int size = 2;
    double total = SelfCheckoutKiosk.getSubTotalPrice(items,size);
    
    double expectedPriceOutput = 3.09+2.09;
    if (Math.abs((total - expectedPriceOutput)) > 0.001) {
      System.out.println("Problem detected: try to sum up the total price. "
          + "experted 5.18 but get " + total);
      return false;
    }
    return true;
  }

  /**
   * Checks whether getUniqueCheckedOutput function is correct
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetUniqueCheckedOutItems() {
    // Create bagging area,  size, and an empty array
    
    String[] items = new String[] {"Milk", "banana", "eggs", "Milk", "Milk", "banana", null, null};
    int size = 6;
    String[] correct = new String[] {"Milk","banana", "eggs", null, null, null, null, null};
    String[] itemsSet = new String[items.length];
    int setSize;
    
    setSize = SelfCheckoutKiosk.getUniqueCheckedOutItems(items, size, itemsSet);

    if (setSize != 3) {
      System.out.println("Problem detected: itemsSet has wrong set size. We expect to have set size"
          + "= 3, but get "+ setSize);
      return false;
    }
    //Check itemsSet contain the set of items checked out ( does not contain duplicates)
    if (!Arrays.equals(itemsSet, correct)) {
      System.out.println("Problem detected: Wrong content.");
      return false;
    }
    return true;
  }

  /**
   * Calls the test methods implemented in this class and displays their output
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out
        .println("testItemNameAndPriceGetterMethods(): " + testItemNameAndPriceGetterMethods());

    SelfCheckoutKiosk.printCatalog();

    System.out.println("testAddItemToBaggingArea(): " + testAddItemToBaggingArea());

    System.out.println("testCount(): " + testCount());

    System.out.println("testindexOf(): " + testIndexOf());

    System.out.println("testRemove(): " + testRemove());
    
    System.out.println("testGetUniqueCheckedOutItems(): " + testGetUniqueCheckedOutItems());
    
    System.out.println("testGetSubTotalPrice(): " + testGetSubTotalPrice());

  }

}
