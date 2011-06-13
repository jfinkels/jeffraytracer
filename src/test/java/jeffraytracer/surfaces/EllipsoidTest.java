/**
 * EllipsoidTest.java - test for the Ellipsoid class
 */
package jeffraytracer.surfaces;

import static org.junit.Assert.assertEquals;
import jeffraytracer.Matrix4x4;
import jeffraytracer.Vector3D;

import org.junit.Test;

/**
 * Test for the Ellipsoid class.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class EllipsoidTest {

  /**
   * Test method for {@link jeffraytracer.surfaces.Ellipsoid#baseMatrix()}.
   */
  @Test
  public void testBaseMatrix() {
    final Ellipsoid ellipsoid = new Ellipsoid();
    ellipsoid.setRadii(new Vector3D(1, 2, 3));
    final Matrix4x4 base = ellipsoid.baseMatrix();

    assertEquals(1.0 / 1, base.get(0, 0), 0);
    assertEquals(1.0 / 4, base.get(1, 1), 0);
    assertEquals(1.0 / 9, base.get(2, 2), 0);
    assertEquals(-1, base.get(3, 3), 0);
  }

}