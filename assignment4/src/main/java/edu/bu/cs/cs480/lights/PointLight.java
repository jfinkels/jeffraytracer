/**
 * PointLight.java - a light source which is a point
 */
package edu.bu.cs.cs480.lights;

/**
 * A light source which is a point.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class PointLight extends DistantLight {

  /**
   * {@inheritDoc}
   * 
   * @param cosineAngle
   *          {@inheritDoc}
   * @return {@inheritDoc}
   * @see edu.bu.cs.cs480.lights.Light#angularAttenuation(double)
   */
  @Override
  public double angularAttenuation(final double cosineAngle) {
    return Math.pow(cosineAngle, this.attenuationExponent());
  }

}
