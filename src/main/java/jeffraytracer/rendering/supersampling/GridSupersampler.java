/**
 * GridSupersampler.java - supersampling on a square grid
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

import jeffraytracer.Ray;
import jeffraytracer.camera.RayGenerator;

/**
 * Supersampling of rays using a square grid of fixed size.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class GridSupersampler implements Supersampler {
  /**
   * The size of the square grid of virtual subpixels to create for each pixel.
   */
  private final int gridSize;
  /** The height in pixels of the original viewport. */
  private final int height;
  /**
   * The object which generates rays through the virtual viewport corresponding
   * to the subpixel grid.
   */
  private RayGenerator rayGenerator = null;
  /** The width in pixels of the original viewport. */
  private final int width;

  /**
   * Instantiates this supersampler with the specified width and height of the
   * <em>original</em> viewport, and the size of the grid (that is, the length
   * of one side of the square) of subpixels to create for each original pixel.
   * 
   * @param viewportWidth
   *          The width in pixels of the original viewport.
   * @param viewportHeight
   *          The height in pixels of the original viewport.
   * @param gridSize
   *          The size of the grid of subpixels with which to replace each
   *          original pixel.
   */
  public GridSupersampler(final int viewportWidth, final int viewportHeight,
      final int gridSize) {
    this.gridSize = gridSize;
    this.width = viewportWidth;
    this.height = viewportHeight;
  }

  /**
   * Generates a grid of virtual rays for each original ray.
   * 
   * Pre-condition: the {@link #setRayGenerator(RayGenerator)} must be called
   * with a non-{@code null} parameter before this method can be called.
   * 
   * @return An array of blocks of rays, in which each block contains
   *         {@code (gridSize * gridSize)} rays.
   * @see jeffraytracer.rendering.supersampling.Supersampler#generateRays()
   */
  @Override
  public Ray[][] generateRays() {
    // initialize the array of blocks of rays
    final Ray[][] rays = new Ray[this.width * this.height][];
    for (int i = 0; i < rays.length; ++i) {
      rays[i] = new Ray[this.gridSize * this.gridSize];
    }
    // Generate the rays in each block. Each block has gridSize * gridSize
    // superpixels. For example, if gridSize == 3, then the blocks looks like:
    //
    // ...pixel 0.......pixel 1......
    // +---+---+---+ +---+---+---+
    // | 0 | 1 | 2 | | 0 | 1 | 2 |
    // +---+---+---+ +---+---+---+
    // | 3 | 4 | 5 | | 3 | 4 | 5 | ...
    // +---+---+---+ +---+---+---+
    // | 6 | 7 | 8 | | 6 | 7 | 8 |
    // +---+---+---+ +---+---+---+
    // .pixel width...pixel width+1...
    // +---+---+---+ +---+---+---+
    // | 0 | 1 | 2 | | 0 | 1 | 2 |
    // +---+---+---+ +---+---+---+
    // | 3 | 4 | 5 | | 3 | 4 | 5 | ...
    // +---+---+---+ +---+---+---+
    // | 6 | 7 | 8 | | 6 | 7 | 8 |
    // +---+---+---+ +---+---+---+
    //
    // The outer two loops compute the block number of the original pixel in
    // the array, and the inner two loops compute the column and row offset due
    // to the virtual superpixel blocks.
    for (int y = 0; y < this.height; ++y) {
      final int rowOffset = y * this.width;
      final int row = y * this.gridSize;
      for (int x = 0; x < this.width; ++x) {
        final int blockNum = rowOffset + x;
        final int column = x * this.gridSize;
        for (int yy = 0; yy < this.gridSize; ++yy) {
          final int gridRowOffset = yy * this.gridSize;
          for (int xx = 0; xx < this.gridSize; ++xx) {
            final int gridPixel = gridRowOffset + xx;
            rays[blockNum][gridPixel] = this.rayGenerator.generateRay(
                row + yy, column + xx);
          }
        }
      }
    }
    return rays;
  }

  /**
   * The object which generates rays which extend from a center of projection
   * through the pixels of the virtual viewport (that is, the viewport with
   * dimensions {@code (gridSize * viewportWidth) * (gridSize *
   * viewportHeight)}).
   * 
   * The dimensions and resolution of the viewport property of the ray
   * generator must match the dimensions specified from the given viewport
   * width, viewport height, and grid size in the constructor of this class.
   * 
   * This method must be called before the {@link #generateRays()} method can
   * be called.
   * 
   * @param rayGenerator
   *          The object which generates rays from a center of projection
   */
  public void setRayGenerator(final RayGenerator rayGenerator) {
    this.rayGenerator = rayGenerator;
  }
}
