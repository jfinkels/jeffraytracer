/**
 * Colorable.java - an object which has a color
 */
package edu.bu.cs.cs480;

/**
 * An object which has a color.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public interface Colorable {
  /**
   * Gets the color of this object.
   * 
   * @return The color of this object.
   */
  DoubleColor color();

  /**
   * Sets the color of this object.
   * 
   * @param color
   *          The color of this object.
   */
  void setColor(final DoubleColor color);
}
