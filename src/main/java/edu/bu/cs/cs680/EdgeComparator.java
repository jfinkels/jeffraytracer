/**
 * EdgeComparator.java
 */
package edu.bu.cs.cs680;

import java.util.Comparator;

/**
 * Compares the y values of the vertically lowest endpoints of two line
 * segments.
 * 
 * @author Jeffrey Finkelstein <jeffreyf>
 */
public class EdgeComparator implements Comparator<LineSegment> {

  /** The singleton instance of this class. */
  public static final EdgeComparator INSTANCE = new EdgeComparator();

  /**
   * Returns the result of comparing the <em>y</em> values from the vertically
   * lowest endpoints of the specified edges.
   * 
   * Imposes an order for line segments based on vertically lowest endpoints.
   * 
   * @return The result of comparing the <em>y</em> values from the vertically
   *         lowest endpoints of the specified edges.
   * 
   * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
   */
  // TODO test for this method
  @Override
  public int compare(final LineSegment edge1, final LineSegment edge2) {
    // TODO FIX THIS
    final float min1 = Math.min(edge1.first().y, edge1.second().y);
    final float min2 = Math.min(edge2.first().y, edge2.second().y);
    
    // if these edges have the same least y value, 
    if (min1 == min2) {
      
    }
    
    return Float.compare(min1, min2);
  }

}
