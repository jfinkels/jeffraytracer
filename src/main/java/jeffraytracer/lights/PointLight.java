/**
 * PointLight.java - a light source which is a point
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
