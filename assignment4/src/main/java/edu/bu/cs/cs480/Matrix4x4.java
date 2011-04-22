/**
 * Matrix4x4.java - a 4x4 matrix with real-valued entries
 */
package edu.bu.cs.cs480;

import java.util.Arrays;

/**
 * A 4x4 matrix with real-valued entries.
 * 
 * The matrix initially has zeros in all its entries. To get a new object which
 * is the identity matrix, use the {@link #identity()} method.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Matrix4x4 {
  /**
   * Returns a new matrix which is the 4x4 identity matrix.
   * 
   * @return A new matrix which is the 4x4 identity matrix.
   */
  public static Matrix4x4 identity() {
    final Matrix4x4 result = new Matrix4x4();
    for (int i = 0; i < 4; ++i) {
      result.m[i][i] = 1;
    }
    return result;
  }

  /** The entries of this matrix. */
  final double[][] m = new double[4][4];

  /**
   * Gets the value of the entry at the specified row and column in this matrix.
   * 
   * @param row
   *          The row of the entry to get.
   * @param column
   *          The column of the entry to get.
   * @return The value to get.
   */
  public double get(final int row, final int column) {
    return this.m[row][column];
  }

  /**
   * Returns the result of matrix multiplication of this matrix (on the left)
   * and the specified other matrix (on the right).
   * 
   * @param right
   *          The matrix with which to multiply (on the right).
   * @return The product of this matrix and the specified other matrix.
   */
  public Matrix4x4 product(final Matrix4x4 right) {
    final Matrix4x4 result = new Matrix4x4();
    for (int i = 0; i < 4; ++i) {
      for (int j = 0; j < 4; ++j) {
        for (int k = 0; k < 4; ++k) {
          result.m[i][j] += (this.m[i][k] * right.m[k][j]);
        }
      }
    }

    return result;
  }

  /**
   * Computes the product of this matrix (on the left) with the specified vector
   * (on the right).
   * 
   * @param right
   *          The vector with which to multiply (on the right).
   * @return The product of this matrix and the specified vector.
   */
  public Vector4D product(final Vector4D right) {
    final double x = this.m[0][0] * right.x() + this.m[0][1] * right.y()
        + this.m[0][2] * right.z() + this.m[0][3] * right.w();
    final double y = this.m[1][0] * right.x() + this.m[1][1] * right.y()
        + this.m[1][2] * right.z() + this.m[1][3] * right.w();
    final double z = this.m[2][0] * right.x() + this.m[2][1] * right.y()
        + this.m[2][2] * right.z() + this.m[2][3] * right.w();
    final double w = this.m[3][0] * right.x() + this.m[3][1] * right.y()
        + this.m[3][2] * right.z() + this.m[3][3] * right.w();
    return new Vector4D(x, y, z, w);
  }

  /**
   * Sets the value of the entry at the specified row and column in this matrix.
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
   * Returns the string representation of this matrix.
   * 
   * @return The string representation of this matrix.
   */
  @Override
  public String toString() {
    String result = "[" + Arrays.toString(this.m[0]) + "]\n";
    result += "[" + Arrays.toString(this.m[1]) + "]\n";
    result += "[" + Arrays.toString(this.m[2]) + "]\n";
    result += "[" + Arrays.toString(this.m[3]) + "]";
    return result;
  }

  public boolean equals(final Matrix4x4 that) {
    return this.equals(that.m);
  }

  public boolean equals(final double[][] matrix) {
    for (int i = 0; i < 4; ++i) {
      for (int j = 0; j < 4; ++j) {
        if (this.m[i][j] != matrix[i][j]) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Returns the transpose of this matrix.
   * 
   * @return The transpose of this matrix.
   */
  public Matrix4x4 transposed() {
    final Matrix4x4 result = new Matrix4x4();
    for (int i = 0; i < 4; ++i) {
      for (int j = 0; j < 4; ++j) {
        result.m[i][j] = this.m[j][i];
      }
    }

    return result;
  }
}
