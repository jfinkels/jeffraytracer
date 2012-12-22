/**
 * SimpleAverager.java - unweighted averager for a collection of pixels values
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

import java.util.Arrays;

import jeffraytracer.Vector3D;

/**
 * Averages pixel values from a collection using unweighted averaging.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class SimpleAverager implements Averager {

  /**
   * The number of pixels in a block to be averaged.
   */
  private int blockSize = 1;

  /**
   * Sets the size of blocks of pixels to consider for averaging in the
   * {@link #average(Vector3D[])} method.
   * 
   * @param blockSize
   *          A positive integer representing the number of pixels to consider
   *          for averaging.
   */
  public void setBlockSize(final int blockSize) {
    this.blockSize = blockSize;
  }

  /**
   * Computes the average of successive blocks of pixels, according to
   * {@link #blockSize}.
   * 
   * The returned array is smaller than the input array by a factor of a
   * {@link #blockSize} because successive blocks of that size are averaged.
   * 
   * @param pixels
   *          {@inheritDoc}
   * @return {@inheritDoc}
   * @see jeffraytracer.rendering.averagers.Averager#average(jeffraytracer.Vector3D[])
   */
  @Override
  public Vector3D[] average(final Vector3D[] pixels) {
    final Vector3D[] result = new Vector3D[pixels.length / this.blockSize];
    for (int i = 0; i < result.length; ++i) {
      final int start = i * this.blockSize;
      final int end = start + this.blockSize;
      final Vector3D[] slice = Arrays.copyOfRange(pixels, start, end);
      result[i] = Vector3D.sum(slice).scaledBy(1.0 / this.blockSize);
    }
    return result;
  }
}
