/**
 * 
 */
package edu.bu.cs.cs480;

/**
 * @author jeff
 * 
 */
public class QuadraticSolver {
  public static Pair solve(final double a, final double b, final double c) {
    final double discriminant = (b * b) - (4 * a * c);
    if (discriminant < 0) {
      return null;
    }
    final double left = (-b + Math.sqrt(discriminant)) / (2 * a);
    final double right = (-b - Math.sqrt(discriminant)) / (2 * a);
    return new Pair(left, right);
  }
}
