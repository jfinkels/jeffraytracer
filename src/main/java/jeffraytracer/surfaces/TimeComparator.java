/**
 * TimeComparator.java - compares the times of two intercepts
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
package jeffraytracer.surfaces;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Compares the times of two intercepts.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class TimeComparator implements Comparator<Intercept>, Serializable {
  /** Generated serial version unique ID. */
  private static final long serialVersionUID = -7839457362000205223L;
  /** The singleton instance of this comparator. */
  public static final TimeComparator INSTANCE = new TimeComparator();

  /**
   * Compares the times of the two intercepts.
   * 
   * @param intercept1
   *          An intercept.
   * @param intercept2
   *          An intercept.
   * @return The result of comparing the intercept times of the two intercepts.
   * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
   */
  @Override
  public int compare(final Intercept intercept1, final Intercept intercept2) {
    return Double.compare(intercept1.time(), intercept2.time());
  }
}
