/**
 * UnrotatedSimpleQuadricForm.java - an unrotated quadric surface
 */
package jeffraytracer.surfaces;

import jeffraytracer.Matrix4x4;

/**
 * An unrotated quadric surface.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public abstract class UnrotatedSimpleQuadricForm extends SimpleQuadricForm {

  /**
   * Return the identity matrix.
   * 
   * @return The identity matrix.
   * @see jeffraytracer.surfaces.SimpleQuadricForm#rotation()
   */
  @Override
  protected Matrix4x4 rotation() {
    return Matrix4x4.identity();
  }

}
