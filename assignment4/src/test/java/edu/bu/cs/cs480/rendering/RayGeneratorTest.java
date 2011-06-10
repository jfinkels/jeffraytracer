/**
 * RayGeneratorTest.java -
 */
package edu.bu.cs.cs480.rendering;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.bu.cs.cs480.Ray;
import edu.bu.cs.cs480.Vector3D;
import edu.bu.cs.cs480.camera.PerspectiveCamera;
import edu.bu.cs.cs480.camera.Resolution;
import edu.bu.cs.cs480.camera.Viewport;
import edu.bu.cs.cs480.rendering.RayGenerator;

/**
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class RayGeneratorTest {

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.rendering.RayGenerator#generateRay(int, int)}.
   */
  @Test
  public void testGenerateRay() {
    final Viewport viewport = new Viewport();
    viewport.setWidth(4);
    viewport.setHeight(4);

    final Resolution resolution = new Resolution();
    resolution.setxResolution(1);
    resolution.setyResolution(1);

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
        assertTrue(ray.position().equals(
            new Vector3D(expected[row][col][0], expected[row][col][1],
                expected[row][col][2])));
      }
    }
  }

}
