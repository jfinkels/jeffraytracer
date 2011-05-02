/**
 * TracerEnvironment.java - test for the TracerEnvironment class
 */
package edu.bu.cs.cs480.main;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.junit.Test;

import edu.bu.cs.cs480.TestUtils;
import edu.bu.cs.cs480.camera.PerspectiveCamera;
import edu.bu.cs.cs480.camera.Resolution;
import edu.bu.cs.cs480.camera.Viewport;
import edu.bu.cs.cs480.vectors.Ray;
import edu.bu.cs.cs480.vectors.Vector3D;

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
  // "singlesphere"
  // , "singlecylinder"
  // , "singleellipsoid"
  // , "singlebox"
  //"sphereTest"
  "model1"
  , "model2"
  , "model3"
  , "model4"
  ,  "model5"
      );

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.main.TracerEnvironment#generateRay(int, int)}.
   */
  @Test
  public void testGenerateRay() {
    final Viewport viewport = new Viewport();
    viewport.setWidth(4);
    viewport.setHeight(4);

    final Resolution resolution = new Resolution();
    resolution.setxResolution(1);
    resolution.setyResolution(1);

    final PerspectiveCamera camera = new PerspectiveCamera();
    camera.setPosition(new Vector3D(0, 0, -1));
    camera.setDirection(new Vector3D(0, 0, 1));
    camera.setUp(new Vector3D(0, 1, 0));
    camera.setFocalLength(1);

    final TracerEnvironment e = new TracerEnvironment();
    e.setCamera(camera);
    e.setResolution(resolution);
    e.setViewport(viewport);

    final double[][][] expected = {
        { { -1, 1, 0 }, { 0, 1, 0 }, { 1, 1, 0 }, { 2, 1, 0 } },
        { { -1, 0, 0 }, { 0, 0, 0 }, { 1, 0, 0 }, { 2, 0, 0 } },
        { { -1, -1, 0 }, { 0, -1, 0 }, { 1, -1, 0 }, { 2, -1, 0 } },
        { { -1, -2, 0 }, { 0, -2, 0 }, { 1, -2, 0 }, { 2, -2, 0 } } };
    for (int row = 0; row < 4; ++row) {
      for (int col = 0; col < 4; ++col) {
        final Ray ray = e.generateRay(row, col);
        assertTrue(ray.position().equals(
            new Vector3D(expected[row][col][0], expected[row][col][1],
                expected[row][col][2])));
      }
    }
  }

  /**
   * Test method for {@link edu.bu.cs.cs480.main.TracerEnvironment#render()}.
   */
  @Test
  public void testRender() {
    for (final String dataFile : TEST_FILES) {
      try {
        LOG.debug("Reading file: " + DATA_DIR + dataFile + "."
            + INPUT_FILE_TYPE);
        final TracerEnvironment e = new ModelReader(DATA_DIR + dataFile + "."
            + INPUT_FILE_TYPE).environment();
        final File outputFile = new File(OUTPUT_DIR + dataFile + "."
            + OUTPUT_FILE_TYPE);
        ImageIO.write(e.render(), OUTPUT_FILE_TYPE, outputFile);

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
