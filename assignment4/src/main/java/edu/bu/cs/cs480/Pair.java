/**
 * 
 */
package edu.bu.cs.cs480;

/**
 * @author jeff
 * 
 */
public class Pair {
  private final double left;
  private final double right;

  public Pair(final double left, final double right) {
    this.left = left;
    this.right = right;
  }

  public double left() {
    return this.left;
  }

  public double right() {
    return this.right;
  }

  public String toString() {
    return "(" + this.left + ", " + this.right + ")";
  }
}
