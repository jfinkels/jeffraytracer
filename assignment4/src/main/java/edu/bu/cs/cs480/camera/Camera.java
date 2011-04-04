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
  private Vector3D lookAt = null;
  private Vector3D up = null;
  private double near = 0;
  private double far = 0;
  /**
   * @return the center
   */
  public Vector3D center() {
    return this.center;
  }
  /**
   * @param center the center to set
   */
  public void setCenter(Vector3D center) {
    this.center = center;
  }
  /**
   * @return the lookAt
   */
  public Vector3D lookAt() {
    return this.lookAt;
  }
  /**
   * @param lookAt the lookAt to set
   */
  public void setLookAt(Vector3D lookAt) {
    this.lookAt = lookAt;
  }
  /**
   * @return the up
   */
  public Vector3D up() {
    return this.up;
  }
  /**
   * @param up the up to set
   */
  public void setUp(Vector3D up) {
    this.up = up;
  }
  /**
   * @return the near
   */
  public double near() {
    return this.near;
  }
  /**
   * @param near the near to set
   */
  public void setNear(double near) {
    this.near = near;
  }
  /**
   * @return the far
   */
  public double far() {
    return this.far;
  }
  /**
   * @param far the far to set
   */
  public void setFar(double far) {
    this.far = far;
  }
}
