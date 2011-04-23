/**
 * Plane.java - a plane represented as a quadric surface
 */
package edu.bu.cs.cs480.surfaces;

import edu.bu.cs.cs480.Matrix4x4;
import edu.bu.cs.cs480.Vector3D;

/**
 * A plane represented as a quadric surface.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
class Plane extends UnrotatedSimpleQuadricForm {
  /** The normal to the plane. */
  private final Vector3D normal;
  /** The scalar value in the equation of this plane. */
  private final double d;

  Plane(final Vector3D normal, final double d) {
    this.normal = normal;
    this.d = d;
    // HACK set the position to 0, since the position is taken care of by the
    // quadric form matrix
    this.setPosition(new Vector3D(0, 0, 0));
  }

  /**
   * @param point
   * @return
   */
  public boolean pointIsBelow(final Vector3D point) {
    return this.normal.dotProduct(point) + this.d < 0;
  }

  /**
   * @param point
   * @return
   */
  public boolean pointIsAbove(final Vector3D point) {
    return this.normal.dotProduct(point) + this.d > 0;
  }

  /*
   * (non-Javadoc)
   * 
   * @see edu.bu.cs.cs480.surfaces.SimpleQuadricForm#baseMatrix()
   */
  @Override
  protected Matrix4x4 baseMatrix() {
    final Matrix4x4 result = new Matrix4x4();
    result.set(0, 3, this.normal.x() / 2);
    result.set(1, 3, this.normal.y() / 2);
    result.set(2, 3, this.normal.z() / 2);
    result.set(3, 0, this.normal.x() / 2);
    result.set(3, 1, this.normal.y() / 2);
    result.set(3, 2, this.normal.z() / 2);
    result.set(3, 3, this.d);

    return result;
  }

}
