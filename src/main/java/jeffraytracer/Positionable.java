/**
 * Positionable.java - an object which has a position
 */
package jeffraytracer;

/**
 * An object which has a position.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public interface Positionable {
  /**
   * Gets the position of this object.
   * 
   * @return The position of this object.
   */
  Vector3D position();

  /**
   * Sets the position of this object.
   * 
   * @param position
   *          The position of this object.
   */
  void setPosition(final Vector3D position);
}
