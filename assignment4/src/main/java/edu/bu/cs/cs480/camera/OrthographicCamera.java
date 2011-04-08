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
