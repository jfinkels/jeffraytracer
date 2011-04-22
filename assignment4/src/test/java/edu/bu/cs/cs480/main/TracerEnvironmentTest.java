/**
 * 
 */
package edu.bu.cs.cs480.main;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import edu.bu.cs.cs480.TestUtils;

/**
 * @author jeff
 *
 */
public class TracerEnvironmentTest {

  /**
   * Test method for {@link edu.bu.cs.cs480.main.TracerEnvironment#addLight(edu.bu.cs.cs480.Light)}.
   */
  @Test
  public void testAddLight() {
    fail("Not yet implemented");
  }

  /**
   * Test method for {@link edu.bu.cs.cs480.main.TracerEnvironment#addSurfaceObject(edu.bu.cs.cs480.surfaces.SurfaceObject)}.
   */
  @Test
  public void testAddSurfaceObject() {
    fail("Not yet implemented");
  }

  /**
   * Test method for {@link edu.bu.cs.cs480.main.TracerEnvironment#render()}.
   */
  @Test
  public void testRender() {
    try {
      final TracerEnvironment e = ModelReader.fromFile("src/test/resources/edu/bu/cs/cs480/singlecylinder.dat");
      final File outputFile = new File("target/test.png");
      ImageIO.write(e.render(), "png", outputFile);
    } catch (final FileNotFoundException exception) {
      TestUtils.fail(exception);
    } catch (final FileFormatException exception) {
      TestUtils.fail(exception);
    } catch (final IOException exception) {
      TestUtils.fail(exception);
    }
  }

  /**
   * Test method for {@link edu.bu.cs.cs480.main.TracerEnvironment#generateRay(int, int)}.
   */
  @Test
  public void testGenerateRay() {
    fail("Not yet implemented");
  }

  /**
   * Test method for {@link edu.bu.cs.cs480.main.TracerEnvironment#setCamera(edu.bu.cs.cs480.camera.Camera)}.
   */
  @Test
  public void testSetCamera() {
    fail("Not yet implemented");
  }

  /**
   * Test method for {@link edu.bu.cs.cs480.main.TracerEnvironment#setResolution(edu.bu.cs.cs480.camera.Resolution)}.
   */
  @Test
  public void testSetResolution() {
    fail("Not yet implemented");
  }

  /**
   * Test method for {@link edu.bu.cs.cs480.main.TracerEnvironment#setViewport(edu.bu.cs.cs480.camera.Viewport)}.
   */
  @Test
  public void testSetViewport() {
    fail("Not yet implemented");
  }

}
