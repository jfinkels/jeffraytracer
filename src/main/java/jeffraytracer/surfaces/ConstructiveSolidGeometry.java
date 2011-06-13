/**
 * ConstructiveSolidGeometry.java - a surface object comprised of two others
 */
package jeffraytracer.surfaces;

import jeffraytracer.TracerObject;

/**
 * A surface object which is comprised of two other surface objects.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public abstract class ConstructiveSolidGeometry extends TracerObject implements
    SurfaceObject {
  /**
   * One surface object which makes up this constructive solid geometry object.
   */
  private final SurfaceObject object1;
  /**
   * Another surface object which makes up this constructive solid geometry
   * object.
   */
  private final SurfaceObject object2;

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
  public ConstructiveSolidGeometry(final SurfaceObject object1,
      final SurfaceObject object2) {
    this.object1 = object1;
    this.object2 = object2;
  }

  /**
   * Gets the first surface object.
   * 
   * @return One of the surface objects.
   */
  protected SurfaceObject object1() {
    return this.object1;
  }

  /**
   * Gets the second surface object.
   * 
   * @return One of the surface objects.
   */
  protected SurfaceObject object2() {
    return this.object2;
  }

  /**
   * {@inheritDoc}
   * 
   * @see jeffraytracer.surfaces.SurfaceObject#compile()
   */
  @Override
  public void compile() {
    this.object1.compile();
    this.object2.compile();
  }

}