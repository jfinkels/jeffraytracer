package edu.bu.cs.cs680;

/**
 * A line or line segment defined by two points in two-dimensional space.
 * 
 * @author Jeffrey Finkelstein <jeffreyf>
 */
public abstract class Line {
  /** The first point of this line segment. */
  private final Vector2D v1;
  /** The second point of this line segment. */
  private final Vector2D v2;

  /**
   * Instantiates this line with the two specified points.
   * 
   * @param v1
   *          The first point.
   * @param v2
   *          The second point.
   */
  public Line(final Vector2D v1, final Vector2D v2) {
    this.v1 = v1;
    this.v2 = v2;
  }

  /**
   * Returns the shortest distance from this line to the specified point.
   * 
   * Algorithm: computes the length of the projection of the vector which is the
   * difference between the first point on this line to the specified other
   * point onto the perpendicular from the point to the line.
   * 
   * For this method, we assume this line is extended to infinity in both
   * directions.
   * 
   * @param point
   *          The point whose distance will be determined.
   * @return The shortest distance from the specified point to this line.
   */
  public double distanceTo(final Vector3D point) {
    final Vector3D thisVector = this.toVector();
    return thisVector.crossProduct(this.first().difference(point)).norm()
        / thisVector.norm();
  }

  /**
   * Gets the first point of this line.
   * 
   * @return The first point of this line.
   */
  protected Vector2D first() {
    return this.v1;
  }

  /**
   * Returns true if and only if this line is horizontal in terms of its y
   * component (that is, the y components of its two defining points are equal).
   * 
   * @return True if and only if this line is horizontal in terms of its y
   *         component.
   */
  public boolean isHorizontal() {
    return this.v1.y == this.v2.y;
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
  public boolean parallelTo(final Line that) {
    final Vector2D v1 = this.toVector();
    final Vector2D v2 = that.toVector();
    return ((v1.x * v2.y) - (v1.y * v2.x) == 0)
        && ((v1.x * v2.z) - (v1.y * v2.z) == 0);
  }

  /**
   * Gets the second point of this line.
   * 
   * @return The second point of this line.
   */
  protected Vector2D second() {
    return this.v2;
  }

  /**
   * Returns the String representation of this line.
   * 
   * @return The String representation of this line.
   */
  @Override
  public String toString() {
    return "[" + this.v1 + ", " + this.v2 + "]";
  }

  /**
   * Returns the vector between the two points specified in the constructor of
   * this class.
   * 
   * @return The vector between the two points specified in the constructor of
   *         this class.
   */
  public Vector2D toVector() {
    return this.v2.difference(this.v1);
  }
}
