/**
 * Sphere.java -
 */
package edu.bu.cs.cs480.surfaces;

/**
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Sphere extends ConcreteSurfaceObject {

  private double radius = 0;

  public double radius() {
    return this.radius;
  }

  public void setRadius(final double radius) {
    this.radius = radius;
  }

}
