/**
 * LensCamera.java - a perspective camera with a rectangular lens
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

import jeffraytracer.Vector3D;

/**
 * A perspective camera with a rectangular lens of arbitrary size.
 * 
 * The lens allows depth of field effects in the rendered scene. The width and
 * height of the lens determine the number of rays which will be generated for
 * a single pixel in the viewport.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 */
public class LensCamera extends PerspectiveCamera {
  /**
   * Flattens a two-dimensional array into a one-dimensional array in row-major
   * order.
   * 
   * Pre-condition: the input array has size greater than zero.
   * 
   * Pre-condition: the input array is rectangular (that is, each row has the
   * same number of elements).
   * 
   * @param array
   *          The two-dimensional array to flatten.
   * @return A new one-dimensional array with the same elements of the
   *         two-dimensional array in row-major order.
   */
  public static final Vector3D[] flattened(final Vector3D[][] array) {
    // here we assume the input array is rectangular and has size > 0
    final Vector3D[] result = new Vector3D[array.length * array[0].length];
    int j = 0;
    for (int i = 0; i < array.length; ++i) {
      final int length = array[i].length;
      System.arraycopy(array[i], 0, result, j, length);
      j += length;
    }
    return result;
  }

  // TODO add camera resolution
  /**
   * The width of the rectangular lens.
   */
  private int lensWidth = 1;
  /**
   * The height of the rectangular lens.
   */
  private int lensHeight = 1;

  /**
   * {@inheritDoc}
   * 
   * @param position
   *          {@inheritDoc}
   * @see jeffraytracer.camera.Camera#rayDirection(jeffraytracer.Vector3D)
   */
  @Override
  public Vector3D[] rayDirections(final Vector3D position) {
    // Create a two-dimensional array to hold the ray for each subcamera on the
    // rectangular lens. It will be flattened when returned.
    final Vector3D[][] result = new Vector3D[this.lensWidth][this.lensHeight];
    for (int i = 0; i < this.lensHeight; ++i) {
      for (int j = 0; j < this.lensWidth; ++j) {
        // Create a pinhole perspective camera for each point on the lens.
        final Camera subcamera = new PerspectiveCamera();

        // Note: this code is very similar to the code from RayGenerator.
        //
        // Compute the location of the subcamera on the lens with respect to
        // the center of the lens (as specified by this.position()).
        final double du = j - (this.lensWidth / 2.0) + 1;
        final double dv = -(i - (this.lensHeight / 2.0) + 1);

        // Get the vectors which define the camera's basis.
        final Vector3D c = this.position();
        final Vector3D n = this.direction();
        final Vector3D v = this.up();
        final Vector3D u = v.crossProduct(n);

        // Compute the position of the subcamera.
        final Vector3D horiz = u.scaledBy(du);
        final Vector3D vert = v.scaledBy(dv);
        final Vector3D newPosition = Vector3D.sum(c, horiz, vert);

        // Set the position and direction of the subcamera.
        subcamera.setPosition(newPosition);
        subcamera.setDirection(this.direction());

        // Each subcamera is a perspective camera, which is guaranteed to
        // produce an array of length one when `rayDirection()` is called.
        result[i][j] = subcamera.rayDirections(position)[0];
      }
    }
    return flattened(result);
  }

  /**
   * Returns the size of the rectangular lens.
   * 
   * @return The size of the rectangular lens.
   * @see jeffraytracer.camera.Camera#raysPerPixel()
   */
  @Override
  public int raysPerPixel() {
    return this.lensHeight * this.lensWidth;
  }

  /**
   * Sets the height of the lens.
   * 
   * @param height
   *          The new height of the lens.
   */
  public void setLensHeight(final int height) {
    this.lensHeight = height;
  }

  /**
   * Sets the width of the lens.
   * 
   * @param width
   *          The new width of the lens.
   */
  public void setLensWidth(final int width) {
    this.lensWidth = width;
  }
}
