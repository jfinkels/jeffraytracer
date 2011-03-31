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
public class BaseRotatable extends BaseAngled implements Rotatable {
  /** The minimum angle to which this joint can be rotated around the x axis. */
  private double xBackwardExtent = -360;
  /** The maximum angle to which this joint can be rotated around the x axis. */
  private double xForwardExtent = 360;
  /** The minimum angle to which this joint can be rotated around the y axis. */
  private double yBackwardExtent = -360;
  /** The maximum angle to which this joint can be rotated around the y axis. */
  private double yForwardExtent = 360;
  /** The minimum angle to which this joint can be rotated around the z axis. */
  private double zBackwardExtent = -360;
  /** The maximum angle to which this joint can be rotated around the z axis. */
  private double zForwardExtent = 360;

  /**
   * {@inheritDoc}
   * 
   * @param newXBackwardExtent
   *          {@inheritDoc}
   */
  @Override
  public void setXBackwardExtent(final double newXBackwardExtent) {
    this.xBackwardExtent = newXBackwardExtent;
  }

  /**
   * {@inheritDoc}
   * 
   * @param newXForwardExtent
   *          {@inheritDoc}
   */
  @Override
  public void setXForwardExtent(final double newXForwardExtent) {
    this.xForwardExtent = newXForwardExtent;
  }

  /**
   * {@inheritDoc}
   * 
   * @param newYBackwardExtent
   *          {@inheritDoc}
   */
  @Override
  public void setYBackwardExtent(final double newYBackwardExtent) {
    this.yBackwardExtent = newYBackwardExtent;
  }

  /**
   * {@inheritDoc}
   * 
   * @param newYForwardExtent
   *          {@inheritDoc}
   */
  @Override
  public void setYForwardExtent(final double newYForwardExtent) {
    this.yForwardExtent = newYForwardExtent;
  }

  /**
   * {@inheritDoc}
   * 
   * @param newZBackwardExtent
   *          {@inheritDoc}
   */
  @Override
  public void setZBackwardExtent(final double newZBackwardExtent) {
    this.zBackwardExtent = newZBackwardExtent;
  }

  /**
   * {@inheritDoc}
   * 
   * @param newZForwardExtent
   *          {@inheritDoc}
   */
  @Override
  public void setZForwardExtent(final double newZForwardExtent) {
    this.zForwardExtent = newZForwardExtent;
  }

  /**
   * {@inheritDoc}
   * 
   * @param axis
   *          {@inheritDoc}
   * @param angleDeltan
   *          {@inheritDoc}
   */
  @Override
  public void rotate(final Axis axis, final double angleDelta) {
    double newAngle;
    switch (axis) {
    case X:
      newAngle = this.xAngle() + angleDelta;
      newAngle = Math.min(newAngle, this.xForwardExtent);
      newAngle = Math.max(newAngle, this.xBackwardExtent);
      this.setXAngle(newAngle);
      break;
    case Y:
      newAngle = this.yAngle() + angleDelta;
      newAngle = Math.min(newAngle, this.yForwardExtent);
      newAngle = Math.max(newAngle, this.yBackwardExtent);
      this.setYAngle(newAngle);
      break;
    case Z:
      newAngle = this.zAngle() + angleDelta;
      newAngle = Math.min(newAngle, this.zForwardExtent);
      newAngle = Math.max(newAngle, this.zBackwardExtent);
      this.setZAngle(newAngle);
      break;
    default:
      throw new IllegalArgumentException("Unknown axis type: " + axis);
    }
  }

}
