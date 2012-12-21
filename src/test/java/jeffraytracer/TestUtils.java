/**
 * TestUtils.java - utilities for JUnit tests
 * 
 * Copyright 2011 Jeffrey Finkelstein
 * 
 * This file is part of jeffraytracer.
 * 
 * jeffraytracer is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 * 
 * jeffraytracer is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * jeffraytracer. If not, see <http://www.gnu.org/licenses/>.
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
    org.junit.Assert.fail(throwable.getLocalizedMessage());
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
