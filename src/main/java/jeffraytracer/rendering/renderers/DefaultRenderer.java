/**
 * BaseRenderer.java - renders a scene by calling a tracer
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

import java.awt.image.BufferedImage;

import jeffraytracer.DoubleColor;
import jeffraytracer.Ray;
import jeffraytracer.Vector3D;
import jeffraytracer.camera.Camera;
import jeffraytracer.camera.RayGenerator;
import jeffraytracer.camera.Resolution;
import jeffraytracer.camera.Viewport;
import jeffraytracer.rendering.RenderingEnvironment;
import jeffraytracer.rendering.averagers.SimpleAverager;
import jeffraytracer.rendering.tracers.Tracer;
import jeffraytracer.surfaces.SurfaceObject;

import org.apache.log4j.Logger;

/**
 * Renders a scene by generating primary rays and then calling on a tracer do
 * the actual tracing and shading of pixels.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class DefaultRenderer implements Renderer {
  /** The logger for this class. */
  private static final transient Logger LOG = Logger
      .getLogger(DefaultRenderer.class);
  /** The scene to render. */
  private final RenderingEnvironment environment;
  /** The object which traces and shades the scene. */
  private Tracer tracer = null;

  /**
   * Instantiates this renderer with access to the specified scene to render.
   * 
   * @param environment
   *          The scene to render.
   */
  public DefaultRenderer(final RenderingEnvironment environment) {
    this.environment = environment;
  }

  /**
   * Returns an RGB image with the specified pixel color values set.
   * 
   * The width and height of this image are the width and height of the
   * viewport of the RenderingEnvironment specified in the constructor of this
   * class.
   * 
   * Pre-condition: the colors array must be of size w * h, where w and h are
   * the width and height of the viewport of the RenderingEnvironment specified
   * in the constructor of this class.
   * 
   * @param colors
   *          The color values to set in a pixel.
   * @return A new RGB image with the specified color values set on each of its
   *         pixels.
   */
  protected BufferedImage generateImage(final int[] colors) {
    final int width = this.environment.viewport().width();
    final int height = this.environment.viewport().height();
    final BufferedImage result = new BufferedImage(width, height,
        BufferedImage.TYPE_INT_RGB);
    result.setRGB(0, 0, width, height, colors, 0, width);
    return result;
  }

  /**
   * Generates a three-dimensional array representing the primary rays
   * extending from the camera through the viewport specified by the
   * RenderingEnvironment provided in the constructor of this class.
   * 
   * The first two dimensions of the returned array represent the row and
   * column position on the viewport. The third dimension represents the index
   * into the array of all rays emanating from that point.
   * 
   * Each element in the second dimension is an array of primary rays, each of
   * size at least one. A pinhole camera will produce an array of size for each
   * of the innermost arrays. A lens camera will produce an array of size at
   * least one; the lens can be thought of a collection of pinhole cameras.
   * 
   * Post-condition: each array in {@code result[i][j]} will have size at least
   * one, for all {@code i} and {@code j}.
   * 
   * @return A three-dimensional array in which each element of the array is a
   *         primary ray through the viewport, grouped by column and row.
   */
  protected Ray[][][] generatePrimaryRays() {
    final Camera camera = this.environment.camera();
    final Resolution resolution = this.environment.resolution();
    final Viewport viewport = this.environment.viewport();
    final RayGenerator rayGenerator = new RayGenerator(camera, resolution,
        viewport);

    final int height = viewport.height();
    final int width = viewport.width();
    final Ray[][][] result = new Ray[height][width][];
    for (int y = 0; y < height; ++y) {
      for (int x = 0; x < width; ++x) {
        result[y][x] = rayGenerator.generateRays(y, x);
      }
    }

    return result;
  }

  /**
   * Converts the colors as vectors into their corresponding RGB integer
   * representations.
   * 
   * Post-condition: the length of the returned array equals the length of the
   * input array.
   * 
   * Post-condition: no element of the colors array is mutated.
   * 
   * @param colors
   *          The color values of each traced and shaded pixel, according to
   *          the scene.
   * @return A new array containing the corresponding integer representations
   *         of the colors specified by the input array.
   */
  protected int[] postProcessing(final Vector3D[] colors) {
    // Compute the average of the color values for each group of colors
    // corresponding to a group of rays emanating from the same point on the
    // viewport. This assumes that the input array is arranged in blocks whose
    // size depends on the size of the camera.
    final Camera camera = this.environment.camera();
    final SimpleAverager averager = new SimpleAverager();
    averager.setBlockSize(camera.raysPerPixel());
    final Vector3D[] averagedColors = averager.average(colors);
    final int[] result = new int[colors.length];
    for (int i = 0; i < colors.length; ++i) {
      result[i] = DoubleColor.toRGB(averagedColors[i]);
    }
    return result;
  }

  /**
   * Renders the scene and returns the resulting image.
   * 
   * @return The image which is the result of rendering the scene.
   */
  @Override
  public BufferedImage render() {
    LOG.debug("Generating primary rays...");
    final Ray[][][] rays = this.generatePrimaryRays();
    LOG.debug("Compiling quadric form matrices...");
    for (final SurfaceObject surfaceObject : this.environment.surfaceObjects()) {
      surfaceObject.compile();
    }
    LOG.debug("Tracing rays...");
    final Ray[] rays1D = flattened(rays);
    final Vector3D[] pixels = this.tracer.traceAll(rays1D);
    LOG.debug("Post processing pixels...");
    final int[] colors = this.postProcessing(pixels);
    LOG.debug("Generating image...");
    final BufferedImage result = this.generateImage(colors);
    LOG.debug("Done.");
    return result;
  }

  /**
   * Returns a new one-dimensional array resulting from flattening the
   * specified three-dimensional array in row-major order.
   * 
   * @param rays
   *          The three-dimensional array.
   * @return A new one-dimensional array with the same contents as the input
   *         array in row-major order.
   */
  private static Ray[] flattened(final Ray[][][] rays) {
    int height = rays.length;
    int width = rays[0].length;
    int depth = rays[0][0].length;
    final Ray[] result = new Ray[width * height * depth];
    int i = 0;
    for (final Ray[][] columns : rays) {
      for (final Ray[] raysPerPixel : columns) {
        System.arraycopy(raysPerPixel, 0, result, i, raysPerPixel.length);
        i += raysPerPixel.length;
      }
    }
    return result;
  }

  /**
   * Sets the object which traces and shades the scene.
   * 
   * @param tracer
   *          The object which traces and shades the scene.
   */
  public void setTracer(final Tracer tracer) {
    this.tracer = tracer;
  }

}
