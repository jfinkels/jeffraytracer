/**
 * WeightedGridAverager.java - weighted averager for pixel values in a grid
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
package jeffraytracer.rendering.averagers;

import jeffraytracer.Vector3D;

/**
 * Weighted averager for pixel values in a grid.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class WeightedGridAverager extends GridAverager {

  /**
   * The weights of each subpixel in a {@code gridSize * gridSize} grid given
   * in row major order.
   */
  private double[] weights;

  /**
   * The precomputed sum of the weights specified in the
   * {@link #setWeights(double[])} method.
   */
  private double weightSum;

  /**
   * Averages the specified grid of pixel values using weights for each subgrid
   * specified in the {@link #setWeights(double[])} method.
   * 
   * The returned array will be a one dimensional array representing a two
   * dimensional array in row major order.
   * 
   * The pixels parameter is a one-dimensional representing a four dimensional
   * array. If the array of superpixels originally looked like
   * 
   * <pre>
   * {          [pixel 0]               [pixel 1]
   *         {sp0, sp1, sp2},        {sp0, sp1, sp2},
   *   {  {  {sp3, sp4, sp5},  }, {  {sp3, sp4, sp5},  }, ... },
   *         {sp6, sp7, sp8}         {sp6, sp7, sp8}
   *   {  ... },
   *   ...
   *   
   * }
   * </pre>
   * 
   * where {@code sp0} is subpixel 0 in each pixel, then the one dimensional
   * representation should look like {@code sp0, sp1, sp2, ..., sp8, sp0, sp1,
   * ...}.
   * 
   * Algorithm: For each consecutive substring of {@code gridSize * gridSize}
   * pixel color values in the *pixels* array, compute the weighted average and
   * assign that to the corresponding original pixel number in the output
   * array.
   * 
   * Pre-condition: each of the colors has coordinates between 0 and 1 (thus
   * the average must be between 0 and 1).
   * 
   * Pre-condition: the {@link #setWeights(double[])} method must be called
   * before this method is called.
   * 
   * @param pixels
   *          The color values of the subpixels as described in the
   *          documentation for this method.
   * @return The weighted average of each grid of subpixels.
   * @see jeffraytracer.rendering.averagers.Averager#average(Vector3D[])
   */
  @Override
  public Vector3D[] average(final Vector3D[] pixels) {
    final int gridSize = this.gridSize();
    final int gridSizeSquared = gridSize * gridSize;
    final Vector3D[] result = new Vector3D[pixels.length / gridSizeSquared];

    // for each original pixel, compute the weighted average of the superpixels
    for (int i = 0; i < result.length; ++i) {
      Vector3D sum = Vector3D.ORIGIN;
      final int pixelOffset = i * gridSizeSquared;
      for (int j = 0; j < gridSizeSquared; ++j) {
        sum = sum.sumWith(pixels[pixelOffset + j].scaledBy(this.weights[j]));
      }
      final Vector3D average = sum.scaledBy(1.0 / this.weightSum);
      result[i] = average;
    }

    return result;
  }

  /**
   * Sets the weights for each position in the grid, given in row-major order
   * in a one-dimensional array.
   * 
   * Pre-condition: the length of the weights array must equal the square of (
   * {@link #gridSize()}.
   * 
   * @param weights
   *          The one-dimensional array in row-major order representing the
   *          weights of the positions in the grid.
   */
  public void setWeights(final double[] weights) {
    this.weights = weights.clone();
    this.weightSum = 0;
    for (int i = 0; i < this.weights.length; ++i) {
      this.weightSum += this.weights[i];
    }
  }
}
