/**
 * LineSegmentTest.java
 */
package edu.bu.cs.cs680;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test class for the LineSegment class.
 * 
 * @author Jeffrey Finkelstein <jeffreyf>
 */
public class LineSegmentTest {

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

  /**
   * Test method for {@link edu.bu.cs.cs680.LineSegment#intersects(LineSegment)}
   * .
   */
  @Test
  public void testIntersects() {
    final LineSegment line1 = new LineSegment(new Vector2D(0, 0),
        new Vector2D(1, 1));
    final LineSegment line1Inverse = new LineSegment(new Vector2D(1, 1),
        new Vector2D(0, 0));
    final LineSegment line2 = new LineSegment(new Vector2D(1, 0),
        new Vector2D(0, 1));
    final LineSegment line3 = new LineSegment(new Vector2D(-1, -1),
        new Vector2D(1, -1));

    assertTrue(line1.intersects(line2));
    assertTrue(line1Inverse.intersects(line2));
    assertTrue(line2.intersects(line1));
    assertTrue(line2.intersects(line1Inverse));
    assertFalse(line1.intersects(line3));
    assertFalse(line1Inverse.intersects(line3));
    assertFalse(line3.intersects(line1Inverse));
    assertFalse(line2.intersects(line3));
    assertFalse(line3.intersects(line2));
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs680.LineSegment#intersectionWithHorizontalAt(float)}.
   */
  @Test
  public void testIntersectionWithHorizontalAt() {
    LineSegment line = new LineSegment(new Vector2D(0, 0), new Vector2D(2, 2));
    assertEquals(1, line.intersectionWithHorizontalAt(1).x, 0);
    assertEquals(1, line.intersectionWithHorizontalAt(1).y, 0);

    line = new LineSegment(new Vector2D(1, 0), new Vector2D(2, 2));
    assertEquals(1.5, line.intersectionWithHorizontalAt(1).x, 0);
    assertEquals(1, line.intersectionWithHorizontalAt(1).y, 0);

    line = new LineSegment(new Vector2D(0, 0), new Vector2D(-1, 2));
    assertEquals(-0.5, line.intersectionWithHorizontalAt(1).x, 0);
    assertEquals(1, line.intersectionWithHorizontalAt(1).y, 0);

    line = new LineSegment(new Vector2D(0, 0), new Vector2D(0, 1));
    assertEquals(0, line.intersectionWithHorizontalAt(1).x, 0);
    assertEquals(1, line.intersectionWithHorizontalAt(1).y, 0);
  }

  /**
   * Test method for {@link edu.bu.cs.cs680.LineSegment#length()}.
   */
  @Test
  public void testLength() {
    final LineSegment line = new LineSegment(new Vector2D(-1, 1),
        new Vector2D(2, 5));
    assertEquals(5.0, line.length(), 0.0);
  }

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
   * Test method for {@link edu.bu.cs.cs680.LineSegment#lowerEndpoint()}.
   */
  @Test
  public void testLowerEndpoint() {
    LineSegment line = new LineSegment(new Vector2D(0, 0), new Vector2D(2, 2));
    assertTrue(line.lowerEndpoint().equalTo(new Vector2D(0, 0)));

    line = new LineSegment(new Vector2D(1, 0), new Vector2D(2, 2));
    assertTrue(line.lowerEndpoint().equalTo(new Vector2D(1, 0)));

    line = new LineSegment(new Vector2D(0, 0), new Vector2D(-1, -2));
    assertTrue(line.lowerEndpoint().equalTo(new Vector2D(-1, -2)));

    line = new LineSegment(new Vector2D(0, 0), new Vector2D(0, -1));
    assertTrue(line.lowerEndpoint().equalTo(new Vector2D(0, -1)));
  }

  /**
   * Test method for {@link edu.bu.cs.cs680.LineSegment#otherEndpoint(Vector2D)}
   * .
   */
  @Test
  public void testOtherEndpoint() {
    LineSegment line = new LineSegment(new Vector2D(0, 0), new Vector2D(2, 2));
    assertEquals(2, line.otherEndpoint(new Vector2D(0, 0)).x, 0);
    assertEquals(2, line.otherEndpoint(new Vector2D(0, 0)).y, 0);

    line = new LineSegment(new Vector2D(1, 0), new Vector2D(2, 2));
    assertEquals(1, line.otherEndpoint(new Vector2D(2, 2)).x, 0);
    assertEquals(0, line.otherEndpoint(new Vector2D(2, 2)).y, 0);

    line = new LineSegment(new Vector2D(0, 0), new Vector2D(-1, 2));
    assertEquals(0, line.otherEndpoint(new Vector2D(-1, 2)).x, 0);
    assertEquals(0, line.otherEndpoint(new Vector2D(-1, 2)).y, 0);

    line = new LineSegment(new Vector2D(0, 0), new Vector2D(0, 1));
    assertEquals(0, line.otherEndpoint(new Vector2D(0, 1)).x, 0);
    assertEquals(0, line.otherEndpoint(new Vector2D(0, 1)).y, 0);

    assertNull(line.otherEndpoint(new Vector2D(123, 456)));
    assertNull(line.otherEndpoint(new Vector2D(-123, -456)));
  }

  /**
   * Test method for {@link edu.bu.cs.cs680.LineSegment#upperEndpoint()}.
   */
  @Test
  public void testUpperEndpoint() {
    LineSegment line = new LineSegment(new Vector2D(0, 0), new Vector2D(2, 2));
    assertTrue(line.upperEndpoint().equalTo(new Vector2D(2, 2)));

    line = new LineSegment(new Vector2D(1, 0), new Vector2D(2, 2));
    assertTrue(line.upperEndpoint().equalTo(new Vector2D(2, 2)));

    line = new LineSegment(new Vector2D(0, 0), new Vector2D(-1, -2));
    assertTrue(line.upperEndpoint().equalTo(new Vector2D(0, 0)));

    line = new LineSegment(new Vector2D(0, 0), new Vector2D(0, -1));
    assertTrue(line.upperEndpoint().equalTo(new Vector2D(0, 0)));
  }
}
