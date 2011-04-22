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

  private Matrix4x4 matrix = null;
protected Matrix4x4 matrix() { return this.matrix; }
  /*
   * (non-Javadoc)
   * 
   * @see
   * edu.bu.cs.cs480.surfaces.SurfaceObject#interceptWith(edu.bu.cs.cs480.Ray)
   */
  @Override
  public Intercept interceptWith(final Ray ray) {
    final Vector4D u = new Vector4D(ray.direction(), 0);
    final Vector4D p = new Vector4D(ray.position(), 1);

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

  /**
   * The matrix representing the rotation which is applied to this surface
   * object.
   * 
   * @return The matrix representing the rotation which is applied to this
   *         surface object.
   */
  protected abstract Matrix4x4 rotation();

  @Override
  public void compile() {
    // Need to account for translation to the center of the object and
    // rotation. To do this we apply the inverse transformation to the points
    // in the quadratic equation. To save time, we compile this inverse
    // transformation on the vector into the quadric form matrix of this
    // object.

    // first we need the inverse of the matrix representing the translation to
    // the center of the sphere
    final Matrix4x4 inverseTranslation = Matrix4x4.identity();
    inverseTranslation.set(0, 3, -this.position().x());
    inverseTranslation.set(1, 3, -this.position().y());
    inverseTranslation.set(2, 3, -this.position().z());

    // next we need its transpose
    final Matrix4x4 translationTranspose = inverseTranslation.transposed();

    // now we need the inverse of the rotation matrix (the inverse of a
    // homogeneous matrix with an orthonormal basis as its columns is just the
    // transpose) and its transpose (which is just the original)
    final Matrix4x4 rotationTranspose = this.rotation();
    final Matrix4x4 inverseRotation = rotationTranspose.transposed();

    // finally we multiply them all together
    final Matrix4x4 left = rotationTranspose.product(translationTranspose);
    final Matrix4x4 right = inverseTranslation.product(inverseRotation);
    this.matrix = left.product(this.baseMatrix().product(right));
  }

  /**
   * The matrix of the quadric form of this surface object centered at the
   * origin and with no rotation.
   * 
   * @return The matrix of the quadric form of this surface object centered at
   *         the origin and with no rotation.
   */
  protected abstract Matrix4x4 baseMatrix();
}
