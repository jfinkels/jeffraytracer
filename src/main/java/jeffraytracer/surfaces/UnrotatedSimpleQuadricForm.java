/**
 * UnrotatedSimpleQuadricForm.java - an unrotated quadric surface
 * 
 * Copyright 2011 Jeffrey Finkelstein
 * 
 * This file is part of jeffraytracer.
 * 
 * jeffraytracer is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 * 
 * jeffraytracer is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * jeffraytracer. If not, see <http://www.gnu.org/licenses/>.
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
