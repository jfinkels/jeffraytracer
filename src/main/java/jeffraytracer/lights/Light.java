/**
 * Light.java - a light source
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

import jeffraytracer.Colorable;
import jeffraytracer.Directed;
import jeffraytracer.DoubleColor;
import jeffraytracer.PositionableTracerObject;
import jeffraytracer.Vector3D;

/**
 * A light source.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public abstract class Light extends PositionableTracerObject implements
    Directed, Colorable {
  /** The coefficients of attenuation of this light source. */
  private Vector3D attenuationCoefficients = null;
  /** The exponent of attenuation of this light source. */
  private int attenuationExponent = 0;
  /** The color of this light source. */
  private DoubleColor color = null;
  /** The direction in which this light source points. */
  private Vector3D direction = null;
  /** Whether this light source casts a shadow. */
  private boolean shadow = false;

  /**
   * Attenuation (i.e. weakening) factor of the energy from this light due to
   * angle.
   * 
   * @param cosineAngle
   *          The cosine of the angle between the object and the direction of
   *          the light source.
   * @return The attenuation factor of the energy from this light due to angle.
   */
  public abstract double angularAttenuation(final double cosineAngle);

  /**
   * Gets the attenuation coefficients of this light source.
   * 
   * @return The attenuation coefficients of this light source.
   */
  public Vector3D attenuationCoefficients() {
    return this.attenuationCoefficients;
  }

  /**
   * Gets the attenuation exponent of this light source.
   * 
   * @return The attenuation exponent of this light source.
   */
  public int attenuationExponent() {
    return this.attenuationExponent;
  }

  /**
   * Gets whether this light source casts a shadow.
   * 
   * @return Whether this light source casts a shadow.
   */
  public boolean castsShadow() {
    return this.shadow;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jeffraytracer.Colorable#color()
   */
  @Override
  public DoubleColor color() {
    return this.color;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jeffraytracer.Directed#direction()
   */
  @Override
  public Vector3D direction() {
    return this.direction;
  }

  /**
   * Attenuation (i.e. weakening) factor of the energy from this light due to
   * distance.
   * 
   * @param distance
   *          The distance from this light.
   * @return The attenuation factor of the energy from this light due to
   *         distance.
   */
  public abstract double radialAttenuation(final double distance);

  /**
   * Sets the attenuation coefficients of this light source.
   * 
   * @param attenuationCoefficients
   *          The attenuation coefficients of this light source.
   */
  public void setAttenuationCoefficients(final Vector3D attenuationCoefficients) {
    this.attenuationCoefficients = attenuationCoefficients;
  }

  /**
   * Sets the attenuation exponent of this light source.
   * 
   * @param attenuationExponent
   *          The attenuation exponent of this light source.
   */
  public void setAttenuationExponent(final int attenuationExponent) {
    this.attenuationExponent = attenuationExponent;
  }

  /**
   * {@inheritDoc}
   * 
   * @param color
   *          {@inheritDoc}
   * @see jeffraytracer.Colorable#setColor(jeffraytracer.DoubleColor)
   */
  @Override
  public void setColor(final DoubleColor color) {
    this.color = color;
  }

  /**
   * {@inheritDoc}
   * 
   * @param direction
   *          {@inheritDoc}
   * @see jeffraytracer.Directed#setDirection(jeffraytracer.Vector3D)
   */
  @Override
  public void setDirection(final Vector3D direction) {
    this.direction = direction;
  }

  /**
   * Sets whether this light source casts a shadow.
   * 
   * @param shadow
   *          Whether this light source casts a shadow.
   */
  public void setShadow(final boolean shadow) {
    this.shadow = shadow;
  }

  /**
   * Gets the vector from the specified point to this light source.
   * 
   * @param point
   *          The source of the vector.
   * @return The vector from the specified point to this light source.
   */
  public Vector3D vectorFrom(final Vector3D point) {
    return this.position().difference(point);
  }
}
