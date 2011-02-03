package edu.bu.cs.cs680;

import java.util.Comparator;

/**
 * A comparator which orders vectors from left to right by comparing their x
 * components.
 * 
 * @author Jeffrey Finkelstein <jeffreyf>
 */
public class LeftToRightComparator implements Comparator<Vector2D> {

  /**
   * Returns the result of comparing the x coordinates of the specified vectors.
   * 
   * @param vector1
   *          A vector to compare.
   * @param vector2
   *          Another vector to compare.
   * @return The result of comparing the x coordinates of the specified vectors.
   */
  @Override
  public int compare(final Vector2D vector1, final Vector2D vector2) {
    return Float.compare(vector1.x, vector2.x);
  }

}
