/**
 * Ray.java - a ray originating from a point and extending in a direction
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
 * A "half line", starting at a point and extending to infinity in a direction.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Ray extends PositionedDirected {
  /**
   * Gets the human-readable string representation of this ray.
   * 
   * @return The human-readable string representation of this ray.
   */
  @Override
  public String toString() {
    return "Ray at " + this.position() + " directed by " + this.direction();
  }
}
