/**
 * Creature.java
 */
package edu.bu.cs.cs480.model.creatures;

import javax.media.opengl.GL;

import edu.bu.cs.cs480.drawing.Displayable;
import edu.bu.cs.cs480.model.Component;
import edu.bu.cs.cs480.model.Point3D;

/**
 * A creature which has a velocity, can move, and has a bounding sphere.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public abstract class Creature extends Component {

  /** The current velocity of this creature. */
  private Point3D velocity = new Point3D(0.01, 0, 0);

  public static final double MAX_X = 3;
  public static final double MIN_X = -3;
  public static final double MAX_Y = 3;
  public static final double MIN_Y = -3;
  public static final double MAX_Z = 3;
  public static final double MIN_Z = -3;

  /**
   * @param position
   * @param displayable
   * @param name
   */
  public Creature(Point3D position, Displayable displayable, String name) {
    super(position, displayable, name);
  }

  /**
   * @param position
   * @param name
   */
  public Creature(Point3D position, String name) {
    super(position, name);
  }

  /** Updates the position of this component using the velocity. */
  protected void move() {
    this.setPosition(this.position().sumWith(this.velocity));
    this.checkBounds();
  }

  /**
   * Returns true if and only if this creature is touching the specified other
   * creature.
   * 
   * Algorithm: check if the distance between the two centers (that is, the
   * positions of the components) is greater than the sum of the radii of the
   * two bounding spheres.
   * 
   * @param that
   *          The creature to check for collision with this one.
   * @return {@code true} if and only if this creature is touching the specified
   *         other creature.
   */
  public boolean isTouching(final Creature that) {
    return this.position().distanceTo(that.position()) < this.boundingRadius()
        + that.boundingRadius();
  }

  public abstract double boundingRadius();

  private void checkBounds() {
    double x = this.position().x();
    double y = this.position().y();
    double z = this.position().z();
    x = Math.max(Math.min(x, MAX_X), MIN_X);
    y = Math.max(Math.min(y, MAX_Y), MIN_Y);
    z = Math.max(Math.min(z, MAX_Z), MIN_Z);
    this.setPosition(new Point3D(x, y, z));
  }

  public void setVelocity(final Point3D velocity) {
    final double thisNorm = this.velocity.norm();
    final double thatNorm = velocity.norm();
    final double dotProduct = this.velocity.dotProduct(velocity);
    final Point3D crossProduct = this.velocity.crossProduct(velocity);
    if (crossProduct.x() == 0 && crossProduct.y() == 0 && crossProduct.z() == 0) {
      // TODO need to check if they are collinear
      return;
    }
    System.out.println("old velocity: " + this.velocity);
    System.out.println("new velocity: " + velocity);
    this.velocity = velocity;

    // compute angle between current velocity and new velocity
    final double angle = Math.acos(dotProduct / (thisNorm * thatNorm))
        * (180 / Math.PI);

    System.out.println("new axis:  " + crossProduct);
    System.out.println("new angle: " + angle);
    this.rotate(crossProduct.normalized(), angle);
  }

  @Override
  public void update(final GL gl) {
    this.move();

    super.update(gl);
  }
}
