//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Room
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

import java.util.ArrayList;

/**
 * This class models a Room
 */
public class Room {
  private static ArrayList<String> names = new ArrayList<String>();

  /**
   * Returns the current list of Room names as an array of Strings
   * 
   * @return an array of Strings
   */
  public static String[] getNames() {
    String arr[] = new String[names.size()];
    for (int i = 0; i < names.size(); i++) {
      arr[i] = names.get(i);
    }
    return arr;
  }

  private String name;
  private Person occupants[];
  private int currentOccupancy;

  /**
   * A two-argument constructor, which initializes the instance variables for the object
   * 
   * @param name     of the room
   * @param capacity is the maximum number of Persons who can occupy the room without ​ social
   *                 distancing enforced
   * @throws IllegalArgumentException with a descriptive If the provided capacity is 0 or less, or
   *                                  if the name is already in use by another Room object
   */
  public Room(String name, int capacity) {
    if (capacity <= 0) {
      throw new IllegalArgumentException("Warning! Capacity is 0 or less.");
    }
    for (int i = 0; i < names.size(); i++) {
      if (names.get(i) == name) {
        throw new IllegalArgumentException("Warning! Name is already in use by another Room.");
      }
    }
    this.name = name;
    this.occupants = new Person[capacity];
    this.currentOccupancy = 0;
    names.add(this.name);
  }

  /**
   * Access the name of the room
   * 
   * @return the name of this room
   */
  public String getName() {
    return this.name;
  }

  /**
   * Access the current occupancy of this room
   * 
   * @return the current occupancy
   */
  public int getOccupancy() {
    return this.currentOccupancy;
  }

  /**
   * Get the number of people allowed in Room under COVID protocols
   * 
   * @return the capacity of Room
   */
  public int getCOVIDCapacity() {
    return (this.occupants.length + 1)/2;
  }

  /**
   * Get the number of people allowed in Room under normal condition
   * 
   * @return the capacity of Room
   */
  public int getCapacity() {
    return this.occupants.length;
  }

  /**
   * Returns true if and only if the provided Person is present in the Room’s occupants array
   * 
   * @param the Person object
   * @return boolean
   */
  public boolean contains(Person p) {
    for (int i = 0; i < occupants.length; i++) {
      if (p.equals(occupants[i])) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns true if and only if the provided Person was successfully added to the room.
   * 
   * @param Person object
   * @return boolean if Person is added
   * @throws IllegalArgumentException if the Person passed as input to this method is null ​ or is
   *                                  already in the room
   */
  public boolean checkIn(Person in) {
    if (in == null) {
      throw new IllegalArgumentException("Warning! The person passed in is null.");
    }
    if (this.contains(in)) {
      throw new IllegalArgumentException("Warning! The person is already in the Room.");
    }
    if (this.currentOccupancy == this.getCOVIDCapacity()) {
      return false;
    } else {
      for (int i = 0; i < occupants.length; i += 2) {
        if (occupants[i] == null) {
          occupants[i] = in;
          this.currentOccupancy++;
          in.toggleWaiting();
          return true;
        }
      }
    }
    return true;
  }

/**
 * ​Returns true if and only if the provided Person was successfully removed from the Room.
 * 
 * @param Person object
 * @return boolean if the Person was removed
 * @throws IllegalArgumentException with descriptive message if the Person passed as input to 
 *   this method is null
 */
  public boolean checkOut(Person out) {
    if (out == null) {
      throw new IllegalArgumentException("Warning! The provided Person is null.");
    }
    if (this.contains(out)==false) {
      return false;
    }
    else {
      for (int i=0; i<occupants.length; i += 2) {
        if (occupants[i] != null) {
          if (occupants[i].equals(out)) {
            occupants[i] = null;
            this.currentOccupancy--;
            out.toggleWaiting();
            return true;
          }
        }
      }
    }
    return true;
  }
  
  /**
   * Return the string of this Room and its occupants in certain format
   * 
   * @return a String representation of this Room and its occupants
   */
  @Override
  public String toString() {
    String output = this.name + "\n===";
    
    // System.out.println(occupants.length);
    for (int i=0; i<occupants.length; i++) {
      if (occupants[i] != null) {
        output = output + "\n" + occupants[i].getName();
      }
      else {
        output = output + "\n-";
      }
    }
    return output;
  }
  
  public static void clearRooms(){
    names.clear();
  }
}


