/**
 * RayGenerator.java - generates rays at a point with respect to a camera
 * 
 * Copyright 2011 Jeffrey Finkelstein
 * 
 * This file is part of jeffraytracer.
 * 
 * jeffraytracer is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 * 
 * jeffraytracer is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * jeffraytracer. If not, see <http://www.gnu.org/licenses/>.
 */
package jeffraytracer.camera;

import jeffraytracer.Ray;
import jeffraytracer.Vector3D;

/**
 * Generates rays at a specified position with respect to a given camera,
 * viewport, and resolution.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class RayGenerator extends ViewingSystemSupport {

  /** Instantiates this class without setting any properties. */
  public RayGenerator() {
    // intentionally unimplemented
  }

  /**
   * Instantiates this ray generator with the specified camera, viewport, and
   * resolution.
   * 
   * @param camera
   *          The camera from which rays originate.
   * @param resolution
   *          The resolution of the viewport in pixels with respect to the
   *          model coordinate system.
   * @param viewport
   *          The viewport through which rays pass.
   */
  public RayGenerator(final Camera camera, final Resolution resolution,
      final Viewport viewport) {
    this.setCamera(camera);
    this.setResolution(resolution);
    this.setViewport(viewport);
  }

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
    final double du = (column - (this.viewport().width() / 2.0) + 1)
        * this.resolution().xResolution();
    final double dv = -(row - (this.viewport().height() / 2.0) + 1)
        * this.resolution().yResolution();

    // get the vectors which define the camera's basis
    final Vector3D c = this.camera().position();
    final Vector3D n = this.camera().direction();
    final Vector3D v = this.camera().up();
    final Vector3D u = v.crossProduct(n);

    // convert (du, dv) to location in scene coordinates using the camera's
    // basis vectors
    final Vector3D temp1 = n.scaledBy(this.camera().focalLength());
    final Vector3D temp2 = u.scaledBy(du);
    final Vector3D temp3 = v.scaledBy(dv);
    final Vector3D origin = c.sumWith(temp1).sumWith(temp2).sumWith(temp3);

    // compute the direction of the ray with respect to the camera and position
    final Vector3D direction = this.camera().rayDirection(origin);

    final Ray result = new Ray();
    result.setPosition(origin);
    result.setDirection(direction);

    return result;
  }

}
