/**
 * LineSegmentTest.java
 */
package edu.bu.cs.cs680;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test class for the LineSegment class.
 * 
 * @author Jeffrey Finkelstein <jeffreyf>
 */
public class LineSegmentTest {

  /**
   * Test method for
   * {@link edu.bu.cs.cs680.LineSegment#LineSegment(edu.bu.cs.cs680.Vector2D, edu.bu.cs.cs680.Vector2D)}
   * .
   */
  @Test
  public void testLineSegment() {
    new LineSegment(new Vector2D(0, 0), new Vector2D(0, 1));
  }

  /**
   * Test method for {@link edu.bu.cs.cs680.LineSegment#contains(Vector2D)}.
   */
  @Test
  public void testContains() {
    final LineSegment edge = new LineSegment(new Vector2D(0, 0), new Vector2D(
        1, 1));
    for (double x = 0; x < 1; x += 0.1) {
      assertTrue(edge.contains(new Vector2D(x, x)));
    }
    assertFalse(edge.contains(new Vector2D(1.0, 0.0)));
    assertFalse(edge.contains(new Vector2D(0.0, 1.0)));
    assertFalse(edge.contains(new Vector2D(2.0, 2.0)));
    assertFalse(edge.contains(new Vector2D(-1.0, -1.0)));

  }
}
