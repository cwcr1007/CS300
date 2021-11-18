//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Person
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
 * This class models a person object with field name and isWaiting
 */
public class Person {
  private String name;
  private boolean isWaiting;

  /**
   * A one-argument constructor, which initializes the instance variables for the object
   * 
   * @param the name to be assign with Person
   */
  public Person(String name) {
    this.name = name;
    this.isWaiting = true;
  }

  /**
   * Accessor methods for the name of Person
   */
  public String getName() {
    return this.name;
  }

  /**
   * Accessor methods for the status of Person
   */
  public boolean isWaiting() {
    return this.isWaiting;
  }

  /**
   * Sets isWaiting to true if it is currently false, and to false if it is currently true\
   */
  public void toggleWaiting() {
    this.isWaiting = !this.isWaiting;
  }

  /**
   * Check if two Person have same name
   * @para and object o
   * 
   * @return true if two Person have same name, false otherwise
   */
  public boolean equals(Object o) {
    if (o instanceof Person) {
      return this.name.equals(((Person) o).name);
    }
    return false;
  }
}
