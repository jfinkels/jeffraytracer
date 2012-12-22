/**
 * OrthographicCamera.java - a camera which uses orthographic projection
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
package jeffraytracer.camera;

import jeffraytracer.Vector3D;

/**
 * A camera which use orthographic projection.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class OrthographicCamera extends Camera {

  /**
   * {@inheritDoc}
   * 
   * Since this is an orthographic projection camera, the focal length has no
   * effect on the projection, so we just return 1.
   * 
   * @return {@inheritDoc}
   * @see jeffraytracer.camera.Camera#focalLength()
   */
  @Override
  public double focalLength() {
    return 1.0;
  }

  /**
   * Returns an array of length one containing the direction of the sole ray
   * that passes through the specified position.
   * 
   * @param position
   *          {@inheritDoc}
   * @return An array of length one containing the direction of the sole ray
   *         that passes through the specified position.
   * @see jeffraytracer.camera.Camera#rayDirection(jeffraytracer.Vector3D)
   */
  @Override
  public Vector3D[] rayDirections(final Vector3D position) {
    return new Vector3D[] { this.direction() };
  }

  /**
   * Always returns 1.
   * 
   * @return Always returns 1.
   * @see jeffraytracer.camera.Camera#raysPerPixel()
   */
  @Override
  public int raysPerPixel() {
    return 1;
  }
}
