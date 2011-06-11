/**
 * ImageCreator.java -
 */
package jeffraytracer.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;

import jeffraytracer.camera.RayGenerator;
import jeffraytracer.camera.Resolution;
import jeffraytracer.camera.Viewport;
import jeffraytracer.io.FileFormatException;
import jeffraytracer.io.ModelReader;
import jeffraytracer.rendering.RenderingEnvironment;
import jeffraytracer.rendering.renderers.SupersamplingRenderer;
import jeffraytracer.rendering.supersampling.FlatGridAverager;
import jeffraytracer.rendering.supersampling.GridSupersampler;
import jeffraytracer.rendering.tracers.DefaultThreadedTracer;
import jeffraytracer.rendering.tracers.Tracer;

/**
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class ImageCreator {
  /**
   * The default size of the supersampling grid for each pixel (that is, the
   * number of virtual subpixels on one side of the grid).
   */
  public static final int DEFAULT_SUPERSAMPLING_GRID_SIZE = 3;

  /**
   * High-level method which reads a model from the specified file and renders
   * it with two threads, and grid supersampling by default.
   * 
   * @param file
   *          The file containing the model of the scene to render.
   * @return The traced image representing the scene read from the specified
   *         file.
   * @throws FileNotFoundException
   *           If no file exists at the specified location.
   * @throws FileFormatException
   *           If the specified model file is not in the correct file format.
   */
  public static BufferedImage fromFile(final File file)
      throws FileNotFoundException, FileFormatException {

    // read the environment
    final ModelReader modelReader = new ModelReader(file);
    final RenderingEnvironment environment = modelReader.environment();
    final int width = environment.viewport().width();
    final int height = environment.viewport().height();

    // create the resolution for the virtual superpixel camera
    final Resolution superpixelResolution = new Resolution();
    superpixelResolution.setXResolution(environment.resolution().xResolution()
        / DEFAULT_SUPERSAMPLING_GRID_SIZE);
    superpixelResolution.setYResolution(environment.resolution().yResolution()
        / DEFAULT_SUPERSAMPLING_GRID_SIZE);

    // create the viewport for the virtual superpixel camera
    final int superWidth = width * DEFAULT_SUPERSAMPLING_GRID_SIZE;
    final int superHeight = height * DEFAULT_SUPERSAMPLING_GRID_SIZE;
    final Viewport superpixelViewport = new Viewport();
    superpixelViewport.setWidth(superWidth);
    superpixelViewport.setHeight(superHeight);

    // create the object which generates primary rays extending from the
    // camera through the superpixel viewport
    final RayGenerator rayGenerator = new RayGenerator(environment.camera(),
        superpixelResolution, superpixelViewport);

    // create the supersampler
    final GridSupersampler supersampler = new GridSupersampler(environment
        .viewport().width(), environment.viewport().height(),
        DEFAULT_SUPERSAMPLING_GRID_SIZE);
    supersampler.setRayGenerator(rayGenerator);

    // create the corresponding averager
    final FlatGridAverager averager = new FlatGridAverager();
    averager.setGridSize(DEFAULT_SUPERSAMPLING_GRID_SIZE);

    // create the tracer
    final Tracer tracer = new DefaultThreadedTracer(environment);

    // create the renderer
    final SupersamplingRenderer renderer = new SupersamplingRenderer(
        environment);
    renderer.setSupersampler(supersampler);
    renderer.setAverager(averager);
    renderer.setTracer(tracer);

    // render the image and write it to the output file
    return renderer.render();
  }

  /**
   * High-level method which reads a model from the specified file and renders
   * it with two threads, and grid supersampling by default.
   * 
   * @param filename
   *          The path to the file containing the model of the scene to render.
   * @return The traced image representing the scene read from the specified
   *         file.
   * @throws FileNotFoundException
   *           If no file exists at the specified location.
   * @throws FileFormatException
   *           If the specified model file is not in the correct file format.
   */
  public static BufferedImage fromFile(final String filename)
      throws FileNotFoundException, FileFormatException {
    return fromFile(new File(filename));
  }
}
