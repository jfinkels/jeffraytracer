/**
 * Sphere.java - a sphere surface object
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
 * A sphere surface object.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Sphere extends UnrotatedSimpleQuadricForm {
  /** The radius of this sphere. */
  private double radius = 0;

  /**
   * Gets the radius of this sphere.
   * 
   * @return The radius of this sphere.
   */
  public double radius() {
    return this.radius;
  }

  /**
   * Sets the radius of this sphere.
   * 
   * @param radius
   *          The radius of this sphere.
   */
  public void setRadius(final double radius) {
    this.radius = radius;
  }

  @Override
  public String toString() {
    return "Sphere " + this.id();
  }

  /*
   * (non-Javadoc)
   * 
   * @see jeffraytracer.surfaces.SimpleQuadricForm#baseMatrix()
   */
  @Override
  protected Matrix4x4 baseMatrix() {
    final Matrix4x4 result = Matrix4x4.identity();
    result.set(3, 3, -(this.radius * this.radius));
    return result;
  }

}
