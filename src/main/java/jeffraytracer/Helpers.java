/**
 * Helpers.java - generic helper methods
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
 * Generic helper methods.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 */
public final class Helpers {
  /**
   * Flattens a two-dimensional array into a one-dimensional array in row-major
   * order.
   * 
   * Pre-condition: the input array has size greater than zero.
   * 
   * Pre-condition: the input array is rectangular (that is, each row has the
   * same number of elements).
   * 
   * @param array
   *          The two-dimensional array to flatten.
   * @return A new one-dimensional array with the same elements of the
   *         two-dimensional array in row-major order.
   */
  @SuppressWarnings("unchecked")
  public static final <T> T[] flattened(final T[][] array) {
    // here we assume the input array is rectangular and has size > 0
    final T[] result = (T[]) new Object[array.length * array[0].length];
    int j = 0;
    for (int i = 0; i < array.length; ++i) {
      final int length = array[i].length;
      System.arraycopy(array[i], 0, result, j, length);
      j += length;
    }
    return result;
  }
}
