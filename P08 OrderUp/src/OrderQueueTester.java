//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: OrderQueueTester
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
 * This class is unit testing class, which tests the correctness of OrderQueue class and includes 
 * the main method.
 *
 */
public class OrderQueueTester {
  
  public static boolean OrderIteratorTest() {
    Order.resetIDGenerator();
    LinkedOrder a = new LinkedOrder(new Order("A", 3));
    LinkedOrder b = new LinkedOrder(new Order("B", 5));
    a.setNext(b);
    
    OrderIterator list = new OrderIterator(a);
    if (list.hasNext() != true) {
      System.out.println("Error in hasNext() method in OrderIterator");
      return false;
    }
    if (!list.next().equals(a.getOrder())) {
      System.out.println("Error in Next() method in OrderIterator");
      return false;
    }
    list.next();
    try {
      list.next();
    }
    catch(Exception e){
      System.out.println(e.getMessage());
    }
    return true;
  }
  
  /**
   * This class is unit testing class, which tests the correctness of enqueue method.
   *
   */
  public static boolean enqueueTest() {
    Order.resetIDGenerator();
    
    OrderQueue queue = new OrderQueue();
    queue.enqueue(new Order("fries", 2));
    queue.enqueue(new Order("shake", 1));
    queue.enqueue(new Order("burger", 3));
    
    if(!queue.toString().equals("1001: fries (2) -> 1002: shake (1) -> 1003: burger (3) -> END")) {
      System.out.println("Error in enqueue() method in OrderQueue");
      return false;
    }
    return true;
  }
  
  /**
   * This class is unit testing class, which tests the correctness of dequeue method.
   *
   */
  public static boolean dequeueTest() {
    Order.resetIDGenerator();
    
    OrderQueue queue = new OrderQueue();
    queue.enqueue(new Order("fries", 2));
    queue.enqueue(new Order("shake", 1));
    queue.enqueue(new Order("burger", 3));

    if (queue.dequeue().getPrepTime() != 2) {
      System.out.println("Error in dequeue() method in OrderQueue");
      return false;
    }
    queue.dequeue();
    queue.dequeue();
    System.out.println(queue);
    if (!queue.toString().equals("")) {
      System.out.println("Error in dequeue() method in OrderQueue");
      return false;
    }
    
    return true;
  }
  
  /**
   * This class is unit testing class, which tests the correctness of peek method.
   *
   */
  public static boolean peekTest() {
    Order.resetIDGenerator();
    
    OrderQueue queue = new OrderQueue();
    queue.enqueue(new Order("fries", 2));
    queue.enqueue(new Order("shake", 1));
    queue.enqueue(new Order("burger", 3));
    
    if (queue.peek().getPrepTime() != 2) {
      System.out.println("Error in peek() method in OrderQueue");
      return false;
    }
    return true;
  }
  
  /**
   * checks a test suite method to run all your test methods
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean runAllTests() {
    return OrderIteratorTest() && peekTest() && dequeueTest() && enqueueTest();
  }
  
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    System.out.println(runAllTests());
  }

}
