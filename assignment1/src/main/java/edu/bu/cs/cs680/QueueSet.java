/**
 * QueueSet.java
 */
package edu.bu.cs.cs680;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * A priority queue that stores only unique objects.
 * 
 * @author Jeffrey Finkelstein <jeffreyf>
 */
public class QueueSet<E> extends TreeSet<E> {
  /** Randomly generated serial version UID. */
  private static final long serialVersionUID = -3625498784895557520L;

  /**
   * Instantiates this set by calling the corresponding constructor of the
   * superclass.
   * 
   * @param instance
   *          The comparator by which to order elements of this set.
   */
  public QueueSet(final Comparator<E> comparator) {
    super(comparator);
  }

  /**
   * Returns the first element from this set and removes it.
   * 
   * @return The first element from this set.
   */
  // TODO test for this method
  public E pop() {
    final E result = this.first();
    this.remove(result);
    return result;
  }
}
