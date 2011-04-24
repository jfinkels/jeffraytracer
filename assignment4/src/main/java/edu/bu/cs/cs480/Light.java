/**
 * Light.java - a light source
 */
package edu.bu.cs.cs480;

/**
 * A light source.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public abstract class Light extends PositionableTracerObject implements Directed,
    Colorable {
  /** The coefficients of attenuation of this light source. */
  private Vector3D attenuationCoefficients = null;
  /** The exponent of attenuation of this light source. */
  private int attenuationExponent = 0;
  /** The color of this light source. */
  private FloatColor color = null;
  /** The direction in which this light source points. */
  private Vector3D direction = null;
  /** Whether this light source casts a shadow. */
  private boolean shadow = false;
  
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
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see edu.bu.cs.cs480.Colorable#color()
   */
  @Override
  public FloatColor color() {
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
   * @see edu.bu.cs.cs480.Colorable#setColor(edu.bu.cs.cs480.FloatColor)
   */
  @Override
  public void setColor(FloatColor color) {
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
   * Gets whether this light source casts a shadow.
   * 
   * @return Whether this light source casts a shadow.
   */
  public boolean shadow() {
    return this.shadow;
  }
}
