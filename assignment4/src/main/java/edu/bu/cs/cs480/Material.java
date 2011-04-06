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
  private double specularExponent;
  /** The coefficient of specular reflection of this material. */
  private double specularReflection = 0;

  /** The coefficient of transmission of this material. */
  private double transmission = 0;

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

  public double diffuseReflection() {
    return this.diffuseReflection;
  }

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
  public void setColor(FloatColor color) {
    this.color = color;
  }

  public void setDiffuseReflection(final double diffuseReflection) {
    this.diffuseReflection = diffuseReflection;
  }

  public void setReflection(final double reflection) {
    this.reflection = reflection;
  }

  /**
   * Sets the index of refraction of this material.
   * 
   * @param refraction
   *          The index of refraction of this material.
   */
  public void setRefraction(double refraction) {
    this.refraction = refraction;
  }

  public void setSpecularExponent(final double specularExponent) {
    this.specularExponent = specularExponent;
  }

  public void setSpecularReflection(final double specularReflection) {
    this.specularReflection = specularReflection;
  }

  public void setTransmission(final double transmission) {
    this.transmission = transmission;
  }

  public double specularExponent() {
    return this.specularExponent;
  }

  public double specularReflection() {
    return this.specularReflection;
  }

  public double transmission() {
    return this.transmission;
  }
}
