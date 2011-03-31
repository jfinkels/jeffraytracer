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

  @Override
  public boolean equals(final Object other) {
    if (!(other instanceof Point3D)) {
      return false;
    }

    final Point3D o = (Point3D) other;
    return this.x == o.x && this.y == o.y && this.z == o.z;
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
   * Computes the norm of this vector.
   * 
   * @return The norm of this vector.
   */
  public double norm() {
    return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
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

  public double dotProduct(final Point3D that) {
    return this.x * that.x + this.y * that.y + this.z * that.z;
  }

  /**
   * 
   */
  public Point3D normalized() {
    final double norm = this.norm();
    return new Point3D(this.x / norm, this.y / norm, this.z / norm);
  }

}
