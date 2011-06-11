/**
 * OrthographicCamera.java - a camera which uses orthographic projection
 */
package edu.bu.cs.cs480.camera;

import edu.bu.cs.cs480.Vector3D;

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
   * @see edu.bu.cs.cs480.camera.Camera#focalLength()
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
   * @see edu.bu.cs.cs480.camera.Camera#rayDirection(edu.bu.cs.cs480.Vector3D)
   */
  @Override
  public Vector3D rayDirection(final Vector3D position) {
    return this.direction();
  }

}
