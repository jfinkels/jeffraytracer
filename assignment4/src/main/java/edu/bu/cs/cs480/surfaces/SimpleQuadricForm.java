/**
 * SimpleQuadricForm.java - a surface object described by a quadric matrix
 */
package edu.bu.cs.cs480.surfaces;

import edu.bu.cs.cs480.Intercept;
import edu.bu.cs.cs480.Matrix4x4;
import edu.bu.cs.cs480.Pair;
import edu.bu.cs.cs480.QuadraticSolver;
import edu.bu.cs.cs480.Ray;
import edu.bu.cs.cs480.Vector3D;
import edu.bu.cs.cs480.Vector4D;

/**
 * A surface object which can be described by a quadric matrix.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public abstract class SimpleQuadricForm extends ConcreteSurfaceObject {
  /** The tolerance for testing equality of double values. */
  public static final double TOLERANCE = Double.MIN_VALUE;

  /**
   * The matrix assigned by the {@link #compile()} method, which represents the
   * quadric form of this surface object.
   */
  private Matrix4x4 matrix = null;

  /**
   * The matrix of the quadric form of this surface object centered at the
   * origin and with no rotation.
   * 
   * @return The matrix of the quadric form of this surface object centered at
   *         the origin and with no rotation.
   */
  protected abstract Matrix4x4 baseMatrix();

  /**
   * Compiles the matrix which represents the quadric form of this surface
   * object with respect to its base matrix representation, its rotation
   * matrix, and its translation matrix.
   * 
   * This method MUST be called before calling the {@link #interceptWith(Ray)}
   * method.
   * 
   * This method must only be called AFTER the
   * {@link #setPosition(edu.bu.cs.cs480.Vector3D)} method has been called, in
   * order to create the translation matrix.
   * 
   * This method will overwrite the contents of the {@link #matrix} field.
   * 
   * Algorithm: To account for the translation and rotation of this object, we
   * save time by compiling it into {@link #matrix} field. Let L be the
   * translation matrix from the origin to the position of this object. Let R
   * be the rotation matrix from the standard Euclidean orthonormal basis to
   * the orthonormal basis of this object, and let Q be the quadric form matrix
   * of this object. This method compiles and stores Q' =
   * ((L^-1)^T)((R^-1)^T)Q(R^-1)(L^-1) in the {@link #matrix} field.
   */
  @Override
  public void compile() {
    // first we need the inverse of the matrix representing the translation to
    // the center of the sphere
    final Matrix4x4 inverseTranslation = this.inverseTranslationMatrix();

    // next we need its transpose
    final Matrix4x4 translationTranspose = inverseTranslation.transposed();

    // now we need the inverse of the rotation matrix (the inverse of a
    // homogeneous matrix with an orthonormal basis as its columns is just the
    // transpose) and its transpose (which is just the original)
    final Matrix4x4 rotationTranspose = this.rotation();
    final Matrix4x4 inverseRotation = rotationTranspose.transposed();

    // finally we multiply them all together, from right to left
    this.matrix = inverseRotation.product(inverseTranslation);
    this.matrix = this.baseMatrix().product(this.matrix);
    this.matrix = rotationTranspose.product(this.matrix);
    this.matrix = translationTranspose.product(this.matrix);
  }

  /**
   * {@inheritDoc}
   * 
   * @param point
   *          {@inheritDoc}
   * @return {@inheritDoc}
   */
  @Override
  public boolean inside(final Vector3D point) {
    final Vector4D homogeneous = new Vector4D(point, 1);
    return homogeneous.dotProduct(this.matrix.product(homogeneous)) < -TOLERANCE;
  }

  /**
   * {@inheritDoc}
   * 
   * @param ray
   *          {@inheritDoc}
   * @return {@inheritDoc}
   * @see edu.bu.cs.cs480.surfaces.SurfaceObject#interceptWith(edu.bu.cs.cs480.Ray)
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

    return new Intercept(ray, time, this);
  }

  /**
   * Returns a new matrix representing the inverse of the translation to the
   * center of this object as specified by the {@link #position()} method.
   * 
   * @return The inverse of the translation matrix to the position of this
   *         object.
   */
  private Matrix4x4 inverseTranslationMatrix() {
    final Matrix4x4 result = Matrix4x4.identity();
    result.set(0, 3, -this.position().x());
    result.set(1, 3, -this.position().y());
    result.set(2, 3, -this.position().z());
    return result;
  }

  /**
   * Gets the matrix which represents the quadric form of this surface object.
   * 
   * This method returns {@code null} unless the {@link #compile()} method has
   * been called.
   * 
   * @return The matrix which represents the quadric form of this surface
   *         object.
   */
  protected Matrix4x4 matrix() {
    return this.matrix;
  }

  /**
   * {@inheritDoc}
   * 
   * @param point
   *          {@inheritDoc}
   * @return {@inheritDoc}
   */
  @Override
  public boolean outside(final Vector3D point) {
    final Vector4D homogeneous = new Vector4D(point, 1);
    return homogeneous.dotProduct(this.matrix.product(homogeneous)) > TOLERANCE;
  }

  /**
   * The matrix representing the rotation which is applied to this surface
   * object.
   * 
   * @return The matrix representing the rotation which is applied to this
   *         surface object.
   */
  protected abstract Matrix4x4 rotation();
}
