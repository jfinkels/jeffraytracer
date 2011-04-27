/**
 * InfinityLight.java - a light source at distance infinity
 */
package edu.bu.cs.cs480;

/**
 * A light source at distance infinity.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class InfinityLight extends Light {

  /**
   * {@inheritDoc}
   * 
   * @param point
   *          {@inheritDoc}
   * @return {@inheritDoc}
   */
  @Override
  public Vector3D vectorFrom(final Vector3D point) {
    return this.direction().scaledBy(-1);
  }
}
