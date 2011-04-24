/**
 * PlaneTest.java - test for the Plane class
 */
package edu.bu.cs.cs480.surfaces;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.bu.cs.cs480.Intercept;
import edu.bu.cs.cs480.Ray;
import edu.bu.cs.cs480.Vector3D;

/**
 * Test for the Plane class.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class PlaneTest {

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.surfaces.Plane#interceptWith(edu.bu.cs.cs480.Ray)}.
   */
  @Test
  public void testInterceptWith() {
    final Plane p = new Plane(new Vector3D(0, 0, 1), -4);
    final Ray r = new Ray();
    r.setPosition(Vector3D.ORIGIN);
    r.setDirection(new Vector3D(0, 0, 1));

    Intercept i = p.interceptWith(r);
    assertNotNull(i);
    assertEquals(4, i.time(), 0);
    assertSame(p, i.surfaceObject());

    r.setPosition(new Vector3D(0, 0, 10));
    r.setDirection(new Vector3D(0, 0, -1));

    i = p.interceptWith(r);
    assertNotNull(i);
    assertEquals(6, i.time(), 0);
    assertSame(p, i.surfaceObject());

    r.setPosition(Vector3D.ORIGIN);
    r.setDirection(new Vector3D(0, 1, 1).normalized());
    i = p.interceptWith(r);
    assertNotNull(i);
    assertEquals(4 * Math.sqrt(2), i.time(), 0);
    assertSame(p, i.surfaceObject());
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.surfaces.Plane#inside(edu.bu.cs.cs480.Vector3D)}
   * .
   */
  @Test
  public void testPointIsBelow() {
    final Plane p = new Plane(new Vector3D(0, 0, 1), -1);
    assertTrue(p.inside(Vector3D.ORIGIN));
    assertFalse(p.inside(new Vector3D(0, 0, 2)));
    assertTrue(p.inside(new Vector3D(10, 20, -100)));
    assertFalse(p.inside(new Vector3D(10, 20, 100)));
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.surfaces.Plane#outside(edu.bu.cs.cs480.Vector3D)}
   * .
   */
  @Test
  public void testPointIsAbove() {
    final Plane p = new Plane(new Vector3D(0, 0, 1), -1);
    assertFalse(p.outside(Vector3D.ORIGIN));
    assertTrue(p.outside(new Vector3D(0, 0, 2)));
    assertFalse(p.outside(new Vector3D(10, 20, -100)));
    assertTrue(p.outside(new Vector3D(10, 20, 100)));
  }

}
