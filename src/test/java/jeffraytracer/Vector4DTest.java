/**
 * Vector4DTest.java - test for the Vector4D class
 */
package jeffraytracer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test for the Vector4D class.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Vector4DTest {

  /**
   * Test method for
   * {@link jeffraytracer.Vector4D#dotProduct(jeffraytracer.Vector4D)}.
   */
  @Test
  public void testDotProduct() {
    final Vector4D v1 = new Vector4D(0, 0, 1, 0);
    final Vector4D v2 = new Vector4D(0, 0, -10, -25);
    assertEquals(-10, v1.dotProduct(v2), 0);
  }

}
