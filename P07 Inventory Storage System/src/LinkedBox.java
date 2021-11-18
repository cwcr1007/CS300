//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: LinkedBox
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
 * This class models a Box used for inventory
 *
 */
public class LinkedBox {
  private Box box;
  private LinkedBox next;
  
  /**
   * Creates a new LinkedBox node with given box and next fields
   * 
   * @param box - the box carried by this linked box
   * @param next - reference to the next linked box in the list
   */
  public LinkedBox(Box box, LinkedBox next) {
    this.box = box;
    this.next = next;
  }
  
  /**
   * Creates a new LinkedBox with a specified box and null as next field
   * 
   * @param box - the box carried by this linked box
   */
  public LinkedBox(Box box) {
    this.box = box;
  }
  
  /**
   * Returns the next linked box node
   * 
   * @return the next 
   */
  public LinkedBox getNext() {
    return this.next;
  }
  
  /**
   * Sets the link to the next linked box node
   * 
   * @param next - the next to set
   */
  public void setNext​(LinkedBox next) {
    this.next = next;
  }
  
  /**
   * Returns the data field box
   * 
   * @return the box
   */
  public Box getBox() {
    return this.box;
  }
  
  /**
   * Returns a String representation of this Linked box.
   * 
   * @return a String representation of this Linked MegaBlock object
   */
  @Override
  public String toString(){
    if(this.next == null) {
      return box.toString() + " -> END";
    }
    else {
      return box.toString() + " -> ";
    }
  }
    

  
}

















