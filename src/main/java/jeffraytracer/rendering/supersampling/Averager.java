/**
 * Averager.java - averages colors in neighborhoods of traced pixels
 * 
 * Copyright 2011 Jeffrey Finkelstein
 * 
 * This file is part of jeffraytracer.
 * 
 * jeffraytracer is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 * 
 * jeffraytracer is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * jeffraytracer. If not, see <http://www.gnu.org/licenses/>.
 */
package jeffraytracer.rendering.supersampling;

import jeffraytracer.Vector3D;

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
