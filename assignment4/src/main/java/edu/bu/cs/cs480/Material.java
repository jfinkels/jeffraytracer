/**
 * Material.java - a type of material which surface objects can use
 */
package edu.bu.cs.cs480;

/**
 * A type of material which surface objects can use.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Material extends TracerObject implements Colorable {
  /** The coefficient of ambient reflection of this material. */
  private double ambientReflection = 0;
  /** The color of this material. */
  private FloatColor color = null;
  /** The coefficient of diffuse reflection of this material. */
  private double diffuseReflection = 0;
  /** The coefficient of reflection of this material. */
  private double reflection = 0;
  /** The index of refraction of this material. */
  private double refraction = 0;
  /** The exponent for the specular reflection of this material. */
  private double specularExponent;
  /** The coefficient of specular reflection of this material. */
  private double specularReflection = 0;
  /** The coefficient of transmission of this material. */
  private double transmission = 0;

  /**
   * Gets the coefficient of ambient reflection.
   * 
   * @return The coefficient of ambient reflection.
   */
  public double ambientReflection() {
    return this.ambientReflection;
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
   * Gets the coefficient of diffuse reflection.
   * 
   * @return The coefficient of diffuse reflection.
   */
  public double diffuseReflection() {
    return this.diffuseReflection;
  }

  /**
   * Gets the coefficient of reflection.
   * 
   * @return The coefficient of reflection.
   */
  public double reflection() {
    return this.reflection;
  }

  /**
   * Gets the index of refraction of this material.
   * 
   * @return The index of refraction of this material.
   */
  public double refraction() {
    return this.refraction;
  }

  /**
   * Sets the coefficient of ambient reflection.
   * 
   * @param ambientReflection
   *          The coefficient of ambient reflection.
   */
  public void setAmbientReflection(final double ambientReflection) {
    this.ambientReflection = ambientReflection;
  }

  /**
   * {@inheritDoc}
   * 
   * @param color
   *          {@inheritDoc}
   * @see edu.bu.cs.cs480.Colorable#setColor(edu.bu.cs.cs480.FloatColor)
   */
  @Override
  public void setColor(final FloatColor color) {
    this.color = color;
  }

  /**
   * Sets the coefficient of diffuse reflection.
   * 
   * @param diffuseReflection
   *          The coefficient of diffuse reflection.
   */
  public void setDiffuseReflection(final double diffuseReflection) {
    this.diffuseReflection = diffuseReflection;
  }

  /**
   * Sets the coefficient of reflection.
   * 
   * @param reflection
   *          The coefficient of reflection.
   */
  public void setReflection(final double reflection) {
    this.reflection = reflection;
  }

  /**
   * Sets the index of refraction of this material.
   * 
   * @param refraction
   *          The index of refraction of this material.
   */
  public void setRefraction(final double refraction) {
    this.refraction = refraction;
  }

  /**
   * Sets the exponent of specular reflection.
   * 
   * @param specularExponent
   *          The exponent of specular reflection.
   */
  public void setSpecularExponent(final double specularExponent) {
    this.specularExponent = specularExponent;
  }

  /**
   * Sets the coefficient of specular reflection.
   * 
   * @param specularReflection
   *          The coefficient of specular reflection.
   */
  public void setSpecularReflection(final double specularReflection) {
    this.specularReflection = specularReflection;
  }

  /**
   * Sets the coefficient of transmission.
   * 
   * @param transmission
   *          The coefficient of transmission.
   */
  public void setTransmission(final double transmission) {
    this.transmission = transmission;
  }

  /**
   * Gets the exponent of specular reflection.
   * 
   * @return The exponent of specular reflection.
   */

  public double specularExponent() {
    return this.specularExponent;
  }

  /**
   * Gets the coefficient of specular reflection.
   * 
   * @return The coefficient of specular reflection.
   */
  public double specularReflection() {
    return this.specularReflection;
  }

  /**
   * Gets the coefficient of transmission.
   * 
   * @return The coefficient of transmission.
   */
  public double transmission() {
    return this.transmission;
  }
}
