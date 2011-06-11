/**
 * PositionedDirected.java - an object with a position and a direction
 */
package jeffraytracer;

/**
 * An object with a position and a direction.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class PositionedDirected implements Positionable, Directed {

  /** The point at which this vector originates. */
  private Vector3D position = null;
  /** The direction in which this ray extends from its origin. */
  private Vector3D direction = null;

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jeffraytracer.Directed#direction()
   */
  @Override
  public Vector3D direction() {
    return this.direction;
  }

  /**
   * {@inheritDoc}
   * 
   * @param direction
   *          {@inheritDoc}
   * @see jeffraytracer.Directed#setDirection(jeffraytracer.Vector3D)
   */
  @Override
  public void setDirection(final Vector3D direction) {
    this.direction = direction;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jeffraytracer.Positionable#position()
   */
  @Override
  public Vector3D position() {
    return this.position;
  }

  /**
   * {@inheritDoc}
   * 
   * @param position
   *          {@inheritDoc}
   * @see jeffraytracer.Positionable#setPosition(jeffraytracer.Vector3D)
   */
  @Override
  public void setPosition(final Vector3D position) {
    this.position = position;
  }
}
