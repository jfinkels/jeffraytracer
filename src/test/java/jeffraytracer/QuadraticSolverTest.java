/**
 * QuadraticSolverTest.java - test class for the QuadraticSolver class
 */
package jeffraytracer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * Test class for the QuadraticSolver class.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class QuadraticSolverTest {

  /**
   * Test method for
   * {@link jeffraytracer.QuadraticSolver#solve(double, double, double)}.
   */
  @Test
  public void testSolve() {
    /** x^2 + 2x + 1 = 0 -> x = -1 */
    Pair pair = QuadraticSolver.solve(1, 2, 1);
    assertEquals(-1, pair.left(), 0);
    assertEquals(-1, pair.right(), 0);

    /** x^2 - 2x + 1 = 0 -> x = 1 */
    pair = QuadraticSolver.solve(1, -2, 1);
    assertEquals(1, pair.left(), 0);
    assertEquals(1, pair.right(), 0);

    /** x^2 - 4 = 0 -> x = +2 or x = -2 */
    pair = QuadraticSolver.solve(1, 0, -4);
    assertEquals(2, pair.left(), 0);
    assertEquals(-2, pair.right(), 0);

    /** x^2 + 1 = 0 -> x is imaginary, so result is null */
    pair = QuadraticSolver.solve(1, 0, 1);
    assertNull(pair);

    /** a linear equation */
    pair = QuadraticSolver.solve(0, 5, 2);
    assertNull(pair);
  }
}
