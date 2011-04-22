/**
 * Ellipsoid.java - an ellipsoid surface object
 */
package edu.bu.cs.cs480.surfaces;

import edu.bu.cs.cs480.Intercept;
import edu.bu.cs.cs480.Matrix4x4;
import edu.bu.cs.cs480.Ray;
import edu.bu.cs.cs480.Vector3D;

/**
 * An ellipsoid surface object.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Ellipsoid extends UnrotatedSimpleQuadricForm {

  /** The radii of the x, y, and z axes of this ellipsoid. */
  private Vector3D radii;

  /**
   * Gets the radii of the x, y, and z axes of this ellipsoid.
   * 
   * @return The radii of the x, y, and z axes of this ellipsoid.
   */
  public Vector3D radii() {
    return this.radii;
  }

  /**
   * Sets the radii of the x, y, and z axes of this ellipsoid.
   * 
   * @param radii
   *          The radii of the x, y, and z axes of this ellipsoid.
   */
  public void setRadii(final Vector3D radii) {
    this.radii = radii;
  }

}
