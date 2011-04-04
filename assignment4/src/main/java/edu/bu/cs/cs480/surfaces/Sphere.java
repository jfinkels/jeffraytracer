/**
 * Sphere.java -
 */
package edu.bu.cs.cs480.surfaces;

import edu.bu.cs.cs480.Vector3D;

/**
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Sphere extends Ellipsoid {

  public Sphere() {
    this.setRadii(new Vector3D(1, 1, 1));
  }

  public void setRadius(final double radius) {
    this.setRadii(new Vector3D(radius, radius, radius));
  }

  public double radius() {
    return this.radii().x();
  }

  @Override
  public Vector3D radii() {
    throw new UnsupportedOperationException("Cannot get radii of a sphere. "
        + "Must use the radius() method.");
  }

  public void setRadii(final Vector3D radii) {
    throw new UnsupportedOperationException("Cannot set radii on a sphere. "
        + "Must use the setRadius() method.");
  }
}
