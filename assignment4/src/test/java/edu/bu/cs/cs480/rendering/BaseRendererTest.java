/**
 * BaseRendererTest.java - test for the BaseRenderer class
 */
package edu.bu.cs.cs480.rendering;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.image.RenderedImage;

import org.junit.Before;
import org.junit.Test;

import edu.bu.cs.cs480.DoubleColor;
import edu.bu.cs.cs480.Ray;
import edu.bu.cs.cs480.Vector3D;
import edu.bu.cs.cs480.camera.Camera;
import edu.bu.cs.cs480.camera.OrthographicCamera;
import edu.bu.cs.cs480.camera.Resolution;
import edu.bu.cs.cs480.camera.Viewport;

/**
 * Test for the BaseRenderer class.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class BaseRendererTest {

  /** The renderer to test. */
  private BaseRenderer r = null;

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
    this.r = new BaseRenderer(e);
    this.r.setTracer(new LinearTracer(e));
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.rendering.BaseRenderer#generateImage(int[])}.
   */
  @Test
  public void testGenerateImage() {
    final RenderedImage i = this.r.generateImage(new int[] { 0xFF0000,
        0x00FF00, 0x0000FF, 0xFFFFFF });
    final int[] output = new int[4];
    assertArrayEquals(new int[] { 0xFF, 0, 0, 0 },
        i.getData().getPixel(0, 0, output));
    assertArrayEquals(new int[] { 0, 0xFF, 0, 0 },
        i.getData().getPixel(1, 0, output));
    assertArrayEquals(new int[] { 0, 0, 0xFF, 0 },
        i.getData().getPixel(0, 1, output));
    assertArrayEquals(new int[] { 0xFF, 0xFF, 0xFF, 0 },
        i.getData().getPixel(1, 1, output));
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.rendering.BaseRenderer#generatePrimaryRays()}.
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
   * {@link edu.bu.cs.cs480.rendering.BaseRenderer#postProcessing(edu.bu.cs.cs480.Vector3D[])}
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
   * Test method for {@link edu.bu.cs.cs480.rendering.BaseRenderer#render()}.
   */
  @Test
  public void testRender() {
    final RenderedImage i = this.r.render();
    final int[] output = new int[4];
    final int backgroundColor = DoubleColor
        .toRGB(BaseTracer.BACKGROUND_COLOR);
    final int[] components = { (backgroundColor >> 16) & 0xFF,
        (backgroundColor >> 8) & 0xFF, backgroundColor & 0xFF, 0 };
    assertArrayEquals(components, i.getData().getPixel(0, 0, output));
    assertArrayEquals(components, i.getData().getPixel(1, 0, output));
    assertArrayEquals(components, i.getData().getPixel(0, 1, output));
    assertArrayEquals(components, i.getData().getPixel(1, 1, output));
  }
}
