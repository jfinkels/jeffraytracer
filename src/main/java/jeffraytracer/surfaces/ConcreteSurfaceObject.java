/**
 * ConcreteSurfaceObject.java - a base class for concrete surface objects
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

import jeffraytracer.Material;
import jeffraytracer.PositionableTracerObject;

/**
 * A base class for concrete surface objects which have a specified material.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public abstract class ConcreteSurfaceObject extends PositionableTracerObject
    implements SurfaceObject {
  /** The material to use to display the surface of this object. */
  private Material material = null;

  /**
   * Gets the material to use to display the surface of this object.
   * 
   * @return The material to use to display the surface of this object.
   */
  public Material material() {
    return this.material;
  }

  /**
   * Sets the material to use to display the surface of this object.
   * 
   * @param material
   *          The material to use to display the surface of this object.
   */
  public void setMaterial(final Material material) {
    this.material = material;
  }
}
