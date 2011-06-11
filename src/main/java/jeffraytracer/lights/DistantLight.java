/**
 * DistantLight.java - a light source with radial attenuation due to distance
 */
package jeffraytracer.lights;

import jeffraytracer.QuadraticSolver;

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
   * @see jeffraytracer.lights.Light#radialAttenuation(double)
   */
  @Override
  public double radialAttenuation(final double distance) {
    return 1.0 / QuadraticSolver.evaluate(this.attenuationCoefficients().z(),
        this.attenuationCoefficients().y(),
        this.attenuationCoefficients().x(), distance);
  }

}
