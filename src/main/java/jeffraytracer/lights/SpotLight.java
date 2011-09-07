/**
 * SpotLight.java - a spotlight which emits a cone of light
 * 
 * Copyright 2011 Jeffrey Finkelstein
 * 
 * This file is part of jeffraytracer.
 * 
 * jeffraytracer is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 * 
 * jeffraytracer is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * jeffraytracer. If not, see <http://www.gnu.org/licenses/>.
 */
package jeffraytracer.lights;

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
   * @see jeffraytracer.lights.Light#angularAttenuation(double)
   */
  @Override
  public double angularAttenuation(final double cosineAngle) {
    if (cosineAngle > COS_MAX_ANGLE) {
      return 0;
    }
    return super.angularAttenuation(cosineAngle);
  }

}
