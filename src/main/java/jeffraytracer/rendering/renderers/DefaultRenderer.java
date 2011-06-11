/**
 * BaseRenderer.java - renders a scene by calling a tracer
 */
package jeffraytracer.rendering.renderers;

import java.awt.image.BufferedImage;

import jeffraytracer.DoubleColor;
import jeffraytracer.Ray;
import jeffraytracer.Vector3D;
import jeffraytracer.camera.RayGenerator;
import jeffraytracer.rendering.RenderingEnvironment;
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

  /**
   * Flattens a two-dimensional array into a one-dimensional array in row-major
   * order.
   * 
   * Pre-condition: the input array has size greater than zero.
   * 
   * Pre-condition: the input array is rectangular (that is, each row has the
   * same number of elements).
   * 
   * @param array
   *          The two-dimensional array to flatten.
   * @return A new one-dimensional array with the same elements of the
   *         two-dimensional array in row-major order.
   */
  private static Ray[] flatten(final Ray[][] array) {
    // here we assume the input array is rectangular and has size > 0
    final Ray[] result = new Ray[array.length * array[0].length];
    int j = 0;
    for (int i = 0; i < array.length; ++i) {
      final int length = array[i].length;
      System.arraycopy(array[i], 0, result, j, length);
      j += length;
    }
    return result;
  }

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
   * Generates a two-dimensional array representing the primary rays extending
   * from the camera through the viewport specified by the RenderingEnvironment
   * provided in the constructor of this class.
   * 
   * @return A two-dimensional array of primary arrays through the viewport.
   */
  protected Ray[][] generatePrimaryRays() {
    final int height = this.environment.viewport().height();
    final int width = this.environment.viewport().width();
    final Ray[][] result = new Ray[height][width];
    final RayGenerator rayGenerator = new RayGenerator(
        this.environment.camera(), this.environment.resolution(),
        this.environment.viewport());

    for (int y = 0; y < height; ++y) {
      for (int x = 0; x < width; ++x) {
        result[y][x] = rayGenerator.generateRay(y, x);
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
    final int[] result = new int[colors.length];
    for (int i = 0; i < colors.length; ++i) {
      result[i] = DoubleColor.toRGB(colors[i]);
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
    final Ray[][] rays = this.generatePrimaryRays();
    LOG.debug("Compiling quadric form matrices...");
    for (final SurfaceObject surfaceObject : this.environment.surfaceObjects()) {
      surfaceObject.compile();
    }
    LOG.debug("Tracing rays...");
    final Ray[] rays1D = flatten(rays);
    final Vector3D[] pixels = this.tracer.traceAll(rays1D);
    LOG.debug("Post processing pixels...");
    final int[] colors = this.postProcessing(pixels);
    LOG.debug("Generating image...");
    return this.generateImage(colors);
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
