/**
 * Ellipsoid.java - an ellipsoid surface object
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
