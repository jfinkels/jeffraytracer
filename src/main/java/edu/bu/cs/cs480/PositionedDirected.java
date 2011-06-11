/**
 * PositionedDirected.java - an object with a position and a direction
 */
package edu.bu.cs.cs480;


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
   * @see edu.bu.cs.cs480.Directed#direction()
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
   * @see edu.bu.cs.cs480.Directed#setDirection(edu.bu.cs.cs480.Vector3D)
   */
  @Override
  public void setDirection(final Vector3D direction) {
    this.direction = direction;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see edu.bu.cs.cs480.Positionable#position()
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
   * @see edu.bu.cs.cs480.Positionable#setPosition(edu.bu.cs.cs480.Vector3D)
   */
  @Override
  public void setPosition(final Vector3D position) {
    this.position = position;
  }
}
