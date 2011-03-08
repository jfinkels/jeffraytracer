/**
 * 
 */
package edu.bu.cs.cs480.shapes;

import com.sun.opengl.util.GLUT;

/**
 * @author Jeffrey Finkelstein <jeffreyf>
 */
public class Cylindrical extends Circular {
  private final double height;

  /**
   * @param radius
   * @param glut
   */
  public Cylindrical(final double radius, final double height, final GLUT glut) {
    super(radius, glut);
    this.height = height;
  }

  protected double height() {
    return this.height;
  }

}
