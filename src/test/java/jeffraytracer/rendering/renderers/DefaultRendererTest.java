/**
 * DefaultRendererTest.java - test for the BaseRenderer class
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
package jeffraytracer.rendering.renderers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;

import jeffraytracer.DoubleColor;
import jeffraytracer.Ray;
import jeffraytracer.Vector3D;
import jeffraytracer.camera.Camera;
import jeffraytracer.camera.OrthographicCamera;
import jeffraytracer.camera.Resolution;
import jeffraytracer.camera.Viewport;
import jeffraytracer.rendering.DefaultRenderingEnvironment;
import jeffraytracer.rendering.RenderingEnvironment;
import jeffraytracer.rendering.tracers.BaseTracer;
import jeffraytracer.rendering.tracers.SequentialTracer;

import org.junit.Before;
import org.junit.Test;

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
    this.r.setTracer(new SequentialTracer(e));
  }

  /**
   * Test method for
   * {@link jeffraytracer.rendering.renderers.DefaultRenderer#generateImage(int[])}
   * .
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
   * {@link jeffraytracer.rendering.renderers.DefaultRenderer#generatePrimaryRays()}
   * .
   */
  @Test
  public void testGeneratePrimaryRays() {
    final Ray[][][] rays = this.r.generatePrimaryRays();
    for (int i = 0; i < 4; ++i) {
      assertEquals(1, rays[i / 2][i % 2].length);
      assertTrue(rays[i / 2][i % 2][0].direction().equalTo(new Vector3D(0, 0, 1)));
    }
    assertTrue(rays[0][0][0].position().equalTo(new Vector3D(0, 0, 0)));
    assertTrue(rays[0][1][0].position().equalTo(new Vector3D(1, 0, 0)));
    assertTrue(rays[1][0][0].position().equalTo(new Vector3D(0, -1, 0)));
    assertTrue(rays[1][1][0].position().equalTo(new Vector3D(1, -1, 0)));
  }

  /**
   * Test method for
   * {@link jeffraytracer.rendering.renderers.DefaultRenderer#postProcessing(jeffraytracer.Vector3D[])}
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
   * Test method for
   * {@link jeffraytracer.rendering.renderers.DefaultRenderer#render()}.
   */
  @Test
  public void testRender() {
    final BufferedImage image = this.r.render();
    final int backgroundColor = DoubleColor.toRGB(BaseTracer.BACKGROUND_COLOR);
    for (int i = 0; i < 4; ++i) {
      assertEquals(backgroundColor, image.getRGB(i / 2, i % 2));
    }
  }
}
