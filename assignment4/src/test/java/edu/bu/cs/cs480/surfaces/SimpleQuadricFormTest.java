/**
 * SimpleQuadricFormTest.java - test for the SimpleQuadricForm class
 */
package edu.bu.cs.cs480.surfaces;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.bu.cs.cs480.Intercept;
import edu.bu.cs.cs480.Matrix4x4;
import edu.bu.cs.cs480.Ray;
import edu.bu.cs.cs480.Vector3D;

/**
 * Test for the SimpleQuadricForm class.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class SimpleQuadricFormTest {
  private SimpleQuadricForm s = null;

  @Before
  public void setUp() {
    this.s = new SimpleQuadricForm() {
      @Override
      protected Matrix4x4 rotation() {
        return Matrix4x4.identity();
      }

      @Override
      protected Matrix4x4 baseMatrix() {
        return Matrix4x4.identity();
      }
    };

  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.surfaces.SimpleQuadricForm#interceptWith(edu.bu.cs.cs480.Ray)}
   * .
   */
  @Test
  public void testInterceptWith() {
    final Sphere sphere = new Sphere();
    sphere.setPosition(new Vector3D(1, 0, 0));
    sphere.setRadius(1);

    final Ray ray = new Ray();
    ray.setPosition(new Vector3D(-1, 0, 0));
    ray.setDirection(new Vector3D(1, 0, 0));

    sphere.compile();
    final Intercept intercept = sphere.interceptWith(ray);
    assertEquals(1, intercept.time(), 0);
    assertSame(sphere, intercept.surfaceObject());
    assertTrue(intercept.pointOfIntersection().equals(Vector3D.ORIGIN));
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.surfaces.SimpleQuadricForm#compile()}.
   */
  @Test
  public void testCompile() {
    this.s = new SimpleQuadricForm() {

      @Override
      protected Matrix4x4 rotation() {
        final Matrix4x4 result = new Matrix4x4();
        result.set(0, 2, 1);
        result.set(1, 1, 1);
        result.set(2, 0, 1);
        result.set(3, 3, 1);
        return result;
      }

      @Override
      protected Matrix4x4 baseMatrix() {
        final Matrix4x4 result = Matrix4x4.identity();
        result.set(3, 3, -25);
        return result;
      }
    };
    this.s.setPosition(new Vector3D(1, 2, 3));

    this.s.compile();
    System.out.println(this.s.matrix());
    // first row
    assertEquals(1, this.s.matrix().get(0, 0), 0);
    assertEquals(0, this.s.matrix().get(0, 1), 0);
    assertEquals(0, this.s.matrix().get(0, 2), 0);
    assertEquals(-1, this.s.matrix().get(0, 3), 0);

    // second row
    assertEquals(0, this.s.matrix().get(1, 0), 0);
    assertEquals(1, this.s.matrix().get(1, 1), 0);
    assertEquals(0, this.s.matrix().get(1, 2), 0);
    assertEquals(-2, this.s.matrix().get(1, 3), 0);

    // third row
    assertEquals(0, this.s.matrix().get(2, 0), 0);
    assertEquals(0, this.s.matrix().get(2, 1), 0);
    assertEquals(1, this.s.matrix().get(2, 2), 0);
    assertEquals(-3, this.s.matrix().get(2, 3), 0);

    // fourth row
    assertEquals(-1, this.s.matrix().get(3, 0), 0);
    assertEquals(-2, this.s.matrix().get(3, 1), 0);
    assertEquals(-3, this.s.matrix().get(3, 2), 0);
    assertEquals(-11, this.s.matrix().get(3, 3), 0);
  }

}
