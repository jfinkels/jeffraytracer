/**
 * TracerEnvironment.java - test for the TracerEnvironment class
 */
package edu.bu.cs.cs480.rendering;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.junit.Test;

import edu.bu.cs.cs480.TestUtils;
import edu.bu.cs.cs480.io.FileFormatException;
import edu.bu.cs.cs480.io.ModelReader;

/**
 * Test for the TracerEnvironment class.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class TracerEnvironmentTest {

  /** The directory containing the data files for testing. */
  public static final String DATA_DIR = "src/test/resources/edu/bu/cs/cs480/";
  /** The type of input model files. */
  public static final String INPUT_FILE_TYPE = "dat";
  /** The logger for this class. */
  private static final Logger LOG = Logger
      .getLogger(TracerEnvironmentTest.class);
  /** The directory to which to write generated image files. */
  public static final String OUTPUT_DIR = "target/";
  /** The type of output image files. */
  public static final String OUTPUT_FILE_TYPE = "png";

  /** The list of files to test. */
  public static final List<String> TEST_FILES = Arrays.asList(
  //"singlesphere"
  // , "singlecylinder"
  // , "singleellipsoid"
  // , "singlebox"
  //, "sphereTest"
  "model1"
  //, "model2"
  //, "model3"
  //, "model4"
  //, "model5"
      );

  /**
   * Test method for {@link edu.bu.cs.cs480.rendering.BaseTracerEnvironment#render()}.
   */
  @Test
  public void testRender() {
    for (final String dataFile : TEST_FILES) {
      try {
        LOG.debug("Reading file: " + DATA_DIR + dataFile + "."
            + INPUT_FILE_TYPE);
        final RenderingEnvironment e = new ModelReader(DATA_DIR + dataFile + "."
            + INPUT_FILE_TYPE).environment();
        final File outputFile = new File(OUTPUT_DIR + dataFile + "."
            + OUTPUT_FILE_TYPE);
        final BaseRenderer renderer = new BaseRenderer(e);
        ImageIO.write(renderer.render(), OUTPUT_FILE_TYPE, outputFile);

      } catch (final FileNotFoundException exception) {
        TestUtils.fail(exception);
      } catch (final FileFormatException exception) {
        TestUtils.fail(exception);
      } catch (final IOException exception) {
        TestUtils.fail(exception);
      }
    }
  }

}
