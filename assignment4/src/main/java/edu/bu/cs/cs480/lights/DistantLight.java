/**
 * DistantLight.java - a light source with radial attenuation due to distance
 */
package edu.bu.cs.cs480.lights;

import edu.bu.cs.cs480.QuadraticSolver;

/**
 * A light source which has radial attenuation due to distance.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public abstract class DistantLight extends Light {

  /**
   * {@inheritDoc}
   * 
   * @param distance
   *          {@inheritDoc}
   * @return {@inheritDoc}
   * @see edu.bu.cs.cs480.Light#radialAttenuation(double)
   */
  @Override
  public double radialAttenuation(double distance) {
    return 1.0 / QuadraticSolver.evaluate(this.attenuationCoefficients().z(),
        this.attenuationCoefficients().y(), this.attenuationCoefficients().x(),
        distance);
  }

}
