/**
 * Viewport.java - the viewport on which the scene is displayed
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

/**
 * The viewport on which the scene is displayed.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Viewport {
  /** The height of the viewport in pixels. */
  private int height = 0;

  /** The width of the viewport in pixels. */
  private int width = 0;

  /**
   * Gets the height of the viewport in pixels.
   * 
   * @return The height of the viewport in pixels.
   */
  public int height() {
    return this.height;
  }

  /**
   * Sets the height of the viewport in pixels.
   * 
   * @param height
   *          The height of the viewport in pixels.
   */
  public void setHeight(final int height) {
    this.height = height;
  }

  /**
   * Sets the width of the viewport in pixels.
   * 
   * @param width
   *          The width of the viewport in pixels.
   */
  public void setWidth(final int width) {
    this.width = width;
  }

  /**
   * Gets the width of the viewport in pixels.
   * 
   * @return The width of the viewport in pixels.
   */
  public int width() {
    return this.width;
  }
}
