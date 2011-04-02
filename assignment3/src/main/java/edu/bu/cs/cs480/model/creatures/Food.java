/**
 * 
 */
package edu.bu.cs.cs480.model.creatures;

import com.sun.opengl.util.GLUT;

import edu.bu.cs.cs480.model.Point3D;
import edu.bu.cs.cs480.model.SizedComponent;
import edu.bu.cs.cs480.shapes.RoundedCylinder;

/**
 * @author jeff
 * 
 */
public class Food extends SizedComponent {

  public static final double HEIGHT = 0.02;
  public static final double RADIUS = 0.05;

  /**
   * @param position
   * @param displayable
   * @param name
   */
  public Food(final Point3D position, final GLUT glut, final String name) {
    super(position, new RoundedCylinder(HEIGHT, RADIUS, glut), name);
  }

  /*
   * (non-Javadoc)
   * 
   * @see edu.bu.cs.cs480.model.Component#boundingRadius()
   */
  @Override
  public double boundingRadius() {
    return RADIUS;
  }

}
