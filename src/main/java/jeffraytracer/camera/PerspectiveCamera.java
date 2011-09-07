/**
 * PerspectiveCamera.java - a camera which uses perspective projection
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
 * A camera which uses perspective projection.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class PerspectiveCamera extends Camera {
  /** The focal length of this camera. */
  private double focalLength = 0;

  /**
   * Gets the focal length of this camera.
   * 
   * @return The focal length of this camera.
   */
  @Override
  public double focalLength() {
    return this.focalLength;
  }

  /**
   * Sets the focal length of this camera.
   * 
   * @param focalLength
   *          The focal length of this camera.
   */
  public void setFocalLength(final double focalLength) {
    this.focalLength = focalLength;
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
    return position.difference(this.position()).normalized();
  }
}
