/**
 * 
 */
package edu.bu.cs.cs480.shapes;

import com.sun.opengl.util.GLUT;

/**
 * @author Jeffrey Finkelstein <jeffreyf>
 */
public class Cone extends ScaledCone {

  /**
   * @param radius
   * @param height
   * @param glut
   */
  public Cone(double radius, double height, GLUT glut) {
    super(radius, height, 0, 0, 0, glut);
  }

}
