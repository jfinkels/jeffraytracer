/**
 * PerspectiveCamera.java - a camera which uses perspective projection
 */
package edu.bu.cs.cs480.camera;

/**
 * A camera which uses perspective projection.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class PerspectiveCamera extends Camera {
  /** The focal length of this camera. */
  private double focalLength = 0;

  /**
   * Gets the focal length of this camera.
   * 
   * @return The focal length of this camera.
   */
  public double focalLength() {
    return this.focalLength;
  }

  /**
   * Sets the focal length of this camera.
   * 
   * @param focalLength
   *          The focal length of this camera.
   */
  public void setFocalLength(double focalLength) {
    this.focalLength = focalLength;
  }
}
