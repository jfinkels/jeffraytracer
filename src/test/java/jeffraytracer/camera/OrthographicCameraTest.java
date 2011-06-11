/**
 * OrthographicCameraTest.java - test for the OrthographicCamera class
 */
package jeffraytracer.camera;

import static org.junit.Assert.assertTrue;
import jeffraytracer.Vector3D;

import org.junit.Test;

/**
 * Test for the OrthographicCamera class.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class OrthographicCameraTest {

  /**
   * Test method for
   * {@link jeffraytracer.camera.OrthographicCamera#rayDirection(jeffraytracer.Vector3D)}
   * .
   */
  @Test
  public void testRayDirection() {
    final OrthographicCamera c = new OrthographicCamera();
    final Vector3D d = new Vector3D(0, 0, 1);
    c.setPosition(new Vector3D(0, 0, -1));
    c.setDirection(d);
    c.setUp(new Vector3D(1, 0, 0));
    assertTrue(c.rayDirection(new Vector3D(1, 2, 3)).equalTo(d));
  }

}
