//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Rabbit
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

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class represents the Rabbit animal. It extends the Animal class. Rabbits can be added to
 * randomly positions to the CarrotPatch application. They can move in the patch. They watch out for
 * wolves and when they are clicked they make one hop towards a carrot. They also eat carrots. Every
 * created Rabbit has their own label in the format R#. The first created Rabbit is labelled R1, the
 * second R2, the third R3, and so on.
 *
 */
public class Rabbit extends Animal {
  private static final String RABBIT = "images" + File.separator + "rabbit.png";
  private static final String TYPE = "R"; // A String that represents the rabbit type
  private static int hopStep = 70; // one hop step
  private static int scanRange = 175; // scan range to watch out for threats
  private static int nextID = 1; // class variable that represents the identifier
  // of the next rabbit to be created
  private final int ID; // positive number that represents the order of this rabbit

  /**
   * Creates a new rabbit object located at a random position of the display window
   */
  public Rabbit() {
    // Set rabbit drawing parameters
    super(RABBIT);
    // Set rabbit identification fields
    ID = nextID;
    this.label = TYPE + ID; // String that identifies the current rabbit
    nextID++;
  }

  /**
   * Getter of Rabbit.scanRange static field
   * 
   * @return scan range of rabbit
   */
  public static int getScanRange() {
    return scanRange;
  }

  /**
   * Getter of Rabbit.hopStep static field
   * 
   * @return hop step of rabbit
   */
  public static int getHopStep() {
    return hopStep;
  }
  
  /**
   * Gets the first carrot in the patch. If the carrot is in the Rabbit
   * hopStep range, the rabbit eats it. It sets its position to the (x,y)
   * position of the carrot and the carrot will be removed from the Carrot Patch.
   * Otherwise, the rabbit moves one hopStep towards that carrot. If no carrot
   * found (meaning Carrots.getFirstCarrot() returns false),
   * the rabbit does nothing.
   */
  public void hopTowardsCarrot() {
    // get the first carrot
    int[] carrot = Carrots.getFirstCarrot();
    if (carrot != null) {
      double dist = this.distance(carrot[1], carrot[2]);
      if (dist <= Rabbit.hopStep) {
        this.setX(carrot[1]);
        this.setY(carrot[2]);
        Carrots.remove(carrot[0]);
      } else {
        this.setX((int) (this.getX() + (Rabbit.hopStep * (carrot[1] - this.getX()))/dist));
        this.setY((int) (this.getY() + (Rabbit.hopStep * (carrot[2] - this.getY()))/dist));
      }
     // TODO complete the implementation of this method
    }
  }
  /**
   * 
   */
  @Override
  public void mousePressed() {
    // TODO
    super.mousePressed();
    // call the mousePressed defined in the Animal super class
    this.hopTowardsCarrot();
    // call hopTowardsCarrot() method
  }
  
  /**
   * This method watches out for wolves. Checks if there is a wolf
   * in the Rabbit.scanRange of this Rabbit.
   *
   * @return true if the current rabbit is close to at least one wolf
   */
  public boolean watchOutForWolf() {
    // TODO complete the implementation of this method
    for (int i=0; i<processing.objects.size(); i++) {
      if (processing.objects.get(i) instanceof Wolf) {
        if (this.isClose((Animal) processing.objects.get(i), Rabbit.scanRange)) {
          return true;
        }
      }
    }
    return false;
    // Traverse the processing.objects arraylist checking
    // whether there is a wolf which is close by Rabbit.scanRange
    // of this rabbit.
  }
  
  /**
   * Watches out for a wolf and display a Warning message "WOLF!"
   * if there is any wolf in its neighborhood.
   */
  @Override
  public void action() {
    if (watchOutForWolf()) {
     // this.setScaredImage();
     processing.fill(0); // specify font color: black
     processing.text("WOLF!", this.getX(), this.getY() - this.image.height / 2 - 6);
} }

  
}
