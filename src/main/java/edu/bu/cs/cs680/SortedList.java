/**
 * SortedList.java
 */
package edu.bu.cs.cs680;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * The worst possible implementation of a sorted list--it simply calls
 * {@link Collections#sort(java.util.List)} on every add.
 * 
 * @author Jeffrey Finkelstein <jeffreyf>
 */
public class SortedList<E> extends ArrayList<E> {

  /** Randomly generated serial version UID. */
  private static final long serialVersionUID = 2843585292891006812L;

  /** The comparator to use when sorting this list. */
  private final Comparator<E> comparator;

  /**
   * Instantiates this list with the specified Comparator to use for sorting the
   * list on each add operation.
   * 
   * @param comparator
   *          The comparator to use when sorting this list.
   */
  public SortedList(final Comparator<E> comparator) {
    this.comparator = comparator;
  }

  /**
   * Calls the corresponding method in the superclass, then sorts the list.
   * 
   * @param e
   *          {@inheritDoc}
   * @return {@inheritDoc}
   */
  @Override
  public boolean add(final E e) {
    if (super.add(e)) {
      Collections.sort(this, this.comparator);
      return true;
    }
    return false;
  }
}
