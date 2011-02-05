/**
 * EdgeComparatorTest.java
 */
package edu.bu.cs.cs680;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test class for the EdgeComparator class.
 * 
 * @author Jeffrey Finkelstein <jeffreyf>
 */
public class EdgeComparatorTest {

  /**
   * Test method for
   * {@link edu.bu.cs.cs680.EdgeComparator#compare(edu.bu.cs.cs680.LineSegment, edu.bu.cs.cs680.LineSegment)}
   * .
   */
  @Test
  public void testCompare() {
    LineSegment line1 = new LineSegment(new Vector2D(0, 0), new Vector2D(1, 1));
    LineSegment line2 = new LineSegment(new Vector2D(0, 1), new Vector2D(1, 2));
    assertEquals(-1, EdgeComparator.INSTANCE.compare(line1, line2));
    assertEquals(EdgeComparator.INSTANCE.compare(line1, line2),
        -EdgeComparator.INSTANCE.compare(line2, line1));

    line1 = new LineSegment(new Vector2D(0, 0), new Vector2D(1, 1));
    line2 = new LineSegment(new Vector2D(1, 1), new Vector2D(2, 0));
    assertEquals(-1, EdgeComparator.INSTANCE.compare(line1, line2));
    assertEquals(EdgeComparator.INSTANCE.compare(line1, line2),
        -EdgeComparator.INSTANCE.compare(line2, line1));
  }

}
