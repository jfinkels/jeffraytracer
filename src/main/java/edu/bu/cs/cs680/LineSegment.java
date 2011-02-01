package edu.bu.cs.cs680;

/**
 * A line segment which has two distinct endpoints.
 * 
 * @author Jeffrey Finkelstein <jeffreyf>
 */
public class LineSegment extends Line {
  /**
   * Instantiates this line segment with the specified endpoints.
   * 
   * @param start
   *          The initial endpoint.
   * @param end
   *          The terminal endpoint.
   */
  public LineSegment(final Vector2D start, final Vector2D end) {
    super(start, end);
  }

  /**
   * Returns true if and only if the specified point is on this line segment.
   * 
   * Algorithm: computes the distance from this vector to the specified point
   * and returns true if and only if the distance is zero.
   * 
   * @param point
   *          The point to test.
   */
  public boolean contains(final Vector2D point) {

    // the point may be at distance 0 from the extension of this line segment to
    // infinity in either direction; ensure that it is not too far away from
    // either endpoint
    if (point.distanceTo(this.first()) > this.length()
        || point.distanceTo(this.second()) > this.length()) {
      return false;
    }

    // if it is not too far away, and it is at distance 0, then this line
    // segment contains the point
    if (this.distanceTo(point) == 0.0) {
      return true;
    }

    return false;
  }

  // TODO test this method
  private double length() {
    return this.toVector().norm();
  }
}