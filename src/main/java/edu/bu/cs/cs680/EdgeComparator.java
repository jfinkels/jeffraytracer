/**
 * EdgeComparator.java
 */
package edu.bu.cs.cs680;

import java.util.Comparator;

/**
 * A comparator which orders line segments from bottom to top, then left to
 * right.
 * 
 * @author Jeffrey Finkelstein <jeffreyf>
 */
public class EdgeComparator implements Comparator<LineSegment> {

  /** The singleton instance of this class. */
  public static final EdgeComparator INSTANCE = new EdgeComparator();

  /**
   * Returns the result of comparing the y components of the lower endpoints of
   * both line segments, then the result of comparing the x components of the
   * lower endpoints of both line segments, then the result of comparing the x
   * components of the upper endpoints of both line segments.
   * 
   * @param line1
   *          A line segment to compare.
   * @param line2
   *          Another line segment to compare.
   * @return The result of comparing for bottom to top, then left to right.
   * 
   * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
   */
  // TODO test for this method
  @Override
  public int compare(final LineSegment edge1, final LineSegment edge2) {

    int result = Float.compare(edge1.lowerEndpoint().y,
        edge2.lowerEndpoint().y);
    if (result != 0) {
      return result;
    }

    result = Float.compare(edge1.lowerEndpoint().x, edge2.lowerEndpoint().x);
    if (result != 0) {
      return result;
    }

    return Float.compare(edge1.upperEndpoint().x, edge2.upperEndpoint().x);
  }
}
