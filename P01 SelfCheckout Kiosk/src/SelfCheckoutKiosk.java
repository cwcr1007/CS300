//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Self Checkout Kiosk
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
 * SelfCheckoutKiosk class implement all the main operations related to scanning an item 
 * (and eventually adding it to the bagging area), removing an item from the bagging area, 
 * displaying the checkout summary, getting the total/subtotal of your cart, and more.
 */
public class SelfCheckoutKiosk {
  public static final double TAX_RATE = 0.05; // sales tax
  // a perfect-size two-dimensional array that stores the available items in the grocery store
  // GROCERY_ITEMS[i][0] refers to a String that represents the name of the item
  // identified by index i
  // GROCERY_ITEMS[i][1] refers to a String that represents the unit price of the item
  // identified by index i in dollars.
  public static final String[][] GROCERY_ITEMS = new String[][] {{"Apple", "$1.59"},
      {"Avocado", "$0.59"}, {"Banana", "$0.49"}, {"Beef", "$3.79"}, {"Blueberry", "$6.89"},
      {"Broccoli", "$1.79"}, {"Butter", "$4.59"}, {"Carrot", "$1.19"}, {"Cereal", "$3.69"},
      {"Cheese", "$3.49"}, {"Chicken", "$5.09"}, {"Chocolate", "$3.19"}, {"Cookie", "$9.5"},
      {"Cucumber", "$0.79"}, {"Eggs", "$3.09"}, {"Grape", "$2.29"}, {"Ice Cream", "$5.39"},
      {"Milk", "$2.09"}, {"Mushroom", "$1.79"}, {"Onion", "$0.79"}, {"Pepper", "$1.99"},
      {"Pizza", "$11.5"}, {"Potato", "$0.69"}, {"Spinach", "$3.09"}, {"Tomato", "$1.79"}};

  /**
   * Returns the item’s name given its index
   *
   * @param index - unique identifier of an item
   * @return the item name
   */
  public static String getItemName(int index) {
    return GROCERY_ITEMS[index][0];
  }

  /**
   * Returns the price of an item given its index (unique identifier)
   *
   * @param index - unique identifier of an item
   * @return the price of an item
   */
  public static double getItemPrice(int index) {
    return Double.valueOf(GROCERY_ITEMS[index][1].substring(1));
  }

  /**
   * Prints the Catalog of the grocery store (item identifiers, names, and prices)
   *
   */
  public static void printCatalog() {
    // Complete the missing code /* */ in the following implementation
    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
    System.out.println("Item id \tName \t Price");
    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
    for (int i = 0; i < GROCERY_ITEMS.length; i++) {
      System.out.println(i + "\t\t" + SelfCheckoutKiosk.getItemName(i) + "    \t" + "$" 
    + SelfCheckoutKiosk.getItemPrice(i));
    }
    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
  }

  /**
   * Adds the name of a grocery item given its identifier at the end of (the bagging area) the
   * oversize array defined by the items array and its size
   * 
   * @param id    - identifier of the item to be added to the bagging area (index of the item in the
   *              GROCERY_ITEMS array)
   * @param items - array storing the names of the items checked out and placed in the bagging area
   * @param size  - number of elements stored in items before trying to add a new item
   * @return the number of elements stored in bagging area after the item with the provided
   *         identifier was added to the bagging area
   * @throws "Error! No additional item can be scanned. Please wait for assistance." if the items
   *                 array reaches its capacity. The method returns without making any change to the
   *                 contents of the items array.
   */
  public static int addItemToBaggingArea(int id, String[] items, int size) {
    // case 1: full bagging area
    if (size == items.length) {
      System.out.println("Error! No additional item can be scanned. Please wait for assistance.");
      return size;
    }
    // case 2: not full bagging area
    else {
      items[size] = SelfCheckoutKiosk.getItemName(id);
      size += 1;
      return size;
    }
  }


  // Returns the number of occurrences of a given item in an oversize array of
  // strings. The comparison to find the occurrences of item is case insensitive.

  /**
   * Returns the number of occurrences of a given item in an oversize array of strings. The
   * comparison to find the occurrences of item is case insensitive.
   * 
   * @param item  - item to count its occurrences
   * @param items - a bag of string items
   * @param size  - number of items stored in items
   * @return the number of occurrences
   */
  public static int count(String item, String[] items, int size) {
    int count = 0;
    for (int i = 0; i < size; i++) {
      // it should be case insensitive for convenient 
      if (item.equalsIgnoreCase(items[i])) {
        count++;
      }
    }
    return count;
  }

  /**
   * Returns the index of the first occurrence of item in items if found, and -1 if the item not
   * found
   * 
   * @param item  - element to search for
   * @param items - an array of string elements
   * @param size  - number of elements stored in items
   * @return the index of the first occurrence of item in items if found, and -1 if the item not
   *         found
   */
  public static int indexOf(String item, String[] items, int size) {
    for (int i = 0; i < size; i++) {
      if (item.equalsIgnoreCase(items[i])) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Removes the first occurrence of itemToRemove from the bagging area defined by the array items
   * and its size. This method compacts the contents of the items array after removing the
   * itemToRemove so there are no empty spaces in the middle of the array.
   * 
   * @param itemToRemove - item to remove from the bagging area
   * @param items        - a bag of items
   * @param size         - number of elements stored in the bag of items
   * @return the number of items present in the cart after the itemToRemove is removed from the cart
   * @throws "WARNING: item not found." if no match with itemToRemove is found without making any
   *                   change to the items array.
   */
  public static int remove(String itemToRemove, String[] items, int size) {
    int index = SelfCheckoutKiosk.indexOf(itemToRemove, items, size);
    // make sure the item is in the array
    if (index != -1) {
      items[index] = null;
      // compacts the contents of the items array after removing the
      // itemToRemove so there are no empty spaces in the middle of the array.
      for (int i = index+1; i < size; i++) {
        items[i-1] = items[i];
        items[i] = null;
      }
      size --;
      return size;
    }
    else {
      System.out.println("WARNING: item not found.");
      return size;
    }
  }

  /**
   * Gets a copy of the items array without duplicates. Adds every unique item stored within the
   * items array to the itemsSet array.The itemsSet array is initially empty. Recall that a set is a
   * collection which does not contain duplicate items). On the other hand, this method does not
   * make any change to the contents of the items array.
   * 
   * @param items    - list of items added to the bagging area
   * @param size     - number of elements stored in items
   * @param itemsSet - reference to an empty array which is going to contain the set of items
   *                 checked out (it does not contain duplicates)
   * @return the number of elements in items without accounting duplicates. In other words, this
   *         method returns the new size of the itemsSet array
   */
  public static int getUniqueCheckedOutItems(String[] items, int size, String[] itemsSet) {
    // Note that we assume that the length of itemsSet equals
    // at least the size of items. This means that itemsSet array
    // can store the set of scanned items at checkout
    int setSize = 0;
    // if find same element in itemsSet
    boolean found;
    for (int i=0; i<size; i++) {
      found = false;
      for (int j=0; j<setSize; j++) {
        if (items[i]==itemsSet[j]) {
          found = true;
        }
      }
      if (found == false) {
        itemsSet[setSize] = items[i];
        setSize++;
      }
    }
    return setSize;
  }
    
  /**
   * Returns the total value (price) of the scanned items at checkout without tax in $ (double)
   * 
   * @param items - an array which stores the items checked out
   * @param size  - number of elements stored in the items array
   * @return the price of scanned items at checkout without tax
   */
  public static double getSubTotalPrice(String[] items, int size) {
    // [Hint] Try to break down this problem into subproblems.
    // define helper methods to help implement the behavior of this method
    double sum = 0;
    int id;
    double price;
    for (int i=0; i < size; i++) {
      // get identifier number to use getItemPrice method
      id = helper(items[i]);
      price = getItemPrice(id);
      sum += price;
    }
    return sum;
  }
  
  /**
   * Returns the identifier of scanned items at checkout
   * 
   * @param item - the name of item
   * @return the identifier of item
   * @throws Cannot find this item and return -1 if the item is not in grocery items.
   */
  private static int helper(String item) {
    for (int i=0; i<GROCERY_ITEMS.length; i++) {
      if (item.equalsIgnoreCase(GROCERY_ITEMS[i][0])) {
        return i;
      }
    }
    System.out.println("Cannot find this item.");
    return -1;
  }
}
