/**
 * SortedList.java
 */
package edu.bu.cs.cs680;

import java.util.ArrayList;
import java.util.Collection;
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

  /**
   * The comparator to use when sorting this list.
   */
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

  /**
   * Calls the corresponding method in the superclass, then sorts the list.
   * 
   * @param index
   *          {@inheritDoc}
   * @param element
   *          {@inheritDoc}
   * @throws IndexOutOfBoundsException
   *           {@inheritDoc}
   */
  @Override
  public void add(final int index, final E element) {
    super.add(index, element);
    Collections.sort(this, this.comparator);
  }

  /**
   * {@inheritDoc}
   * 
   * @param c
   *          {@inheritDoc}
   * @return {@inheritDoc}
   * @throws NullPointerException
   *           {@inheritDoc}
   */
  @Override
  public boolean addAll(final Collection<? extends E> c) {
    if (super.addAll(c)) {
      Collections.sort(this, this.comparator);
      return true;
    }
    return false;
  }

  /**
   * {@inheritDoc}
   * 
   * @param index
   *          {@inheritDoc}
   * @param c
   *          {@inheritDoc}
   * @return {@inheritDoc}
   * @throws IndexOutOfBoundsException
   *           {@inheritDoc}
   * @throws NullPointerException
   *           {@inheritDoc}
   */
  @Override
  public boolean addAll(final int index, final Collection<? extends E> c) {
    if (super.addAll(index, c)) {
      Collections.sort(this, this.comparator);
      return true;
    }
    return false;
  }
}
