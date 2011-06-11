/**
 * GridSupersamplerTest.java - test for the GridSupersampler class
 */
package edu.bu.cs.cs480.rendering.supersampling;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.bu.cs.cs480.Ray;
import edu.bu.cs.cs480.Vector3D;
import edu.bu.cs.cs480.camera.Camera;
import edu.bu.cs.cs480.camera.OrthographicCamera;
import edu.bu.cs.cs480.camera.RayGenerator;
import edu.bu.cs.cs480.camera.Resolution;
import edu.bu.cs.cs480.camera.Viewport;

/**
 * Test for the GridSupersampler class.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class GridSupersamplerTest {

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.rendering.supersampling.GridSupersampler#generateRays()}.
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
