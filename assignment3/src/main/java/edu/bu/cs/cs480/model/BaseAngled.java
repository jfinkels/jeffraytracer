/**
 * BaseAngled.java - base class for an object with angles around three axes
 */
package edu.bu.cs.cs480.model;

/**
 * A base class for an object which has angles around three axes.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class BaseAngled implements Angled {
  /** The current angle at which this joint is rotated around the x axis. */
  private double xAngle = 0.0;
  /** The current angle at which this joint is rotated around the y axis. */
  private double yAngle = 0.0;
  /** The current angle at which this joint is rotated around the z axis. */
  private double zAngle = 0.0;

  /**
   * Instantiates this object with no rotations around any axis.
   */
  BaseAngled() {
    this(0, 0, 0);
  }

  /**
   * Instantiates this object with the specified initial angles around the x, y,
   * and z axes.
   * 
   * @param xAngle
   *          The initial angle around the x axis.
   * @param yAngle
   *          The initial angle around the y axis.
   * @param zAngle
   *          The initial angle around the z axis.
   */
  BaseAngled(final double xAngle, final double yAngle, final double zAngle) {
    this.xAngle = xAngle;
    this.yAngle = yAngle;
    this.zAngle = zAngle;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   */
  @Override
  public double xAngle() {
    return this.xAngle;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   */
  @Override
  public double yAngle() {
    return this.yAngle;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   */
  @Override
  public double zAngle() {
    return this.zAngle;
  }

  /**
   * {@inheritDoc}
   * 
   * @param xAngle
   *          {@inheritDoc}
   */
  @Override
  public void setXAngle(final double xAngle) {
    this.xAngle = xAngle;
  }

  /**
   * {@inheritDoc}
   * 
   * @param yAngle
   *          {@inheritDoc}
   */
  @Override
  public void setYAngle(final double yAngle) {
    this.yAngle = yAngle;
  }

  /**
   * {@inheritDoc}
   * 
   * @param zAngle
   *          {@inheritDoc}
   */
  @Override
  public void setZAngle(final double zAngle) {
    this.zAngle = zAngle;
  }

}
