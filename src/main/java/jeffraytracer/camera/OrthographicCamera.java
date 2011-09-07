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
   * effect on the projection, so we just return 1. Because what is life
   * without whimsy?
   * 
   * @return {@inheritDoc}
   * @see jeffraytracer.camera.Camera#focalLength()
   */
  @Override
  public double focalLength() {
    return 1.0;
  }

  /**
   * {@inheritDoc}
   * 
   * @param position
   *          {@inheritDoc}
   * @return {@inheritDoc}
   * @see jeffraytracer.camera.Camera#rayDirection(jeffraytracer.Vector3D)
   */
  @Override
  public Vector3D rayDirection(final Vector3D position) {
    return this.direction();
  }

}
