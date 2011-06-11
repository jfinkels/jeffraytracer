/**
 * SupersamplingRendererTest.java - test for the SupersamplingRenderer class
 */
package jeffraytracer.rendering.renderers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import jeffraytracer.Ray;
import jeffraytracer.Vector3D;
import jeffraytracer.camera.Camera;
import jeffraytracer.camera.OrthographicCamera;
import jeffraytracer.camera.RayGenerator;
import jeffraytracer.camera.Resolution;
import jeffraytracer.camera.Viewport;
import jeffraytracer.rendering.DefaultRenderingEnvironment;
import jeffraytracer.rendering.RenderingEnvironment;
import jeffraytracer.rendering.supersampling.FlatGridAverager;
import jeffraytracer.rendering.supersampling.GridAverager;
import jeffraytracer.rendering.supersampling.GridSupersampler;
import jeffraytracer.rendering.tracers.LinearTracer;

import org.junit.Before;
import org.junit.Test;

/**
 * Test for the SupersamplingRenderer class.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class SupersamplingRendererTest {

  /** The renderer to test. */
  private SupersamplingRenderer r = null;

  /** Set up the renderer for testing. */
  @Before
  public void setUp() {
    final Viewport v = new Viewport();
    v.setWidth(2);
    v.setHeight(2);

    final Resolution r = new Resolution();
    r.setXResolution(1);
    r.setYResolution(1);

    final Camera c = new OrthographicCamera();
    c.setPosition(new Vector3D(0, 0, -1));
    c.setDirection(new Vector3D(0, 0, 1));
    c.setUp(new Vector3D(0, 1, 0));

    final RenderingEnvironment e = new DefaultRenderingEnvironment();
    e.setViewport(v);
    e.setResolution(r);
    e.setCamera(c);

    final Resolution subpixelResolution = new Resolution();
    subpixelResolution.setXResolution(1 / 2.0);
    subpixelResolution.setYResolution(1 / 2.0);

    final Viewport subpixelViewport = new Viewport();
    subpixelViewport.setWidth(4);
    subpixelViewport.setHeight(4);

    final RayGenerator g = new RayGenerator();
    g.setCamera(c);
    g.setResolution(subpixelResolution);
    g.setViewport(subpixelViewport);

    final GridSupersampler s = new GridSupersampler(2, 2, 2);
    s.setRayGenerator(g);

    final GridAverager a = new FlatGridAverager();
    a.setGridSize(2);

    this.r = new SupersamplingRenderer(e);
    this.r.setTracer(new LinearTracer(e));
    this.r.setSupersampler(s);
    this.r.setAverager(a);
  }

  /**
   * Test method for
   * {@link jeffraytracer.rendering.renderers.SupersamplingRenderer#generatePrimaryRays()}
   * .
   */
  @Test
  public void testGeneratePrimaryRays() {
    final Ray[][] rays = this.r.generatePrimaryRays();
    assertEquals(4, rays.length);
    assertEquals(4, rays[0].length);
    for (final Ray[] block : rays) {
      for (final Ray ray : block) {
        assertTrue(ray.direction().equalTo(new Vector3D(0, 0, 1)));
      }
    }

    // first row
    assertTrue(rays[0][0].position().equalTo(new Vector3D(-.5, .5, 0)));
    assertTrue(rays[0][1].position().equalTo(new Vector3D(0, .5, 0)));
    assertTrue(rays[1][0].position().equalTo(new Vector3D(0.5, .5, 0)));
    assertTrue(rays[1][1].position().equalTo(new Vector3D(1, .5, 0)));

    // second row
    assertTrue(rays[0][2].position().equalTo(new Vector3D(-.5, 0, 0)));
    assertTrue(rays[0][3].position().equalTo(new Vector3D(0, 0, 0)));
    assertTrue(rays[1][2].position().equalTo(new Vector3D(0.5, 0, 0)));
    assertTrue(rays[1][3].position().equalTo(new Vector3D(1, 0, 0)));

    // third row
    assertTrue(rays[2][0].position().equalTo(new Vector3D(-.5, -.5, 0)));
    assertTrue(rays[2][1].position().equalTo(new Vector3D(0, -.5, 0)));
    assertTrue(rays[3][0].position().equalTo(new Vector3D(0.5, -.5, 0)));
    assertTrue(rays[3][1].position().equalTo(new Vector3D(1, -.5, 0)));

    // fourth row
    assertTrue(rays[2][2].position().equalTo(new Vector3D(-.5, -1, 0)));
    assertTrue(rays[2][3].position().equalTo(new Vector3D(0, -1, 0)));
    assertTrue(rays[3][2].position().equalTo(new Vector3D(0.5, -1, 0)));
    assertTrue(rays[3][3].position().equalTo(new Vector3D(1, -1, 0)));
  }

  /**
   * Test method for
   * {@link jeffraytracer.rendering.renderers.SupersamplingRenderer#postProcessing(jeffraytracer.Vector3D[])}
   * .
   */
  @Test
  public void testPostProcessing() {
    final Vector3D[] colors = new Vector3D[16];
    // block 1
    colors[0] = new Vector3D(1, 0, 0);
    colors[1] = new Vector3D(1, 0, 0);
    colors[2] = new Vector3D(0, 1, 0);
    colors[3] = new Vector3D(0, 1, 0);
    // block 2
    colors[4] = new Vector3D(0, 1, 0);
    colors[5] = new Vector3D(0, 1, 0);
    colors[6] = new Vector3D(0, 0, 1);
    colors[7] = new Vector3D(0, 0, 1);
    // block 3
    colors[8] = new Vector3D(0, 0, 1);
    colors[9] = new Vector3D(0, 0, 1);
    colors[10] = new Vector3D(1, 0, 0);
    colors[11] = new Vector3D(1, 0, 0);
    // block 4
    colors[12] = new Vector3D(1, 1, 1);
    colors[13] = new Vector3D(1, 1, 1);
    colors[14] = new Vector3D(0, 0, 0);
    colors[15] = new Vector3D(0, 0, 0);

    final int[] averages = this.r.postProcessing(colors);
    assertEquals(4, averages.length);

    // block 1
    assertEquals(0x7F, (averages[0] >> 16) & 0xFF);
    assertEquals(0x7F, (averages[0] >> 8) & 0xFF);
    assertEquals(0, averages[0] & 0xFF);
    // block 2
    assertEquals(0, (averages[1] >> 16) & 0xFF);
    assertEquals(0x7F, (averages[1] >> 8) & 0xFF);
    assertEquals(0x7F, averages[1] & 0xFF);
    // block 3
    assertEquals(0x7F, (averages[2] >> 16) & 0xFF);
    assertEquals(0, (averages[2] >> 8) & 0xFF);
    assertEquals(0x7F, averages[2] & 0xFF);
    // block 4
    assertEquals(0x7F, (averages[3] >> 16) & 0xFF);
    assertEquals(0x7F, (averages[3] >> 8) & 0xFF);
    assertEquals(0x7F, averages[3] & 0xFF);
  }

}
