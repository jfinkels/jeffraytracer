/**
 * Averager.java - averages colors in neighborhoods of traced pixels
 */
package edu.bu.cs.cs480.rendering.supersampling;

import edu.bu.cs.cs480.Vector3D;

/**
 * Averages colors in neighborhoods of traced pixels.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public interface Averager {
  /**
   * Average by some algorithm the color values of the specified pixels to
   * produce a new (possibly smaller) array of color values.
   * 
   * @param pixels
   *          The color values to average.
   * @return A new (possibly smaller) array of color values averaged from the
   *         specified input.
   */
  Vector3D[] average(final Vector3D[] pixels);
}
