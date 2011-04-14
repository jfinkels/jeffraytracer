/**
 * TestUtils.java - utilities for JUnit tests
 */
package edu.bu.cs.cs480.main;

/**
 * Utilities for JUnit tests.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class TestUtils {

  /**
   * Prints the stack trace of the specified throwable to stderr, then fails
   * the current test.
   * 
   * @param throwable
   *          The throwable which was the cause of the current test failure.
   */
  public static void fail(final Throwable throwable) {
    throwable.printStackTrace(System.err);
    junit.framework.Assert.fail(throwable.getLocalizedMessage());
  }
}
