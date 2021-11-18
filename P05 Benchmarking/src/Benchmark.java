//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Benchmark
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
import java.io.FileWriter; // or java.io.PrintWriter
import java.io.IOException;

/**
 * This class contain the compareLoadData, compareRemove, createResultsFile methods and main method.
 *
 */
public class Benchmark {
  /**
   * Runs both classes’ loadData() implementations on the same text file. Tracks the time spent in 
   * milliseconds to complete each loadData().
   * 
   * @param f   File object
   * @param s   SimpleBag object
   * @param c   CleverBag object
   * @return a formatted String with the elapsed times for each of the bag types.
   */
  public static String compareLoadData(File f, SimpleBag s, CleverBag c) {
    long simpleStart = System.currentTimeMillis();
    s.loadData(f);
    long simpleEnd = System.currentTimeMillis();
    long simpleBagLoadTime = simpleEnd - simpleStart;
    
    long cleverStart = System.currentTimeMillis();
    c.loadData(f);
    long cleverEnd = System.currentTimeMillis();
    long cleverBagLoadTime = cleverEnd - cleverStart;
    
    String output = "load:\t" + simpleBagLoadTime + "\t" + cleverBagLoadTime + "\n";
    return output;
  }
  
  /**
   * Runs both classes’ removeRandom() method n times. Tracks the time spent in milliseconds to 
   * complete each type of remove
   * 
   * @param n   int type
   * @param s   SimpleBag object
   * @param c   CleverBag object
   * @return a formatted string with n and the elapsed times for each of the bag types.
   */
  public static String compareRemove(int n, SimpleBag s, CleverBag c) {
    long simpleStart = System.currentTimeMillis();
    for (int i=0; i<n; i++) {
      s.removeRandom();
    }
    long simpleEnd = System.currentTimeMillis();
    long simpleBagRemoveTime = simpleEnd - simpleStart;
    
    long cleverStart = System.currentTimeMillis();
    for (int i=0; i<n; i++) {
      c.removeRandom();
    }
    long cleverEnd = System.currentTimeMillis();
    long cleverBagRemoveTime = cleverEnd - cleverStart;
    
    String output = n + "\t" + simpleBagRemoveTime + "\t" + cleverBagRemoveTime + "\n";
    return output;
  }
  
  /**
   * Creates one instance each of a SimpleBag and a CleverBag. Calls compareLoadData() to compare 
   * the two different data loads using the in parameter. Calls compareRemove() on each of the 
   * provided nValues to compare the two different remove implementations. Writes the results of 
   * the data load comparison followed by the remove comparisons to a file specified by the out 
   * parameter. Handles any exceptions raised by the methods it uses.
   * 
   * @param in      File object
   * @param out     File object
   * @param nValues int[] array
   */
  public static void createResultsFile(File in, File out, int[] nValues) {
    SimpleBag s = new SimpleBag(0);
    CleverBag c = new CleverBag(0);
    
    String[] output = new String[100];
    int size = 0;
    
    output[0] = compareLoadData(in, s, c);
    size++;
    
    for (int i=0; i<nValues.length; i++) {
      output[i+1] = compareRemove(nValues[i], s, c);
      size++;
    }
    try {
      FileWriter f = new FileWriter(out);
      for (int i=0; i<size; i++) {
        f.write(output[i]);
      }
      f.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
      return;
    }
  }
  
  public static void main(String[] args) {
    int[] n = {10, 100, 1000, 10000};
    File f = new File("bob.txt");
    Benchmark.createResultsFile(new File("frank.txt"), f, n);
//    SimpleBag s = new SimpleBag(0);
//    CleverBag c = new CleverBag(0);
//    System.out.println(Benchmark.compareLoadData(new File("frank.txt"), s, c));
//    System.out.println(Benchmark.compareRemove(1000, s, c));
  }

}
