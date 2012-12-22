/**
 * FlatGridAverager.java - unweighted averager for pixels values in a grid
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

/**
 * Averages pixel values from a grid using unweighted averaging.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class FlatGridAverager extends WeightedGridAverager {

  /**
   * {@inheritDoc}
   * 
   * @param gridSize
   *          {@inheritDoc}
   */
  @Override
  public void setGridSize(final int gridSize) {
    super.setGridSize(gridSize);
    final int gridSizeSquared = gridSize * gridSize;
    final double[] weights = new double[gridSizeSquared];
    for (int i = 0; i < gridSizeSquared; ++i) {
      weights[i] = 1;
    }
    super.setWeights(weights);
  }

  /**
   * This method is unsupported for this class.
   * 
   * The weights are always all uniform.
   * 
   * @param weights
   *          This parameter is ignored.
   * @throws UnsupportedOperationException
   *           Always throws this exception.
   */
  @Override
  public void setWeights(final double[] weights) {
    throw new UnsupportedOperationException("Weights are all uniform.");
  }
}
