//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: MovieTreeTester
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
import java.util.NoSuchElementException;

/**
 * This class checks the correctness of the implementation of the methods defined in the class
 * MovieTree.
 *
 */
public class MovieTreeTester {

  /**
   * Checks the correctness of the implementation of both addMovie() and toString() methods
   * implemented in the MovieTree class. This unit test considers at least the following scenarios.
   * (1) Create a new empty MovieTree, and check that its size is 0, it is empty, and that its
   * string representation is an empty string "". (2) try adding one movie and then check that the
   * addMovie() method call returns true, the tree is not empty, its size is 1, and the .toString()
   * called on the tree returns the expected output. (3) Try adding another movie which is smaller
   * that the movie at the root, (4) Try adding a third movie which is greater than the one at the
   * root, (5) Try adding at least two further movies such that one must be added at the left
   * subtree, and the other at the right subtree. For all the above scenarios, and more, double
   * check each time that size() method returns the expected value, the add method call returns
   * true, and that the .toString() method returns the expected string representation of the
   * contents of the binary search tree in an increasing order from the smallest to the greatest
   * movie with respect to year, rating, and then name. (6) Try adding a movie already stored in the
   * tree. Make sure that the addMovie() method call returned false, and that the size of the tree
   * did not change.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddMovieToStringSize() {
    String expect = null;
    // case 1
    MovieTree bst = new MovieTree();
    if (bst.size() != 0 || bst.isEmpty() != true || !bst.toString().equals("")) {
      System.out.println("Error: expect size = 0; it is empty; empty string \"\".");
      return false;
    }
    // case 2
    if (bst.addMovie(new Movie(2018, 6.5, "Airplanes")) != true) {
      System.out.println("Error: expect addMovie return true");
      return false;
    }
    expect = "[(Year: 2018) (Rate: 6.5) (Name: Airplanes)]\n";
    if (bst.size() != 1 || bst.isEmpty() != false || !bst.toString().equals(expect)) {
      System.out.println("Error: expect size = 1; it's empty; string = " + expect);
      return false;
    }
    // case 3
    bst.addMovie(new Movie(1988, 9.5, "Best"));
    expect = "[(Year: 1988) (Rate: 9.5) (Name: Best)]\n"
        + "[(Year: 2018) (Rate: 6.5) (Name: Airplanes)]\n";
    if (bst.size() != 2 || !bst.toString().equals(expect)) {
      System.out.println("Error: expect size = 2; string = \n" + expect);
      return false;
    }
    // case 4
    bst.addMovie(new Movie(2018, 8.5, "Cats"));
    expect = "[(Year: 1988) (Rate: 9.5) (Name: Best)]\n"
        + "[(Year: 2018) (Rate: 6.5) (Name: Airplanes)]\n"
        + "[(Year: 2018) (Rate: 8.5) (Name: Cats)]\n";
    if (bst.size() != 3 || !bst.toString().equals(expect)) {
      System.out.println("Error: expect size = 3; string = " + expect);
      return false;
    }
    // case 5
    bst.addMovie(new Movie(2018, 7.5, "Earth"));
    bst.addMovie(new Movie(2018, 6.0, "Yes"));
    expect =
        "[(Year: 1988) (Rate: 9.5) (Name: Best)]\n" + "[(Year: 2018) (Rate: 6.0) (Name: Yes)]\n"
            + "[(Year: 2018) (Rate: 6.5) (Name: Airplanes)]\n"
            + "[(Year: 2018) (Rate: 7.5) (Name: Earth)]\n"
            + "[(Year: 2018) (Rate: 8.5) (Name: Cats)]\n";
    if (bst.size() != 5 || !bst.toString().equals(expect)) {
      System.out.println("Error: expect size = 3; string = " + expect);
      return false;
    }
    // case 6
    if (bst.addMovie(new Movie(2018, 6.0, "Yes")) != false || bst.size() != 5) {
      System.out.println("Error: expect add return false; size = 5");
      return false;
    }
    return true;
  }

  /**
   * This method checks mainly for the correctness of the MovieTree.contains() method. It must
   * consider at least the following test scenarios. (1) Create a new MovieTree. Then, check that
   * calling the contains() method on an empty MovieTree returns false. (2) Consider a MovieTree of
   * height 3 which contains at least 5 movies. Then, try to call contains() method to search for
   * the movie having a match at the root of the tree. (3) Then, search for a movie at the right and
   * left subtrees at different levels considering successful and unsuccessful search operations.
   * Make sure that the contains() method returns the expected output for every method call.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testContains() {
    // case 1
    MovieTree bst = new MovieTree();
    if (bst.contains(2018, 6.5, "Airplanes") != false) {
      System.out.println("Error: expect false");
      return false;
    }
    // case 2
    bst.addMovie(new Movie(2018, 6.5, "Airplanes"));
    bst.addMovie(new Movie(1988, 9.5, "Best"));
    bst.addMovie(new Movie(2018, 8.5, "Cats"));
    bst.addMovie(new Movie(2018, 6.0, "Yes"));
    bst.addMovie(new Movie(2018, 7.5, "Earth"));
    if (bst.contains(2018, 6.5, "Airplanes") != true) {
      System.out.println("Error: expect true");
      return false;
    }
    // case 3
    if (bst.contains(2018, 7.5, "Earth") != true) {
      System.out.println("Error: expect true");
      return false;
    }
    if (bst.contains(1988, 9.5, "Best") != true) {
      System.out.println("Error: expect true");
      return false;
    }
    if (bst.contains(2017, 5.5, "Dogs") != false) {
      System.out.println("Error: expect false");
      return false;
    }
    if (bst.contains(2017, 5.5, "Dogs") != false) {
      System.out.println("Error: expect false");
      return false;
    }
    return true;
  }

  /**
   * Checks for the correctness of MovieTree.height() method. This test must consider several
   * scenarios such as, (1) ensures that the height of an empty movie tree is zero. (2) ensures that
   * the height of a tree which consists of only one node is 1. (3) ensures that the height of a
   * MovieTree with the following structure for instance, is 4. (*) / \ (*) (*) \ / \ (*) (*) (*) /
   * (*)
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testHeight() {
    // case 1
    MovieTree bst = new MovieTree();
    if (bst.height() != 0) {
      System.out.println("Error: expect 0");
      return false;
    }
    // case 2
    bst.addMovie(new Movie(2018, 6.5, "Airplanes"));
    if (bst.height() != 1) {
      System.out.println("Error: expect 1");
      return false;
    }
    // case 3
    bst.addMovie(new Movie(1988, 9.5, "Best"));
    bst.addMovie(new Movie(2018, 8.5, "Cats"));
    bst.addMovie(new Movie(2018, 6.0, "Yes"));
    bst.addMovie(new Movie(2018, 7.5, "Earth"));
    bst.addMovie(new Movie(2019, 7.5, "Good"));
    bst.addMovie(new Movie(2020, 7.5, "Nice"));
    if (bst.height() != 4) {
      System.out.println("Error: expect 4");
      return false;
    }
    return true;
  }

  /**
   * Checks for the correctness of MovieTree.getBestMovie() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetBestMovie() {
    MovieTree bst = new MovieTree();

    bst.addMovie(new Movie(2018, 6.5, "Airplanes"));
    bst.addMovie(new Movie(1988, 9.5, "Best"));
    bst.addMovie(new Movie(2018, 8.5, "Cats"));
    bst.addMovie(new Movie(2018, 6.0, "Yes"));
    bst.addMovie(new Movie(2017, 5.5, "Dogs"));
    bst.addMovie(new Movie(2018, 7.5, "Earth"));
    bst.addMovie(new Movie(2018, 6.0, "Flights"));
    bst.addMovie(new Movie(2015, 8.5, "Grand Parents"));

    String expect = "[(Year: 2018) (Rate: 8.5) (Name: Cats)]";
    try {
      if (!bst.getBestMovie().toString().equals(expect)) {
        System.out.println("Error: expect " + expect);
        return false;
      }
    } catch(Exception e) {
      System.out.println(e.getMessage());
      return false;
    }
    return true;
  }

  /**
   * Checks for the correctness of MovieTree.lookup() method. This test must consider at least 3
   * test scenarios. (1) Ensures that the MovieTree.lookup() method throws a NoSuchElementException
   * when called on an empty tree. (2) Ensures that the MovieTree.lookup() method returns an array
   * list which contains all the movies satisfying the search criteria of year and rating, when
   * called on a non empty movie tree with one match, and two matches and more. Vary your search
   * criteria such that the lookup() method must check in left and right subtrees. (3) Ensures that
   * the MovieTree.lookup() method throws a NoSuchElementException when called on a non-empty movie
   * tree with no search results found.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLookup() {
    MovieTree bst = new MovieTree();
    // case 1
    try {
      bst.lookup(2018, 5);
      return false;
    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
    }
    // case 2
    bst.addMovie(new Movie(2018, 6.5, "Airplanes"));
    bst.addMovie(new Movie(1988, 9.5, "Best"));
    bst.addMovie(new Movie(2018, 8.5, "Cats"));
    bst.addMovie(new Movie(2018, 6.0, "Yes"));
    bst.addMovie(new Movie(2018, 7.5, "Earth"));
    bst.addMovie(new Movie(2019, 7.5, "Good"));
    bst.addMovie(new Movie(2020, 7.5, "Nice"));

    if (!bst.lookup(2018, 8.5).toString().equals("[[(Year: 2018) (Rate: 8.5) (Name: Cats)]]")) {
      System.out.println("Error: expect [[(Year: 2018) (Rate: 8.5) (Name: Cats)]]");
      return false;
    }
    String expect =
        "[[(Year: 2018) (Rate: 8.5) (Name: Cats)], [(Year: 2018) (Rate: 7.5) (Name: Earth)]]";
    if (!bst.lookup(2018, 7.5).toString().equals(expect)) {
      System.out.println("Error: expect" + expect);
      return false;
    }
    expect = "[[(Year: 2018) (Rate: 6.5) (Name: Airplanes)], "
        + "[(Year: 2018) (Rate: 8.5) (Name: Cats)], "
        + "[(Year: 2018) (Rate: 7.5) (Name: Earth)]]";
    if (!bst.lookup(2018, 6.5).toString().equals(expect)) {
      System.out.println("Error: expect" + expect);
      return false;
    }

    // case 3
    try {
      bst.lookup(2018, 10);
      return false;
    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
    }

    return true;
  }

  /**
   * Calls the test methods
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("testAddMovieToStringSize() is " + testAddMovieToStringSize());
    System.out.println("testContains() is " + testContains());
    System.out.println("testGetBestMovie() is " + testGetBestMovie());
    System.out.println("testHeight() is " + testHeight());
    System.out.println("testLookup() is " + testLookup());
  }
}
