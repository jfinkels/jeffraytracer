/**
 * SymmetricDifference.java - the symmetric difference of two surface objects
 * 
 * Copyright 2011 Jeffrey Finkelstein
 * 
 * This file is part of jeffraytracer.
 * 
 * jeffraytracer is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 * 
 * jeffraytracer is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * jeffraytracer. If not, see <http://www.gnu.org/licenses/>.
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
