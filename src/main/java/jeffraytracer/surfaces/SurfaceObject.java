/**
 * SurfaceObject.java - surface objects which can be rendered in a scene
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
package jeffraytracer.surfaces;

import jeffraytracer.Identifiable;
import jeffraytracer.Ray;
import jeffraytracer.Vector3D;

/**
 * A marker interface for surface objects which can be rendered in a scene.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public interface SurfaceObject extends Identifiable {

  /**
   * Computes and stores the matrix or matrices which defines or define this
   * object, so that many the object can be tested for intersection with many
   * rays without having to recompute the matrix.
   */
  void compile();

  /**
   * Returns {@code true} if and only if the specified point is inside this
   * object.
   * 
   * Pre-condition: the quadratic matrix which defines this surface object has
   * already been built by a call to the {@link #compile()} method.
   * 
   * @param point
   *          The point to test.
   * @return {@code true} if and only if the specified point is inside this
   *         object.
   */
  boolean inside(final Vector3D point);

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
   * Returns {@code true} if and only if the specified point is outside this
   * object.
   * 
   * Pre-condition: the quadratic matrix which defines this surface object has
   * already been built by a call to the {@link #compile()} method.
   * 
   * @param point
   *          The point to test.
   * @return {@code true} if and only if the specified point is outside this
   *         object.
   */
  boolean outside(final Vector3D point);
}
