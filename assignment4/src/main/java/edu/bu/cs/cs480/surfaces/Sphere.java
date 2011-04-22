/**
 * Sphere.java - a sphere surface object
 */
package edu.bu.cs.cs480.surfaces;

import edu.bu.cs.cs480.Matrix4x4;

/**
 * A sphere surface object.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Sphere extends UnrotatedSimpleQuadricForm {
  /** The radius of this sphere. */
  private double radius = 0;

  /**
   * Gets the radius of this sphere.
   * 
   * @return The radius of this sphere.
   */
  public double radius() {
    return this.radius;
  }

  /**
   * Sets the radius of this sphere.
   * 
   * @param radius
   *          The radius of this sphere.
   */
  public void setRadius(final double radius) {
    this.radius = radius;
  }

  @Override
  public String toString() {
    return "Sphere " + this.id();
  }

  /*
   * (non-Javadoc)
   * 
   * @see edu.bu.cs.cs480.surfaces.SimpleQuadricForm#baseMatrix()
   */
  @Override
  protected Matrix4x4 baseMatrix() {
    final Matrix4x4 result = Matrix4x4.identity();
    result.set(3, 3, -(this.radius * this.radius));
    return result;
  }

}
