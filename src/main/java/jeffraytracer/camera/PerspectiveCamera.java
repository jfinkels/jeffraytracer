/**
 * PerspectiveCamera.java - a camera which uses perspective projection
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