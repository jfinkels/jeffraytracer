/**
 * Food.java - a drawable model of a piece of food
 */
package edu.bu.cs.cs480.model.creatures;

import com.sun.opengl.util.GLUT;

import edu.bu.cs.cs480.model.Point3D;
import edu.bu.cs.cs480.model.SizedComponent;
import edu.bu.cs.cs480.shapes.RoundedCylinder;

/**
 * A drawable model of a piece of food for the vivarium simulation.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Food extends SizedComponent {

  /** The height of the food. */
  public static final double HEIGHT = 0.05;
  /** The radius of the food. */
  public static final double RADIUS = 0.1;

  /**
   * Creates a piece of food which looks like a pellet.
   * 
   * @param position
   *          The position of the component.
   * @param glut
   *          The OpenGL utility toolkit object, used for drawing circular
   *          objects.
   * @param name
   *          The name of this component.
   */
  public Food(final Point3D position, final GLUT glut, final String name) {
    super(position, new RoundedCylinder(HEIGHT, RADIUS, glut), name);
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see edu.bu.cs.cs480.model.Component#boundingRadius()
   */
  @Override
  public double boundingRadius() {
    return RADIUS;
  }

}
