/**
 * Plane.java - a plane represented as a quadric surface
 */
package edu.bu.cs.cs480.surfaces;

import edu.bu.cs.cs480.PositionableTracerObject;
import edu.bu.cs.cs480.Ray;
import edu.bu.cs.cs480.Vector3D;

/**
 * A plane in three dimensions which is component of a larger surface object.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
class Plane extends PositionableTracerObject implements SurfaceObject {
  /** The scalar value in the equation of this plane. */
  private final double d;
  /** The normal to the plane. */
  private final Vector3D normal;
  /** The surface object which contains this plane. */
  private final ConcreteSurfaceObject containingObject;

  /**
   * Instantiates this plane with the specified normal vector and scalar
   * coefficient (in the plane equation ax + by + cz + d = 0).
   * 
   * @param normal
   *          The unit vector normal to this plane.
   * @param d
   *          The scalar coefficient.
   * @param containingObject
   *          The surface object of which this plane is a component.
   */
  Plane(final Vector3D normal, final double d,
      final ConcreteSurfaceObject containingObject) {
    this.normal = normal;
    this.d = d;
    this.containingObject = containingObject;
  }

  /**
   * {@inheritDoc}
   * 
   * @see edu.bu.cs.cs480.surfaces.SurfaceObject#compile()
   */
  @Override
  public void compile() {
    // intentionally unimplemented; everything is done in the constructor
  }

  /**
   * {@inheritDoc}
   * 
   * @param ray
   *          {@inheritDoc}
   * @return {@inheritDoc}
   * @see edu.bu.cs.cs480.surfaces.SurfaceObject#interceptWith(edu.bu.cs.cs480.Ray)
   */
  @Override
  public Intercept interceptWith(final Ray ray) {
    final double temp1 = this.normal.dotProduct(ray.position());
    final double temp2 = this.normal.dotProduct(ray.direction());
    final double time = -(this.d + temp1) / (temp2);
    final Intercept result = new Intercept(ray, time, this.containingObject,
        this.normal);
    return result;
  }

  /**
   * Returns {@code true} if and only if the specified point is above this plane
   * (with respect to its normal).
   * 
   * @param point
   *          The point to test.
   * @return {@code true} if and only if the specified point is above this plane
   *         (with respect to its normal).
   */
  @Override
  public boolean outside(final Vector3D point) {
    return this.normal.dotProduct(point) + this.d > TOLERANCE;
  }

  // TODO extract this to a superclass
  public static final double TOLERANCE = Double.MIN_VALUE;

  /**
   * Returns {@code true} if and only if the specified point is below this plane
   * (with respect to its normal).
   * 
   * @param point
   *          The point to test.
   * @return {@code true} if and only if the specified point is below this plane
   *         (with respect to its normal).
   */
  @Override
  public boolean inside(final Vector3D point) {
    return this.normal.dotProduct(point) + this.d < -TOLERANCE;
  }

  /**
   * Returns a String representation of this plane.
   * 
   * @return A String representation of this plane.
   */
  @Override
  public String toString() {
    return "Plane[" + this.normal.x() + "x + " + this.normal.y() + "y + "
        + this.normal.z() + "z + " + this.d + "]";
  }
}
