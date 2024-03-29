/**
 * Vector3D.java - a three-dimensional point with double values
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
package jeffraytracer;

/**
 * A three-dimensional point with double values.
 * 
 * @author Tai-Peng Tian <tiantp@gmail.com>
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2008
 */
@Immutable
public class Vector3D {
  /** The origin, (0, 0, 0). */
  public static final Vector3D ORIGIN = new Vector3D(0, 0, 0);

  /**
   * Returns the {@code Vector3D} object which equals the sum of each of the
   * specified vectors.
   * 
   * @param vectors
   *          A sequence of {@code Vector3D} instances.
   * @return A new {@code Vector3D} instance which equals the sum of the
   *         specified vectors.
   */
  public static Vector3D sum(final Vector3D... vectors) {
    Vector3D result = Vector3D.ORIGIN;
    for (final Vector3D addend : vectors) {
      result = result.sumWith(addend);
    }
    return result;
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
  public Vector3D(final double x, final double y, final double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  /**
   * Computes the cross product of this with the specified other vector (in
   * that order).
   * 
   * @param that
   *          The other vector with which to compute the cross product.
   * @return The cross product of this with the specified other vector (in that
   *         order).
   */
  public Vector3D crossProduct(final Vector3D that) {
    return new Vector3D(this.y * that.z - this.z * that.y, this.z * that.x
        - this.x * that.z, this.x * that.y - this.y * that.x);
  }

  /**
   * Returns a vector representing the difference between this vector and the
   * specified other vector.
   * 
   * @param that
   *          The vector to subtract from this one.
   * @return A new vector whose values are the component-wise difference
   *         between this and the specified other vector.
   */
  public Vector3D difference(final Vector3D that) {
    return new Vector3D(this.x - that.x, this.y - that.y, this.z - that.z);
  }

  /**
   * Returns the dot product of this vector and the specified other vector.
   * 
   * @param that
   *          The other vector.
   * @return The dot product of this vector and the specified other vector.
   */
  public double dotProduct(final Vector3D that) {
    return this.x * that.x + this.y * that.y + this.z * that.z;
  }

  /**
   * Returns {@code true} if and only if the components of this vector equal
   * the components of the specified other vector exactly.
   * 
   * @param that
   *          The other vector with which to test for equality.
   * @return {@code true} if and only if the components of the two vectors are
   *         equal.
   */
  public boolean equalTo(final Vector3D that) {
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
  public Vector3D normalized() {
    final double norm = this.norm();
    return new Vector3D(this.x / norm, this.y / norm, this.z / norm);
  }

  /**
   * Returns a new unit vector which is orthogonal to this one.
   * 
   * @return A new unit vector which is orthogonal to this one.
   */
  public Vector3D orthogonal() {
    if (this.parallelTo(new Vector3D(1, 0, 0))) {
      return new Vector3D(0, 1, 0);
    }
    return this.crossProduct(new Vector3D(1, 0, 0)).normalized();
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
  private boolean parallelTo(final Vector3D that) {
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
  public Vector3D scaledBy(final double scale) {
    return new Vector3D(scale * this.x, scale * this.y, scale * this.z);
  }

  /**
   * Returns a new Vector3D object which is the component-wise sum of this
   * point with the specified other point.
   * 
   * @param that
   *          The point with which to sum this one.
   * @return The Vector3D object which is the sum of this point and the other
   *         point.
   */
  public Vector3D sumWith(final Vector3D that) {
    return new Vector3D(this.x + that.x, this.y + that.y, this.z + that.z);
  }

  /**
   * Returns the String representation of this object.
   * 
   * @return The String representation of this object.
   */
  @Override
  public String toString() {
    return "Vector[" + this.x + ", " + this.y + ", " + this.z + "]";
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
