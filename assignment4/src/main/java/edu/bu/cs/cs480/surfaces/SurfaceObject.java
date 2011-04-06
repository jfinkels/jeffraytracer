/**
 * SurfaceObject.java - 
 */
package edu.bu.cs.cs480.surfaces;

import edu.bu.cs.cs480.Material;
import edu.bu.cs.cs480.PositionableTracerObject;

/**
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public abstract class SurfaceObject extends PositionableTracerObject {
  private Material material = null;

  /**
   * @return the material
   */
  public Material material() {
    return this.material;
  }

  /**
   * @param material the material to set
   */
  public void setMaterial(final Material material) {
    this.material = material;
  }
}
