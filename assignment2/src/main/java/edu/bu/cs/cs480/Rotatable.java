/**
 * Rotatable.java - an object which can rotate about three axes
 */
package edu.bu.cs.cs480;

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
   *          The angle by which to rotate this joint.
   */
  void rotate(final Axis axis, final double angleDelta);
  double xAngle();
  double yAngle();
  double zAngle();
  void setXNegativeExtent(final double newXNegativeExtent);
  void setYNegativeExtent(final double newYNegativeExtent);
  void setZNegativeExtent(final double newZNegativeExtent);
  void setXPositiveExtent(final double newXPositiveExtent);
  void setYPositiveExtent(final double newYPositiveExtent);
  void setZPositiveExtent(final double newZPositiveExtent);
   

}
