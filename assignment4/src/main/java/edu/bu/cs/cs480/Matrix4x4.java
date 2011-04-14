/**
 * 
 */
package edu.bu.cs.cs480;

/**
 * @author jeff
 * 
 */
public class Matrix4x4 {
  final double[][] m = new double[4][4];

  public void set(final int row, final int column, final double value) {
    this.m[row][column] = value;
  }
  
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

}
