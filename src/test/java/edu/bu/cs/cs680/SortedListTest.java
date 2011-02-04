/**
 * SortedListTest.java
 */
package edu.bu.cs.cs680;

import static org.junit.Assert.assertTrue;

import java.util.Comparator;
import java.util.List;

import org.junit.Test;

/**
 * Test class for the SortedList class.
 * 
 * @author Jeffrey Finkelstein <jeffreyf>
 */
public class SortedListTest {

  /** The number of elements in the list to test. */
  public static final int NUM_ELEMENTS = 100;
  /** The range of numbers to be added to the list to test. */
  public static final int RANGE = 100;

  /**
   * Returns true if and only if the specified list is sorted according to the
   * order implied by the specified comparator.
   * 
   * @param <E>
   *          The type of element in the list.
   * @param list
   *          The list to check for sorted-ness
   * @param comparator
   *          The comparator to use to check whether elements of the specified
   *          list are in order.
   * @return {@code true} if and only if the specified list is sorted according
   *         to the order implied by the specified comparator.
   */
  private static <E> boolean isSorted(final List<E> list,
      final Comparator<E> comparator) {
    for (int i = 0; i < list.size() - 1; ++i) {
      if (comparator.compare(list.get(i), list.get(i + 1)) > 0) {
        return false;
      }
    }
    return true;
  }

  /** The natural ordering of integers. */
  private final Comparator<Integer> naturalOrder = new Comparator<Integer>() {
    @Override
    public int compare(final Integer o1, final Integer o2) {
      return o1 - o2;
    }
  };

  /**
   * Test method for {@link edu.bu.cs.cs680.SortedList#add(java.lang.Object)}.
   */
  @Test
  public void testAdd() {
    final SortedList<Integer> list = new SortedList<Integer>(this.naturalOrder);
    for (int i = 0; i < NUM_ELEMENTS; ++i) {
      list.add((int) (Math.random() * RANGE));
    }
    assertTrue(isSorted(list, this.naturalOrder));
  }

  /**
   * Test method for
   * {@link edu.bu.cs.cs680.SortedList#SortedList(java.util.Comparator)}.
   */
  @Test
  public void testSortedList() {
    new SortedList<Integer>(this.naturalOrder);
  }
}
