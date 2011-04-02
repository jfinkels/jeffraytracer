/**
 * Cylindrical.java - a displayable cylindrical object
 */
package edu.bu.cs.cs480.shapes;

import com.sun.opengl.util.GLUT;

/**
 * A displayable cylindrical object.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Cylindrical extends Circular {

  /** The height of this cylinder. */
  private final double height;

  /**
   * Instantiates this object with the specified height and radius.
   * 
   * @param radius
   *          The radius of this cylinder.
   * @param height
   *          The height of this cylinder.
   * @param glut
   *          The OpenGL utility toolkit object used to draw this circular
   *          object.
   */
  public Cylindrical(final double radius, final double height, final GLUT glut) {
    super(radius, glut);
    this.height = height;
  }

  /**
   * Gets the height of this cylinder.
   * 
   * @return The height of this cylinder.
   */
  protected double height() {
    return this.height;
  }

}
