/**
 * Viewport.java - the viewport on which the scene is displayed
 */
package edu.bu.cs.cs480.camera;

/**
 * The viewport on which the scene is displayed.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Viewport {
  /** The height of the viewport in pixels. */
  private int height = 0;

  /** The width of the viewport in pixels. */
  private int width = 0;

  /**
   * Gets the height of the viewport in pixels.
   * 
   * @return The height of the viewport in pixels.
   */
  public int height() {
    return this.height;
  }

  /**
   * Sets the height of the viewport in pixels.
   * 
   * @param height
   *          The height of the viewport in pixels.
   */
  public void setHeight(final int height) {
    this.height = height;
  }

  /**
   * Sets the width of the viewport in pixels.
   * 
   * @param width
   *          The width of the viewport in pixels.
   */
  public void setWidth(final int width) {
    this.width = width;
  }

  /**
   * Gets the width of the viewport in pixels.
   * 
   * @return The width of the viewport in pixels.
   */
  public int width() {
    return this.width;
  }
}
