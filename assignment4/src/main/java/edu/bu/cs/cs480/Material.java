/**
 * Material.java -
 */
package edu.bu.cs.cs480;

/**
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Material extends TracerObject implements Colorable {
  private FloatColor color = null;
  private double ambientReflection = 0;
  private double diffuseReflection = 0;
  private double specularReflection = 0;
  private double transmission = 0;
  private double reflection = 0;
  private double refraction = 0;
  /**
   * @return the refraction
   */
  public double getRefraction() {
    return refraction;
  }

  /**
   * @param refraction the refraction to set
   */
  public void setRefraction(double refraction) {
    this.refraction = refraction;
  }

  public void setTransmission(final double transmission) {
    this.transmission = transmission;
  }

  public double transmission() {
    return this.transmission;
  }

  public void setReflection(final double reflection) {
    this.reflection = reflection;
  }

  public double reflection() {
    return this.reflection;
  }

  public void setAmbientReflection(final double ambientReflection) {
    this.ambientReflection = ambientReflection;
  }

  public void setDiffuseReflection(final double diffuseReflection) {
    this.diffuseReflection = diffuseReflection;
  }

  public void setSpecularReflection(final double specularReflection) {
    this.specularReflection = specularReflection;
  }

  public void setSpecularExponent(final int specularExponent) {
    this.specularExponent = specularExponent;
  }

  private int specularExponent;

  public int specularExponent() {
    return this.specularExponent;
  }

  public double ambientReflection() {
    return this.ambientReflection;
  }

  public double diffuseReflection() {
    return this.diffuseReflection;
  }

  public double speculaeReflection() {
    return this.specularReflection;
  }

  /*
   * (non-Javadoc)
   * 
   * @see edu.bu.cs.cs480.Colorable#color()
   */
  @Override
  public FloatColor color() {
    // TODO Auto-generated method stub
    return this.color;
  }

  /*
   * (non-Javadoc)
   * 
   * @see edu.bu.cs.cs480.Colorable#setColor(edu.bu.cs.cs480.FloatColor)
   */
  @Override
  public void setColor(FloatColor color) {
    this.color = color;
  }
}
