/**
 * LeftToRightComparator.java
 */
package edu.bu.cs.cs680;

import java.util.Comparator;

/**
 * A comparator which orders vectors from left to right by comparing their x
 * components.
 * 
 * @author Jeffrey Finkelstein <jeffreyf>
 */
public class LeftToRightComparator implements Comparator<Vector2D> {

  /** The singleton instance of this class. */
  public static final LeftToRightComparator INSTANCE = new LeftToRightComparator();

  /**
   * Returns the result of comparing the x components of the specified vectors.
   * 
   * @param vector1
   *          A vector to compare.
   * @param vector2
   *          Another vector to compare.
   * @return The result of comparing the x components of the specified vectors.
   * 
   * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
   */
  @Override
  public int compare(final Vector2D vector1, final Vector2D vector2) {
    return Float.compare(vector1.x, vector2.x);
  }

}