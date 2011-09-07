/**
 * Orientation.java - a three dimensional vector basis defining an orientation
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
package jeffraytracer.surfaces;

import jeffraytracer.Immutable;
import jeffraytracer.Vector3D;

/**
 * A three dimensional vector basis defining an orientation.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
@Immutable
public class Orientation {
  /** The standard orthonormal Euclidean basis in three dimensions. */
  public static final Orientation STANDARD_BASIS = new Orientation(
      new Vector3D(1, 0, 0), new Vector3D(0, 1, 0), new Vector3D(0, 0, 1));

  /** The u vector. */
  private final Vector3D u;
  /** The v vector. */
  private final Vector3D v;
  /** The w vector. */
  private final Vector3D w;

  /**
   * Instantiates this orientation with the three specified vectors as a basis.
   * 
   * Pre-condition: the vectors are unit vectors.
   * 
   * @param u
   *          The u vector.
   * @param v
   *          The v vector.
   * @param w
   *          The w vector.
   */
  public Orientation(final Vector3D u, final Vector3D v, final Vector3D w) {
    this.u = u;
    this.v = v;
    this.w = w;
  }

  /**
   * The u vector of this orientation.
   * 
   * @return The u vector of this orientation.
   */
  public Vector3D u() {
    return this.u;
  }

  /**
   * The v vector of this orientation.
   * 
   * @return The v vector of this orientation.
   */
  public Vector3D v() {
    return this.v;
  }

  /**
   * The w vector of this orientation.
   * 
   * @return The w vector of this orientation.
   */
  public Vector3D w() {
    return this.w;
  }
}
