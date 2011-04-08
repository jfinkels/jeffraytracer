/**
 * Orientation.java - a three dimensional vector basis defining an orientation
 */
package edu.bu.cs.cs480.surfaces;

import edu.bu.cs.cs480.Vector3D;

/**
 * A three dimensional vector basis defining an orientation.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Orientation {
  /** The u vector. */
  private final Vector3D u;
  /** The v vector. */
  private final Vector3D v;
  /** The w vector. */
  private final Vector3D w;

  /**
   * Instantiates this orientation with the three specified vectors as a basis.
   * 
   * @param u
   *          The u vector.
   * @param v
   *          The v vector.
   * @param w
   *          The w vector.
   */
  public Orientation(final Vector3D u, final Vector3D v, final Vector3D w) {
    this.u = u;
    this.v = v;
    this.w = w;
  }

  /**
   * The u vector of this orientation.
   * 
   * @return The u vector of this orientation.
   */
  public Vector3D u() {
    return this.u;
  }

  /**
   * The v vector of this orientation.
   * 
   * @return The v vector of this orientation.
   */
  public Vector3D v() {
    return this.v;
  }

  /**
   * The w vector of this orientation.
   * 
   * @return The w vector of this orientation.
   */
  public Vector3D w() {
    return this.w;
  }
}