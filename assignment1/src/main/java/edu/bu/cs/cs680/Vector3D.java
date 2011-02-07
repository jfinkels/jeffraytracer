package edu.bu.cs.cs680;

/**
 * A floating point three dimensional vector.
 * 
 * @author Jeffrey Finkelstein <jeffreyf>
 */
public class Vector3D {
  /** The components of this vector. */
  protected float x, y, z;

  /**
   * Instantiates this as a three dimensional vector with the specified
   * components cast to floating point values.
   * 
   * @param _x
   *          The x component.
   * @param _y
   *          The y component.
   * @param _z
   *          The z component.
   */
  public Vector3D(final double _x, final double _y, final double _z) {
    this((float) _x, (float) _y, (float) _z);
  }

  /**
   * Instantiates this as a three dimensional vector with the specified
   * components.
   * 
   * @param _x
   *          The x component.
   * @param _y
   *          The y component.
   * @param _z
   *          The z component.
   */
  public Vector3D(final float _x, final float _y, final float _z) {
    this.x = _x;
    this.y = _y;
    this.z = _z;
  }

  /**
   * Computes the cross product of this with the specified other vector (in that
   * order).
   * 
   * @param that
   *          The other vector with which to compute the cross product.
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
   * @return A new vector whose values are the component-wise difference between
   *         this and the specified other vector.
   */
  public Vector3D difference(final Vector3D that) {
    return new Vector3D(this.x - that.x, this.y - that.y, this.z - that.z);
  }

  /**
   * Computes the distance from this vector to the specified other vector.
   * 
   * @param that
   *          The vector to which distance will be computed.
   * @return The distance from this vector to the specified other vector.
   */
  public double distanceTo(final Vector3D that) {
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
   * Returns a new vector which is the result of scaling this vector by the
   * specified scalar.
   * 
   * @param scale
   *          The amount by which to scale this vector.
   * @return A new vector which is the result of scaling this vector by the
   *         specified scalar.
   */
  public Vector3D scaledBy(final double scale) {
    return this.scaledBy((float) scale);
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
  public Vector3D scaledBy(final float scale) {
    return new Vector3D(scale * this.x, scale * this.y, scale * this.z);
  }

  /**
   * Returns a new vector which is the component-wise sum of this vector and the
   * specified other vector.
   * 
   * @param that
   *          The other vector with which to sum this vector.
   * @return A new vector which is the component-wise sum of this vector and the
   *         specified other vector.
   */
  public Vector3D sumWith(final Vector3D that) {
    return new Vector3D(this.x + that.x, this.y + that.y, this.z + that.z);
  }

  /**
   * Returns the String representation of this vector.
   * 
   * @return The String representation of this vector.
   */
  @Override
  public String toString() {
    return "(" + this.x + ", " + this.y + ", " + this.z + ")";
  }
}
