/**
 * 
 */
package edu.bu.cs.cs480.surfaces;

import edu.bu.cs.cs480.Intercept;
import edu.bu.cs.cs480.Matrix4x4;
import edu.bu.cs.cs480.Pair;
import edu.bu.cs.cs480.QuadraticSolver;
import edu.bu.cs.cs480.Ray;
import edu.bu.cs.cs480.Vector4D;

/**
 * @author jeff
 *
 */
public abstract class SimpleQuadricForm extends ConcreteSurfaceObject {

  private final Matrix4x4 matrix = new Matrix4x4();
  protected Matrix4x4 matrix() {
    return this.matrix;
  }
  /*
   * (non-Javadoc)
   * 
   * @see
   * edu.bu.cs.cs480.surfaces.SurfaceObject#interceptWith(edu.bu.cs.cs480.Ray)
   */
  @Override
  public Intercept interceptWith(final Ray ray) {
    final Vector4D u = new Vector4D(ray.direction(), 0);
    final Vector4D p = new Vector4D(ray.position().difference(this.position()), 1);

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


}
