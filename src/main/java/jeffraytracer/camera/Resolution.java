/**
 * Resolution.java - the resolution of the viewport
 */
package jeffraytracer.camera;

/**
 * The resolution of the viewport.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Resolution {
  /** The width of a pixel in world coordinates. */
  private double xResolution = 1;
  /** The height of a pixel in world coordinates. */
  private double yResolution = 1;

  /**
   * Sets the width of a pixel in world coordinates.
   * 
   * @param xResolution
   *          The width of a pixel in world coordinates.
   */
  public void setXResolution(final double xResolution) {
    this.xResolution = xResolution;
  }

  /**
   * Sets the height of a pixel in world coordinates.
   * 
   * @param yResolution
   *          The height of a pixel in world coordinates.
   */
  public void setYResolution(final double yResolution) {
    this.yResolution = yResolution;
  }

  /**
   * Gets the width of a pixel in world coordinates.
   * 
   * @return The width of a pixel in world coordinates.
   */
  public double xResolution() {
    return this.xResolution;
  }

  /**
   * Gets the height of a pixel in world coordinates.
   * 
   * @return The height of a pixel in world coordinates.
   */
  public double yResolution() {
    return this.yResolution;
  }
}
