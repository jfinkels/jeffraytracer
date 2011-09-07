/**
 * PerspectiveCameraTest.java - test for the PerspectiveCamera class
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

import static org.junit.Assert.assertTrue;
import jeffraytracer.Vector3D;

import org.junit.Before;
import org.junit.Test;

/**
 * Test for the PerspectiveCamera class.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class PerspectiveCameraTest {

  /** The PerspectiveCamera object for testing. */
  private PerspectiveCamera c = null;
  /** The position of the camera. */
  private Vector3D position = null;
  /** The direction in which the camera is pointing. */
  private Vector3D direction = null;
  /** The up vector for the camera. */
  private Vector3D up = null;

  /** Create a PerspectiveCamera object for testing. */
  @Before
  public void setUp() {
    this.position = new Vector3D(0, 0, -1);
    this.direction = new Vector3D(0, 0, 1);
    this.up = new Vector3D(0, 1, 0);
    this.c = new PerspectiveCamera();
    this.c.setPosition(this.position);
    this.c.setDirection(this.direction);
    this.c.setUp(this.up);
  }

  /**
   * Test method for
   * {@link jeffraytracer.camera.PerspectiveCamera#rayDirection(jeffraytracer.Vector3D)}
   * .
   */
  @Test
  public void testRayDirection() {
    assertTrue(this.c.rayDirection(new Vector3D(1, 2, 3)).equalTo(
        new Vector3D(1, 2, 4).normalized()));
  }
}
