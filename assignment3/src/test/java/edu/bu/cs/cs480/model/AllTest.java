/**
 * AllTest.java
 */
package edu.bu.cs.cs480.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Runs all tests in this package.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
@RunWith(Suite.class)
@SuiteClasses({ BaseRotatableTest.class, Point3DTest.class })
public class AllTest {
  // intentionally unimplemented
}
