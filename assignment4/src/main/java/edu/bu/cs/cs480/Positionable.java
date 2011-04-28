/**
 * Positionable.java - an object which has a position
 */
package edu.bu.cs.cs480;

import edu.bu.cs.cs480.vectors.Vector3D;

/**
 * An object which has a position.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public interface Positionable {
  /**
   * Gets the position of this object.
   * 
   * @return The position of this object.
   */
  Vector3D position();

  /**
   * Sets the position of this object.
   * 
   * @param position
   *          The position of this object.
   */
  void setPosition(final Vector3D position);
}
