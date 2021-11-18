//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Occupancy Tester
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
 * Occupancy Tester class implement all the method to check the correctness of operations in Person
 * and Room class.
 */
public class OccupancyTester {

  /**
   * Checks the correctness the constructor, accessors, mutator, and equals method of Person ​class
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testPerson() {
    Person p1 = new Person("Joyce");
    Person p2 = new Person("Bob");

    if (p1.getName() != "Joyce" || p2.getName() != "Bob") {
      System.out.println("Problem detected in getName(): get wrong name of Person.");
      return false;
    }

    if (p1.isWaiting() != true || p2.isWaiting() != true) {
      System.out.println("Problem detected in isWaiting(): expect return true but get false");
      return false;
    }

    p1.toggleWaiting();
    p2.toggleWaiting();
    p2.toggleWaiting();
    if (p1.isWaiting() != false) {
      System.out
          .println("Problem detected in toggleWaiting(): expect to return false but get " + "true");
      return false;
    }

    if (p2.isWaiting() != true) {
      System.out
          .println("Problem detected in toggleWaiting(): expect to return true but get " + "false");
      return false;
    }

    Person p3 = new Person("Bob");

    if (p3.equals(p2) != true) {
      System.out.println("Problem detected in equals(): two Person object with same name. "
          + "Expect return true but get false");
      return false;
    }

    if (p3.equals(p1) != false) {
      System.out.println("Problem detected in equals(): two Person object with different name. "
          + "Expect return false but get true");
      return false;
    }

    if (p3.equals("Bob") != false) {
      System.out.println("Problem detected in equals(): one Person object and a string object."
          + "Expect return false but get true.");
      return false;
    }
    return true;
  }

  /**
   * Checks the correctness the constructor​ of Room ​class
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRoomConstructor() {
    Room.clearRooms();
    Room r1 = new Room("r1", 5);
    Room r2 = new Room("r2", 4);

    try {
      Room r3 = new Room("r2", 7); // Name is already used by another room
      System.out.println("Problem detected in Room(): Expect to throw IllegalArgumentException "
          + "due to repeat name, but no exception is thrown.");
      return false;
    } catch (IllegalArgumentException e1) {
      System.out.println(e1.getMessage());
    }

    try {
      Room r4 = new Room("r4", 0); // illegal capacity
      System.out.println("Problem detected in Room(): Expect to throw IllegalArgumentException "
          + "due to illegal capacity, but no exception is thrown.");
      return false;
    } catch (IllegalArgumentException e2) {
      System.out.println(e2.getMessage());
    }
    return true;
  }

  /**
   * Checks the correctness the accessor methods​ of Room ​class
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRoomAccessors() {
    Room.clearRooms();
    Room r1 = new Room("r1", 4);
    Room r2 = new Room("r2", 5);

    // Checks getName()
    if (r1.getName() != "r1") {
      System.out.println("Problem detected in getName(): Expect to return String 'r1' " + "but get "
          + r1.getName());
      return false;
    }
    if (r2.getName() != "r2") {
      System.out.println("Problem detected in getName(): Expect to return String 'r2' " + "but get "
          + r2.getName());
      return false;
    }
    // Checks getNames()
    String[] arr = new String[] {"r1", "r2"};
    if (Room.getNames().equals(arr)) {
      System.out.println("Problem detected in getNames(): expect to return String array '[r1, r2]'"
          + " but get" + Arrays.toString(Room.getNames()));
      return false;
    }

    // Checks getCapacity()
    if (r1.getCapacity() != 4) {
      System.out.println("Problem detected in getCapacity(): expect to return int 4, but return "
          + r1.getCapacity());
      return false;
    }

    // Checks getCOVIDCapacity()
    if (r2.getCOVIDCapacity() != 3) {
      System.out.println("Problem detected in getCOVIDCapacity(): expect to return 2, but return "
          + r2.getCOVIDCapacity());
      return false;
    }

    // Checks getOccupancy()
    if (r1.getOccupancy() != 0 || r2.getOccupancy() != 0) {
      System.out.println("Problem detected in getOccupancy(): expect to return 0, but return "
          + r2.getOccupancy());
      return false;
    }
    // Checks contains()
    return true;
  }

  /**
   * Checks the correctness the check-in functionality and its effects on both the ​Room ​and the
   * ​Person ​being checked in
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRoomCheckIn() {
    Room.clearRooms();
    Room r1 = new Room("CS 1240", 9);
    
    Person p1 = new Person("Bob");
    Person p2 = new Person("Joyce");

    if (r1.checkIn(p1) != true) {
      System.out.println("Problem deteced in checkIn(): expect to return true when check a Person"
          + "object in an empty array, but get false.");
      return false;
    }
    if (p1.isWaiting() != false) {
      System.out.println("Problem deteced in checkIn(): expect to return false when check a Person"
          + "object in an empty room, but get true.");
      return false;
    }
    if (r1.getOccupancy() != 1) {
      System.out.println("Problem deteced in checkIn(): expect to return 1 when check a Person"
          + "object in an empty room, but get " + r1.getOccupancy());
      return false;
    }
    if (r1.contains(p1) != true) {
      System.out.println("Problem deteced in checkIn(): expect to return true when check a Person"
          + "object in an empty room, but get false.");
      return false;
    }
    
    try {
      r1.checkIn(p2);
      r1.checkIn(p1);
      System.out.println("Problem deteced in checkIn(): Expect to throw IllegalArgumentException"
          + "due to check in Person who already in the room.");
      return false;
    } catch (IllegalArgumentException e1) {
      System.out.println(e1.getMessage());
    }
    
    try {
      r1.checkIn(null);
      System.out.println("Problem deteced in checkIn(): Expect to throw IllegalArgumentException"
          + "due to check in null.");
      return false;
    } catch (IllegalArgumentException e2) {
      System.out.println(e2.getMessage());
    }
    return true;
  }

  /**
   * Checks the correctness of the check-out functionality and its effects on both the Room and the
   * Person being checked out
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRoomCheckOut() {
    Room.clearRooms();
    Room r1 = new Room("CS 1240", 9);
    
    Person p1 = new Person("Mouna");
    Person p2 = new Person("Hobbes");
    Person p3 = new Person("Bob");
    Person p4 = new Person("Michelle");
    Person p5 = new Person("Sulong");
    
    r1.checkIn(p1);
    r1.checkIn(p2);
    r1.checkIn(p3);
    r1.checkIn(p4);
    r1.checkIn(p5);
    
    r1.checkOut(p3);
    if (r1.contains(p3) != false) {
      System.out.println("Problem deteced in checkOut(): expect to return false, but return true");
      return false;
    }
    if (p3.isWaiting() != true) {
      System.out.println("Problem deteced in checkOut(): checked out Person's status is waiting. "
          + "Expect to return true, but return false");
      return false;
    }
    if (r1.getOccupancy() != 4) {
      System.out.println("Problem deteced in checkOut(): There are 5 people in room, after checked"
          + " out one Person. We expect to return 4, but return " + r1.getOccupancy());
      return false;
    }
    try {
      r1.checkOut(null);
      System.out.println("Problem deteced in checkOut(): Expect to throw IllegalArgumentException"
          + "due to check out null.");
      return false;
    } catch (IllegalArgumentException e1) {
      System.out.println(e1.getMessage());
    }
    
    Person p6 = new Person("Joyce");
    if (r1.checkOut(p6) != false){
      System.out.println("Problem deteced in checkOut(): Try to check out Person not in the room."
          + "Expect to return false, but return ture");
      return true;
    }
    return true;
  }


  /**
   * Checks the correctness of the result of Room’​stoString method
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRoomToString() {
    Room.clearRooms();
    Room r1 = new Room("CS300", 3);
    
    String correct = "CS300\n===\n-\n-\n-";
    if (!r1.toString().equals(correct)) {
      
      System.out.println("Problem detected in toString(): expect\n" + correct + "\nbut return\n"
          + r1.toString());
      return false;
    }
    return true;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    System.out.println("testPerson() is " + testPerson() + "\n");
    System.out.println("testRoomConstructor() is " + testRoomConstructor() + "\n");
    System.out.println("testRoomAccessors() is " + testRoomAccessors() + "\n");
    
    System.out.println("testRoomCheckIn() is " + testRoomCheckIn());
    System.out.println("testRoomCheckOut() is " + testRoomCheckOut());
    System.out.println("testRoomToString() is " + testRoomToString());

  }

}
