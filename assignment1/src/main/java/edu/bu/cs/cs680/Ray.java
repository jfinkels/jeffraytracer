/**
 * Ray.java
 */
package edu.bu.cs.cs680;

/**
 * A "half line", starting at a point and extending to infinity through another
 * point.
 * 
 * @author Jeffrey Finkelstein <jeffreyf>
 */
public class Ray extends Line {

  /**
   * Instantiates this ray with the specified initial point and the point
   * through which the ray passes.
   * 
   * @param start
   *          The initial point of this ray.
   * @param passesThrough
   *          The point through which this ray passes on its way to infinity.
   */
  public Ray(final Vector2D start, final Vector2D passesThrough) {
    super(start, passesThrough);
  }

  // TODO test for this method
  public Vector2D intersectionPointWith(final LineSegment line) {
    final float intersectionTime = this.intersectionTimeWith(line);
    if (Float.isNaN(intersectionTime)) {
      return null;
    }

    final Vector2D v = line.toVector();
    final Vector2D w = line.first().difference(this.first());

    // get the vector which is the point of intersection
    final Vector2D result = w.sumWith(v.scaledBy(intersectionTime)).sumWith(
        this.first());

    if (this.pointIsBehind(result)) {
      return null;
    }

    return result;
  }

  // TODO test for this method
  protected float intersectionTimeWith(final LineSegment line) {
    if (this.parallelTo(line)) {
      return Float.NaN;
    }

    final Vector2D u = line.toVector();
    final Vector2D v = this.toVector();

    // the vector from the beginning of this ray to the beginning of the
    // specified line segment
    final Vector2D w = line.first().difference(this.first());

    // the denominator cannot be zero, since we have already checked whether
    // these two lines are parallel
    return (v.y * w.x - v.x * w.y) / (v.x * u.y - v.y * u.x);
  }

  /**
   * Returns whether this ray intersects the specified line segment.
   * 
   * Using the parametric formulation of the specified line segment, we can
   * derive the time <em>t</em> at which the line created by extending the line
   * segment would intersect this ray. If <em>&epsilon;&lt;t&le;1</em>, where
   * <em>&epsilon;</em> is the intersection tolerance, then this ray intersects
   * the specified line segment.
   * 
   * @param line
   *          The line segment to test for intersection.
   * @return Whether this ray intersects the specified line segment.
   */
  public boolean intersects(final LineSegment line) {
    if (this.parallelTo(line)) {
      return false;
    }

    final Vector2D u = line.toVector();
    final Vector2D v = this.toVector();

    // the vector from the beginning of this ray to the beginning of the
    // specified line segment
    final Vector2D w = line.first().difference(this.first());

    // the denominator cannot be zero, since we have already checked whether
    // these two lines are parallel
    final float intersectionTime = (v.y * w.x - v.x * w.y)
        / (v.x * u.y - v.y * u.x);

    // get the vector which is the point of intersection
    final Vector2D intersection = w.sumWith(u.scaledBy(intersectionTime))
        .sumWith(this.first());

    // if the intersection point is behind this ray, then reject it
    if (this.pointIsBehind(intersection)) {
      return false;
    }

    // return true if and only if the intersection time is between the tolerance
    // and 1
    return (INTERSECTION_TOLERANCE < intersectionTime)
        && (intersectionTime <= 1);
  }

  /**
   * Returns true if and only if the specified point is behind this ray.
   * 
   * Pre-condition: the specified point is on the infinite line on which this
   * ray lies.
   * 
   * Algorithm: given a point, we check if either the change in x or the change
   * in y from the start of the ray to that point is the same as or different
   * from the change in x or the change in y between the two points which define
   * this ray.
   * 
   * @param point
   *          The point on the line on which this ray lies to test for
   *          opposite-ness.
   * @return True if and only if the point is behind this ray.
   */
  protected boolean pointIsBehind(final Vector2D point) {
    float thisDeltaX = this.second().x - this.first().x;
    float thisDeltaY = this.second().y - this.first().y;
    float otherDeltaX = point.x - this.first().x;
    float otherDeltay = point.y - this.first().y;

    // if the sign of the change in either x or y is different between this
    // vector and the vector from the first endpoint of this vector to the
    // specified point, then the specified point is in the opposite direction
    return (Math.signum(thisDeltaX) == -Math.signum(otherDeltaX) || Math
        .signum(thisDeltaY) == -Math.signum(otherDeltay));

  }
}
