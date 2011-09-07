/**
 * RayGeneratorTest.java - test for the RayGenerator class
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
import jeffraytracer.Ray;
import jeffraytracer.Vector3D;

import org.junit.Test;

/**
 * Test for the RayGenerator class.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class RayGeneratorTest {

  /**
   * Test method for
   * {@link jeffraytracer.camera.RayGenerator#generateRay(int, int)}.
   */
  @Test
  public void testGenerateRay() {
    final Viewport viewport = new Viewport();
    viewport.setWidth(4);
    viewport.setHeight(4);

    final Resolution resolution = new Resolution();
    resolution.setXResolution(1);
    resolution.setYResolution(1);

    final PerspectiveCamera camera = new PerspectiveCamera();
    camera.setPosition(new Vector3D(0, 0, -1));
    camera.setDirection(new Vector3D(0, 0, 1));
    camera.setUp(new Vector3D(0, 1, 0));
    camera.setFocalLength(1);

    final RayGenerator g = new RayGenerator();
    g.setCamera(camera);
    g.setResolution(resolution);
    g.setViewport(viewport);

    final double[][][] expected = {
        { { -1, 1, 0 }, { 0, 1, 0 }, { 1, 1, 0 }, { 2, 1, 0 } },
        { { -1, 0, 0 }, { 0, 0, 0 }, { 1, 0, 0 }, { 2, 0, 0 } },
        { { -1, -1, 0 }, { 0, -1, 0 }, { 1, -1, 0 }, { 2, -1, 0 } },
        { { -1, -2, 0 }, { 0, -2, 0 }, { 1, -2, 0 }, { 2, -2, 0 } } };
    for (int row = 0; row < 4; ++row) {
      for (int col = 0; col < 4; ++col) {
        final Ray ray = g.generateRay(row, col);
        assertTrue(ray.position().equalTo(
            new Vector3D(expected[row][col][0], expected[row][col][1],
                expected[row][col][2])));
      }
    }
  }

}
