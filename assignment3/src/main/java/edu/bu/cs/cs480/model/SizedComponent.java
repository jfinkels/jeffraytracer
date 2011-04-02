/**
 * 
 */
package edu.bu.cs.cs480.model;

import edu.bu.cs.cs480.drawing.Displayable;
import edu.bu.cs.cs480.model.creatures.Creature;

/**
 * @author jeff
 * 
 */
public abstract class SizedComponent extends Component {

  /**
   * @param position
   * @param displayable
   * @param name
   */
  public SizedComponent(Point3D position, Displayable displayable, String name) {
    super(position, displayable, name);
    // TODO Auto-generated constructor stub
  }

  /**
   * @param position
   * @param displayable
   * @param name
   */
  public SizedComponent(Point3D position, String name) {
    super(position, name);
    // TODO Auto-generated constructor stub
  }

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

  /**
   * Returns the radius of the sphere which bounds this object.
   * 
   * @return The radius of the sphere which bounds this object.
   */
  public abstract double boundingRadius();

}
