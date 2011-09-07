/**
 * Camera.java - representation of a camera which views the scene
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

import jeffraytracer.PositionedDirected;
import jeffraytracer.Vector3D;

/**
 * A camera which has a position, a direction, an orientation, and near and far
 * clip plane.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public abstract class Camera extends PositionedDirected {
  /** The distance to the far clip plane. */
  private double far = 0;
  /** The distance to the near clip plane. */
  private double near = 0;
  /** The up direction of the camera. */
  private Vector3D up = null;

  /**
   * Gets the distance to the far clip plane.
   * 
   * @return The distance to the far clip plane.
   */
  public double far() {
    return this.far;
  }

  /**
   * Gets the focal length of this camera.
   * 
   * @return The focal length of this camera.
   */
  public abstract double focalLength();

  /**
   * Gets the distance to the near clip plane.
   * 
   * @return The distance to the near clip plane.
   */
  public double near() {
    return this.near;
  }

  /**
   * Computes the direction of a ray which originates at the specified
   * position.
   * 
   * The returned vector is of unit length.
   * 
   * @param position
   *          The starting position of the ray whose direction will be
   *          computed.
   * @return The direction of the ray starting at the specified position.
   */
  public abstract Vector3D rayDirection(final Vector3D position);

  /**
   * Sets the distance to the far clip plane.
   * 
   * @param far
   *          The distance to the far clip plane.
   */
  public void setFar(final double far) {
    this.far = far;
  }

  /**
   * Sets the distance to the near clip plane.
   * 
   * @param near
   *          The distance to the near clip plane.
   */
  public void setNear(final double near) {
    this.near = near;
  }

  /**
   * Sets the up direction of this camera.
   * 
   * Pre-condition: the up vector is normalized.
   * 
   * @param up
   *          The up direction of this camera.
   */
  public void setUp(final Vector3D up) {
    this.up = up;
  }

  /**
   * Gets the up direction of this camera as a unit vector.
   * 
   * @return The up direction of this camera as a unit vector.
   */
  public Vector3D up() {
    return this.up;
  }
}
