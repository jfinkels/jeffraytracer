/**
 * Point3DTest.java
 */
package edu.bu.cs.cs480.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.bu.cs.cs480.model.Point3D;

/**
 * Test class for the Point3DTest.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Point3DTest {

  /**
   * Test method for
   * {@link edu.bu.cs.cs680.Point3D#crossProduct(edu.bu.cs.cs680.Point3D)}.
   */
  @Test
  public void testCrossProduct() {
    final Point3D v1 = new Point3D(1.0, 0.0, 0.0);
    final Point3D v2 = new Point3D(0.0, 1.0, 0.0);
    final Point3D v3 = new Point3D(2.0, 0.0, 0.0);
    Point3D result = v1.crossProduct(v2);
    assertEquals(0.0, result.x(), 0.0);
    assertEquals(0.0, result.y(), 0.0);
    assertEquals(1.0, result.z(), 0.0);
    result = v2.crossProduct(v3);
    assertEquals(0.0, result.x(), 0.0);
    assertEquals(0.0, result.y(), 0.0);
    assertEquals(-2.0, result.z(), 0.0);
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs680.Point3D#difference(edu.bu.cs.cs680.Point3D)}.
   */
  @Test
  public void testDifference3D() {
    final Point3D v1 = new Point3D(1.0, 0.0, 1.0);
    final Point3D v2 = new Point3D(0.0, 1.0, 0.0);
    final Point3D result = v1.difference(v2);
    assertEquals(1.0, result.x(), 0.0);
    assertEquals(-1.0, result.y(), 0.0);
    assertEquals(1.0, result.z(), 0.0);
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs680.Point3D#distanceTo(edu.bu.cs.cs680.Point3D)}.
   */
  @Test
  public void testDistanceTo() {
    final Point3D v1 = new Point3D(1.0, 0.0, 1.0);
    final Point3D v2 = new Point3D(0.0, 1.0, 0.0);
    assertEquals(0.0, v1.distanceTo(v1), 0.0);
    assertEquals(0.0, v2.distanceTo(v2), 0.0);
    assertEquals(Math.sqrt(1 + 1 + 1), v1.distanceTo(v2), 0.0);
    assertEquals(v2.distanceTo(v1), v1.distanceTo(v2), 0.0);
  }

  /**
   * Test method for {@link edu.bu.cs.cs680.Point3D#norm()}.
   */
  @Test
  public void testNorm() {
    Point3D v1 = new Point3D(3.0, 0.0, 4.0);
    assertEquals(5.0, v1.norm(), 0.0);
    v1 = new Point3D(3.0, 0.0, 0.0);
    assertEquals(3.0, v1.norm(), 0.0);
  }

  /**
   * Test method for {@link edu.bu.cs.cs680.Point3D#scaledBy(double)}.
   */
  @Test
  public void testScaledBy() {
    final Point3D v1 = new Point3D(1.0, 0.0, -1.0);
    final float scale = 10;
    assertEquals(scale, v1.scaledBy(scale).x(), 0.0);
    assertEquals(0.0, v1.scaledBy(scale).y(), 0.0);
    assertEquals(-scale, v1.scaledBy(scale).z(), 0.0);
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs680.Point3D#sumWith(edu.bu.cs.cs680.Point3D)}.
   */
  @Test
  public void testSumWith() {
    final Point3D v1 = new Point3D(1.0, 0.0, 1.0);
    final Point3D v2 = new Point3D(0.0, 1.0, 0.0);
    assertEquals(1.0, v1.sumWith(v2).x(), 0.0);
    assertEquals(1.0, v1.sumWith(v2).y(), 0.0);
    assertEquals(1.0, v1.sumWith(v2).z(), 0.0);
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs680.Point3D#Point3D(double, double, double)}.
   */
  @Test
  public void testPoint3DDoubleDoubleDouble() {
    final Point3D vector = new Point3D(0.0d, 1.0d, 2.0d);
    assertEquals(0.0, vector.x(), 0.0);
    assertEquals(1.0, vector.y(), 0.0);
    assertEquals(2.0, vector.z(), 0.0);
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs680.Point3D#Point3D(float, float, float)}.
   */
  @Test
  public void testPoint3DFloatFloatFloat() {
    final Point3D vector = new Point3D(0.0f, 1.0f, 2.0f);
    assertEquals(0.0, vector.x(), 0.0);
    assertEquals(1.0, vector.y(), 0.0);
    assertEquals(2.0, vector.z(), 0.0);
  }

}
