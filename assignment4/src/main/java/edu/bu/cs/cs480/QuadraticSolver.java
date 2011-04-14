/**
 * QuadraticSolver.java - solves quadratic equations
 */
package edu.bu.cs.cs480;

/**
 * Solves quadratic equations using the quadratic formula.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class QuadraticSolver {
  /**
   * Returns the pair of solutions to the quadratic equation with the specified
   * coefficients, or {@code null} if the solution is imaginary.
   * 
   * @param a
   *          The coefficient of the quadratic term.
   * @param b
   *          The coefficient of the linear term.
   * @param c
   *          The constant term.
   * @return The pair of solutions to the quadratic equation with the specified
   *         coefficients, or {@code null} if the solution is imaginary.
   */
  public static Pair solve(final double a, final double b, final double c) {
    final double discriminant = (b * b) - (4 * a * c);
    if (discriminant < 0) {
      return null;
    }
    final double sqrtDiscriminant = Math.sqrt(discriminant);
    final double twoA = 2 * a;
    final double left = (-b + sqrtDiscriminant) / twoA;
    final double right = (-b - sqrtDiscriminant) / twoA;
    return new Pair(left, right);
  }
}
