/**
 * PlaneTest.java - test for the Plane class
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import jeffraytracer.Ray;
import jeffraytracer.Vector3D;

import org.junit.Test;

/**
 * Test for the Plane class.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class PlaneTest {

  /**
   * Test method for
   * {@link jeffraytracer.surfaces.Plane#interceptWith(jeffraytracer.Ray)}.
   */
  @Test
  public void testInterceptWith() {
    final Plane p = new Plane(new Vector3D(0, 0, 1), -4, null);
    final Ray r = new Ray();
    r.setPosition(Vector3D.ORIGIN);
    r.setDirection(new Vector3D(0, 0, 1));

    Intercept i = p.interceptWith(r);
    assertNotNull(i);
    assertEquals(4, i.time(), 0);
    assertNull(i.surfaceObject());

    r.setPosition(new Vector3D(0, 0, 10));
    r.setDirection(new Vector3D(0, 0, -1));

    i = p.interceptWith(r);
    assertNotNull(i);
    assertEquals(6, i.time(), 0);
    assertNull(i.surfaceObject());

    r.setPosition(Vector3D.ORIGIN);
    r.setDirection(new Vector3D(0, 1, 1).normalized());
    i = p.interceptWith(r);
    assertNotNull(i);
    assertEquals(4 * Math.sqrt(2), i.time(), 0);
    assertNull(i.surfaceObject());
  }

  /**
   * Test method for
   * {@link jeffraytracer.surfaces.Plane#inside(jeffraytracer.Vector3D)} .
   */
  @Test
  public void testPointIsBelow() {
    final Plane p = new Plane(new Vector3D(0, 0, 1), -1, null);
    assertTrue(p.inside(Vector3D.ORIGIN));
    assertFalse(p.inside(new Vector3D(0, 0, 2)));
    assertTrue(p.inside(new Vector3D(10, 20, -100)));
    assertFalse(p.inside(new Vector3D(10, 20, 100)));
  }

  /**
   * Test method for
   * {@link jeffraytracer.surfaces.Plane#outside(jeffraytracer.Vector3D)} .
   */
  @Test
  public void testPointIsAbove() {
    final Plane p = new Plane(new Vector3D(0, 0, 1), -1, null);
    assertFalse(p.outside(Vector3D.ORIGIN));
    assertTrue(p.outside(new Vector3D(0, 0, 2)));
    assertFalse(p.outside(new Vector3D(10, 20, -100)));
    assertTrue(p.outside(new Vector3D(10, 20, 100)));
  }

}
