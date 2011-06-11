/**
 * PositionableTracerObject.java - an object in the scene which has a position
 */
package jeffraytracer;

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
