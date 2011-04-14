/**
 * SurfaceObject.java - surface objects which can be rendered in a scene
 */
package edu.bu.cs.cs480.surfaces;

import edu.bu.cs.cs480.Identifiable;
import edu.bu.cs.cs480.Intercept;
import edu.bu.cs.cs480.Ray;

/**
 * A marker interface for surface objects which can be rendered in a scene.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public interface SurfaceObject extends Identifiable {

  /**
   * Get the intercept of the specified ray and this surface object, or
   * {@code null} if no such intercept exists.
   * 
   * Pre-condition: the quadratic matrix which defines this surface object has
   * already been built by a call to the {@link #compile()} method.
   * 
   * @param ray
   *          The ray which may intercept this surface object.
   * @return The intercept of the specified ray and this surface object, or
   *         {@code null} if no such intercept exists.
   */
  Intercept interceptWith(final Ray ray);

  /**
   * Computes and stores the matrix or matrices which defines or define this
   * object, so that many the object can be tested for intersection with many
   * rays without having to recompute the matrix.
   */
  void compile();
}
