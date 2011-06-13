/**
 * AllTest.java - runs all tests in this package
 */
package jeffraytracer.rendering.renderers;

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
@SuiteClasses({ DefaultRendererTest.class, SupersamplingRendererTest.class })
public class AllTest {
  // intentionally unimplemented
}