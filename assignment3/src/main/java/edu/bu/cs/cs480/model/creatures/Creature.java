/**
 * Creature.java
 */
package edu.bu.cs.cs480.model.creatures;

import javax.media.opengl.GL;

import edu.bu.cs.cs480.drawing.Displayable;
import edu.bu.cs.cs480.model.Component;
import edu.bu.cs.cs480.model.Point3D;

/**
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Creature extends Component {

  /** The current velocity of this creature. */
  private Point3D velocity = new Point3D(0.1, 0, 0);

  /**
   * @param position
   * @param displayable
   * @param name
   */
  public Creature(Point3D position, Displayable displayable, String name) {
    super(position, displayable, name);
  }

  /** Updates the position of this component using the velocity. */
  protected void move() {
    this.setPosition(this.position().sumWith(this.velocity));
  }

  public void setVelocity(final Point3D velocity) {
    final double thisNorm = this.velocity.norm();
    final double thatNorm = velocity.norm();
    final double dotProduct = this.velocity.dotProduct(velocity);
    final Point3D crossProduct = this.velocity.crossProduct(velocity);
    this.velocity = velocity;

    // compute angle between current velocity and new velocity
    final double angle = Math.acos(dotProduct / (thisNorm * thatNorm))
        * (180 / Math.PI);
    
    //this.rotateBy(crossProduct, angle);
  }

  @Override
  public void update(final GL gl) {
    this.move();

    super.update(gl);
  }
}
