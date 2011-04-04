package edu.bu.cs.cs480.camera;
/**
 * Resolution.java - 
 */

/**
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Resolution {
  private double xResolution = 1;
  private double yResolution = 1;
  /**
   * @return the xResolution
   */
  public double xResolution() {
    return this.xResolution;
  }
  /**
   * @param xResolution the xResolution to set
   */
  public void setxResolution(double xResolution) {
    this.xResolution = xResolution;
  }
  /**
   * @return the yResolution
   */
  public double yResolution() {
    return this.yResolution;
  }
  /**
   * @param yResolution the yResolution to set
   */
  public void setyResolution(double yResolution) {
    this.yResolution = yResolution;
  }
}
