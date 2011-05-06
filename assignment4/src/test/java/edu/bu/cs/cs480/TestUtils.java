/**
 * TestUtils.java - utilities for JUnit tests
 */
package edu.bu.cs.cs480;

import java.util.Random;


/**
 * Utilities for JUnit tests.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class TestUtils {

  /** A random number generator. */
  private static final Random random = new Random();

  /**
   * Prints the stack trace of the specified throwable to stderr, then fails the
   * current test.
   * 
   * @param throwable
   *          The throwable which was the cause of the current test failure.
   */
  public static void fail(final Throwable throwable) {
    throwable.printStackTrace(System.err);
    junit.framework.Assert.fail(throwable.getLocalizedMessage());
  }

  /**
   * Returns a new matrix with random entries.
   * 
   * @return A new matrix with random entries.
   */
  public static Matrix4x4 randomMatrix() {
    final Matrix4x4 result = new Matrix4x4();
    for (int i = 0; i < 4; ++i) {
      for (int j = 0; j < 4; ++j) {
        result.set(i, j, random.nextDouble());
      }
    }
    return result;
  }
}
