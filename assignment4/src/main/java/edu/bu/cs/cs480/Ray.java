/**
 * Ray.java - a ray originating from a point and extending in a direction
 */
package edu.bu.cs.cs480;

/**
 * A "half line", starting at a point and extending to infinity in a direction.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Ray extends PositionedDirected {
  /**
   * Gets the human-readable string representation of this ray.
   * 
   * @return The human-readable string representation of this ray.
   */
  @Override
  public String toString() {
    return "Ray at " + this.position() + " directed by " + this.direction();
  }
}
