/**
 * RendererTestSupport.java - support for test classes of renderers
 */
package edu.bu.cs.cs480.rendering.renderers;

import java.util.Arrays;
import java.util.List;

/**
 * Support class for test classes of renderers.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public abstract class RendererTestSupport {
  /** The directory containing the data files for testing. */
  public static final String DATA_DIR = "src/test/resources/edu/bu/cs/cs480/";
  /** The type of input model files. */
  public static final String INPUT_FILE_TYPE = "dat";
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
}
