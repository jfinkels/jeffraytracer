/**
 * Directed.java - an object which has a direction
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
package jeffraytracer;

/**
 * An object which has a direction.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public interface Directed {
  /**
   * Gets the direction of this object as a unit vector.
   * 
   * @return The direction of this object.
   */
  Vector3D direction();

  /**
   * Sets the direction of this object.
   * 
   * Pre-condition: the direction vector is a unit vector.
   * 
   * @param direction
   *          The direction of this object.
   */
  void setDirection(final Vector3D direction);
}
