/**
 * Intersection.java - the intersection of two surface objects
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

import jeffraytracer.Ray;
import jeffraytracer.Vector3D;

/**
 * The intersection of two surface objects.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Intersection extends ConstructiveSolidGeometry {

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
  public Intersection(final SurfaceObject object1, final SurfaceObject object2) {
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
    return this.object1().inside(point) && this.object2().inside(point);
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
    if (intercept1 == null || intercept2 == null) {
      return null;
    }
    final SurfaceObject surfaceObject1 = intercept1.surfaceObject();
    final SurfaceObject surfaceObject2 = intercept2.surfaceObject();
    final Vector3D point1 = intercept1.pointOfIntersection();
    final Vector3D point2 = intercept2.pointOfIntersection();
    final double time1 = intercept1.time();
    final double time2 = intercept2.time();
    if (time1 < time2 && surfaceObject1.inside(point2)) {
      return intercept2;
    }
    if (time2 < time1 && surfaceObject2.inside(point1)) {
      return intercept1;
    }
    return null;
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
    return this.object1().outside(point) || this.object2().outside(point);
  }

}
