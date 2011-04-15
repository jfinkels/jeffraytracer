/**
 * Sphere.java - a sphere surface object
 */
package edu.bu.cs.cs480.surfaces;

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
    for (int i = 0; i < 3; ++i) {
      this.matrix().set(i, i, 1);
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
   * @see edu.bu.cs.cs480.surfaces.SurfaceObject#compile()
   */
  @Override
  public void compile() {
    this.matrix().set(3, 3, -(this.radius * this.radius));
  }

  @Override
  public String toString() {
    return "Sphere " + this.id();
  }
}
