/**
 * Resolution.java - the resolution of the viewport
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
 * The resolution of the viewport.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Resolution {
  /** The width of a pixel in world coordinates. */
  private double xResolution = 1;
  /** The height of a pixel in world coordinates. */
  private double yResolution = 1;

  /**
   * Sets the width of a pixel in world coordinates.
   * 
   * @param xResolution
   *          The width of a pixel in world coordinates.
   */
  public void setXResolution(final double xResolution) {
    this.xResolution = xResolution;
  }

  /**
   * Sets the height of a pixel in world coordinates.
   * 
   * @param yResolution
   *          The height of a pixel in world coordinates.
   */
  public void setYResolution(final double yResolution) {
    this.yResolution = yResolution;
  }

  /**
   * Gets the width of a pixel in world coordinates.
   * 
   * @return The width of a pixel in world coordinates.
   */
  public double xResolution() {
    return this.xResolution;
  }

  /**
   * Gets the height of a pixel in world coordinates.
   * 
   * @return The height of a pixel in world coordinates.
   */
  public double yResolution() {
    return this.yResolution;
  }
}
