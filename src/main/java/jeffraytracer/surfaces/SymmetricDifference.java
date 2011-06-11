/**
 * SymmetricDifference.java - the symmetric difference of two surface objects
 */
package jeffraytracer.surfaces;

import java.util.Arrays;
import java.util.Collections;

import jeffraytracer.Ray;
import jeffraytracer.Vector3D;

/**
 * The symmetric difference of two surface objects.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
// TODO should be non-symmetric difference, I think
public class SymmetricDifference extends ConstructiveSolidGeometry {

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
  public SymmetricDifference(final SurfaceObject object1,
      final SurfaceObject object2) {
    super(object1, object2);
  }

  /**
   * {@inheritDoc}
   * 
   * @param point
   *          {@inheritDoc}
   * @return {@inheritDoc}
   * @see jeffraytracer.surfaces.SurfaceObject#inside(jeffraytracer.Vector3D)
   */
  @Override
  public boolean inside(final Vector3D point) {
    final boolean inside1 = this.object1().inside(point);
    final boolean inside2 = this.object2().inside(point);
    return (inside1 && !inside2) || (!inside1 && inside2);
  }

  /**
   * {@inheritDoc}
   * 
   * @param ray
   *          {@inheritDoc}
   * @return {@inheritDoc}
   * @see jeffraytracer.surfaces.SurfaceObject#interceptWith(jeffraytracer.Ray)
   */
  @Override
  public Intercept interceptWith(final Ray ray) {
    final Intercept intercept1 = this.object1().interceptWith(ray);
    final Intercept intercept2 = this.object2().interceptWith(ray);

    if (intercept1 == null && intercept2 == null) {
      return null;
    }

    if (intercept1 == null && intercept2 != null) {
      return intercept2;
    }

    if (intercept2 == null && intercept1 != null) {
      return intercept1;
    }

    return Collections.min(Arrays.asList(intercept1, intercept2),
        TimeComparator.INSTANCE);
  }

  /**
   * {@inheritDoc}
   * 
   * @param point
   *          {@inheritDoc}
   * @return {@inheritDoc}
   * @see jeffraytracer.surfaces.SurfaceObject#outside(jeffraytracer.Vector3D)
   */
  @Override
  public boolean outside(final Vector3D point) {
    final boolean outside1 = this.object1().outside(point);
    final boolean outside2 = this.object1().outside(point);
    return (outside1 && outside2) || (!outside1 && !outside2);
  }

}
