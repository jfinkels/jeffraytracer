/**
 * BottomLeftToTopRightComparator.java
 */
package edu.bu.cs.cs680.unneeded;

import java.util.Comparator;

import edu.bu.cs.cs680.Vector2D;

/**
 * A comparator which orders vectors first from bottom to top by comparing their
 * y components, then from left to right by comparing their x components.
 * 
 * @author Jeffrey Finkelstein <jeffreyf>
 */
public class BottomLeftToTopRightComparator implements Comparator<Vector2D> {

  /** The singleton instance of this class. */
  public static final BottomLeftToTopRightComparator INSTANCE = new BottomLeftToTopRightComparator();

  /**
   * Returns the result of comparing first the y components of the specified
   * vectors, then the x components of the specified vectors.
   * 
   * @param vector1
   *          A vector to compare.
   * @param vector2
   *          Another vector to compare.
   * @return The result of comparing the y components of the specified vectors,
   *         then the x components of the specified vectors.
   * 
   * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
   */
  @Override
  public int compare(final Vector2D vector1, final Vector2D vector2) {
    final int result = Float.compare(vector1.y, vector2.y);
    if (result == 0) {
      return Float.compare(vector1.x, vector2.x);
    }
    return result;
  }

}
