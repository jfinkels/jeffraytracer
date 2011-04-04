/**
 * PerspectiveCamera.java - 
 */
package edu.bu.cs.cs480.camera;

/**
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class PerspectiveCamera extends Camera {
  /**
   * @return the focalLength
   */
  public double focalLength() {
    return this.focalLength;
  }

  /**
   * @param focalLength the focalLength to set
   */
  public void setFocalLength(double focalLength) {
    this.focalLength = focalLength;
  }

  private double focalLength = 0;
}
