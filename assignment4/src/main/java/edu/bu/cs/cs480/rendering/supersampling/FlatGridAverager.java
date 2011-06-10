/**
 * FlatGridAverager.java - unweighted averager for pixels values in a grid
 */
package edu.bu.cs.cs480.rendering.supersampling;

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
