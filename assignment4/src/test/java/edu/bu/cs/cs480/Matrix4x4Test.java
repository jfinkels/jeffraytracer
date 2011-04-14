/**
 * Matrix4x4Test.java - test class for the Matrix4x4 class
 */
package edu.bu.cs.cs480;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test class for the Matrix4x4 matrix.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Matrix4x4Test {

  /**
   * Test method for {@link edu.bu.cs.cs480.Matrix4x4#set(int, int, double)}.
   */
  @Test
  public void testSet() {
    final Matrix4x4 matrix = new Matrix4x4();
    matrix.set(0, 0, -1);
    matrix.set(1, 1, .1);
    matrix.set(2, 2, .2);
    matrix.set(3, 3, .3);

    assertEquals(-1, matrix.get(0, 0), 0);
    assertEquals(.1, matrix.get(1, 1), 0);
    assertEquals(.2, matrix.get(2, 2), 0);
    assertEquals(.3, matrix.get(3, 3), 0);
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.Matrix4x4#product(edu.bu.cs.cs480.Vector4D)}.
   */
  @Test
  public void testProduct() {
    // create a matrix which doubles vectors
    final Matrix4x4 matrix = new Matrix4x4();
    matrix.set(0, 0, 2);
    matrix.set(1, 1, 2);
    matrix.set(2, 2, 2);
    matrix.set(3, 3, 2);

    final Vector4D vector = matrix.product(new Vector4D(1, 2, 3, 4));
    assertEquals(2, vector.x(), 0);
    assertEquals(4, vector.y(), 0);
    assertEquals(6, vector.z(), 0);
    assertEquals(8, vector.w(), 0);
  }

}
