/**
 * Matrix4x4.java - a 4x4 matrix
 */
package edu.bu.cs.cs480;

import java.util.Arrays;

/**
 * A 4x4 matrix.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Matrix4x4 {
  /** The entries of this matrix. */
  final double[][] m = new double[4][4];

  /**
   * Sets the value of the entry at the specified row and column in this
   * matrix.
   * 
   * @param row
   *          The row of the entry to set.
   * @param column
   *          The column of the entry to set.
   * @param value
   *          The value to set.
   */
  public void set(final int row, final int column, final double value) {
    this.m[row][column] = value;
  }

  /**
   * Gets the value of the entry at the specified row and column in this
   * matrix.
   * 
   * @param row
   *          The row of the entry to get.
   * @param column
   *          The column of the entry to get.
   * @return The value to get.
   */
  protected double get(final int row, final int column) {
    return this.m[row][column];
  }

  /**
   * Computes the product of this matrix (on the left) with the specified
   * vector (on the right).
   * 
   * @param right
   *          The vector with which to multiply (on the right).
   * @return The product of this matrix and the specified vector.
   */
  public Vector4D product(final Vector4D right) {
    final double x = m[0][0] * right.x() + m[0][1] * right.y() + m[0][2]
        * right.z() + m[0][3] * right.w();
    final double y = m[1][0] * right.x() + m[1][1] * right.y() + m[1][2]
        * right.z() + m[1][3] * right.w();
    final double z = m[2][0] * right.x() + m[2][1] * right.y() + m[2][2]
        * right.z() + m[2][3] * right.w();
    final double w = m[3][0] * right.x() + m[3][1] * right.y() + m[3][2]
        * right.z() + m[3][3] * right.w();
    return new Vector4D(x, y, z, w);
  }

  @Override
  public String toString() {
    String result = "[" + Arrays.toString(this.m[0]) + "]\n";
    result += "[" + Arrays.toString(this.m[1]) + "]\n";
    result += "[" + Arrays.toString(this.m[2]) + "]\n";
    result += "[" + Arrays.toString(this.m[3]) + "]";
    return result;
  }
}
