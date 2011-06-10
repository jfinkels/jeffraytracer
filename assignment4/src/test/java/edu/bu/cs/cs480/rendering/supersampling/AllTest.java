/**
 * AllTest.java - runs all tests in this package
 */
package edu.bu.cs.cs480.rendering.supersampling;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import edu.bu.cs.cs480.rendering.supersampling.GridSupersamplerTest;
import edu.bu.cs.cs480.rendering.supersampling.WeightedGridAveragerTest;

/**
 * Runs all tests in this package.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
@RunWith(Suite.class)
@SuiteClasses({ GridSupersamplerTest.class, WeightedGridAveragerTest.class })
public class AllTest {
  // intentionally unimplemented
}
