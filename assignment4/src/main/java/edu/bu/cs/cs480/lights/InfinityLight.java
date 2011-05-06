/**
 * InfinityLight.java - a light source at distance infinity
 */
package edu.bu.cs.cs480.lights;

import edu.bu.cs.cs480.Vector3D;

/**
 * A light source at distance infinity.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class InfinityLight extends DistantLight {

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

  /**
   * {@inheritDoc}
   * 
   * @param cosineAngle
   *          {@inheritDoc}
   * @return {@inheritDoc}
   * @see edu.bu.cs.cs480.Light#angularAttenuation(double)
   */
  @Override
  public double angularAttenuation(final double cosineAngle) {
    return 1;
  }

}
