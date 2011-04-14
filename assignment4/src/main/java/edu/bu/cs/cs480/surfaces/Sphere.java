/**
 * Sphere.java - a sphere surface object
 */
package edu.bu.cs.cs480.surfaces;

import edu.bu.cs.cs480.Intercept;
import edu.bu.cs.cs480.Matrix4x4;
import edu.bu.cs.cs480.Pair;
import edu.bu.cs.cs480.QuadraticSolver;
import edu.bu.cs.cs480.Ray;
import edu.bu.cs.cs480.Vector4D;

/**
 * A sphere surface object.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Sphere extends ConcreteSurfaceObject {
  /** The radius of this sphere. */
  private double radius = 0;

  public Sphere() {
    for (int i = 0; i < 3; ++i) {
      this.matrix.set(i, i, 1);
    }
  }

  /**
   * Gets the radius of this sphere.
   * 
   * @return The radius of this sphere.
   */
  public double radius() {
    return this.radius;
  }

  /**
   * Sets the radius of this sphere.
   * 
   * @param radius
   *          The radius of this sphere.
   */
  public void setRadius(final double radius) {
    this.radius = radius;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * edu.bu.cs.cs480.surfaces.SurfaceObject#interceptWith(edu.bu.cs.cs480.Ray)
   */
  @Override
  public Intercept interceptWith(final Ray ray) {
    final Vector4D u = ray.direction().toVector4D();
    final Vector4D p = ray.position().toVector4D();

    final double a = u.dotProduct(this.matrix.product(u));
    final Vector4D temp = this.matrix.product(p);
    final double b = 2 * u.dotProduct(temp);
    final double c = p.dotProduct(temp);
    
    final Pair pair = QuadraticSolver.solve(a, b, c);
    if (pair == null) {
      return null;
    }
    final double time = Math.min(pair.left(), pair.right());

    return new Intercept(this, time);
  }

  /*
   * (non-Javadoc)
   * 
   * @see edu.bu.cs.cs480.surfaces.SurfaceObject#compile()
   */
  @Override
  public void compile() {
    this.matrix.set(3, 3, -(this.radius * this.radius));
  }

  private final Matrix4x4 matrix = new Matrix4x4();

  public String toString() {
    return "Sphere " + this.id();
  }
}
