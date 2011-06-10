/**
 * RayGenerator.java - generates rays at a point with respect to a camera
 */
package edu.bu.cs.cs480.rendering;

import edu.bu.cs.cs480.Ray;
import edu.bu.cs.cs480.Vector3D;
import edu.bu.cs.cs480.camera.Camera;
import edu.bu.cs.cs480.camera.Resolution;
import edu.bu.cs.cs480.camera.Viewport;

/**
 * Generates rays at a specified position with respect to a given camera,
 * viewport, and resolution.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class RayGenerator {

  /** The virtual camera through which the scene is viewed. */
  private Camera camera = null;
  /** The resolution of the scene when displayed in the viewport. */
  private Resolution resolution = null;
  /** The dimensions of the viewport in which the scene is displayed. */
  private Viewport viewport = null;

  /**
   * Generates the ray which would start at pixel location (row, column) in the
   * viewport based on the resolution, the viewport size, and the camera's
   * measurements.
   * 
   * Algorithm adapted from source code of Zheng Wu.
   * 
   * @param row
   *          The vertical pixel location in the viewport at which the ray
   *          originates.
   * @param column
   *          The horizontal pixel location in the viewport at which the ray
   *          originates.
   * @return The ray which would start at pixel location (row, column) in the
   *         viewport.
   */
  public Ray generateRay(final int row, final int column) {
    // compute location of pixel on view plane
    final double du = (column - (this.viewport.width() / 2.0) + 1)
        * this.resolution.xResolution();
    final double dv = -(row - (this.viewport.height() / 2.0) + 1)
        * this.resolution.yResolution();

    // get the vectors which define the camera's basis
    final Vector3D c = this.camera.position();
    final Vector3D n = this.camera.direction();
    final Vector3D v = this.camera.up();
    final Vector3D u = v.crossProduct(n);

    // convert (du, dv) to location in scene coordinates using the camera's
    // basis vectors
    final Vector3D temp1 = n.scaledBy(this.camera.focalLength());
    final Vector3D temp2 = u.scaledBy(du);
    final Vector3D temp3 = v.scaledBy(dv);
    final Vector3D origin = c.sumWith(temp1).sumWith(temp2).sumWith(temp3);

    // compute the direction of the ray with respect to the camera and position
    final Vector3D direction = this.camera.rayDirection(origin);

    final Ray result = new Ray();
    result.setPosition(origin);
    result.setDirection(direction);

    return result;
  }

  /**
   * Sets the virtual camera through which the scene is viewed.
   * 
   * @param camera
   *          The virtual camera through which the scene is viewed.
   */
  public void setCamera(final Camera camera) {
    this.camera = camera;
  }

  /**
   * Sets the resolution of the scene when displayed in the viewport.
   * 
   * @param resolution
   *          The resolution of the scene when displayed in the viewport.
   */
  public void setResolution(final Resolution resolution) {
    this.resolution = resolution;
  }

  /**
   * Sets the dimensions of the viewport in which the scene is displayed.
   * 
   * @param viewport
   *          The dimensions of the viewport in which the scene is displayed.
   */
  public void setViewport(final Viewport viewport) {
    this.viewport = viewport;
  }
}
