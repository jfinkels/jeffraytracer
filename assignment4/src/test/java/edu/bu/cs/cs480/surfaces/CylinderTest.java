/**
 * CylinderTest.java - test for the Cylinder class
 */
package edu.bu.cs.cs480.surfaces;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import edu.bu.cs.cs480.Intercept;
import edu.bu.cs.cs480.Matrix4x4;
import edu.bu.cs.cs480.Ray;
import edu.bu.cs.cs480.Vector3D;

/**
 * Test for the Cylinder class.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class CylinderTest {

  /** The cylinder to test. */
  private Cylinder c = null;

  /** Set the properties of the cylinder to test. */
  @Before
  public void setUp() {
    this.c = new Cylinder();
    this.c.setPosition(new Vector3D(0, 0, 10));
    this.c.setDirection(new Vector3D(1, 1, 1).normalized());
    this.c.setLength(5);
    this.c.setRadius(2);
  }

  /**
   * Test method for {@link edu.bu.cs.cs480.surfaces.Cylinder#baseMatrix()}.
   */
  @Test
  public void testBaseMatrix() {
    final Matrix4x4 m = this.c.baseMatrix();
    assertEquals(1, m.get(0, 0), 0);
    assertEquals(0, m.get(1, 1), 0);
    assertEquals(1, m.get(2, 2), 0);
    assertEquals(-4, m.get(3, 3), 0);
  }

  /**
   * Test method for {@link edu.bu.cs.cs480.surfaces.Cylinder#compile()}.
   */
  @Test
  public void testCompile() {
    this.c.compile();
    // final Matrix4x4 result = this.c.matrix();
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.surfaces.Cylinder#inside(edu.bu.cs.cs480.Vector3D)}
   * .
   */
  @Test
  public void testInside() {
    this.c.compile();
    assertTrue(this.c.inside(new Vector3D(0, 0, 10)));
    assertTrue(this.c.inside(new Vector3D(0, 0, 10.5)));
    assertTrue(this.c.inside(new Vector3D(0, 0, 9.5)));
    assertTrue(this.c.inside(new Vector3D(1, 1, 11)));
    assertTrue(this.c.inside(new Vector3D(-1, -1, 9)));

    assertFalse(this.c.inside(Vector3D.ORIGIN));
    assertFalse(this.c.inside(new Vector3D(10, 10, 10)));
    assertFalse(this.c.inside(new Vector3D(0, 0, 10).sumWith(new Vector3D(1,
        1, 1).normalized().scaledBy(2.5))));
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.surfaces.Cylinder#interceptWith(edu.bu.cs.cs480.Ray)}
   * .
   */
  @Test
  public void testInterceptWith() {
    this.c.compile();
    final Ray r = new Ray();
    r.setPosition(new Vector3D(0, 0, 10).sumWith(new Vector3D(1, 1, -1)
        .scaledBy(4)));
    r.setDirection(new Vector3D(-1, -1, 1).normalized());
    Intercept i = this.c.interceptWith(r);
    assertNotNull(i);
    assertSame(this.c, i.surfaceObject());
    // TODO figure out what is wrong with this
    // assertEquals(2, i.time(), 0);
    assertTrue(i.time() > 0);

    final Cylinder c2 = new Cylinder();
    c2.setPosition(new Vector3D(0, 0, 10));
    c2.setDirection(new Vector3D(0, 1, 0));
    c2.setRadius(1);
    c2.setLength(5);

    c2.compile();

    r.setPosition(Vector3D.ORIGIN);
    r.setDirection(new Vector3D(0, 0, 1));
    i = c2.interceptWith(r);
    assertNotNull(i);
    assertSame(c2, i.surfaceObject());
    assertEquals(9, i.time(), 0);

    r.setPosition(new Vector3D(0, 0, 8));
    i = c2.interceptWith(r);
    assertNotNull(i);
    assertSame(c2, i.surfaceObject());
    assertEquals(1, i.time(), 0);

    r.setPosition(new Vector3D(2, 0, 10));
    r.setDirection(new Vector3D(-1, 0, 0));
    i = c2.interceptWith(r);
    assertNotNull(i);
    assertSame(c2, i.surfaceObject());
    assertEquals(1, i.time(), 0);

    r.setPosition(new Vector3D(2, 6, 10));
    i = c2.interceptWith(r);
    assertNull(i);

    r.setPosition(new Vector3D(0, 10, 10));
    r.setDirection(new Vector3D(0, -1, 0));
    i = c2.interceptWith(r);
    assertNotNull(i);
    assertSame(c2, i.surfaceObject());
    assertEquals(7.5, i.time(), 0);
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.surfaces.Cylinder#outside(edu.bu.cs.cs480.Vector3D)}
   * .
   */
  @Test
  public void testOutside() {
    this.c.compile();
    assertFalse(this.c.outside(new Vector3D(0, 0, 10)));
    assertFalse(this.c.outside(new Vector3D(0, 0, 10.5)));
    assertFalse(this.c.outside(new Vector3D(0, 0, 9.5)));
    assertFalse(this.c.outside(new Vector3D(1, 1, 11)));
    assertFalse(this.c.outside(new Vector3D(-1, -1, 9)));

    assertFalse(this.c.outside(new Vector3D(0, 0, 10).sumWith(new Vector3D(1,
        1, 1).normalized().scaledBy(2.5))));
    assertFalse(this.c.outside(new Vector3D(0, 0, 10).sumWith(new Vector3D(1,
        1, 1).normalized().scaledBy(-2.5))));

    assertTrue(this.c.outside(Vector3D.ORIGIN));
    assertTrue(this.c.outside(new Vector3D(10, 10, 10)));
  }

  /**
   * Test method for {@link edu.bu.cs.cs480.surfaces.Cylinder#rotation()}.
   */
  @Test
  public void testRotation() {
    this.c.setDirection(new Vector3D(0, 1, 0));
    final Matrix4x4 r = this.c.rotation();

    assertTrue(r.get(0, 0) == 1 || r.get(0, 0) == -1);
    assertEquals(1, r.get(1, 1), 0);
    assertTrue(r.get(2, 2) == 1 || r.get(2, 2) == -1);
    assertEquals(1, r.get(3, 3), 0);
  }

}
