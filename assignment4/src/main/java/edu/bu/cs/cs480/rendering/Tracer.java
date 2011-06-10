/**
 * Tracer.java - an object which traces and shades a scene
 */
package edu.bu.cs.cs480.rendering;

import edu.bu.cs.cs480.Ray;
import edu.bu.cs.cs480.Vector3D;

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
