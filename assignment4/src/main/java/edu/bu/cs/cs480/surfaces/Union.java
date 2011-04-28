/**
 * Union.java - the union of two surface objects
 */
package edu.bu.cs.cs480.surfaces;

import java.util.Arrays;
import java.util.Collections;

import edu.bu.cs.cs480.vectors.Ray;
import edu.bu.cs.cs480.vectors.Vector3D;

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

  /**
   * {@inheritDoc}
   * 
   * @param ray
   *          {@inheritDoc}
   * @return {@inheritDoc}
   * @see edu.bu.cs.cs480.surfaces.SurfaceObject#interceptWith(edu.bu.cs.cs480.vectors.Ray)
   */
  @Override
  public Intercept interceptWith(final Ray ray) {
    final Intercept intercept1 = this.object1().interceptWith(ray);
    final Intercept intercept2 = this.object2().interceptWith(ray);
    return Collections.min(Arrays.asList(intercept1, intercept2));
  }

  /**
   * {@inheritDoc}
   * 
   * @param point
   *          {@inheritDoc}
   * @return {@inheritDoc}
   * @see edu.bu.cs.cs480.surfaces.SurfaceObject#inside(edu.bu.cs.cs480.vectors.Vector3D)
   */
  @Override
  public boolean inside(final Vector3D point) {
    return this.object1().inside(point) && this.object2().inside(point);
  }

  /**
   * {@inheritDoc}
   * 
   * @param point
   *          {@inheritDoc}
   * @return {@inheritDoc}
   * @see edu.bu.cs.cs480.surfaces.SurfaceObject#outside(edu.bu.cs.cs480.vectors.Vector3D)
   */
  @Override
  public boolean outside(final Vector3D point) {
    return this.object1().outside(point) && this.object2().outside(point);
  }

}
