/**
 * Intercept.java - the intercept of a ray with a surface object at a time
 */
package edu.bu.cs.cs480;

import edu.bu.cs.cs480.surfaces.SurfaceObject;

/**
 * The intercept of a ray with a surface object at a specific time.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Intercept implements Comparable<Intercept> {
  /** The surface object which is intercepted by a ray. */
  private final SurfaceObject surfaceObject;
  /** The time at which the ray intercepts the surface object. */
  private final double time;
  /** The ray which intercepts the surface object at a time. */
  private final Ray ray;

  /**
   * Gets the point on the surface object at which this intercept occurs.
   * 
   * @return The point on the surface object at which this intercept occurs.
   */
  public Vector3D pointOfIntersection() {
    return this.ray.position()
        .sumWith(this.ray.direction().scaledBy(this.time));
  }

  /**
   * Instantiates this intercept with the specified surface object at the
   * specified time.
   * 
   * @param ray
   *          The ray which intercepts the surface object at the specified time.
   * @param time
   *          The time at which a ray intercepts the surface object.
   * @param surfaceObject
   *          The surface object which a ray intercepts.
   */
  public Intercept(final Ray ray, final double time,
      final SurfaceObject surfaceObject) {
    this.ray = ray;
    this.time = time;
    this.surfaceObject = surfaceObject;
  }

  /**
   * Compares the intercept times of this and the specified other intercept.
   * 
   * @param that
   *          The other intercept.
   * @return The result of comparing the intercept times of this and the
   *         specified other intercept.
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  @Override
  public int compareTo(final Intercept that) {
    return Double.compare(this.time, that.time);
  }

  /**
   * Gets the surface object which a ray intercepts.
   * 
   * @return The surface object which a ray intercepts.
   */
  public SurfaceObject surfaceObject() {
    return this.surfaceObject;
  }

  /**
   * Gets the time at which a ray intercepts the surface object.
   * 
   * @return The time at which a ray intercepts the surface object.
   */
  public double time() {
    return this.time;
  }

  @Override
  public String toString() {
    return "Intercept[" + this.surfaceObject + " at " + this.time + "]";
  }
}
