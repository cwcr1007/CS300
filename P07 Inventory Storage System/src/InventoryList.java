//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: InventoryList
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
 * This class models a singly linked list of boxes which can be used to store and manage inventory
 *
 */
public class InventoryList {
  private LinkedBox head = null;
  private LinkedBox tail = null;
  private int size = 0;
  private int yellowCount = 0;
  private int blueCount = 0;
  private int brownCount = 0;
  
  // DO NOT need to define an explicit constructor to this class. 
  
  /**
   * Returns the size of this list
   * 
   * @return the size of this LinkedBoxList
   */
  public int size() {
    return this.size;
  }
  
  /**
   * Checks whether this LinkedBoxList is empty
   * 
   * @return true if this LinkedBoxList is empty, false otherwise
   */
  public boolean isEmpty() {
    if (this.head == null && this.tail == null && size == 0) {
      return true;
    }
    else {
      return false;
    }
  }
  
  /**
   * Removes all of the elements from this list. The list will be empty after this call returns.
   * 
   */
  public void clear() {
    this.head = null;
    this.tail = null;
    size = 0;
    yellowCount = 0;
    blueCount = 0;
    brownCount = 0;
  }
  
  /**
   * Adds a brown box at the end of this inventory list
   * 
   * @param brownBox - new brown box to be added to this list
   * @throws java.lang.IllegalArgumentException - with a descriptive error message if brownBox is 
   * null or if the color of the specific brownBox is not equal to Color.BROWN
   */
  public void addBrown​(Box brownBox) {
    if (brownBox == null || brownBox.getColor() != Color.BROWN) {
      throw new IllegalArgumentException("brownBox is null or the color of the specific brownBox "
          + "is not equal to Color.BROWN");
    }
    
    LinkedBox newBox = new LinkedBox(brownBox);
    // CASE 1: empty list
    if(this.isEmpty()) {                
      this.head = newBox;
      this.tail = newBox;
      newBox.setNext​(null);
      brownCount++;
      size++;
    }
    // CASE 2: non-empty list
    else {
      LinkedBox curr = head;
      while(curr.getNext() != null) {
        curr = curr.getNext();
      }
      curr.setNext​(newBox);
      newBox.setNext​(null);
      tail = newBox;
      brownCount++;
      size++;
    }
  }
  
  /**
   * Adds a new yellow box at the head of this list
   * 
   * @param yellowBox - new box to be added to this list
   * @throws java.lang.IllegalArgumentException - with a descriptive error message if yellowBox is 
   * null or if its color does not equal to Color.YELLOW
   */
  public void addYellow​(Box yellowBox) {
    if (yellowBox == null || yellowBox.getColor() != Color.YELLOW) {
      throw new IllegalArgumentException("yellowBox is null or if its color does not equal to "
          + "Color.YELLOW");
    }

    LinkedBox newBox = new LinkedBox(yellowBox);
    // CASE 1: empty list
    if(this.isEmpty()) {                
      this.head = newBox;
      this.tail = newBox;
      newBox.setNext​(null);
      yellowCount++;
      size++;
    }
    // CASE 2: non-empty list
    else {
      newBox.setNext​(head);
      head = newBox;
      yellowCount++;
      size++;
    }
  }

  /**
   * Adds a new blue box at the top of blue boxes if the list contains any blue box. Blue boxes 
   * must be added at the buttom of yellow boxes and at the top of all the brown boxes if any. 
   * This means that a new blue box must be added at index yellowCount.
   * 
   * @param blueBox - new box to be added to this list
   * @throws java.lang.IllegalArgumentException - with a descriptive error message if blueBox is 
   * null or if it does not have a Color.BLUE color
   */
  public void addBlue​(Box blueBox) {
    if (blueBox == null || blueBox.getColor() != Color.BLUE) {
      throw new IllegalArgumentException("blueBox is null or if its color does not equal to "
          + "Color.BLUE");
    }
    
    LinkedBox newBox = new LinkedBox(blueBox);
    LinkedBox curr;
    // case 1: empty array
    if(this.isEmpty()) {
      this.head = newBox;
      this.tail = newBox;
      newBox.setNext​(null);
      blueCount++;
      size++;
      return;
    }
    
    // case 2: yellowCount = 0 
    if(yellowCount == 0 && blueCount+brownCount != 0) {
      newBox.setNext​(head);
      head = newBox;
      blueCount++;
      size++;
      return;
    }
    // case 3: brownCount + blueCount = 0
    if(yellowCount != 0 && blueCount+brownCount == 0) {
      curr = head;
      while(curr.getNext() != null) {
        curr = curr.getNext();
      }
      curr.setNext​(newBox);
      tail = newBox;
      newBox.setNext​(null);
      blueCount++;
      size++;
      return;
    }
    // case 4: yellowCount and BrownCount both are greater than 0
    else {
      int count = 1;
      curr = head;
      while(count != yellowCount) {
        curr = curr.getNext();
        count++;
      }
      newBox.setNext​(curr.getNext());
      curr.setNext​(newBox);
      blueCount++;
      size++;
      return;
    }
  }
  
  /**
   * Returns the element stored at position index of this list without removing it.
   * 
   * @param index - position within this list
   * @return the box stored at position index of this list
   * @throws java.lang.IndexOutOfBoundsException - with a descriptive error message if the index is 
   * out of bounds (index < 0 || index >= size())
   */
  public Box get​(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("the index is out of bounds");
    } 
    
    LinkedBox curr = head;
    int count = 0;
    while(curr.getNext() != null && count<index) {
      count++;
      curr = curr.getNext();
    }
    return curr.getBox();
  }
  
  /**
   * Removes and returns the box at the head of this list if its color is yellow
   * 
   * @return a reference to the removed box
   * @throws java.util.NoSuchElementException - with a descriptive error message if this list does 
   * not contain any yellow boxes
   */
  public Box removeYellow() {
    if(yellowCount == 0) {
      throw new java.util.NoSuchElementException("this list does not contain any yellow boxes");
    }
    LinkedBox temp;
    // case 1: only one element in the list
    if(size == 1) {
      temp = head;
      this.head = null;
      this.tail = null;
      yellowCount--;
      size--;
      return temp.getBox();
    }
    // case 2: more than one element in the list
    else {
      temp = head;
      head = temp.getNext();
      yellowCount--;
      size--;
      return temp.getBox();
    }    
    
  }

  /**
   * Removes and returns the element at the tail of this list if it has a brown color
   * 
   * @return a reference to the removed element
   * @throws java.util.NoSuchElementException - with a descriptive error message if this list does 
   * not contain any brown box (brownCount == 0)
   */
  public Box removeBrown() {
    if(brownCount == 0) {
      throw new java.util.NoSuchElementException("this list does not contain any brown boxes");
    }
    LinkedBox temp;
    // case 1: one element in the list
    if (size == 1) {
      temp = head;
      this.head = null;
      this.tail = null;
      brownCount--;
      size--;
      return temp.getBox();
    }
    // case 2: more than one element in the list
    else {
      temp = tail;
      LinkedBox curr = head;
      while(!curr.getNext().equals(tail)) {
        curr = curr.getNext();
      }
      curr.setNext​(null);
      tail = curr;
      brownCount--;
      size--;
      return temp.getBox();
    }
  }
  
  /**
   * Removes and returns a box given its inventory number from this list
   * 
   * @param inventoryNumber - inventory number of the box to be removed from this list
   * @return a reference to the removed element
   * @throws java.util.NoSuchElementException - with a descriptive error message if no box is found 
   * with the provided inventory number in the list.
   */
  public Box removeBox​(int inventoryNumber) {
    if(this.isEmpty()) {
      throw new java.util.NoSuchElementException("Error, Empty list!");
    }
    LinkedBox curr = head;
    LinkedBox temp = curr;

    while(curr.getBox().getInventoryNumber() != inventoryNumber) {
      if(curr.getNext() == null) {
        throw new java.util.NoSuchElementException("Error to remove box. Box #" + inventoryNumber
            + " not found!");  
      }
      temp = curr;  
      curr = curr.getNext();
    }
    // case 0: only one element in list
    if(size == 1) {
      if(curr.getBox().getColor().equals(Color.YELLOW)) {
        yellowCount--;
      }
      if(curr.getBox().getColor().equals(Color.BLUE)) {
        blueCount--;
      }
      if(curr.getBox().getColor().equals(Color.BROWN)) {
        brownCount--;
      }
      head = null;
      tail = null;
      size--;
      return curr.getBox();
    }
    // case 1: if curr is tail
    if(curr.equals(tail)) {
      temp.setNext​(null);
      tail = temp;
      size--;
      if(curr.getBox().getColor().equals(Color.YELLOW)) {
        yellowCount--;
      }
      if(curr.getBox().getColor().equals(Color.BLUE)) {
        blueCount--;
      }
      if(curr.getBox().getColor().equals(Color.BROWN)) {
        brownCount--;
      }
      return curr.getBox();
    }
    // case 2: if curr is head
    if(curr.equals(head)) {
      head = curr.getNext();
      size--;
      if(curr.getBox().getColor().equals(Color.YELLOW)) {
        yellowCount--;
      }
      if(curr.getBox().getColor().equals(Color.BLUE)) {
        blueCount--;
      }
      if(curr.getBox().getColor().equals(Color.BROWN)) {
        brownCount--;
      }
      return curr.getBox();
    }
    // case 3: curr in the middle
    else {
      temp.setNext​(curr.getNext());
      size--;
      if(curr.getBox().getColor().equals(Color.YELLOW)) {
        yellowCount--;
      }
      if(curr.getBox().getColor().equals(Color.BLUE)) {
        blueCount--;
      }
      if(curr.getBox().getColor().equals(Color.BROWN)) {
        brownCount--;
      }
      return curr.getBox();
    }
  }
  
  /**
   * Returns the number of brown boxes stored in this list
   * 
   * @return the brownCount
   */
  public int getBrownCount() {
    return this.brownCount;
  }

  /**
   * Returns the number of yellow boxes stored in this list
   * 
   * @return the yellowCount
   */
  public int getYellowCount() {
    return this.yellowCount;
  }
  
  /**
   * Returns the number of blue boxes stored in this list
   * 
   * @return the BlueCount
   */
  public int getBlueCount() {
    return this.blueCount;
  }
  
  /**
   * Returns a String representation of the contents of this list
   * 
   * @return a String representation of the content of this list. If this list is empty, an empty 
   * String ("") will be returned.
   */
  @Override
  public String toString(){
    String output = "";
    if (this.isEmpty()) {
      return output;
    }
    else {
      LinkedBox curr = head;
      output = output + curr.toString();
      while(curr.getNext() != null) {
        output = output + curr.getNext().toString();
        curr = curr.getNext();
      }
      return output;
    }
  }
  
}
