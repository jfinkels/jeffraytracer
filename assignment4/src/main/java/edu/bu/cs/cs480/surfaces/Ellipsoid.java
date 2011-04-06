/**
 * Ellipsoid.java -
 */
package edu.bu.cs.cs480.surfaces;

import edu.bu.cs.cs480.Vector3D;

/**
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Ellipsoid extends ConcreteSurfaceObject {

  private Vector3D radii;

  /**
   * @return the radii
   */
  public Vector3D radii() {
    return this.radii;
  }

  /**
   * @param radii
   *          the radii to set
   */
  public void setRadii(Vector3D radii) {
    this.radii = radii;
  }

}
