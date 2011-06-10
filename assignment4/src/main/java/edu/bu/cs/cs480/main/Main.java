/**
 * Main.java - driver for the ray tracer
 */
package edu.bu.cs.cs480.main;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import edu.bu.cs.cs480.camera.RayGenerator;
import edu.bu.cs.cs480.camera.Resolution;
import edu.bu.cs.cs480.camera.Viewport;
import edu.bu.cs.cs480.io.FileFormatException;
import edu.bu.cs.cs480.io.ModelReader;
import edu.bu.cs.cs480.rendering.RenderingEnvironment;
import edu.bu.cs.cs480.rendering.renderers.SupersamplingRenderer;
import edu.bu.cs.cs480.rendering.supersampling.FlatGridAverager;
import edu.bu.cs.cs480.rendering.supersampling.GridSupersampler;
import edu.bu.cs.cs480.rendering.tracers.DefaultThreadedTracer;
import edu.bu.cs.cs480.rendering.tracers.Tracer;

/**
 * The driver for the ray tracer.
 * 
 * The user must provide at least one command-line argument specifying a model
 * file to render. The output will be the name of the model file with ".png"
 * appended.
 * 
 * Multiple input files may be specified as command line arguments.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public final class Main {
  /** The logger for this class. */
  private static final transient Logger LOG = Logger.getLogger(Main.class);
  /** The type of the image file to output. */
  public static final String OUTPUT_FILE_TYPE = "png";
  /**
   * The default size of the supersampling grid for each pixel (that is, the
   * number of virtual subpixels on one side of the grid).
   */
  public static final int DEFAULT_SUPERSAMPLING_GRID_SIZE = 3;

  /**
   * Renders the models specified by the files given as command line arguments.
   * 
   * @param args
   *          The command-line arguments.
   */
  public static void main(final String[] args) {
    // TODO use an options parsing library
    if (args.length < 1) {
      LOG.error("Must provide at least one model file to render.");
      return;
    }

    for (final String filename : args) {
      try {
        // read the environment
        final ModelReader modelReader = new ModelReader(filename);
        final RenderingEnvironment environment = modelReader.environment();
        final int width = environment.viewport().width();
        final int height = environment.viewport().height();

        // create the resolution for the virtual superpixel camera
        final Resolution superpixelResolution = new Resolution();
        superpixelResolution.setXResolution(environment.resolution()
            .xResolution() / DEFAULT_SUPERSAMPLING_GRID_SIZE);
        superpixelResolution.setYResolution(environment.resolution()
            .yResolution() / DEFAULT_SUPERSAMPLING_GRID_SIZE);

        // create the viewport for the virtual superpixel camera
        final int superWidth = width * DEFAULT_SUPERSAMPLING_GRID_SIZE;
        final int superHeight = height * DEFAULT_SUPERSAMPLING_GRID_SIZE;
        final Viewport superpixelViewport = new Viewport();
        superpixelViewport.setWidth(superWidth);
        superpixelViewport.setHeight(superHeight);

        // create the object which generates primary rays extending from the
        // camera through the superpixel viewport
        final RayGenerator rayGenerator = new RayGenerator(
            environment.camera(), superpixelResolution, superpixelViewport);

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
        final RenderedImage image = renderer.render();
        final File outputFile = new File(outputFilename(filename));
        ImageIO.write(image, OUTPUT_FILE_TYPE, outputFile);
      } catch (final FileNotFoundException exception) {
        LOG.error(exception);
      } catch (final FileFormatException exception) {
        LOG.error(exception);
      } catch (final IOException exception) {
        LOG.error(exception);
      }
    }
  }

  /**
   * Returns the same filename but with the file extension (that is, the text
   * after the first period ["."]) replaced by the type of the output image
   * file.
   * 
   * @param inputFilename
   *          The input filename.
   * @return The input filename with the file extension replaced by the type of
   *         the output image file.
   */
  private static String outputFilename(final String inputFilename) {
    final int i = inputFilename.lastIndexOf('.');
    return inputFilename.substring(0, i) + "." + OUTPUT_FILE_TYPE;
  }

  /** Prevents instantiation. */
  private Main() {
    // intentionally unimplemented
  }
}
