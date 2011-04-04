/**
 * ConstructiveSolidGeometry.java -
 */
package edu.bu.cs.cs480.surfaces;

/**
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public abstract class ConstructiveSolidGeometry extends SurfaceObject {
  private final SurfaceObject object1;
  private final SurfaceObject object2;

  public ConstructiveSolidGeometry(final SurfaceObject object1,
      final SurfaceObject object2) {
    this.object1 = object1;
    this.object2 = object2;
  }
  
  protected SurfaceObject object1() {
    return this.object1;
  }
  
  protected SurfaceObject object2() {
    return this.object2;
  }
}
