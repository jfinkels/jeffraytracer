/**
 * Viewport.java - 
 */
package edu.bu.cs.cs480.camera;

/**
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Viewport {
  /**
   * @return the width
   */
  public int width() {
    return this.width;
  }
  /**
   * @param width the width to set
   */
  public void setWidth(int width) {
    this.width = width;
  }
  /**
   * @return the height
   */
  public int height() {
    return this.height;
  }
  /**
   * @param height the height to set
   */
  public void setHeight(int height) {
    this.height = height;
  }
  private int width = 0;
  private int height = 0;
}
