/**
 * Ellipsoid.java - an ellipsoid surface object
 */
package jeffraytracer.surfaces;

import jeffraytracer.Matrix4x4;
import jeffraytracer.Vector3D;

/**
 * An ellipsoid surface object.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Ellipsoid extends UnrotatedSimpleQuadricForm {

  /** The radii of the x, y, and z axes of this ellipsoid. */
  private Vector3D radii;

  /**
   * Gets the radii of the x, y, and z axes of this ellipsoid.
   * 
   * @return The radii of the x, y, and z axes of this ellipsoid.
   */
  public Vector3D radii() {
    return this.radii;
  }

  /**
   * Sets the radii of the x, y, and z axes of this ellipsoid.
   * 
   * This method must be called with a non-{@code null} argument before the
   * {@link #compile()} method is called.
   * 
   * @param radii
   *          The radii of the x, y, and z axes of this ellipsoid.
   */
  public void setRadii(final Vector3D radii) {
    this.radii = radii;
  }

  /**
   * {@inheritDoc}
   * 
   * Pre-condition: the radii of this ellipsoid have been set.
   * 
   * @return {@inheritDoc}
   * @see jeffraytracer.surfaces.SimpleQuadricForm#baseMatrix()
   */
  @Override
  protected Matrix4x4 baseMatrix() {
    final Matrix4x4 result = new Matrix4x4();
    final double xRadius = this.radii.x();
    final double yRadius = this.radii.y();
    final double zRadius = this.radii.z();
    result.set(0, 0, 1 / (xRadius * xRadius));
    result.set(1, 1, 1 / (yRadius * yRadius));
    result.set(2, 2, 1 / (zRadius * zRadius));
    result.set(3, 3, -1);
    return result;
  }

}
