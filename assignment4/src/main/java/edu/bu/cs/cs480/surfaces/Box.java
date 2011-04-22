/**
 * Box.java - a rectangular prism
 */
package edu.bu.cs.cs480.surfaces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.bu.cs.cs480.Intercept;
import edu.bu.cs.cs480.Ray;
import edu.bu.cs.cs480.Vector3D;

/**
 * A rectangular prism.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Box extends ConcreteSurfaceObject {
  /** The dimensions of this box. */
  private Vector3D dimensions = null;
  /** The triple of vectors which define the orientation of this box. */
  private Orientation orientation = null;
  private Plane left = null;
  private Plane right = null;
  private Plane top = null;
  private Plane bottom = null;
  private Plane front = null;
  private Plane back = null;

  /**
   * Gets the dimensions of this box.
   * 
   * @return The dimensions of this box.
   */
  public Vector3D dimensions() {
    return this.dimensions;
  }

  /**
   * Gets the orientation of this box.
   * 
   * @return The orientation of this box.
   */
  public Orientation orientation() {
    return this.orientation;
  }

  /**
   * Sets the dimensions of this box.
   * 
   * @param dimensions
   *          The dimensions of this box.
   */
  public void setDimensions(final Vector3D dimensions) {
    this.dimensions = dimensions;
  }

  /**
   * Sets the orientation of this box.
   * 
   * @param orientation
   *          The orientation of this box.
   */
  public void setOrientation(final Orientation orientation) {
    this.orientation = orientation;
  }

  private static boolean isBetween(final Vector3D point, final Plane face1,
      final Plane face2, final Plane face3, final Plane face4) {
    return face1.pointIsBelow(point) && face2.pointIsBelow(point)
        && face3.pointIsBelow(point) && face4.pointIsBelow(point);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * edu.bu.cs.cs480.surfaces.SurfaceObject#interceptWith(edu.bu.cs.cs480.Ray)
   */
  @Override
  public Intercept interceptWith(final Ray ray) {
    final List<Intercept> possibleIntercepts = new ArrayList<Intercept>();
    Intercept intercept = this.front.interceptWith(ray);
    Vector3D pointOfIntersection = intercept.pointOfIntersection();
    if (isBetween(pointOfIntersection, this.left, this.right, this.top,
        this.bottom)) {
      possibleIntercepts.add(intercept);
    }

    intercept = this.back.interceptWith(ray);
    pointOfIntersection = intercept.pointOfIntersection();
    if (isBetween(pointOfIntersection, this.left, this.right, this.top,
        this.bottom)) {
      possibleIntercepts.add(intercept);
    }

    intercept = this.top.interceptWith(ray);
    pointOfIntersection = intercept.pointOfIntersection();
    if (isBetween(pointOfIntersection, this.left, this.right, this.front,
        this.back)) {
      possibleIntercepts.add(intercept);
    }

    intercept = this.bottom.interceptWith(ray);
    pointOfIntersection = intercept.pointOfIntersection();
    if (isBetween(pointOfIntersection, this.left, this.right, this.front,
        this.back)) {
      possibleIntercepts.add(intercept);
    }

    intercept = this.left.interceptWith(ray);
    pointOfIntersection = intercept.pointOfIntersection();
    if (isBetween(pointOfIntersection, this.top, this.bottom, this.front,
        this.back)) {
      possibleIntercepts.add(intercept);
    }

    intercept = this.right.interceptWith(ray);
    pointOfIntersection = intercept.pointOfIntersection();
    if (isBetween(pointOfIntersection, this.top, this.bottom, this.front,
        this.back)) {
      possibleIntercepts.add(intercept);
    }

    if (possibleIntercepts.isEmpty()) {
      return null;
    }

    return Collections.min(possibleIntercepts);
  }

  /*
   * (non-Javadoc)
   * 
   * @see edu.bu.cs.cs480.surfaces.SurfaceObject#compile()
   */
  @Override
  public void compile() {
    final Vector3D u = this.orientation.u();
    final Vector3D v = this.orientation.v();
    final Vector3D w = this.orientation.w();
    final double width = this.dimensions.x();
    final double height = this.dimensions.y();
    final double depth = this.dimensions.z();

    // the left and right faces
    this.right = forwardPlane(u, width);
    this.left = backwardPlane(u, width);

    // the top and bottom faces
    this.top = forwardPlane(v, height);
    this.bottom = backwardPlane(v, height);

    // the front and back faces
    // TODO are the front and back switched?
    this.front = forwardPlane(w, depth);
    this.back = backwardPlane(w, depth);
  }

  private Plane forwardPlane(final Vector3D normal, final double size) {
    final Vector3D pointOnPlane = this.position().sumWith(
        normal.scaledBy(size / 2));
    return new Plane(normal, -normal.dotProduct(pointOnPlane));
  }

  private Plane backwardPlane(final Vector3D normal, final double size) {
    final Vector3D pointOnPlane = this.position().sumWith(
        normal.scaledBy(-size / 2));
    return new Plane(normal.scaledBy(-1), normal.dotProduct(pointOnPlane));

  }
}
