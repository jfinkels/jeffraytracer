/**
 * PositionableTracerObject.java - an object in the scene which has a position
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
 * An object in a tracer scene which has a position.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class PositionableTracerObject extends TracerObject implements
    Positionable {
  /** The position of this object. */
  private Vector3D position = null;

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
