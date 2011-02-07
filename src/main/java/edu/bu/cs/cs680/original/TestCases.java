/**
 * TestCases.java
 */
package edu.bu.cs.cs480;

import java.util.Iterator;

/**
 * An iterator over polygon test cases.
 * 
 * @author Jeffrey Finkelstein <jeffreyf>
 * @since Spring 2011
 */
public class TestCases implements Iterator<Polygon> {

  /** The total number of test cases. */
  public static final int NUM_TEST_CASES = 12;

  /** The current test case. */
  private int currentTestCase = 0;

  /**
   * Always returns {@code true}.
   * 
   * @return {@code true}
   * @see java.util.Iterator#hasNext()
   */
  @Override
  public boolean hasNext() {
    return true;
  }

  /**
   * Gets a new polygon which is the next test case.
   * 
   * Cycles through {@value #NUM_TEST_CASES} test cases.
   * 
   * @return A new polygon which is the next test case.
   * @see java.util.Iterator#next()
   */
  @Override
  public Polygon next() {
    final Polygon polygon = new Polygon();

    switch (this.currentTestCase) {
    case 0:
      polygon.addVert(50, 50);
      polygon.addVert(50, 100);
      polygon.addVert(100, 100);
      polygon.addVert(100, 50);
      break;

    case 1:
      polygon.addVert(50, 50);
      polygon.addVert(250, 50);
      polygon.addVert(250, 200);
      polygon.addVert(150, 125);
      polygon.addVert(50, 200);
      break;

    case 2:
      polygon.addVert(50, 200);
      polygon.addVert(50, 50);
      polygon.addVert(200, 50);
      polygon.addVert(200, 100);
      polygon.addVert(100, 100);
      polygon.addVert(100, 200);
      break;

    case 3:
      polygon.addVert(50, 50);
      polygon.addVert(200, 50);
      polygon.addVert(200, 200);
      polygon.addVert(150, 200);
      polygon.addVert(150, 100);
      polygon.addVert(50, 100);
      break;

    case 4:
      polygon.addVert(50, 100);
      polygon.addVert(100, 100);
      polygon.addVert(100, 50);
      polygon.addVert(150, 50);
      polygon.addVert(150, 100);
      polygon.addVert(200, 100);
      polygon.addVert(200, 150);
      polygon.addVert(150, 150);
      polygon.addVert(150, 200);
      polygon.addVert(100, 200);
      polygon.addVert(100, 150);
      polygon.addVert(50, 150);
      break;

    case 5:
      polygon.addVert(50, 250);
      polygon.addVert(50, 150);
      polygon.addVert(100, 150);
      polygon.addVert(150, 50);
      polygon.addVert(200, 200);
      polygon.addVert(250, 50);
      polygon.addVert(275, 150);
      polygon.addVert(275, 250);
      break;

    case 6:
      polygon.addVert(50, 50);
      polygon.addVert(50, 200);
      polygon.addVert(75, 200);
      polygon.addVert(75, 100);
      polygon.addVert(100, 100);
      polygon.addVert(100, 200);
      polygon.addVert(125, 200);
      polygon.addVert(125, 100);
      polygon.addVert(150, 100);
      polygon.addVert(150, 200);
      polygon.addVert(175, 200);
      polygon.addVert(175, 100);
      polygon.addVert(275, 100);
      polygon.addVert(275, 5);
      polygon.addVert(250, 5);
      polygon.addVert(250, 50);
      polygon.addVert(225, 50);
      polygon.addVert(225, 5);
      polygon.addVert(200, 5);
      polygon.addVert(200, 50);
      polygon.addVert(175, 50);
      polygon.addVert(175, 5);
      polygon.addVert(150, 5);
      polygon.addVert(150, 50);
      break;

    case 7:
      polygon.addVert(37, 223);
      polygon.addVert(37, 143);
      polygon.addVert(91, 143);
      polygon.addVert(113, 84);
      polygon.addVert(156, 192);
      polygon.addVert(191, 84);
      polygon.addVert(244, 143);
      polygon.addVert(244, 44);
      polygon.addVert(17, 44);
      polygon.addVert(17, 10);
      polygon.addVert(268, 10);
      polygon.addVert(268, 223);
      break;
    case 8:

      polygon.addVert(50, 100);
      polygon.addVert(250, 100);
      polygon.addVert(200, 50);
      polygon.addVert(150, 150);
      polygon.addVert(100, 50);
      break;
    case 9:

      polygon.addVert(131, 54);
      polygon.addVert(52, 207);
      polygon.addVert(134, 146);
      polygon.addVert(226, 207);
      break;
    case 10:
      polygon.addVert(74, 112);
      polygon.addVert(212, 141);
      polygon.addVert(96, 150);
      polygon.addVert(142, 221);
      polygon.addVert(135, 60);
      break;

    case 11:
      polygon.addVert(9, 260);
      polygon.addVert(96, 114);
      polygon.addVert(168, 178);
      polygon.addVert(242, 99);
      polygon.addVert(16, 99);
      polygon.addVert(13, 20);
      polygon.addVert(279, 24);
      polygon.addVert(279, 261);
      break;
    }

    this.currentTestCase = (this.currentTestCase + 1) % NUM_TEST_CASES;

    return polygon;
  }

  /**
   * Always throws a {@link UnsupportedOperationException}.
   * 
   * @throws UnsupportedOperationException
   *           Always throws this exception.
   * @see java.util.Iterator#remove()
   */
  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }
}