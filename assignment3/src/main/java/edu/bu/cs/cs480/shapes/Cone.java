/**
 * Cone.java - a displayable cone
 */
package edu.bu.cs.cs480.shapes;

import com.sun.opengl.util.GLUT;

/**
 * A displayable cone.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Cone extends ScaledCone {

  /**
   * @param radius
   * @param height
   * @param glut
   */
  public Cone(double radius, double height, GLUT glut) {
    super(radius, height, 1, 1, 1, glut);
  }

}
