/**
 * 
 */
package edu.bu.cs.cs480.model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author Jeffrey Finkelstein
 * 
 */
public class BaseRotatableTest {

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.model.BaseRotatable#rotate(edu.bu.cs.cs480.model.Point3D, double)}
   * .
   */
  @Test
  public void testRotate() {
    final BaseRotatable r = new BaseRotatable();
    r.rotate(new Point3D(1, 0, 0), 90);
    Quaternion expected = new Quaternion(new Point3D(1, 0, 0), 90);
    expected.normalize();
    assertTrue(expected.equals(r.rotation()));
    r.rotate(new Point3D(1, 0, 0), 90);
    expected = expected.multiply(new Quaternion(new Point3D(1, 0, 0), 90));
    expected.normalize();
    assertTrue(expected.equals(r.rotation()));
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs480.model.BaseRotatable#rotateTo(edu.bu.cs.cs480.model.Point3D, double)}
   * .
   */
  @Test
  public void testRotateTo() {
    BaseRotatable r = new BaseRotatable();
    r.rotateTo(new Point3D(1, 2, 3), 123);
    assertTrue(r.rotation().equals(new Quaternion(new Point3D(1, 2, 3), 123)));
  }

}
