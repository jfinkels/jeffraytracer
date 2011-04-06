/**
 * Cylinder.java -
 */
package edu.bu.cs.cs480.surfaces;

import edu.bu.cs.cs480.Directed;
import edu.bu.cs.cs480.Vector3D;

/**
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Cylinder extends ConcreteSurfaceObject implements Directed {

  private Vector3D direction = null;
  private double length = 0;

  private double radius = 0;

  /*
   * (non-Javadoc)
   * 
   * @see edu.bu.cs.cs480.Directed#direction()
   */
  @Override
  public Vector3D direction() {
    return this.direction;
  }

  /**
   * @return the length
   */
  public double length() {
    return this.length;
  }

  /**
   * @return the radius
   */
  public double radius() {
    return this.radius;
  }

  /*
   * (non-Javadoc)
   * 
   * @see edu.bu.cs.cs480.Directed#setDirection(edu.bu.cs.cs480.Vector3D)
   */
  @Override
  public void setDirection(Vector3D direction) {
    this.direction = direction;
  }

  /**
   * @param length
   *          the length to set
   */
  public void setLength(double length) {
    this.length = length;
  }

  /**
   * @param radius
   *          the radius to set
   */
  public void setRadius(double radius) {
    this.radius = radius;
  }

}
