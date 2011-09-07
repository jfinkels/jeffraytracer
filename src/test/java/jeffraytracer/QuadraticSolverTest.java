/**
 * QuadraticSolverTest.java - test class for the QuadraticSolver class
 * 
 * Copyright 2011 Jeffrey Finkelstein
 * 
 * This file is part of jeffraytracer.
 * 
 * jeffraytracer is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 * 
 * jeffraytracer is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * jeffraytracer. If not, see <http://www.gnu.org/licenses/>.
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
