package edu.bu.cs.cs680;

/**
 * A floating point three-dimensional vector.
 * 
 * @author Jeffrey Finkelstein <jeffreyf>
 */
public class Vector2D extends Vector3D {

  /**
   * Instantiates this as a three dimensional vector with its specified x and y
   * components cast to floating point values, and a value of 0f for its third
   * component.
   * 
   * @param _x
   *          The x component.
   * @param _y
   *          The y component.
   */
  public Vector2D(final double _x, final double _y) {
    super(_x, _y, 0.0);
  }

  /**
   * Instantiates this as a three dimensional vector with its specified x and y
   * components and a value of 0f for its third component.
   * 
   * @param _x
   *          The x component.
   * @param _y
   *          The y component.
   */
  public Vector2D(final float _x, final float _y) {
    super(_x, _y, 0.0f);
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
  public Vector2D difference(final Vector2D that) {
    return new Vector2D(this.x - that.x, this.y - that.y);
  }

  /**
   * Returns true if and only if the specified other vector is non-null and has
   * the same x and y values as this vector.
   * 
   * Note: this is intentionally different from the
   * {@link Object#equals(Object)} method so that we do not have to override the
   * {@link Object#hashCode()} method, and therefore we can have multiple
   * Vector2D objects in a single HashSet, for example.
   * 
   * @return {@code true} if and only if the specified other vector is not null
   *         and has the same x and y component values.
   */
  public boolean equalTo(final Vector2D that) {
    return that != null && this.x == that.x && this.y == that.y;
  }

  /**
   * Moves this vertex to the specified new location.
   * 
   * @param newX
   *          The new horizontal component.
   * @param newY
   *          The new vertical component.
   */
  public void moveTo(final float newX, final float newY) {
    this.x = newX;
    this.y = newY;
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
  @Override
  public Vector2D scaledBy(final float scale) {
    return new Vector2D(scale * this.x, scale * this.y);
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
  public Vector2D sumWith(final Vector2D that) {
    return new Vector2D(this.x + that.x, this.y + that.y);
  }

  /**
   * Returns a String representation of this point.
   */
  @Override
  public String toString() {
    return "(" + this.x + ", " + this.y + ")";
  }

  /**
   * Translates this vector by the specified x and y values.
   * 
   * @param deltaX
   *          The amount to translate this vector horizontally.
   * @param deltaY
   *          The amount to translate this vector vertically.
   */
  public void translate(final float deltaX, final float deltaY) {
    this.x += deltaX;
    this.y += deltaY;
  }
}
