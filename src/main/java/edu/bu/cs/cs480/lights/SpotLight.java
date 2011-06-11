/**
 * SpotLight.java - a spotlight which emits a cone of light
 */
package edu.bu.cs.cs480.lights;

/**
 * A spotlight which emits a cone of light up to a fixed maximum angle.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class SpotLight extends PointLight {

  /** The maximum angle of the cone of light emitted by this light source. */
  public static final double MAX_ANGLE = Math.PI / 8;
  /**
   * The cosine of the maximum angle of the cone of light emitted by this light
   * source.
   */
  private static final double COS_MAX_ANGLE = Math.cos(MAX_ANGLE);

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
    if (cosineAngle > COS_MAX_ANGLE) {
      return 0;
    }
    return super.angularAttenuation(cosineAngle);
  }

}
