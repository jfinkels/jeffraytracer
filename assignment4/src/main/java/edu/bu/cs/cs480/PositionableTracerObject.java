/**
 * PositionableTracerObject.java - 
 */
package edu.bu.cs.cs480;

/**
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class PositionableTracerObject extends TracerObject implements
    Positionable {
  private Vector3D position = null;

  /* (non-Javadoc)
   * @see edu.bu.cs.cs480.Positionable#position()
   */
  @Override
  public Vector3D position() {
    return this.position;
  }

  /* (non-Javadoc)
   * @see edu.bu.cs.cs480.Positionable#setPosition(edu.bu.cs.cs480.Vector3D)
   */
  @Override
  public void setPosition(Vector3D position) {
    this.position = position;
  }
  
  

}
