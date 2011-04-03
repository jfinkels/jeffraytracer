/**
 * SizedComponent.java - a component which has a bounding sphere
 */
package edu.bu.cs.cs480.model;

import edu.bu.cs.cs480.drawing.Displayable;

/**
 * A Component which has a bounding sphere.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public abstract class SizedComponent extends Component {

  /**
   * Instantiates this class by calling the corresponding constructor of the
   * superclass.
   * 
   * @param position
   *          The position of this component.
   * @param displayable
   *          The object which this component represents.
   * @param name
   *          The human-readable name of this component.
   */
  public SizedComponent(final Point3D position, final Displayable displayable,
      final String name) {
    super(position, displayable, name);
  }

  /**
   * Instantiates this class by calling the corresponding constructor of the
   * superclass.
   * 
   * @param position
   *          The position of this component.
   * @param name
   *          The human-readable name of this component.
   */
  public SizedComponent(final Point3D position, final String name) {
    super(position, name);
  }

  /**
   * Returns the radius of the sphere which bounds this object.
   * 
   * @return The radius of the sphere which bounds this object.
   */
  public abstract double boundingRadius();

  /**
   * Returns true if and only if this component is touching the specified other
   * component.
   * 
   * Algorithm: check if the distance between the two centers (that is, the
   * positions of the components) is greater than the sum of the radii of the
   * two bounding spheres.
   * 
   * @param that
   *          The component to check for collision with this one.
   * @return {@code true} if and only if this component is touching the
   *         specified other component.
   */
  public boolean isTouching(final SizedComponent that) {
    return this.position().distanceTo(that.position()) < this.boundingRadius()
        + that.boundingRadius();
  }

}
