/**
 * Directed.java - an object which has a direction
 */
package edu.bu.cs.cs480;

/**
 * An object which has a direction.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public interface Directed {
  /**
   * Gets the direction of this object.
   * 
   * @return The direction of this object.
   */
  Vector3D direction();

  /**
   * Sets the direction of this object.
   * 
   * @param direction
   *          The direction of this object.
   */
  void setDirection(final Vector3D direction);
}
