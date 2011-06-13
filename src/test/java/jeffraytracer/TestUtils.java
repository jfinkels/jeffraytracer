/**
 * TestUtils.java - utilities for JUnit tests
 */
package jeffraytracer;

import java.util.Random;

import org.apache.log4j.Logger;

/**
 * Utilities for JUnit tests.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public final class TestUtils {

  /** A random number generator. */
  private static final Random RANDOM = new Random();
  /** The logger for this class. */
  private static final transient Logger LOG = Logger
      .getLogger(TestUtils.class);

  /**
   * Prints the stack trace of the specified throwable to stderr, then fails
   * the current test.
   * 
   * @param throwable
   *          The throwable which was the cause of the current test failure.
   */
  public static void fail(final Throwable throwable) {
    LOG.error("Failed due to error.", throwable);
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
        result.set(i, j, RANDOM.nextDouble());
      }
    }
    return result;
  }

  /** Prevents instantiation of this class. */
  private TestUtils() {
    // intentionally unimplemented
  }
}