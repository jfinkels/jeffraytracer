/**
 * MainTest.java - test for the Main class
 */
package edu.bu.cs.cs480.main;

import org.junit.Test;

/**
 * Test for the Main class.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class MainTest {

  /** The directory containing the model files for testing. */
  public static final String TESTDIR = "src/test/resources/edu/bu/cs/cs480/";
  /** The file containing a test model. */
  public static final String TESTFILE = TESTDIR + "model1.dat";

  /**
   * Test method for {@link edu.bu.cs.cs480.main.Main#main(java.lang.String[])}
   * .
   */
  @Test
  public void testMain() {
    Main.main(new String[] { TESTFILE });
  }

}
