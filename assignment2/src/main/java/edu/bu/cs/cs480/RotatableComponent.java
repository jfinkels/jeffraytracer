/**
 * 
 */
package edu.bu.cs.cs480;

/**
 * @author Jeffrey Finkelstein
 *
 */
public class RotatableComponent implements Rotatable {

  /**
   * Sets the minimum angle to which this joint can be rotated around the x
   * axis.
   * 
   * @param newXNegativeExtent
   *          The minimum angle to which this joint can be rotated around the x
   *          axis.
   */
  public void setXNegativeExtent(final double newXNegativeExtent) {
    this.xNegativeExtent = newXNegativeExtent;
  }

  /**
   * Sets the maximum angle to which this joint can be rotated around the x
   * axis.
   * 
   * @param newXPositiveExtent
   *          The maximum angle to which this joint can be rotated around the x
   *          axis.
   */
  public void setXPositiveExtent(final double newXPositiveExtent) {
    this.xPositiveExtent = newXPositiveExtent;
  }

  /**
   * Sets the minimum angle to which this joint can be rotated around the y
   * axis.
   * 
   * @param newYNegativeExtent
   *          The minimum angle to which this joint can be rotated around the y
   *          axis.
   */
  public void setYNegativeExtent(final double newYNegativeExtent) {
    this.yNegativeExtent = newYNegativeExtent;
  }

  /**
   * Sets the maximum angle to which this joint can be rotated around the y
   * axis.
   * 
   * @param newYPositiveExtent
   *          The maximum angle to which this joint can be rotated around the y
   *          axis.
   */
  public void setYPositiveExtent(final double newYPositiveExtent) {
    this.yPositiveExtent = newYPositiveExtent;
  }

  /**
   * Sets the minimum angle to which this joint can be rotated around the z
   * axis.
   * 
   * @param newZNegativeExtent
   *          The minimum angle to which this joint can be rotated around the z
   *          axis.
   */
  public void setZNegativeExtent(final double newZNegativeExtent) {
    this.zNegativeExtent = newZNegativeExtent;
  }

  /**
   * Sets the maximum angle to which this joint can be rotated around the z
   * axis.
   * 
   * @param newZPositiveExtent
   *          The maximum angle to which this joint can be rotated around the z
   *          axis.
   */
  public void setZPositiveExtent(final double newZPositiveExtent) {
    this.zPositiveExtent = newZPositiveExtent;
  }
  /**
   * Gets the current angle at which this joint is rotated around the x axis.
   * 
   * @return The current angle at which this joint is rotated around the x axis.
   */
  public double xAngle() {
    return this.xAngle;
  }

  /**
   * Gets the current angle at which this joint is rotated around the y axis.
   * 
   * @return The current angle at which this joint is rotated around the y axis.
   */
  public double yAngle() {
    return this.yAngle;
  }
  /** The current angle at which this joint is rotated around the x axis. */
  private double xAngle = 0.0;
  /** The minimum angle to which this joint can be rotated around the x axis. */
  private double xNegativeExtent = 0;
  /** The maximum angle to which this joint can be rotated around the x axis. */
  private double xPositiveExtent = 180;
  /** The current angle at which this joint is rotated around the y axis. */
  private double yAngle = 0.0;
  /** The minimum angle to which this joint can be rotated around the y axis. */
  private double yNegativeExtent = 0;
  /** The maximum angle to which this joint can be rotated around the y axis. */
  private double yPositiveExtent = 180;
  /** The current angle at which this joint is rotated around the z axis. */
  private double zAngle = 0.0;
  /** The minimum angle to which this joint can be rotated around the z axis. */
  private double zNegativeExtent = 0;
  /** The maximum angle to which this joint can be rotated around the z axis. */
  private double zPositiveExtent = 180;

  /**
   * Rotates this joint around the specified axis by the specified angle.
   * 
   * @param axis
   *          The axis of rotation.
   * @param angleDelta
   *          The angle by which to rotate this joint.
   */
  public void rotate(final Axis axis, final double angleDelta) {
    if (axis.equals(Axis.X)) {
      this.xAngle += angleDelta;
      this.xAngle = Math.min(this.xAngle, this.xPositiveExtent);
      this.xAngle = Math.max(this.xAngle, this.xNegativeExtent);
    } else if (axis.equals(Axis.Y)) {
      this.yAngle += angleDelta;
      this.yAngle = Math.min(this.yAngle, this.yPositiveExtent);
      this.yAngle = Math.max(this.yAngle, this.yNegativeExtent);
    } else if (axis.equals(Axis.Z)) {
      this.zAngle += angleDelta;
      this.zAngle = Math.min(this.zAngle, this.zPositiveExtent);
      this.zAngle = Math.max(this.zAngle, this.zNegativeExtent);
    }
  }
  /**
   * Gets the current angle at which this joint is rotated around the z axis.
   * 
   * @return The current angle at which this joint is rotated around the z axis.
   */
  public double zAngle() {
    return this.zAngle;
  }
}
