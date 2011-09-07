/**
 * Vector4D.java - a four-dimensional vector
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
 * A four-dimensional vector.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
@Immutable
public class Vector4D {
  /** The w coordinate of this vector. */
  private final double w;
  /** The x coordinate of this vector. */
  private final double x;
  /** The y coordinate of this vector. */
  private final double y;
  /** The z coordinate of this vector. */
  private final double z;

  /**
   * Instantiates this vector with the specified four coordinates.
   * 
   * @param x
   *          The x coordinate of this vector.
   * @param y
   *          The y coordinate of this vector.
   * @param z
   *          The z coordinate of this vector.
   * @param w
   *          The w coordinate of this vector.
   */
  public Vector4D(final double x, final double y, final double z,
      final double w) {
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

  /**
   * Instantiates this vector using the three coordinates of the specified
   * three-dimensional vector as its x, y, and z coordinates, along with the
   * specified w coordinate.
   * 
   * @param vector
   *          The vector specifying the first three components of this vector.
   * @param w
   *          The fourth component of this vector.
   */
  public Vector4D(final Vector3D vector, final double w) {
    this(vector.x(), vector.y(), vector.z(), w);
  }

  /**
   * Returns the dot product of this vector with the specified other vector.
   * 
   * @param that
   *          The other vector with which to compute the dot product.
   * @return The dot product of this vector with the specified other vector.
   */
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
   * Returns the string representation of this vector.
   * 
   * @return The string representation of this vector.
   */
  @Override
  public String toString() {
    return "Vector[" + this.x + ", " + this.y + ", " + this.z + ", " + this.w
        + "]";
  }

  /**
   * Gets the w coordinate of this vector.
   * 
   * @return The w coordinate of this vector.
   */
  public double w() {
    return this.w;
  }

  /**
   * Gets the x coordinate of this vector.
   * 
   * @return The x coordinate of this vector.
   */
  public double x() {
    return this.x;
  }

  /**
   * Gets the y coordinate of this vector.
   * 
   * @return The y coordinate of this vector.
   */
  public double y() {
    return this.y;
  }

  /**
   * Gets the z coordinate of this vector.
   * 
   * @return The z coordinate of this vector.
   */
  public double z() {
    return this.z;
  }
}
