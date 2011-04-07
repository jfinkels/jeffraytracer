/**
 * Camera.java - representation of a camera which views the scene
 */
package edu.bu.cs.cs480.camera;

import edu.bu.cs.cs480.Positionable;
import edu.bu.cs.cs480.Vector3D;

/**
 * A camera which has a position, a direction, an orientation, and near and far
 * clip plane.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public abstract class Camera implements Positionable {
  /** The position of this camera. */
  private Vector3D position = null;
  /** The distance to the far clip plane. */
  private double far = 0;
  /** The point at which this camera is looking. */
  private Vector3D lookAt = null;
  /** The distance to the near clip plane. */
  private double near = 0;
  /** The up direction of the camera. */
  private Vector3D up = null;

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see edu.bu.cs.cs480.Positionable#position()
   */
  @Override
  public Vector3D position() {
    return this.position;
  }

  /**
   * Gets the distance to the far clip plane.
   * 
   * @return The distance to the far clip plane.
   */
  public double far() {
    return this.far;
  }

  /**
   * Gets the point at which this camera is looking.
   * 
   * @return The point at which this camera is looking.
   */
  public Vector3D lookAt() {
    return this.lookAt;
  }

  /**
   * Gets the distance to the near clip plane.
   * 
   * @return The distance to the near clip plane.
   */
  public double near() {
    return this.near;
  }

  /**
   * {@inheritDoc}
   * 
   * @param position
   *          {@inheritDoc}
   * @see edu.bu.cs.cs480.Positionable#setPosition(edu.bu.cs.cs480.Vector3D)
   */
  @Override
  public void setPosition(Vector3D position) {
    this.position = position;
  }

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
   * Sets the point at which this camera is looking.
   * 
   * @param lookAt
   *          The point at which this camera is looking.
   */
  public void setLookAt(Vector3D lookAt) {
    this.lookAt = lookAt;
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
   * @param up
   *          The up direction of this camera.
   */
  public void setUp(Vector3D up) {
    this.up = up;
  }

  /**
   * Gets the up direction of this camera.
   * 
   * @return The up direction of this camera.
   */
  public Vector3D up() {
    return this.up;
  }
}
