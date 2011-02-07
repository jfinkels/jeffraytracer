/**
 * BottomToTopComparator.java
 */
package edu.bu.cs.cs680;

import java.util.Comparator;

/**
 * A comparator which orders vectors from bottom to top by comparing their y
 * components.
 * 
 * @author Jeffrey Finkelstein <jeffreyf>
 */
public class BottomToTopComparator implements Comparator<Vector2D> {

  /** The singleton instance of this class. */
  public static final BottomToTopComparator INSTANCE = new BottomToTopComparator();

  /**
   * Returns the result of comparing the y components of the specified vectors.
   * 
   * @param vector1
   *          A vector to compare.
   * @param vector2
   *          Another vector to compare.
   * @return The result of comparing the y components of the specified vectors.
   * 
   * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
   */
  @Override
  public int compare(final Vector2D vector1, final Vector2D vector2) {
    return Float.compare(vector1.y, vector2.y);
  }

}
