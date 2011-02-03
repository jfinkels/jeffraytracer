/**
 * Vector2DTest.java
 */
package edu.bu.cs.cs680;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for the Vector2D class.
 * 
 * @author Jeffrey Finkelstein <jeffreyf>
 */
public class Vector2DTest {

  /**
   * Test method for {@link edu.bu.cs.cs680.Vector2D#Vector2D(double, double)}.
   */
  @Test
  public void testVector2DDoubleDouble() {
    final Vector2D vector = new Vector2D(0.0d, 1.0d);
    assertEquals(0.0, vector.x, 0.0);
    assertEquals(1.0, vector.y, 0.0);
  }

  /**
   * Test method for {@link edu.bu.cs.cs680.Vector2D#Vector2D(float, float)}.
   */
  @Test
  public void testVector2DFloatFloat() {
    final Vector2D vector = new Vector2D(0.0f, 1.0f);
    assertEquals(0.0, vector.x, 0.0);
    assertEquals(1.0, vector.y, 0.0);
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs680.Vector2D#difference(edu.bu.cs.cs680.Vector2D)}.
   */
  @Test
  public void testDifference2D() {
    final Vector2D up = new Vector2D(0, 1);
    final Vector2D right = new Vector2D(1, 0);
    Vector2D diff = up.difference(right);
    assertEquals(-1.0, diff.x, 0.0);
    assertEquals(1.0, diff.y, 0.0);
    diff = diff.difference(diff);
    assertEquals(0.0, diff.x, 0.0);
    assertEquals(0.0, diff.y, 0.0);
  }

  /**
   * Test method for {@link edu.bu.cs.cs680.Vector2D#moveTo(float, float)}.
   */
  @Test
  public void testMoveTo() {
    final Vector2D vector = new Vector2D(0.0, 0.0);
    vector.moveTo(1.0f, 2.0f);
    assertEquals(1.0, vector.x, 0.0);
    assertEquals(2.0, vector.y, 0.0);
  }
  
  /**
   * Test method for
   * {@link edu.bu.cs.cs680.Vector2D#scaledBy(float)}.
   */
  @Test
  public void testScaledBy() {
    final Vector2D v1 = new Vector2D(1.0, -2.0);
    final float scale = 10;
    assertEquals(scale, v1.scaledBy(scale).x, 0.0);
    assertEquals(-2.0 * scale, v1.scaledBy(scale).y, 0.0);
  }
  
  /**
   * Test method for {@link edu.bu.cs.cs680.Vector2D#sumWith(Vector2D)}.
   */
  @Test
  public void testSumWith() {
    final Vector2D vector1 = new Vector2D(1.0, 2.0);
    final Vector2D vector2 = new Vector2D(3.0, 4.0);
    final Vector2D sum = vector1.sumWith(vector2);
    
    assertEquals(4.0, sum.x, 0.0);
    assertEquals(6.0, sum.y, 0.0);
  }

  /**
   * Test method for {@link edu.bu.cs.cs680.Vector2D#toString()}.
   */
  @Test
  public void testToString() {
    final Vector2D vector = new Vector2D(1.0, 2.0);
    assertTrue(vector.toString().contains(String.valueOf(vector.x)));
    assertTrue(vector.toString().contains(String.valueOf(vector.y)));
  }

  /**
   * Test method for {@link edu.bu.cs.cs680.Vector2D#translate(int, int)}.
   */
  @Test
  public void testTranslate() {
    final Vector2D vector = new Vector2D(1.0, 2.0);
    vector.translate(-1.0f, 1.0f);
    assertEquals(0.0, vector.x, 0.0);
    assertEquals(3.0, vector.y, 0.0);
  }

}
