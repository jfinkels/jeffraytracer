/**
 * Pair.java - a pair of double values
 */
package edu.bu.cs.cs480;

/**
 * A pair of double values.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Pair {
  /** The double value on the left. */
  private final double left;
  /** The double value on the right. */
  private final double right;

  /**
   * Creates this pair with the specified left and right components.
   * 
   * @param left
   *          The left double value.
   * @param right
   *          The right double value.
   */
  public Pair(final double left, final double right) {
    this.left = left;
    this.right = right;
  }

  /**
   * Gets the double value on the left.
   * 
   * @return The double value on the left.
   */
  public double left() {
    return this.left;
  }

  /**
   * Gets the double value on the right.
   * 
   * @return The double value on the right.
   */
  public double right() {
    return this.right;
  }

  /**
   * Gets the human-readable string which represents this pair.
   * 
   * @return The human-readable string which represents this pair.
   */
  public String toString() {
    return "(" + this.left + ", " + this.right + ")";
  }
}
