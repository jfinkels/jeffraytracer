/**
 * Vector4D.java - a four-dimensional vector
 */
package edu.bu.cs.cs480;


/**
 * A four-dimensional vector.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
@Immutable
public class Vector4D {
  private final double w;
  private final double x;
  private final double y;
  private final double z;

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

  /**
   * Returns a new vector which is the homogeneization of this vector (that is,
   * all the components are divided by the fourth component).
   * 
   * @return The homogoneized form of this vector.
   */
  public Vector3D homogeneized() {
    final double divisor = Math.abs(this.w);
    return new Vector3D(this.x / divisor, this.y / divisor, this.z / divisor);
  }

  public Vector4D(final Vector3D vector, final double w) {
    this(vector.x(), vector.y(), vector.z(), w);
  }

  public double dotProduct(final Vector4D that) {
    return this.x * that.x + this.y * that.y + this.z * that.z + this.w
        * that.w;
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
   * Returns a new Vector4D object which is the component-wise sum of this point
   * with the specified other point.
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

  @Override
  public String toString() {
    return "Vector[" + this.x + ", " + this.y + ", " + this.z + ", " + this.w
        + "]";
  }

  public double w() {
    return this.w;
  }

  public double x() {
    return this.x;
  }

  public double y() {
    return this.y;
  }

  public double z() {
    return this.z;
  }
}
