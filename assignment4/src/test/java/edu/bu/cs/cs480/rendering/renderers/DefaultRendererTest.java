/**
 * DefaultRendererTest.java - test for the BaseRenderer class
 */
package edu.bu.cs.cs480.rendering.renderers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;

import org.junit.Before;
import org.junit.Test;

import edu.bu.cs.cs480.DoubleColor;
import edu.bu.cs.cs480.Ray;
import edu.bu.cs.cs480.Vector3D;
import edu.bu.cs.cs480.camera.Camera;
import edu.bu.cs.cs480.camera.OrthographicCamera;
import edu.bu.cs.cs480.camera.Resolution;
import edu.bu.cs.cs480.camera.Viewport;
import edu.bu.cs.cs480.rendering.DefaultRenderingEnvironment;
import edu.bu.cs.cs480.rendering.RenderingEnvironment;
import edu.bu.cs.cs480.rendering.tracers.BaseTracer;
import edu.bu.cs.cs480.rendering.tracers.LinearTracer;

/**
 * Test for the DefaultRenderer class.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class DefaultRendererTest {

  /** The renderer to test. */
  private DefaultRenderer r = null;

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
    this.r = new DefaultRenderer(e);
    this.r.setTracer(new LinearTracer(e));
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.rendering.renderers.DefaultRenderer#generateImage(int[])}.
   */
  @Test
  public void testGenerateImage() {
    final BufferedImage i = this.r.generateImage(new int[] { 0xFF0000,
        0x00FF00, 0x0000FF, 0xFFFFFF });
    assertEquals(0xFFFF0000, i.getRGB(0, 0));
    assertEquals(0xFF00FF00, i.getRGB(1, 0));
    assertEquals(0xFF0000FF, i.getRGB(0, 1));
    assertEquals(0xFFFFFFFF, i.getRGB(1, 1));
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.rendering.renderers.DefaultRenderer#generatePrimaryRays()}.
   */
  @Test
  public void testGeneratePrimaryRays() {
    final Ray[][] rays = this.r.generatePrimaryRays();
    for (int i = 0; i < 4; ++i) {
      assertTrue(rays[i / 2][i % 2].direction().equalTo(new Vector3D(0, 0, 1)));
    }
    assertTrue(rays[0][0].position().equalTo(new Vector3D(0, 0, 0)));
    assertTrue(rays[0][1].position().equalTo(new Vector3D(1, 0, 0)));
    assertTrue(rays[1][0].position().equalTo(new Vector3D(0, -1, 0)));
    assertTrue(rays[1][1].position().equalTo(new Vector3D(1, -1, 0)));
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.rendering.renderers.DefaultRenderer#postProcessing(edu.bu.cs.cs480.Vector3D[])}
   * .
   */
  @Test
  public void testPostProcessing() {
    final Vector3D[] colors = new Vector3D[4];
    colors[0] = new Vector3D(1, 0, 0);
    colors[1] = new Vector3D(0, 1, 0);
    colors[2] = new Vector3D(0, 0, 1);
    colors[3] = new Vector3D(1, 1, 1);
    final int[] pixels = this.r.postProcessing(colors);
    assertEquals(0xFFFF0000, pixels[0]);
    assertEquals(0xFF00FF00, pixels[1]);
    assertEquals(0xFF0000FF, pixels[2]);
    assertEquals(0xFFFFFFFF, pixels[3]);
  }

  /**
   * Test method for {@link edu.bu.cs.cs480.rendering.renderers.DefaultRenderer#render()}.
   */
  @Test
  public void testRender() {
    final BufferedImage image = this.r.render();
      final int backgroundColor = DoubleColor
        .toRGB(BaseTracer.BACKGROUND_COLOR);
    for (int i = 0; i < 4; ++i) {
      assertEquals(backgroundColor, image.getRGB(i / 2, i % 2));
    }
  }
}
