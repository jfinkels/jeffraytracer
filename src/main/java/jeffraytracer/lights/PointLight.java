/**
 * PointLight.java - a light source which is a point
 */
package jeffraytracer.lights;

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
   * @see jeffraytracer.lights.Light#angularAttenuation(double)
   */
  @Override
  public double angularAttenuation(final double cosineAngle) {
    return Math.pow(cosineAngle, this.attenuationExponent());
  }

}
