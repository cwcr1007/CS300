//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: PlantCarrotsButton
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
 * This class models the PlantCarrotsButton object derived from button class of type GUIListener
 */
public class PlantCarrotsButton extends Button {
  /**
   * A two-argument constructor, which initializes the instance variables for the object
   * 
   * @param x represents the x-position of the button
   * @param y represents the xy-position of the button
   */
  public PlantCarrotsButton(float x, float y) {
    super("Plant Carrots", x, y);
  }
  
  
  /**
   * Implements the default behavior of this button when the mouse is pressed. This method must be
   * overridden in the sub-classes to implement a specific behavior if needed.
   */
  @Override
  public void mousePressed() {
    if (isMouseOver()) {
      // System.out.println("Plant Carrots Button Pressed");
      Carrots.plant();
    }
  }
}
