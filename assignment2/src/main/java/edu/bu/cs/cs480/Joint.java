/**
 * Joint.java - a single joint of a finger which can be rotated
 */
package edu.bu.cs.cs480;

/**
 * A joint which can be rotated.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 */
public class Joint extends SolidCylinder {

  public static final int NUM_STEPS = 100;

  public FloatColor color() {
    return this.color;
  }

  /**
   * @param x
   * @param y
   * @param z
   * @param radius
   * @param height
   */
  public Joint(final double x, final double y, final double z,
      final double radius, final double height) {
    super(x, y, z, radius, height, NUM_STEPS);
  }

  /** The current angle at which this joint is rotated. */
  private double xAngle = 0.0;
  private double yAngle = 0.0;
  private double zAngle = 0.0;

  private double xPositiveExtent = 45;
  private double xNegativeExtent = 45;
  private double yPositiveExtent = 45;
  private double yNegativeExtent = 45;
  private double zPositiveExtent = 45;
  private double zNegativeExtent = 45;

  public void setXPositiveExtent(final double newXPositiveExtent) {
    this.xPositiveExtent = newXPositiveExtent;
  }

  public void setXNegativeExtent(final double newXNegativeExtent) {
    this.xNegativeExtent = newXNegativeExtent;
  }

  public void setYPositiveExtent(final double newYPositiveExtent) {
    this.yPositiveExtent = newYPositiveExtent;
  }

  public void setYNegativeExtent(final double newYNegativeExtent) {
    this.yNegativeExtent = newYNegativeExtent;
  }

  public void setZPositiveExtent(final double newZPositiveExtent) {
    this.zPositiveExtent = newZPositiveExtent;
  }

  public void setZNegativeExtent(final double newZNegativeExtent) {
    this.zNegativeExtent = newZNegativeExtent;
  }

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

  public double xAngle() {
    return this.xAngle;
  }

  public double yAngle() {
    return this.yAngle;
  }

  public double zAngle() {
    return this.zAngle;
  }

  /**
   * @param color
   */
  public void setColor(final FloatColor color) {
    this.color = color;
  }

  private FloatColor color = new FloatColor(.8f, .5f, .2f);
}
