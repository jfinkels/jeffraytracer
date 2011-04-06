/**
 * ModelReaderTest.java -
 */
package edu.bu.cs.cs480.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

import edu.bu.cs.cs480.FloatColor;
import edu.bu.cs.cs480.Vector3D;
import edu.bu.cs.cs480.camera.Camera;
import edu.bu.cs.cs480.camera.OrthographicCamera;

/**
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class ModelReaderTest {

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.main.ModelReader#fromFile(java.lang.String)}.
   */
  @Test
  public void testFromFile() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.main.ModelReader#getObjectWithID(java.util.List, int)}
   * .
   */
  @Test
  public void testGetObjectWithID() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.main.ModelReader#readBox(java.util.Scanner, java.util.List)}
   * .
   */
  @Test
  public void testReadBox() {
    fail("Not yet implemented");
  }

  public static final String RESOURCE_DIR = "src/test/resources/edu/bu/cs/cs480/main/camera.dat";
  private static final File CAMERA_FILE = new File(RESOURCE_DIR + "camera.dat");
  private static final File COLOR_FILE = new File(RESOURCE_DIR + "color.dat");

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.main.ModelReader#readCamera(java.util.Scanner)}.
   */
  @Test
  public void testReadCamera() {
    Scanner s = null;
    try {
      s = new Scanner(CAMERA_FILE);
    } catch (final FileNotFoundException exception) {
      exception.printStackTrace(System.err);
      fail(exception.getLocalizedMessage());
    }

    Camera camera = null;
    try {
      camera = ModelReader.readCamera(s);
    } catch (final FileFormatException exception) {
      exception.printStackTrace(System.err);
      fail(exception.getLocalizedMessage());
    }

    assertTrue(camera instanceof OrthographicCamera);
    assertTrue(camera.center().equals(new Vector3D(7, -7, 7)));
    assertTrue(camera.lookAt().equals(new Vector3D(0, 1, 1)));
    assertTrue(camera.up().equals(new Vector3D(0, 0, 1)));
    assertEquals(1, camera.near(), 0);
    assertEquals(20, camera.far(), 0);
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.main.ModelReader#readColor(java.util.Scanner)}.
   */
  @Test
  public void testReadColor() {
    Scanner s = null;
    try {
      s = new Scanner(COLOR_FILE);
    } catch (final FileNotFoundException exception) {
      exception.printStackTrace(System.err);
      fail(exception.getLocalizedMessage());
    }

    final FloatColor color = ModelReader.readColor(s);
    
    assertEquals(0.1, color.getRed(), 0);
    assertEquals(0.2, color.getGreen(), 0);
    assertEquals(0.3, color.getBlue(), 0);
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.main.ModelReader#readCSG(java.util.Scanner, java.util.List)}
   * .
   */
  @Test
  public void testReadCSG() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.main.ModelReader#readCylinder(java.util.Scanner, java.util.List)}
   * .
   */
  @Test
  public void testReadCylinder() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.main.ModelReader#readEllipsoid(java.util.Scanner, java.util.List)}
   * .
   */
  @Test
  public void testReadEllipsoid() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.main.ModelReader#readIntegerList(java.util.Scanner)}
   * .
   */
  @Test
  public void testReadIntegerList() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.main.ModelReader#readLight(java.util.Scanner)}.
   */
  @Test
  public void testReadLight() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.main.ModelReader#readMaterial(java.util.Scanner)}.
   */
  @Test
  public void testReadMaterial() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.main.ModelReader#readOrientation(java.util.Scanner)}
   * .
   */
  @Test
  public void testReadOrientation() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.main.ModelReader#readResolution(java.util.Scanner)}.
   */
  @Test
  public void testReadResolution() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.main.ModelReader#readSphere(java.util.Scanner, java.util.List)}
   * .
   */
  @Test
  public void testReadSphere() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.main.ModelReader#readSurfaceObject(java.util.Scanner, java.util.List, java.util.List)}
   * .
   */
  @Test
  public void testReadSurfaceObject() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.main.ModelReader#readTriple(java.util.Scanner)}.
   */
  @Test
  public void testReadTriple() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.main.ModelReader#readViewport(java.util.Scanner)}.
   */
  @Test
  public void testReadViewport() {
    fail("Not yet implemented");
  }

}
