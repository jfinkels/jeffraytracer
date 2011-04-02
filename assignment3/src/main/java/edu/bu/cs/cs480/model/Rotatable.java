/**
 * Rotatable.java - an object which can rotate about three axes
 */
package edu.bu.cs.cs480.model;

/**
 * An object which can rotate about the x, y, or z axes.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public interface Rotatable {

  /**
   * Rotates this object around the specified axis by the specified angle.
   * 
   * @param axis
   *          The axis of rotation.
   * @param angleDelta
   *          The angle by which to rotate this object.
   */
  void rotate(final Point3D axis, final double angle);

  /**
   * Rotates this object around the specified axis immediately to the specified
   * angle.
   * 
   * @param axis
   *          The axis of rotation.
   * @param angle
   *          The angle to which to rotate this object.
   */
  void rotateTo(final Point3D axis, final double angle);
}
