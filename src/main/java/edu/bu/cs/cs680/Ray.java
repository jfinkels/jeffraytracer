package edu.bu.cs.cs680;

/**
 * A "half line", starting at a point and extending to infinity through another
 * point.
 * 
 * @author Jeffrey Finkelstein <jeffreyf>
 */
public class Ray extends Line {
  /**
   * The tolerance to use when determining intersection of a ray with a line
   * segment. This is necessary for the case in which the ray passes through a
   * vertex shared by two edges, so that it intersects only one of the edges.
   */
  public static final float INTERSECTION_TOLERANCE = Float.MIN_VALUE;

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

    // if the edge is behind this ray, then reject it
    if (this.first().distanceTo(intersection) < this.second().distanceTo(
        intersection)) {
      return false;
    }

    // return true if and only if the intersection time is between the tolerance
    // and 1
    return (INTERSECTION_TOLERANCE < intersectionTime)
        && (intersectionTime <= 1);
  }
}
