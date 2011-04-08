/**
 * Cylinder.java - a right circular cylinder
 */
package edu.bu.cs.cs480.surfaces;

import edu.bu.cs.cs480.Directed;
import edu.bu.cs.cs480.Vector3D;

/**
 * A right circular cylinder.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Cylinder extends ConcreteSurfaceObject implements Directed {
  /** The direction of the perpendicular to the base of this cylinder. */
  private Vector3D direction = null;
  /** The height of this cylinder. */
  private double height = 0;
  /** The radius of this cylinder. */
  private double radius = 0;

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

}
