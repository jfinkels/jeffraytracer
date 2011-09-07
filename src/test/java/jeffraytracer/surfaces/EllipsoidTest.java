/**
 * EllipsoidTest.java - test for the Ellipsoid class
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
import jeffraytracer.Matrix4x4;
import jeffraytracer.Vector3D;

import org.junit.Test;

/**
 * Test for the Ellipsoid class.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class EllipsoidTest {

  /**
   * Test method for {@link jeffraytracer.surfaces.Ellipsoid#baseMatrix()}.
   */
  @Test
  public void testBaseMatrix() {
    final Ellipsoid ellipsoid = new Ellipsoid();
    ellipsoid.setRadii(new Vector3D(1, 2, 3));
    final Matrix4x4 base = ellipsoid.baseMatrix();

    assertEquals(1.0 / 1, base.get(0, 0), 0);
    assertEquals(1.0 / 4, base.get(1, 1), 0);
    assertEquals(1.0 / 9, base.get(2, 2), 0);
    assertEquals(-1, base.get(3, 3), 0);
  }

}
