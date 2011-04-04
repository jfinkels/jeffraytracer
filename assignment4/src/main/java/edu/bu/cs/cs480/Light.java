/**
 * Light.java -
 */
package edu.bu.cs.cs480;

/**
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Light extends PositionableTracerObject implements Directed,
    Colorable {
  private Vector3D direction = null;
  private FloatColor color = null;
  private Vector3D attenuationCoefficients = null;
  private int attenuationExponent = 0;

  public void setAttenuationExponent(final int attenuationExponent) {
    this.attenuationExponent = attenuationExponent;
  }

  public int attenuationExponent() {
    return this.attenuationExponent;
  }

  public Vector3D attenuationCoefficients() {
    return this.attenuationCoefficients;
  }

  public void setAttenuationCoefficients(final Vector3D attenuationCoefficients) {
    this.attenuationCoefficients = attenuationCoefficients;
  }

  /*
   * (non-Javadoc)
   * 
   * @see edu.bu.cs.cs480.Directed#direction()
   */
  @Override
  public Vector3D direction() {
    return this.direction;
  }

  /*
   * (non-Javadoc)
   * 
   * @see edu.bu.cs.cs480.Directed#setDirection(edu.bu.cs.cs480.Vector3D)
   */
  @Override
  public void setDirection(Vector3D direction) {
    this.direction = direction;
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

  void setShadow(final boolean shadow) {
    this.shadow = shadow;
  }

  private boolean shadow = false;

  boolean shadow() {
    return this.shadow;
  }
}
