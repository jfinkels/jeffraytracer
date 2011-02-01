/**
 * AllTest.java
 */
package edu.bu.cs.cs680;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Runs all tests in this package.
 * 
 * @author Jeffrey Finkelstein <jeffreyf>
 */
@RunWith(Suite.class)
@SuiteClasses({ LineSegmentTest.class, LineTest.class, PolygonTest.class,
    RayTest.class, Vector2DTest.class, Vector3DTest.class })
public class AllTest {
  // intentionally unimplemented
}
