/**
 * ConcreteSurfaceObject.java - a base class for concrete surface objects
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
