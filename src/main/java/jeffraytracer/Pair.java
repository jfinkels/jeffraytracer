/**
 * Pair.java - a pair of double values
 * 
 * Copyright 2011 Jeffrey Finkelstein
 * 
 * This file is part of jeffraytracer.
 * 
 * jeffraytracer is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 * 
 * jeffraytracer is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * jeffraytracer. If not, see <http://www.gnu.org/licenses/>.
 */
package jeffraytracer;

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
  @Override
  public String toString() {
    return "(" + this.left + ", " + this.right + ")";
  }
}
