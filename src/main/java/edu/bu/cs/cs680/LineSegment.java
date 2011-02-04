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
   * Returns the point at which this line segment intersects the horizontal at
   * the specified y value.
   * 
   * If one of the endpoints has the specified y value, then this method returns
   * that endpoint.
   * 
   * Algorithm: use the parametric form of the line segment to solve a system of
   * linear equations which shows where the point of intersection is.
   * 
   * Pre-condition: this line segment is not horizontal. Otherwise a divide by
   * zero error will occur.
   * 
   * @param yvalue
   *          The y value at which to intersect this line segment with a
   *          horizontal.
   * @return The point at which this line segment intersects the horizontal at
   *         the specified y value.
   */
  public Vector2D intersectionWithHorizontalAt(final float yvalue) {
    if (this.first().y == yvalue) {
      return this.first();
    }
    if (this.second().y == yvalue) {
      return this.second();
    }
    final float t = (yvalue - this.first().y)
        / (this.second().y - this.first().y);
    final float xvalue = this.first().x + t
        * (this.second().x - this.first().x);
    return new Vector2D(xvalue, yvalue);
  }

  /**
   * Returns the endpoint with the least y component, or if the y components are
   * equal, the second endpoint.
   * 
   * @return The endpoint with the least y component.
   */
  public Vector2D lowerEndpoint() {
    if (this.first().y < this.second().y) {
      return this.first();
    } else {
      return this.second();
    }
  }

  /**
   * Returns the endpoint with the greatest y component, or if the y components
   * are equal, the second endpoint.
   * 
   * @return The endpoint with the greatest y component.
   */
  public Vector2D upperEndpoint() {
    if (this.first().y > this.second().y) {
      return this.first();
    } else {
      return this.second();
    }
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

  /**
   * Returns the endpoint of this line segment which is the one opposite the
   * specified endpoint, or {@code null} if the specified endpoint is not
   * actually an endpoint of this line segment.
   * 
   * @param endpoint
   *          The endpoint opposite this endpoint will be returned.
   * @return The endpoint of this line segment which is the one opposite the
   *         specified endpoint, or {@code null} if the specified endpoint is
   *         not actually an endpoint of this line segment.
   */
  public Vector2D otherEndpoint(final Vector2D endpoint) {
    if (this.first().equalTo(endpoint)) {
      return this.second();
    }

    if (this.second().equalTo(endpoint)) {
      return this.first();
    }

    return null;
  }

  /**
   * Gets the length of this line segment.
   * 
   * Algorithm: compute the norm of the vector from the first point to the
   * second point.
   * 
   * @return The length of this line segment.
   */
  protected double length() {
    return this.toVector().norm();
  }
}