/**
 * 
 */
package edu.bu.cs.cs480;

import java.awt.Color;

/**
 * @author Jeffrey Finkelstein
 *
 */
public class FloatColor extends Color {
  
  private final float red;
  private final float green;
  private final float blue;
  
  /**
   * @param r
   * @param g
   * @param b
   */
  public FloatColor(float r, float g, float b) {
    super(r, g, b);
    final float[] components = new float[3];
    this.getColorComponents(components);
    this.red = components[0];
    this.green = components[1];
    this.blue = components[2];
  }

  public float red() {
    return this.red;
  }
  
  public float green() {
    return this.green;
  }
  
  public float blue() {
    return this.blue;
  }
}
