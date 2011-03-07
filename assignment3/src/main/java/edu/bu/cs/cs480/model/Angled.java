/**
 * Angled.java - an object which has angles around three axes
 */
package edu.bu.cs.cs480.model;

/**
 * An object which has angles around three axes.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public interface Angled {

  /**
   * Gets the current angle at which this joint is rotated around the x axis.
   * 
   * @return The current angle at which this joint is rotated around the x axis.
   */
  double xAngle();

  /**
   * Gets the current angle at which this joint is rotated around the y axis.
   * 
   * @return The current angle at which this joint is rotated around the y axis.
   */
  double yAngle();

  /**
   * Gets the current angle at which this joint is rotated around the z axis.
   * 
   * @return The current angle at which this joint is rotated around the z axis.
   */
  double zAngle();

  /**
   * Sets the angle around the x axis.
   * 
   * @param xAngle
   *          The angle around the x axis.
   */
  void setXAngle(final double xAngle);

  /**
   * Sets the angle around the y axis.
   * 
   * @param yAngle
   *          The angle around the y axis.
   */
  void setYAngle(final double yAngle);

  /**
   * Sets the angle around the z axis.
   * 
   * @param zAngle
   *          The angle around the z axis.
   */
  void setZAngle(final double zAngle);
}
