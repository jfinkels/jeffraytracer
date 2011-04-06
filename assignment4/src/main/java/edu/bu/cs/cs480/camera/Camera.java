/**
 * Camera.java -
 */
package edu.bu.cs.cs480.camera;

import edu.bu.cs.cs480.Vector3D;

/**
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public abstract class Camera {
  private Vector3D center = null;
  private double far = 0;
  private Vector3D lookAt = null;
  private double near = 0;
  private Vector3D up = null;

  /**
   * @return the center
   */
  public Vector3D center() {
    return this.center;
  }

  /**
   * @return the far
   */
  public double far() {
    return this.far;
  }

  /**
   * @return the lookAt
   */
  public Vector3D lookAt() {
    return this.lookAt;
  }

  /**
   * @return the near
   */
  public double near() {
    return this.near;
  }

  /**
   * @param center
   *          the center to set
   */
  public void setCenter(Vector3D center) {
    this.center = center;
  }

  /**
   * @param far
   *          the far to set
   */
  public void setFar(double far) {
    this.far = far;
  }

  /**
   * @param lookAt
   *          the lookAt to set
   */
  public void setLookAt(Vector3D lookAt) {
    this.lookAt = lookAt;
  }

  /**
   * @param near
   *          the near to set
   */
  public void setNear(double near) {
    this.near = near;
  }

  /**
   * @param up
   *          the up to set
   */
  public void setUp(Vector3D up) {
    this.up = up;
  }

  /**
   * @return the up
   */
  public Vector3D up() {
    return this.up;
  }
}
