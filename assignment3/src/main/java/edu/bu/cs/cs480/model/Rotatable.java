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
public interface Rotatable extends Angled {

  /**
   * Rotates this object around the specified axis by the specified angle.
   * 
   * @param axis
   *          The axis of rotation.
   * @param angleDelta
   *          The angle by which to rotate this joint.
   */
  void rotate(final Axis axis, final double angleDelta);
  
  /**
   * Sets the minimum angle to which this joint can be rotated around the x
   * axis.
   * 
   * @param newXBackwardExtent
   *          The minimum angle to which this joint can be rotated around the x
   *          axis.
   */
  void setXBackwardExtent(final double newXBackwardExtent);

  /**
   * Sets the maximum angle to which this joint can be rotated around the x
   * axis.
   * 
   * @param newXForwardExtent
   *          The maximum angle to which this joint can be rotated around the x
   *          axis.
   */
  void setXForwardExtent(final double newXForwardExtent);

  /**
   * Sets the minimum angle to which this joint can be rotated around the y
   * axis.
   * 
   * @param newYBackwardExtent
   *          The minimum angle to which this joint can be rotated around the y
   *          axis.
   */
  void setYBackwardExtent(final double newYBackwardExtent);
  /**
   * Sets the maximum angle to which this joint can be rotated around the y
   * axis.
   * 
   * @param newYForwardExtent
   *          The maximum angle to which this joint can be rotated around the y
   *          axis.
   */
  void setYForwardExtent(final double newYForwardExtent);

  /**
   * Sets the minimum angle to which this joint can be rotated around the z
   * axis.
   * 
   * @param newZBackwardExtent
   *          The minimum angle to which this joint can be rotated around the z
   *          axis.
   */
  void setZBackwardExtent(final double newZBackwardExtent);

  /**
   * Sets the maximum angle to which this joint can be rotated around the z
   * axis.
   * 
   * @param newZForwardExtent
   *          The maximum angle to which this joint can be rotated around the z
   *          axis.
   */
  void setZForwardExtent(final double newZForwardExtent);

}
