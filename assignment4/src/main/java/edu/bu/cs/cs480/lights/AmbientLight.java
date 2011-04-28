/**
 * AmbientLight.java - an ambient light source
 */
package edu.bu.cs.cs480.lights;

/**
 * An ambient light source.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class AmbientLight extends Light {

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

  /**
   * {@inheritDoc}
   * 
   * @param distance
   *          {@inheritDoc}
   * @return {@inheritDoc}
   * @see edu.bu.cs.cs480.Light#radialAttenuation(double)
   */
  @Override
  public double radialAttenuation(final double distance) {
    return 1;
  }
}
