/**
 * Fish.java - a drawable model of a fish with a body and tail that flaps
 */
package edu.bu.cs.cs480.model.creatures;

import java.util.List;

import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;

import edu.bu.cs.cs480.model.Component;
import edu.bu.cs.cs480.model.Point3D;
import edu.bu.cs.cs480.model.SizedComponent;
import edu.bu.cs.cs480.shapes.Ellipsoid;
import edu.bu.cs.cs480.shapes.FlippedScaledCone;

/**
 * A drawable model of a fish with a body and a tail that moves left to right.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein>
 * @since Spring 2011
 */
public class Fish extends Creature {
  /** A body for the fish. */
  private class Body extends Component {

    /**
     * Instantiates this class by calling the corresponding constructor of the
     * superclass.
     * 
     * @param position
     *          The position of the component.
     * @param glut
     *          The OpenGL utility toolkit object, used for drawing circular
     *          objects.
     * @param name
     *          The name of this component.
     */
    public Body(Point3D position, final GLUT glut, String name) {
      super(position, new Ellipsoid(BODY_RADIUS, glut), name);
    }

  }

  /** A tail for the fish. */
  private class Tail extends Component {

    /**
     * Instantiates this class by calling the corresponding constructor of the
     * superclass.
     * 
     * @param position
     *          The position of the component.
     * @param glut
     *          The OpenGL utility toolkit object, used for drawing circular
     *          objects.
     * @param name
     *          The name of this component.
     */
    public Tail(Point3D position, final GLUT glut, String name) {
      super(position, new FlippedScaledCone(TAIL_RADIUS, TAIL_HEIGHT, 1, 0.4,
          1, glut), name);
    }

  }

  /** The radius of the body. */
  public static final double BODY_RADIUS = 0.08;
  /** The amount by which to increment the angle of the wing on each update. */
  public static final double INCREMENT = 0.3;
  /** The maximum angle for the tail to achieve in either direction. */
  public static final double MAX_TAIL_ANGLE = 45;
  /** The height of the tail. */
  public static final double TAIL_HEIGHT = .1;
  /** The radius of the tail. */
  public static final double TAIL_RADIUS = BODY_RADIUS;
  /** Two times pi. */
  public static final double TWO_PI = 2 * Math.PI;
  /** The counter to use for incrementing the wing angle along the sine curve. */
  private double counter = 0;
  /** The tail of this fish. */
  private final Tail tail;

  /**
   * Creates a fish which has a body and a tail.
   * 
   * @param position
   *          The position of the component.
   * @param glut
   *          The OpenGL utility toolkit object, used for drawing circular
   *          objects.
   * @param name
   *          The name of this component.
   * @param flock
   *          The flock to which this creature belongs which helps guide its
   *          movement.
   * @param food
   *          The list of food visible to this creature which helps guide its
   *          movement.
   */
  public Fish(Point3D position, final GLUT glut, final String name,
      final List<Creature> flock, final List<SizedComponent> food,
      final List<Creature> fleeFrom) {
    super(position, null, name, flock, food, fleeFrom);

    this.setVelocity(new Point3D(0.01, 0, 0));

    final Component body = new Body(Point3D.ORIGIN, glut, name + " - body");
    this.tail = new Tail(new Point3D(-0.8 * BODY_RADIUS, 0, 0), glut, name
        + " - tail");

    this.addChildren(body, this.tail);
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see edu.bu.cs.cs480.model.creatures.Creature#boundingRadius()
   */
  @Override
  public double boundingRadius() {
    return BODY_RADIUS;
  }

  /**
   * Updates the angle of the fin on each call.
   * 
   * @param GL
   *          {@inheritDoc}
   */
  @Override
  public void update(final GL gl) {
    this.counter = (this.counter + INCREMENT) % TWO_PI;
    final double wingAngle = Math.sin(this.counter) * MAX_TAIL_ANGLE;
    this.tail.rotateTo(new Point3D(0, 1, 0), 90 + wingAngle);
    this.tail.rotate(new Point3D(0, 0, 1), 90);

    super.update(gl);
  }

}
