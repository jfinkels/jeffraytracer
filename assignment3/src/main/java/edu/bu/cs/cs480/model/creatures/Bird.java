/**
 * Bird.java - a drawable model of a bird with a body, head, beak, and wings
 */
package edu.bu.cs.cs480.model.creatures;

import java.util.List;

import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;

import edu.bu.cs.cs480.model.Component;
import edu.bu.cs.cs480.model.Point3D;
import edu.bu.cs.cs480.shapes.Cone;
import edu.bu.cs.cs480.shapes.Ellipsoid;
import edu.bu.cs.cs480.shapes.ScaledCone;

/**
 * A drawable model of a bird, with a body, a head, a beak, and two wings which
 * move up and down.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Bird extends Creature {

  /** A body for the bird. */
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

  /** The head of the bird. */
  private class Head extends Component {

    /**
     * Instantiates this class by creating an Ellipsoid for the head and a
     * ScaledCone for the beak.
     * 
     * @param position
     *          The position of the component.
     * @param glut
     *          The OpenGL utility toolkit object, used for drawing circular
     *          objects.
     * @param name
     *          The name of this component.
     */
    public Head(final Point3D position, final GLUT glut, final String name) {
      super(position, new Ellipsoid(HEAD_RADIUS, glut), name);

      final Component beak = new Component(
          new Point3D(0.8 * HEAD_RADIUS, 0, 0), new Cone(BEAK_RADIUS,
              BEAK_HEIGHT, glut), name + " - beak");
      beak.rotate(new Point3D(0, 1, 0), 90);
      this.addChild(beak);
    }

  }

  /** The wing of the bird. */
  private class Wing extends Component {

    /**
     * Instantiates this class by creating a component with a ScaledCone
     * displayable object.
     * 
     * @param position
     *          The position of the component.
     * @param glut
     *          The OpenGL utility toolkit object, used for drawing circular
     *          objects.
     * @param name
     *          The name of this component.
     */
    public Wing(final Point3D position, final GLUT glut, final String name) {
      super(position,
          new ScaledCone(WING_RADIUS, WING_HEIGHT, 1, 0.5, 1, glut), name);
    }

  }

  /** The height of the beak. */
  public static final double BEAK_HEIGHT = 0.05;
  /** The radius of the beak. */
  public static final double BEAK_RADIUS = 0.02;
  /** The radius of the body. */
  public static final double BODY_RADIUS = 0.08;
  /** The radius of the head. */
  public static final double HEAD_RADIUS = 0.05;
  /** The amount by which to increment the angle of the wing on each update. */
  public static final double INCREMENT = 0.3;
  /** The maximum angle for the wing to achieve in either direction. */
  public static final double MAX_WING_ANGLE = 45;
  /** Two times pi. */
  public static final double TWO_PI = 2 * Math.PI;
  /** The height of a wing. */
  public static final double WING_HEIGHT = .1;
  /** The radius of a wing. */
  public static final double WING_RADIUS = 0.04;

  /** The counter to use for incrementing the wing angle along the sine curve. */
  private double counter = 0;
  /** The left wing object. */
  private final Wing leftWing;
  /** The right wing object. */
  private final Wing rightWing;

  /**
   * Creates a bird, which has a body, a head with a beak, and two wings which
   * flap up and down.
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
   */
  public Bird(final Point3D position, final GLUT glut, String name,
      final List<Creature> flock) {
    super(position, null, name, flock);

    this.setVelocity(new Point3D(0.01, 0, 0));

    final Component body = new Body(Point3D.ORIGIN, glut, name + " - body");
    this.leftWing = new Wing(new Point3D(0, 0, -0.8 * BODY_RADIUS), glut, name
        + " - left wing");
    this.rightWing = new Wing(new Point3D(0, 0, 0.8 * BODY_RADIUS), glut, name
        + " - right wing");
    final Component head = new Head(new Point3D(BODY_RADIUS,
        0.5 * BODY_RADIUS, 0), glut, name + " - head");

    this.addChildren(body, this.leftWing, this.rightWing, head);
  }

  /*
   * (non-Javadoc)
   * 
   * @see edu.bu.cs.cs480.model.creatures.Creature#boundingRadius()
   */
  @Override
  public double boundingRadius() {
    return BODY_RADIUS;
  }

  /**
   * Updates the angle of the wings on each call.
   * 
   * @param GL
   *          {@inheritDoc}
   */
  @Override
  public void update(final GL gl) {
    this.counter = (this.counter + INCREMENT) % TWO_PI;
    final double wingAngle = Math.sin(this.counter) * MAX_WING_ANGLE;
    this.leftWing.rotateTo(new Point3D(1, 0, 0), 180 + wingAngle);
    this.rightWing.rotateTo(new Point3D(1, 0, 0), -wingAngle);

    super.update(gl);
  }
}
