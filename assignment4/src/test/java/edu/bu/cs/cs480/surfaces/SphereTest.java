/**
 * SphereTest.java -
 */
package edu.bu.cs.cs480.surfaces;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import org.junit.Test;

import edu.bu.cs.cs480.Intercept;
import edu.bu.cs.cs480.Ray;
import edu.bu.cs.cs480.Vector3D;

/**
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class SphereTest {

  /**
   * Test method for {@link edu.bu.cs.cs480.surfaces.Sphere#Sphere()}.
   */
  @Test
  public void testSphere() {
    fail("Not yet implemented");
  }

  /**
   * Test method for {@link edu.bu.cs.cs480.surfaces.Sphere#radius()}.
   */
  @Test
  public void testRadius() {
    fail("Not yet implemented");
  }

  /**
   * Test method for {@link edu.bu.cs.cs480.surfaces.Sphere#setRadius(double)}.
   */
  @Test
  public void testSetRadius() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.surfaces.Sphere#interceptWith(edu.bu.cs.cs480.Ray)}.
   */
  @Test
  public void testInterceptWith() {
    final Sphere s = new Sphere();
    s.setPosition(new Vector3D(0, 0, 0));
    s.setRadius(5);
    
    final Ray r = new Ray();
    r.setPosition(new Vector3D(0, 0, -10));
    r.setDirection(new Vector3D(0, 0, 1));
    
    s.compile();
    final Intercept intercept = s.interceptWith(r);
    assertEquals(5, intercept.time(), 0);
    assertSame(intercept.surfaceObject(), s);
  }

  /**
   * Test method for {@link edu.bu.cs.cs480.surfaces.Sphere#compile()}.
   */
  @Test
  public void testCompile() {
    fail("Not yet implemented");
  }

  /**
   * Test method for {@link edu.bu.cs.cs480.surfaces.Sphere#toString()}.
   */
  @Test
  public void testToString() {
    fail("Not yet implemented");
  }

}
