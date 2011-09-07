/**
 * GridSupersamplerTest.java - test for the GridSupersampler class
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
package jeffraytracer.rendering.supersampling;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import jeffraytracer.Ray;
import jeffraytracer.Vector3D;
import jeffraytracer.camera.Camera;
import jeffraytracer.camera.OrthographicCamera;
import jeffraytracer.camera.RayGenerator;
import jeffraytracer.camera.Resolution;
import jeffraytracer.camera.Viewport;

import org.junit.Test;

/**
 * Test for the GridSupersampler class.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class GridSupersamplerTest {

  /**
   * Test method for
   * {@link jeffraytracer.rendering.supersampling.GridSupersampler#generateRays()}
   * .
   */
  @Test
  public void testGenerateRays() {
    final int gridSize = 2;
    final int originalWidth = 5;
    final int originalHeight = 5;

    // set up the camera, resolution, and viewport for the ray generator
    final Camera c = new OrthographicCamera();
    c.setPosition(new Vector3D(0, 0, -1));
    c.setDirection(new Vector3D(0, 0, 1));
    c.setUp(new Vector3D(0, 1, 0));
    final Resolution r = new Resolution();
    r.setXResolution(1.0);
    r.setYResolution(1.0);
    final Viewport v = new Viewport();
    v.setWidth(originalWidth * gridSize);
    v.setHeight(originalHeight * gridSize);

    // create the ray generator for the supersampler
    final RayGenerator g = new RayGenerator();
    g.setCamera(c);
    g.setResolution(r);
    g.setViewport(v);

    // create the supersampler
    final GridSupersampler s = new GridSupersampler(originalWidth,
        originalHeight, gridSize);
    s.setRayGenerator(g);

    // generate rays
    final Ray[][] rays = s.generateRays();

    assertEquals(originalWidth * originalHeight, rays.length);
    for (final Ray[] block : rays) {
      assertEquals(gridSize * gridSize, block.length);
    }

    for (int row = 0; row < originalHeight; ++row) {
      for (int col = 0; col < originalWidth; ++col) {
        for (int blockRow = 0; blockRow < gridSize; ++blockRow) {
          for (int blockCol = 0; blockCol < gridSize; ++blockCol) {
            final int blockNum = row * originalWidth + col;
            final int subpixel = blockRow * gridSize + blockCol;
            final Ray ray = rays[blockNum][subpixel];
            assertTrue(ray.direction().equalTo(c.direction()));
            assertEquals(((1 - gridSize) * originalWidth) + (gridSize * col)
                + blockCol + 1, ray.position().x(), 0);
            assertEquals((originalHeight - 1) + (-gridSize * row) - blockRow,
                ray.position().y(), 0);
          }
        }
      }
    }
  }

}
