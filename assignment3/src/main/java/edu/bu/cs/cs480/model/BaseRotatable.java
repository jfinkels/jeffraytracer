/**
 * BaseRotatable.java - a base class for a rotatable object
 */
package edu.bu.cs.cs480.model;

/**
 * A base class for a rotatable object.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class BaseRotatable implements Rotatable {
  private Quaternion rotation = new Quaternion();

  /**
   * {@inheritDoc}
   * 
   * @param axis
   *          {@inheritDoc}
   * @param angle
   *          {@inheritDoc}
   */
  @Override
  public void rotate(final Point3D axis, final double angle) {
    this.rotation = this.rotation.multiply(new Quaternion(axis, angle));
    this.rotation.normalize();
  }

  /**
   * {@inheritDoc}
   * 
   * @param axis
   *          {@inheritDoc}
   * @param angle
   *          {@inheritDoc}
   */
  @Override
  public void rotateTo(final Point3D axis, final double angle) {
    this.rotation = new Quaternion(axis, angle);
  }

  /**
   * Returns the quaternion which represents the current rotation of this
   * object.
   * 
   * @return The quaternion which represents the current rotation of this
   *         object.
   */
  public Quaternion rotation() {
    return this.rotation;
  }
}
