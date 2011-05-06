/**
 * BoxTest.java - test for the Box class
 */
package edu.bu.cs.cs480.surfaces;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import edu.bu.cs.cs480.Ray;
import edu.bu.cs.cs480.Vector3D;

/**
 * Test for the Box class.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class BoxTest {

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.surfaces.Box#interceptWith(edu.bu.cs.cs480.Ray)}.
   */
  @Test
  public void testInterceptWith() {
    final Box b = new Box();
    b.setPosition(new Vector3D(0, 0, 10));
    b.setDimensions(new Vector3D(1, 3, 5));
    b.setOrientation(Orientation.STANDARD_BASIS);

    // must compile before checking intersections
    b.compile();

    // the front face
    final Ray r = new Ray();
    r.setPosition(new Vector3D(0, 0, 0));
    r.setDirection(new Vector3D(0, 0, 1));

    Intercept i = b.interceptWith(r);
    assertNotNull(i);
    assertEquals(7.5, i.time(), 0);
    assertSame(b, i.surfaceObject());

    // the rear face
    r.setPosition(new Vector3D(0, 0, 20));
    r.setDirection(new Vector3D(0, 0, -1));

    i = b.interceptWith(r);
    assertNotNull(i);
    assertEquals(7.5, i.time(), 0);
    assertSame(b, i.surfaceObject());

    // the right face
    r.setPosition(new Vector3D(5, 0, 10));
    r.setDirection(new Vector3D(-1, 0, 0));

    i = b.interceptWith(r);
    assertNotNull(i);
    assertEquals(4.5, i.time(), 0);
    assertSame(b, i.surfaceObject());

    // the left face
    r.setPosition(new Vector3D(-5, 0, 10));
    r.setDirection(new Vector3D(1, 0, 0));

    i = b.interceptWith(r);
    assertNotNull(i);
    assertEquals(4.5, i.time(), 0);
    assertSame(b, i.surfaceObject());

    // the top face
    r.setPosition(new Vector3D(0, 10, 10));
    r.setDirection(new Vector3D(0, -1, 0));

    i = b.interceptWith(r);
    assertNotNull(i);
    assertEquals(8.5, i.time(), 0);
    assertSame(b, i.surfaceObject());

    // the bottom face
    r.setPosition(new Vector3D(0, -10, 10));
    r.setDirection(new Vector3D(0, 1, 0));

    i = b.interceptWith(r);
    assertNotNull(i);
    assertEquals(8.5, i.time(), 0);
    assertSame(b, i.surfaceObject());

    // rays that should miss
    r.setPosition(Vector3D.ORIGIN);
    r.setDirection(new Vector3D(1, 1, 0).normalized());

    i = b.interceptWith(r);
    assertNull(i);

    r.setPosition(new Vector3D(20, 20, 20));
    r.setDirection(new Vector3D(1, 1, 1).normalized());

    i = b.interceptWith(r);
    assertNull(i);
  }

}
