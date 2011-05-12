/**
 * PositionableTracerObject.java - an object in the scene which has a position
 */
package edu.bu.cs.cs480;


/**
 * An object in a tracer scene which has a position.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class PositionableTracerObject extends TracerObject implements
    Positionable {
  /** The position of this object. */
  private Vector3D position = null;

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
