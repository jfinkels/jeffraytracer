/**
 * TracerObject.java - an object with a unique identifying integer
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
 * An object with a unique identifying integer.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class TracerObject implements Identifiable {
  /** The unique identifying integer of this object. */
  private int id = -1;

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jeffraytracer.Identifiable#id()
   */
  @Override
  public int id() {
    return this.id;
  }

  /**
   * {@inheritDoc}
   * 
   * @param id
   *          {@inheritDoc}
   * @see jeffraytracer.Identifiable#setId(int)
   */
  @Override
  public void setId(final int id) {
    this.id = id;
  }

}
