/**
 * Ellipsoid.java - 
 */
package edu.bu.cs.cs480.surfaces;

import edu.bu.cs.cs480.Material;
import edu.bu.cs.cs480.Vector3D;

/**
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Ellipsoid extends SurfaceObject {
  /**
   * @return the material
   */
  public Material getMaterial() {
    return this.material;
  }
  /**
   * @param material the material to set
   */
  public void setMaterial(Material material) {
    this.material = material;
  }
  /**
   * @return the radii
   */
  public Vector3D radii() {
    return this.radii;
  }
  /**
   * @param radii the radii to set
   */
  public void setRadii(Vector3D radii) {
    this.radii = radii;
  }
  private Material material;
  private Vector3D radii;
  
}
