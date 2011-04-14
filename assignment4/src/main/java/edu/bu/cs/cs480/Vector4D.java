/**
 * 
 */
package edu.bu.cs.cs480;

/**
 * @author jeff
 * 
 */
public class Vector4D {
  private final double x;
  private final double y;
  private final double z;
  public double x() { return this.x; }
  public double y() { return this.y; }
  public double z() { return this.z; }
  /**
   * @param x
   * @param y
   * @param z
   */
  public Vector4D(double x, double y, double z, final double w) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.w = w;
  }

  Vector4D(final Vector3D vector, final double w) {
    this(vector.x(), vector.y(), vector.z(), w);
  }

  /**
   * Computes the norm of this vector.
   * 
   * @return The norm of this vector.
   */
  public double norm() {
    return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
  }

  /**
   * Returns a new Vector4D object which is the component-wise sum of this
   * point with the specified other point.
   * 
   * @param that
   *          The point with which to sum this one.
   * @return The Vector4D object which is the sum of this point and the other
   *         point.
   */
  public Vector4D sumWith(final Vector4D that) {
    return new Vector4D(this.x + that.x, this.y + that.y, this.z + that.z,
        this.w + that.w);
  }

  /**
   * Returns a new vector which is the result of scaling this vector by the
   * specified scalar.
   * 
   * @param scale
   *          The amount by which to scale this vector.
   * @return A new vector which is the result of scaling this vector by the
   *         specified scalar.
   */
  public Vector4D scaledBy(final double scale) {
    return new Vector4D(scale * this.x, scale * this.y, scale * this.z, scale
        * this.w);
  }

  /**
   * Returns a new unit vector whose direction is the same as the direction of
   * this vector.
   * 
   * @return A new unit vector whose direction is the same as the direction of
   *         this vector.
   */
  public Vector3D normalized() {
    final double norm = this.norm();
    return new Vector3D(this.x / norm, this.y / norm, this.z / norm);
  }

  public double dotProduct(final Vector4D that) {
    return this.x * this.x + this.y * this.y + this.z * this.z + this.w
        * that.w;
  }

  private final double w;

  public double w() {
    return this.w;
  }
  public String toString() {
    return "Vector[" + this.x + ", " + this.y + ", " + this.z + ", " + this.w + "]";
  }
}
