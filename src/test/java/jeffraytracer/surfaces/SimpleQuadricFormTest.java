/**
 * SimpleQuadricFormTest.java - test for the SimpleQuadricForm class
 * 
 * Copyright 2011 Jeffrey Finkelstein
 * 
 * This file is part of jeffraytracer.
 * 
 * jeffraytracer is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 * 
 * jeffraytracer is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * jeffraytracer. If not, see <http://www.gnu.org/licenses/>.
 */
package jeffraytracer.surfaces;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import jeffraytracer.Matrix4x4;
import jeffraytracer.Ray;
import jeffraytracer.Vector3D;

import org.junit.Before;
import org.junit.Test;

/**
 * Test for the SimpleQuadricForm class.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class SimpleQuadricFormTest {
  /** The object under test. */
  private SimpleQuadricForm s = null;

  /**
   * A "quadric" which is actually just the identity matrix.
   * 
   * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
   * @since Spring 2011
   */
  static class TestQuadric1 extends SimpleQuadricForm {
    @Override
    protected Matrix4x4 rotation() {
      return Matrix4x4.identity();
    }

    @Override
    protected Matrix4x4 baseMatrix() {
      return Matrix4x4.identity();
    }
  }

  /**
   * A quadric which is a sphere of radius 5 with a fixed rotation.
   * 
   * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
   * @since Spring 2011
   */
  static class TestQuadric2 extends SimpleQuadricForm {

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
  }

  /** Instantiate the object before each test. */
  @Before
  public void setUp() {
    this.s = new TestQuadric1();
  }

  /**
   * Test method for
   * {@link jeffraytracer.surfaces.SimpleQuadricForm#interceptWith(jeffraytracer.Ray)}
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
    assertTrue(intercept.pointOfIntersection().equalTo(Vector3D.ORIGIN));
  }

  /**
   * Test method for {@link jeffraytracer.surfaces.SimpleQuadricForm#compile()}
   * .
   */
  @Test
  public void testCompile() {
    this.s = new TestQuadric2();
    this.s.setPosition(new Vector3D(1, 2, 3));

    this.s.compile();

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
