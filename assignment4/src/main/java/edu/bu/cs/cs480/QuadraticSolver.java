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
   * Evaluates the quadratic polynomial over the reals with the specified
   * coefficients at the specified point.
   * 
   * @param a
   *          The coefficient of the quadratic term.
   * @param b
   *          The coefficient of the linear term.
   * @param c
   *          The constant term.
   * @param x
   *          The point at which to evaluate this polynomial.
   * @return The result of evaluating the specified polynomial at the specified
   *         point.
   */
  public static double evaluate(final double a, final double b, final double c,
      final double x) {
    return a * x * x + b * x + c;
  }

  /**
   * Returns the pair of solutions to the quadratic equation with the specified
   * coefficients, or {@code null} if the solution is imaginary.
   * 
   * If a is zero, this method return {@code null}
   * 
   * @param a
   *          The coefficient of the quadratic term.
   * @param b
   *          The coefficient of the linear term.
   * @param c
   *          The constant term.
   * @return The pair of solutions to the quadratic equation with the specified
   *         coefficients, or {@code null} if the solution is imaginary or a is
   *         zero.
   */
  public static Pair solve(final double a, final double b, final double c) {
    // this is a linear equation
    if (a == 0) {
      return null;
    }

    // if the discriminant is negative, the roots are imaginary
    final double discriminant = (b * b) - (4 * a * c);
    if (discriminant < 0) {
      return null;
    }

    // otherwise compute and return the two roots
    final double sqrtDiscriminant = Math.sqrt(discriminant);
    final double twoA = 2 * a;
    final double left = (-b + sqrtDiscriminant) / twoA;
    final double right = (-b - sqrtDiscriminant) / twoA;
    return new Pair(left, right);
  }

}
