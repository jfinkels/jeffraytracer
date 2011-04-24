/**
 * AmbientLight.java - an ambient light source
 */
package edu.bu.cs.cs480;

/**
 * An ambient light source.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class AmbientLight extends Light {

  public static final Vector3D AMBIENT_COEFFICIENTS = new Vector3D(0.1, 0.1,
      0.1);

  /**
   * @return
   */
  public Vector3D ambientColor() {
    return new Vector3D(this.color().red() * AMBIENT_COEFFICIENTS.x(), this
        .color().green() * AMBIENT_COEFFICIENTS.y(), this.color().blue()
        * AMBIENT_COEFFICIENTS.z());
  }
}
