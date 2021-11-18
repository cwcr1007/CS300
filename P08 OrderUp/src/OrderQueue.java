//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: OrderQueue
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
 * This class creates an OrderQueue object.
 * 
 * @author bobdai
 *
 */
public class OrderQueue implements QueueADT<Order>, Iterable<Order> {

  private LinkedOrder front = null;
  private LinkedOrder back = null;
  private int size = 0;

  /**
   * Creates and returns a new OrderIterator beginning with the current value of front
   * 
   * @return Iterator object
   */
  public java.util.Iterator<Order> iterator() {
    return new OrderIterator(front);
  }
  
  /**
   * Adds a new LinkedOrder containing newElement to the back of the queue, updating the size
   * variable and front/back references appropriately
   * 
   * @param newElement - Order
   */
  @Override
  public void enqueue(Order newElement) {
    LinkedOrder newLinked = new LinkedOrder(newElement);
    
    // case 1: empty queue
    if (this.isEmpty()) {
      this.front = newLinked;
      this.back = newLinked;
    }
    // case 2: non-empty queue
    else {
      this.back.setNext(newLinked);
      this.back = newLinked;
    }
    size++;
  }

  /**
   * Removes the next LinkedOrder from the front of the queue and returns its Order, updating the
   * size variable and front/back references appropriately
   * 
   * @return Order object
   * @throws a NoSuchElementException if the queue is empty
   */
  @Override
  public Order dequeue() {
    Order returnVal = null;
    
    if(this.isEmpty()) {
      throw new NoSuchElementException("the queue is empty");
    }
    
    // case 1: only one element queue
    if (size == 1) {
      returnVal = this.front.getOrder();
      this.front = null;
      this.back = null;
    }
    // case 2: more than one element queue
    else {
      returnVal = this.front.getOrder();
      this.front = this.front.getNext();
    }
    
    size--;
    return returnVal;
  }

  /**
   * Returns the Order from the LinkedOrder at the front of the queue without removing the
   * LinkedOrder from the queue
   * 
   * @throws a NoSuchElementException if the queue is empty
   */
  @Override
  public Order peek() {
    // TODO Auto-generated method stub
    return this.front.getOrder();
  }

  /**
   * Returns true if and only if the queue is empty
   * 
   * @return boolean
   */
  @Override
  public boolean isEmpty() {
    // TODO Auto-generated method stub
    if (this.size == 0) {
      return true;
    }
    return false;
  }

  /**
   * Creates and returns a String representation of this OrderQueue using an enhanced-for loop. For
   * example, a queue with three Orders * might look like this: 1001: fries (2) -> 1002: shake (1)
   * -> 1003: burger (3) -> END
   *
   * @return A String representation of the queue
   */
  @Override
  public String toString() {
    if (this.size == 0)
      return "";
    String qString = "";
    for (Order o : this) {
      qString += o.toString();
      qString += " -> ";
    }
    qString += "END";
    return qString;
  }

}
