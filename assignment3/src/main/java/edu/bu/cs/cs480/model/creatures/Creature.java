/**
 * Creature.java
 */
package edu.bu.cs.cs480.model.creatures;

import java.util.List;

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
   * @param flock
   *          The flock to which this creature belongs.
   */
  public Creature(Point3D position, Displayable displayable, String name,
      final List<Creature> flock) {
    super(position, displayable, name);
    this.flock = flock;
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
   * @return {@code true} if and only if this creature is touching the
   *         specified other creature.
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
  
  private void limitVelocity() {
    if (this.velocity.norm() > MAX_SPEED) {
      this.velocity = this.velocity.normalized().scaledBy(MAX_SPEED);
    }
  }

  public static final double MAX_SPEED = 0.2;
  
  public void setVelocity(final Point3D velocity) {
    final double thisNorm = this.velocity.norm();
    final double thatNorm = velocity.norm();
    final double dotProduct = this.velocity.dotProduct(velocity);
    final Point3D crossProduct = this.velocity.crossProduct(velocity);
    if (crossProduct.x() == 0 && crossProduct.y() == 0
        && crossProduct.z() == 0) {
      // TODO need to check if they are collinear
      return;
    }
    this.velocity = velocity;

    // compute angle between current velocity and new velocity
    final double angle = Math.acos(dotProduct / (thisNorm * thatNorm))
        * (180 / Math.PI);

    this.rotate(crossProduct.normalized(), angle);
    
    // limit magnitude of velocity
    this.limitVelocity();
  }

  private final List<Creature> flock;

  // code from: http://www.vergenet.net/~conrad/boids/pseudocode.html
  // pre-condition: flock is not null and includes creatures besides this one
  private Point3D velocityTowardsCenter() {
    double xSum = 0;
    double ySum = 0;
    double zSum = 0;

    for (final Creature creature : this.flock) {
      if (!creature.equals(this)) {
        xSum += creature.position().x();
        ySum += creature.position().y();
        zSum += creature.position().z();
      }
    }

    final int numCreatures = this.flock.size() - 1;
    final Point3D center = new Point3D(xSum / numCreatures, ySum
        / numCreatures, zSum / numCreatures);

    return center.difference(this.position()).scaledBy(CENTER_WEIGHT);
  }

  private Point3D repulsionVelocity() {
    Point3D result = Point3D.ORIGIN;

    for (final Creature creature : this.flock) {
      if (!creature.equals(this)) {
        if (this.position().distanceTo(creature.position()) < REPULSION_DISTANCE) {
          result = result.difference(creature.position().difference(
              this.position()));
        }
      }
    }
    
    return result;
  }
  
  private Point3D perceivedFlockVelocity() {
    double xSum = 0;
    double ySum = 0;
    double zSum = 0;

    for (final Creature creature : this.flock) {
      if (!creature.equals(this)) {
        xSum += creature.velocity.x();
        ySum += creature.velocity.y();
        zSum += creature.velocity.z();
      }
    }

    final int numCreatures = this.flock.size() - 1;
    final Point3D perceivedVelocity = new Point3D(xSum / numCreatures, ySum
        / numCreatures, zSum / numCreatures);

    return perceivedVelocity.difference(this.velocity).scaledBy(VELOCITY_WEIGHT);
    
  }

  public static final double VELOCITY_WEIGHT = 0.125;
  public static final double CENTER_WEIGHT = 0.01;
  public static final double REPULSION_DISTANCE = 0.2;

  private void flockVelocityUpdate() {

    // first compute the velocity towards the center of the flock
    final Point3D velocityTowardsCenter = this.velocityTowardsCenter();

    // second compute the repulsion from other creatures
    final Point3D repulsionVelocity = this.repulsionVelocity();

    // third compute the perceived velocity of the flock
    final Point3D perceivedFlockVelocity = this.perceivedFlockVelocity();
    
    // add the computed flock velocity offsets to the current velocity
    Point3D newVelocity = this.velocity.sumWith(velocityTowardsCenter);
    newVelocity = this.velocity.sumWith(repulsionVelocity);
    newVelocity = this.velocity.sumWith(perceivedFlockVelocity);
        
    // update the new velocity of the creature
    this.setVelocity(newVelocity);
    
  }
  
  @Override
  public void update(final GL gl) {

    // update the velocity based on the known flock
    this.flockVelocityUpdate();
    
    // move the creature
    this.move();

    // update the OpenGL call list
    super.update(gl);
  }
}
