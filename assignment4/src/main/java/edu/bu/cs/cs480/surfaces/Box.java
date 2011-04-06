/**
 * Box.java -
 */
package edu.bu.cs.cs480.surfaces;

import edu.bu.cs.cs480.Vector3D;

/**
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Box extends SurfaceObject {
  private Orientation orientation = null;
  private Vector3D dimensions = null;

  /**
   * @return the orientation
   */
  public Orientation orientation() {
    return this.orientation;
  }

  /**
   * @param orientation
   *          the orientation to set
   */
  public void setOrientation(Orientation orientation) {
    this.orientation = orientation;
  }

  /**
   * @return the dimensions
   */
  public Vector3D dimensions() {
    return this.dimensions;
  }

  /**
   * @param dimensions
   *          the dimensions to set
   */
  public void setDimensions(Vector3D dimensions) {
    this.dimensions = dimensions;
  }

}
