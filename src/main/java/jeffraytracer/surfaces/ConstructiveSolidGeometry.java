/**
 * ConstructiveSolidGeometry.java - a surface object comprised of two others
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
