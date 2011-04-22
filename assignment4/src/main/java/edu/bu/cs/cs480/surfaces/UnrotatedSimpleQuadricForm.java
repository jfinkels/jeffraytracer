/**
 * UnrotatedSimpleQuadricForm.java - an unrotated quadric surface
 */
package edu.bu.cs.cs480.surfaces;

import edu.bu.cs.cs480.Matrix4x4;

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
   * @see edu.bu.cs.cs480.surfaces.SimpleQuadricForm#rotation()
   */
  @Override
  protected Matrix4x4 rotation() {
    return Matrix4x4.identity();
  }

}
