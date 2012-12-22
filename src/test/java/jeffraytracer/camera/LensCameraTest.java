/**
 * LensCameraTest.java - test for the LensCamera class
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

import org.junit.Before;
import org.junit.Test;

/**
 * Test for the LensCamera class.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class LensCameraTest {

  /** The LensCamera object for testing. */
  private LensCamera c = null;
  /** The position of the camera. */
  private Vector3D position = null;
  /** The direction in which the camera is pointing. */
  private Vector3D direction = null;
  /** The up vector for the camera. */
  private Vector3D up = null;
  /** The width of the lens of the camera. */
  private int lensWidth = 1;
  /** The height of the lens of the camera. */
  private int lensHeight = 1;

  /** Create a LensCamera object for testing. */
  @Before
  public void setUp() {
    this.position = new Vector3D(0, 0, -1);
    this.direction = new Vector3D(0, 0, 1);
    this.up = new Vector3D(0, 1, 0);
    this.lensHeight = 3;
    this.lensWidth = 3;
    this.c = new LensCamera();
    this.c.setLensHeight(this.lensHeight);
    this.c.setLensWidth(this.lensWidth);
    this.c.setPosition(this.position);
    this.c.setDirection(this.direction);
    this.c.setUp(this.up);
  }

  /**
   * Test method for
   * {@link jeffraytracer.camera.PerspectiveCamera#rayDirections(jeffraytracer.Vector3D)}
   * .
   */
  @Test
  public void testRayDirections() {
    final Vector3D[] result = this.c.rayDirections(new Vector3D(1, 2, 3));
    assertEquals(this.lensHeight * this.lensWidth, result.length);
  }
}
