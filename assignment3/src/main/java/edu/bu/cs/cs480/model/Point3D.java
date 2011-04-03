/**
 * Point3D.java - a three-dimensional point with double values
 * 
 * History:
 * 
 * 18 February 2011
 * 
 * - made members private and added accessors
 * 
 * - added documentation
 * 
 * - added toString() method
 * 
 * (Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>)
 * 
 * 30 January 2008
 * 
 * - created
 * 
 * (Tai-Peng Tian <tiantp@gmail.com>)
 */
package edu.bu.cs.cs480.model;

/**
 * A three-dimensional point with double values.
 * 
 * @author Tai-Peng Tian <tiantp@gmail.com>
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2008
 */
public class Point3D {
  /** The origin, (0, 0, 0). */
  public static final Point3D ORIGIN = new Point3D(0, 0, 0);

  /**
   * If d is greater than one, this method returns 1.0; if d is less than
   * negative one, this method returns -1.0; otherwise this method returns d.
   * 
   * @param d
   *          The number to bound
   * @return The maximum of -1 and the minimum of d and 1.
   */
  private static double bounded(final double d) {
    return Math.max(-1, Math.min(d, 1));
  }

  /** The x component of this point. */
  private final double x;
  /** The y component of this point. */
  private final double y;
  /** The z component of this point. */
  private final double z;

  /**
   * Instantiates this point with the three specified components.
   * 
   * @param x
   *          The x component of this point.
   * @param y
   *          The y component of this point.
   * @param z
   *          The z component of this point.
   */
  public Point3D(final double x, final double y, final double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  /**
   * Returns the angle in degrees between this vector and the specified other
   * vector.
   * 
   * @param that
   *          The other vector.
   * @return The angle in degrees between this vector and the specified other
   *         vector.
   */
  public double angleBetween(final Point3D that) {
    // need to floating point mathematics causes slight rounding error, so we
    // need to check that the input to Math.acos is in the interval [-1, 1]
    return Math.acos(bounded(this.dotProduct(that)
        / (this.norm() * that.norm())))
        * (180 / Math.PI);
  }

  /**
   * Computes the cross product of this with the specified other vector (in that
   * order).
   * 
   * @param that
   *          The other vector with which to compute the cross product.
   */
  public Point3D crossProduct(final Point3D that) {
    return new Point3D(this.y * that.z - this.z * that.y, this.z * that.x
        - this.x * that.z, this.x * that.y - this.y * that.x);
  }

  /**
   * Returns a vector representing the difference between this vector and the
   * specified other vector.
   * 
   * @param that
   *          The vector to subtract from this one.
   * @return A new vector whose values are the component-wise difference between
   *         this and the specified other vector.
   */
  public Point3D difference(final Point3D that) {
    return new Point3D(this.x - that.x, this.y - that.y, this.z - that.z);
  }

  /**
   * Computes the distance from this vector to the specified other vector.
   * 
   * @param that
   *          The vector to which distance will be computed.
   * @return The distance from this vector to the specified other vector.
   */
  public double distanceTo(final Point3D that) {
    return this.difference(that).norm();
  }

  /**
   * Returns the dot product of this vector and the specified other vector.
   * 
   * @param that
   *          The other vector.
   * @return The dot product of this vector and the specified other vector.
   */
  public double dotProduct(final Point3D that) {
    return this.x * that.x + this.y * that.y + this.z * that.z;
  }

  /**
   * Returns {@code true} if and only if the components of this vector are
   * exactly equal to the components of the specified other vector.
   * 
   * @param that
   *          The vector to compare for equality.
   * @return {@code true} if and only if the components of this vector are
   *         exactly equal to the components of the specified other vector.
   */
  public boolean equals(final Point3D that) {
    return this.x == that.x && this.y == that.y && this.z == that.z;
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
   * Returns a new unit vector whose direction is the same as the direction of
   * this vector.
   * 
   * @return A new unit vector whose direction is the same as the direction of
   *         this vector.
   */
  public Point3D normalized() {
    final double norm = this.norm();
    return new Point3D(this.x / norm, this.y / norm, this.z / norm);
  }

  /**
   * Returns {@code true} if and only if this vector is parallel to and in the
   * opposite direction from the specified other vector.
   * 
   * Pre-condition: the specified other vector is parallel to this one. If not,
   * the result is undefined.
   * 
   * @param that
   *          The other vector which is parallel to this one.
   * @return {@code true} if and only if the specified other vector is in the
   *         opposite direction from this one.
   */
  public boolean oppositeDirectionFrom(final Point3D that) {
    return Math.signum(this.x) != Math.signum(that.x)
        && Math.signum(this.y) != Math.signum(that.y)
        && Math.signum(this.z) != Math.signum(that.z);
  }

  /**
   * Returns a new unit vector which is orthogonal to this one.
   * 
   * @return A new unit vector which is orthogonal to this one.
   */
  public Point3D orthogonal() {
    if (this.parallelTo(new Point3D(1, 0, 0))) {
      return new Point3D(0, 1, 0);
    }
    return this.crossProduct(new Point3D(1, 0, 0)).normalized();
  }

  public boolean orthogonalTo(final Point3D that) {
    return this.dotProduct(that) == 0;
  }

  /**
   * Determines whether this line is parallel to the specified other line.
   * 
   * This method is symmetric, so if {@code v1} and {@code v2} are two
   * {@code Line} objects, then {@code v1.parallelTo(v2)} if and only if
   * {@code v2.parallelTo(v1)}.
   * 
   * Algorithm: since two vectors are parallel if they have the same direction,
   * all we need to check is whether this vector is a dilation of the other
   * vector. Therefore we need only check that the ratios of each of the
   * corresponding components in both vectors are the same. We actually do this
   * by cross-multiplying so that we don't have possible divide by zero errors.
   * 
   * @param that
   *          The other line to compare with.
   * @return Whether this line is parallel to the specified other line.
   */
  public boolean parallelTo(final Point3D that) {
    return this.crossProduct(that).equals(ORIGIN);
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
  public Point3D scaledBy(final double scale) {
    return new Point3D(scale * this.x, scale * this.y, scale * this.z);
  }

  /**
   * Returns a new Point3D object which is the component-wise sum of this point
   * with the specified other point.
   * 
   * @param that
   *          The point with which to sum this one.
   * @return The Point3D object which is the sum of this point and the other
   *         point.
   */
  public Point3D sumWith(final Point3D that) {
    return new Point3D(this.x + that.x, this.y + that.y, this.z + that.z);
  }

  /**
   * Returns the String representation of this object.
   * 
   * @return The String representation of this object.
   */
  @Override
  public String toString() {
    return "Point[" + this.x + ", " + this.y + ", " + this.z + "]";
  }

  /**
   * Gets the x component of this point.
   * 
   * @return The x component of this point.
   */
  public double x() {
    return this.x;
  }

  /**
   * Gets the y component of this point.
   * 
   * @return The y component of this point.
   */
  public double y() {
    return this.y;
  }

  /**
   * Gets the z component of this point.
   * 
   * @return The z component of this point.
   */
  public double z() {
    return this.z;
  }

}
