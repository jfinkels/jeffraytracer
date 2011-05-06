/**
 * TimeComparator.java - compares the times of two intercepts
 */
package edu.bu.cs.cs480.surfaces;

import java.util.Comparator;

/**
 * Compares the times of two intercepts.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class TimeComparator implements Comparator<Intercept> {
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
