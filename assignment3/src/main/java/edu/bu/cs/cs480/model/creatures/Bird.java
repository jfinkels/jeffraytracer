/**
 * 
 */
package edu.bu.cs.cs480.model.creatures;

import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;

import edu.bu.cs.cs480.model.Axis;
import edu.bu.cs.cs480.model.Component;
import edu.bu.cs.cs480.model.Point3D;
import edu.bu.cs.cs480.shapes.Cone;
import edu.bu.cs.cs480.shapes.Ellipsoid;

/**
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Bird extends Component {

  public static final double BODY_RADIUS = 1;
  public static final double HEAD_RADIUS = 0.5;
  public static final double BEAK_RADIUS = 0.2;
  public static final double BEAK_HEIGHT = 0.5;

  private class Body extends Component {

    /**
     * @param position
     * @param glut
     * @param name
     */
    public Body(Point3D position, final GLUT glut, String name) {
      super(position, new Ellipsoid(BODY_RADIUS, glut), name);
    }

  }

  private class Head extends Component {

    /**
     * @param position
     * @param glut
     * @param name
     */
    public Head(final Point3D position, final GLUT glut, final String name) {
      super(position, new Ellipsoid(HEAD_RADIUS, glut), name);

      final Component beak = new Component(new Point3D(HEAD_RADIUS, 0, 0),
          new Cone(BEAK_RADIUS, BEAK_HEIGHT, glut), name + " - beak");

      this.addChild(beak);
    }

  }

  private class Wing extends Component {

    /**
     * @param position
     * @param name
     */
    public Wing(final Point3D position, final String name) {
      super(position, name);
    }

    public void rotateTo(final Axis axis, final double angle) {
      switch (axis) {
      case X:
        this.rotate(axis, angle - this.xAngle());
        break;
      case Y:
        this.rotate(axis, angle - this.yAngle());
        break;
      case Z:
        this.rotate(axis, angle - this.zAngle());
        break;
      default:
        break;
      }
    }
  }

  /**
   * @param position
   * @param glut
   * @param name
   */
  public Bird(final Point3D position, final GLUT glut, String name) {
    super(position, name);

    final Component body = new Body(Point3D.ORIGIN, glut, name + " - body");
    this.leftWing = new Wing(new Point3D(0, BODY_RADIUS, 0), name
        + " - left wing");
    this.rightWing = new Wing(new Point3D(0, -BODY_RADIUS, 0), name
        + " - right wing");
    final Component head = new Head(new Point3D(BODY_RADIUS, 0, 0), glut, name
        + " - head");

    this.leftWing.rotate(Axis.Z, 90);
    this.rightWing.rotate(Axis.Z, -90);

    this.addChildren(body, this.leftWing, this.rightWing, head);
  }

  private final Wing leftWing;
  private final Wing rightWing;
  public static final double TWO_PI = 2 * Math.PI;
  public static final double INCREMENT = 0.1;
  private double counter = 0;
  public static final double MAX_WING_ANGLE = 45;

  @Override
  public void update(final GL gl) {
    this.counter = (this.counter + INCREMENT) % TWO_PI;
    final double wingAngle = Math.sin(this.counter) * MAX_WING_ANGLE;
    this.leftWing.rotateTo(Axis.X, wingAngle);
    this.rightWing.rotateTo(Axis.X, wingAngle);
    super.update(gl);

  }
}