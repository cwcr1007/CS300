//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: OrderIterator
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

import java.util.NoSuchElementException;

/**
 * This class create OrderIterator object.
 * @author bobdai
 *
 */
public class OrderIterator implements java.util.Iterator<Order>{  
  private LinkedOrder current = null;
  
  /**
   * Constructor, initializes current to the provided starting LinkedOrder. Does not care whether 
   * the argument value is null.
   * 
   * @param start - LinkedOrder object
   */
  public OrderIterator(LinkedOrder start) {
    current = start;
  }
  
  /**
   * Returns true if and only if the iteration has more orders
   * 
   * @return boolean
   */
  @Override
  public boolean hasNext() {
    // TODO Auto-generated method stub
    if (current != null) {
      return true;
    }
    return false;
  }

  /**
   * Returns the next Order and updates the current field appropriately.
   * 
   * @return Order object
   * @throws a NoSuchElementException with a descriptive error message if the iteration does not 
   * have more orders to return.
   */
  @Override
  public Order next() throws NoSuchElementException {
    // TODO Auto-generated method stub
    if (!this.hasNext()) {
      throw new NoSuchElementException("The iteration does not have more orders to return");
    }
    else {
      LinkedOrder output = current;
      current = current.getNext();
      return output.getOrder();
    }
  }

}














