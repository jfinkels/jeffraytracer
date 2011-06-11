/**
 * Matrix4x4Test.java - test class for the Matrix4x4 class
 */
package jeffraytracer;

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
   * Test method for {@link jeffraytracer.Matrix4x4#set(int, int, double)}.
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
   * {@link jeffraytracer.Matrix4x4#product(jeffraytracer.Vector4D)}.
   */
  @Test
  public void testProduct() {
    // create a matrix which doubles vectors
    Matrix4x4 matrix = new Matrix4x4();
    matrix.set(0, 0, 2);
    matrix.set(1, 1, 2);
    matrix.set(2, 2, 2);
    matrix.set(3, 3, 2);

    Vector4D vector = matrix.product(new Vector4D(1, 2, 3, 4));
    assertEquals(2, vector.x(), 0);
    assertEquals(4, vector.y(), 0);
    assertEquals(6, vector.z(), 0);
    assertEquals(8, vector.w(), 0);

    matrix = new Matrix4x4();
    matrix.set(2, 3, 0.5);
    matrix.set(3, 2, 0.5);
    matrix.set(3, 3, 4);

    vector = matrix.product(new Vector4D(0, 0, 1, 0));
    assertEquals(0, vector.x(), 0);
    assertEquals(0, vector.y(), 0);
    assertEquals(0, vector.z(), 0);
    assertEquals(.5, vector.w(), 0);
  }

  /**
   * Test method for {@link jeffraytracer.Matrix4x4#transposed()}.
   */
  @Test
  public void testTransposed() {
    for (int n = 0; n < 100; ++n) {
      final Matrix4x4 matrix = TestUtils.randomMatrix();

      final Matrix4x4 transpose = matrix.transposed();
      for (int i = 0; i < 4; ++i) {
        for (int j = 0; j < 4; ++j) {
          assertEquals(matrix.get(i, j), transpose.get(j, i), 0);
        }
      }
    }
  }

  /**
   * Test method for
   * {@link jeffraytracer.Matrix4x4#product(jeffraytracer.Matrix4x4)}.
   */
  @Test
  public void testProductMatrix() {
    final Matrix4x4 matrix1 = new Matrix4x4();
    matrix1.set(0, 0, 2);
    matrix1.set(1, 1, 2);
    matrix1.set(2, 2, 2);
    matrix1.set(3, 3, 2);

    final Matrix4x4 matrix2 = new Matrix4x4();
    matrix2.set(0, 0, 1);
    matrix2.set(1, 1, 1);
    matrix2.set(2, 2, 1);
    matrix2.set(3, 3, 1);

    Matrix4x4 result = matrix1.product(matrix2);
    for (int i = 0; i < 4; ++i) {
      for (int j = 0; j < 4; ++j) {
        assertEquals(matrix1.get(i, j), result.get(i, j), 0);
      }
    }
    result = matrix2.product(matrix1);
    for (int i = 0; i < 4; ++i) {
      for (int j = 0; j < 4; ++j) {
        assertEquals(matrix1.get(i, j), result.get(i, j), 0);
      }
    }

    matrix2.set(0, 3, 1);
    matrix2.set(1, 3, 2);
    matrix2.set(2, 3, 3);

    result = matrix2.product(matrix1);
    for (int i = 0; i < 4; ++i) {
      for (int j = 0; j < 4; ++j) {
        assertEquals(2 * matrix2.get(i, j), result.get(i, j), 0);
      }
    }

    final Matrix4x4 matrix3 = Matrix4x4.identity();
    matrix3.set(3, 3, -16);
    final Matrix4x4 matrix4 = Matrix4x4.identity();
    matrix4.set(0, 3, -1);
    matrix4.set(1, 3, -2);
    matrix4.set(2, 3, -3);

    result = matrix3.product(matrix4);

    // first row
    assertEquals(1, result.get(0, 0), 0);
    assertEquals(0, result.get(0, 1), 0);
    assertEquals(0, result.get(0, 2), 0);
    assertEquals(-1, result.get(0, 3), 0);

    // second row
    assertEquals(0, result.get(1, 0), 0);
    assertEquals(1, result.get(1, 1), 0);
    assertEquals(0, result.get(1, 2), 0);
    assertEquals(-2, result.get(1, 3), 0);

    // third row
    assertEquals(0, result.get(2, 0), 0);
    assertEquals(0, result.get(2, 1), 0);
    assertEquals(1, result.get(2, 2), 0);
    assertEquals(-3, result.get(2, 3), 0);

    // fourth row
    assertEquals(0, result.get(3, 0), 0);
    assertEquals(0, result.get(3, 1), 0);
    assertEquals(0, result.get(3, 2), 0);
    assertEquals(-16, result.get(3, 3), 0);

    final Matrix4x4 matrix5 = Matrix4x4.identity();
    matrix5.set(3, 0, -1);
    matrix5.set(3, 1, -2);
    matrix5.set(3, 2, -3);

    result = matrix5.product(result);

    // first row
    assertEquals(1, result.get(0, 0), 0);
    assertEquals(0, result.get(0, 1), 0);
    assertEquals(0, result.get(0, 2), 0);
    assertEquals(-1, result.get(0, 3), 0);

    // second row
    assertEquals(0, result.get(1, 0), 0);
    assertEquals(1, result.get(1, 1), 0);
    assertEquals(0, result.get(1, 2), 0);
    assertEquals(-2, result.get(1, 3), 0);

    // third row
    assertEquals(0, result.get(2, 0), 0);
    assertEquals(0, result.get(2, 1), 0);
    assertEquals(1, result.get(2, 2), 0);
    assertEquals(-3, result.get(2, 3), 0);

    // fourth row
    assertEquals(-1, result.get(3, 0), 0);
    assertEquals(-2, result.get(3, 1), 0);
    assertEquals(-3, result.get(3, 2), 0);
    assertEquals(1 + 4 + 9 - 16, result.get(3, 3), 0);
  }

}
