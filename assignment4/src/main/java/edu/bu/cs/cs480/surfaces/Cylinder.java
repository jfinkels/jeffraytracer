/**
 * Cylinder.java - a right circular cylinder
 */
package edu.bu.cs.cs480.surfaces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.bu.cs.cs480.Directed;
import edu.bu.cs.cs480.Intercept;
import edu.bu.cs.cs480.Ray;
import edu.bu.cs.cs480.Vector3D;

/**
 * A right circular cylinder.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Cylinder extends SimpleQuadricForm implements Directed {
  /** The direction of the perpendicular to the base of this cylinder. */
  private Vector3D direction = null;
  /** The height of this cylinder. */
  private double height = 0;
  /** The radius of this cylinder. */
  private double radius = 0;

  private Plane top = null;
  private Plane bottom = null;

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * 
   * @see edu.bu.cs.cs480.Directed#direction()
   */
  @Override
  public Vector3D direction() {
    return this.direction;
  }

  /**
   * Gets the height of this cylinder.
   * 
   * @return The height of this cylinder.
   */
  public double length() {
    return this.height;
  }

  /**
   * Gets the radius of this cylinder.
   * 
   * @return The radius of this cylinder.
   */
  public double radius() {
    return this.radius;
  }

  /**
   * {@inheritDoc}
   * 
   * @param direction
   *          {@inheritDoc}
   * @see edu.bu.cs.cs480.Directed#setDirection(edu.bu.cs.cs480.Vector3D)
   */
  @Override
  public void setDirection(final Vector3D direction) {
    this.direction = direction;
  }

  /**
   * Sets the height of this cylinder.
   * 
   * @param height
   *          The height of this cylinder.
   */
  public void setLength(final double length) {
    this.height = length;
  }

  /**
   * Sets the radius of this cylinder.
   * 
   * @param radius
   *          The radius of this cylinder.
   */
  public void setRadius(double radius) {
    this.radius = radius;
  }

  @Override
  public Intercept interceptWith(final Ray ray) {
    final List<Intercept> possibleIntercepts = new ArrayList<Intercept>();
    // only use the intercept if it is not null and between the top and bottom
    // planes
    Intercept intercept = super.interceptWith(ray);
    if (intercept != null) {
      final Vector3D pointOfIntersection = Intercept.pointOfIntersection(ray,
          intercept.time());
      if (this.top.pointIsBelow(pointOfIntersection)
          && this.bottom.pointIsBelow(pointOfIntersection)) {
        possibleIntercepts.add(intercept);
      }
    }

    // get the intercept with the top and bottom planes
    intercept = this.top.interceptWith(ray);
    if (intercept != null) {
      possibleIntercepts.add(intercept);
    }
    intercept = this.bottom.interceptWith(ray);
    if (intercept != null) {
      possibleIntercepts.add(intercept);
    }
    
    // if there are no intercepts, return null. otherwise return the minimum
    if (possibleIntercepts.isEmpty()) {
      return null;
    }
    
    return Collections.min(possibleIntercepts);
  }

  /*
   * (non-Javadoc)
   * 
   * @see edu.bu.cs.cs480.surfaces.SurfaceObject#compile()
   */
  @Override
  public void compile() {
    // create the top and bottom planes
    // TODO check if this is the right way to get the bottom and top planes
    final Vector3D pointOnTopPlane = this.position().sumWith(
        this.direction.normalized().scaledBy(this.height / 2));
    final Vector3D pointOnBottomPlane = this.position().sumWith(
        this.direction.normalized().scaledBy(-(this.height / 2)));
    this.top = new Plane(this.direction,
        -this.direction.dotProduct(pointOnTopPlane));
    this.bottom = new Plane(this.direction.scaledBy(-1),
        this.direction.dotProduct(pointOnBottomPlane));

    // initially, this will be a cylinder along the y axis
    this.matrix().set(0, 0, 1);
    this.matrix().set(2, 2, 1);
    this.matrix().set(3, 3, -(this.radius * this.radius));

    // need to multiply matrix on the left and right by inverse rotation
    // applied to the point
  }
}
