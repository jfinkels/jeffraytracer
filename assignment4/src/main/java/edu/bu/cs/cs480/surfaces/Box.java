/**
 * Box.java - a rectangular prism
 */
package edu.bu.cs.cs480.surfaces;

import edu.bu.cs.cs480.Vector3D;

/**
 * A rectangular prism.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Box extends ConcreteSurfaceObject {
  /** The dimensions of this box. */
  private Vector3D dimensions = null;
  /** The triple of vectors which define the orientation of this box. */
  private Orientation orientation = null;

  /**
   * Gets the dimensions of this box.
   * 
   * @return The dimensions of this box.
   */
  public Vector3D dimensions() {
    return this.dimensions;
  }

  /**
   * Gets the orientation of this box.
   * 
   * @return The orientation of this box.
   */
  public Orientation orientation() {
    return this.orientation;
  }

  /**
   * Sets the dimensions of this box.
   * 
   * @param dimensions The dimensions of this box.
   */
  public void setDimensions(final Vector3D dimensions) {
    this.dimensions = dimensions;
  }

  /**
   * Sets the orientation of this box.
   * 
   * @param orientation The orientation of this box.
   */
  public void setOrientation(final Orientation orientation) {
    this.orientation = orientation;
  }

}
