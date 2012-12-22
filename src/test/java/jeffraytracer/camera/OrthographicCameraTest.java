/**
 * OrthographicCameraTest.java - test for the OrthographicCamera class
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
package jeffraytracer.camera;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import jeffraytracer.Vector3D;

import org.junit.Test;

/**
 * Test for the OrthographicCamera class.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class OrthographicCameraTest {

  /**
   * Test method for
   * {@link jeffraytracer.camera.OrthographicCamera#rayDirection(jeffraytracer.Vector3D)}
   * .
   */
  @Test
  public void testRayDirection() {
    final OrthographicCamera c = new OrthographicCamera();
    final Vector3D d = new Vector3D(0, 0, 1);
    c.setPosition(new Vector3D(0, 0, -1));
    c.setDirection(d);
    c.setUp(new Vector3D(1, 0, 0));
    final Vector3D[] result = c.rayDirections(new Vector3D(1, 2, 3));
    assertEquals(1, result.length);
    assertTrue(result[0].equalTo(d)); 
  }

}
