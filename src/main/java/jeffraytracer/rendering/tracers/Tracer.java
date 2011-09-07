/**
 * Tracer.java - an object which traces and shades a scene
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
package jeffraytracer.rendering.tracers;

import jeffraytracer.Ray;
import jeffraytracer.Vector3D;

/**
 * An object which traces and shades either a single ray or an array of rays
 * according to a specific scene.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public interface Tracer {
  /**
   * Returns the color resulting from tracing and shading the specified ray
   * according to some scene specified elsewhere in this object.
   * 
   * @param ray
   *          The ray to trace.
   * @return The vector representing the RGB color resulting from tracing the
   *         specified ray in some scene.
   */
  Vector3D trace(final Ray ray);

  /**
   * Returns the combined result of tracing each ray in the specified input
   * array according to some scene specified elsewhere in this object.
   * 
   * @param ray
   *          The ray to trace.
   * @return The array of vectors representing each of the RGB colors resulting
   *         from tracing the each of the specified rays in some scene.
   */
  Vector3D[] traceAll(final Ray[] ray);
}
