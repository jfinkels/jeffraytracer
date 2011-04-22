/**
 * Sphere.java - a sphere surface object
 */
package edu.bu.cs.cs480.surfaces;

import edu.bu.cs.cs480.Matrix4x4;

/**
 * A sphere surface object.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Sphere extends SimpleQuadricForm {
  /** The radius of this sphere. */
  private double radius = 0;

  public Sphere() {
    this.setMatrix(Matrix4x4.identity());
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
   * @see edu.bu.cs.cs480.surfaces.SurfaceObject#compile()
   */
  @Override
  public void compile() {
    // Need to account for translation to the center of the sphere. To do this
    // we apply the inverse transformation to the points in the quadratic
    // equation. To save time, we compile this inverse transformation on the
    // vector into the quadric form matrix of this sphere.

    // first we need the inverse of the matrix representing the translation to
    // the center of the sphere
    final Matrix4x4 inverse = Matrix4x4.identity();
    inverse.set(0, 3, -this.position().x());
    inverse.set(1, 3, -this.position().y());
    inverse.set(2, 3, -this.position().z());

    // next we need its transpose
    final Matrix4x4 transpose = inverse.transposed();

    // now we set up the quadric form
    this.matrix().set(3, 3, -(this.radius * this.radius));

    // finally we multiply them all together
    this.setMatrix(transpose.product(this.matrix().product(inverse)));
  }

  @Override
  public String toString() {
    return "Sphere " + this.id();
  }
}
