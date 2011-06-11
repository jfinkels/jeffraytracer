/**
 * PerspectiveCameraTest.java - test for the PerspectiveCamera class
 */
package jeffraytracer.camera;

import static org.junit.Assert.assertTrue;
import jeffraytracer.Vector3D;

import org.junit.Before;
import org.junit.Test;

/**
 * Test for the PerspectiveCamera class.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class PerspectiveCameraTest {

  /** The PerspectiveCamera object for testing. */
  private PerspectiveCamera c = null;
  /** The position of the camera. */
  private Vector3D position = null;
  /** The direction in which the camera is pointing. */
  private Vector3D direction = null;
  /** The up vector for the camera. */
  private Vector3D up = null;

  /** Create a PerspectiveCamera object for testing. */
  @Before
  public void setUp() {
    this.position = new Vector3D(0, 0, -1);
    this.direction = new Vector3D(0, 0, 1);
    this.up = new Vector3D(0, 1, 0);
    this.c = new PerspectiveCamera();
    this.c.setPosition(this.position);
    this.c.setDirection(this.direction);
    this.c.setUp(this.up);
  }

  /**
   * Test method for
   * {@link jeffraytracer.camera.PerspectiveCamera#rayDirection(jeffraytracer.Vector3D)}
   * .
   */
  @Test
  public void testRayDirection() {
    assertTrue(this.c.rayDirection(new Vector3D(1, 2, 3)).equalTo(
        new Vector3D(1, 2, 4).normalized()));
  }
}
