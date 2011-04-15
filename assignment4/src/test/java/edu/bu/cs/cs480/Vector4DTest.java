/**
 * 
 */
package edu.bu.cs.cs480;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author jeff
 * 
 */
public class Vector4DTest {

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.Vector4D#Vector4D(double, double, double, double)}.
   */
  @Test
  public void testVector4DDoubleDoubleDoubleDouble() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.Vector4D#Vector4D(edu.bu.cs.cs480.Vector3D, double)}
   * .
   */
  @Test
  public void testVector4DVector3DDouble() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.Vector4D#dotProduct(edu.bu.cs.cs480.Vector4D)}.
   */
  @Test
  public void testDotProduct() {
    final Vector4D v1 = new Vector4D(0, 0, 1, 0);
    final Vector4D v2 = new Vector4D(0, 0, -10, -25);
    assertEquals(-10, v1.dotProduct(v2), 0);
  }

  /**
   * Test method for {@link edu.bu.cs.cs480.Vector4D#norm()}.
   */
  @Test
  public void testNorm() {
    fail("Not yet implemented");
  }

  /**
   * Test method for {@link edu.bu.cs.cs480.Vector4D#normalized()}.
   */
  @Test
  public void testNormalized() {
    fail("Not yet implemented");
  }

  /**
   * Test method for {@link edu.bu.cs.cs480.Vector4D#scaledBy(double)}.
   */
  @Test
  public void testScaledBy() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.Vector4D#sumWith(edu.bu.cs.cs480.Vector4D)}.
   */
  @Test
  public void testSumWith() {
    fail("Not yet implemented");
  }

  /**
   * Test method for {@link edu.bu.cs.cs480.Vector4D#toString()}.
   */
  @Test
  public void testToString() {
    fail("Not yet implemented");
  }

  /**
   * Test method for {@link edu.bu.cs.cs480.Vector4D#w()}.
   */
  @Test
  public void testW() {
    fail("Not yet implemented");
  }

  /**
   * Test method for {@link edu.bu.cs.cs480.Vector4D#x()}.
   */
  @Test
  public void testX() {
    fail("Not yet implemented");
  }

  /**
   * Test method for {@link edu.bu.cs.cs480.Vector4D#y()}.
   */
  @Test
  public void testY() {
    fail("Not yet implemented");
  }

  /**
   * Test method for {@link edu.bu.cs.cs480.Vector4D#z()}.
   */
  @Test
  public void testZ() {
    fail("Not yet implemented");
  }

}
