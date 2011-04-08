/**
 * Union.java - the union of two surface objects
 */
package edu.bu.cs.cs480.surfaces;

/**
 * The union of two surface objects.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Union extends ConstructiveSolidGeometry {

  /**
   * Instantiates this object with the two specified surface objects which
   * comprise it.
   * 
   * @param object1
   *          A surface object which makes up this constructive solid geometry
   *          object.
   * @param object2
   *          Another surface object which makes up this constructive solid
   *          geometry object.
   */
  public Union(SurfaceObject object1, SurfaceObject object2) {
    super(object1, object2);
  }

}
