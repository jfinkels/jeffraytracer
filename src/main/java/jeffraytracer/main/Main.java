/**
 * Main.java - driver for the ray tracer
 */
package jeffraytracer.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import jeffraytracer.io.FileFormatException;

import org.apache.log4j.Logger;

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
        // render the image and write it to the output file
        final BufferedImage image = ImageCreator.fromFile(filename);
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