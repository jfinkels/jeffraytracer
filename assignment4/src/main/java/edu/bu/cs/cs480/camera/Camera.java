/**
 * Camera.java - representation of a camera which views the scene
 */
package edu.bu.cs.cs480.camera;

import edu.bu.cs.cs480.PositionedDirected;
import edu.bu.cs.cs480.Vector3D;

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
  public void setFar(double far) {
    this.far = far;
  }

  /**
   * Sets the distance to the near clip plane.
   * 
   * @param near
   *          The distance to the near clip plane.
   */
  public void setNear(double near) {
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
