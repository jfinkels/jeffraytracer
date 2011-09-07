/**
 * GridAverager.java - averages pixel values arranged in a grid
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
package jeffraytracer.rendering.supersampling;

/**
 * Averages pixel color values arranged in a grid.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public abstract class GridAverager implements Averager {
  /**
   * The size of the grid (that is, the length of one side of the square) in
   * number of pixels.
   */
  private int gridSize;

  /**
   * Gets the size of the grid (that is, the length of one side of the square)
   * in number of pixels.
   * 
   * @return The number of pixels along one side of the square grid.
   */
  protected int gridSize() {
    return this.gridSize;
  }

  /**
   * Sets the size of the grid (that is, the length of one side of the square)
   * in number of pixels.
   * 
   * @param gridSize
   *          The number of pixels along one side of the square grid.
   */
  public void setGridSize(final int gridSize) {
    this.gridSize = gridSize;
  }

}
