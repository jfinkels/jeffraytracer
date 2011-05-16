/**
 * Light.java - a light source
 */
package edu.bu.cs.cs480.lights;

import edu.bu.cs.cs480.Colorable;
import edu.bu.cs.cs480.Directed;
import edu.bu.cs.cs480.DoubleColor;
import edu.bu.cs.cs480.PositionableTracerObject;
import edu.bu.cs.cs480.Vector3D;

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
   * @see edu.bu.cs.cs480.Colorable#color()
   */
  @Override
  public DoubleColor color() {
    return this.color;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see edu.bu.cs.cs480.Directed#direction()
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
   * @see edu.bu.cs.cs480.Colorable#setColor(edu.bu.cs.cs480.DoubleColor)
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
   * @see edu.bu.cs.cs480.Directed#setDirection(edu.bu.cs.cs480.Vector3D)
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
