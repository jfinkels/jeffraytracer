/**
 * PositionedDirected.java - an object with a position and a direction
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
 * An object with a position and a direction.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class PositionedDirected implements Positionable, Directed {

  /** The point at which this vector originates. */
  private Vector3D position = null;
  /** The direction in which this ray extends from its origin. */
  private Vector3D direction = null;

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jeffraytracer.Directed#direction()
   */
  @Override
  public Vector3D direction() {
    return this.direction;
  }

  /**
   * {@inheritDoc}
   * 
   * @param direction
   *          {@inheritDoc}
   * @see jeffraytracer.Directed#setDirection(jeffraytracer.Vector3D)
   */
  @Override
  public void setDirection(final Vector3D direction) {
    this.direction = direction;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jeffraytracer.Positionable#position()
   */
  @Override
  public Vector3D position() {
    return this.position;
  }

  /**
   * {@inheritDoc}
   * 
   * @param position
   *          {@inheritDoc}
   * @see jeffraytracer.Positionable#setPosition(jeffraytracer.Vector3D)
   */
  @Override
  public void setPosition(final Vector3D position) {
    this.position = position;
  }
}
