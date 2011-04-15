/**
 * 
 */
package edu.bu.cs.cs480.surfaces;

import edu.bu.cs.cs480.Matrix4x4;
import edu.bu.cs.cs480.Vector3D;

/**
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
class Plane extends SimpleQuadricForm {

  private Matrix4x4 matrix = new Matrix4x4();
  private final Vector3D normal;
  private final double d;

  Plane(final Vector3D normal, final double d) {
    this.normal = normal;
    this.d = d;

    this.matrix.set(0, 3, normal.x() / 2);
    this.matrix.set(1, 3, normal.y() / 2);
    this.matrix.set(2, 3, normal.z() / 2);
    this.matrix.set(3, 0, normal.x() / 2);
    this.matrix.set(3, 1, normal.y() / 2);
    this.matrix.set(3, 2, normal.z() / 2);
    this.matrix.set(3, 3, d);
  }

  /**
   * {@inheritDoc}
   * 
   * @see edu.bu.cs.cs480.surfaces.SurfaceObject#compile()
   */
  @Override
  public void compile() {
    // the matrix has already been set in the constructor
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

}
