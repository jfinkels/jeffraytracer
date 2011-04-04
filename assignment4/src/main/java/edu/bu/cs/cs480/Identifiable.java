/**
 * Identifiable.java - an object which has a unique identifying integer
 */
package edu.bu.cs.cs480;

/**
 * An object which has a unique identifying integer.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public interface Identifiable {
  /**
   * Gets the unique identifying integer of this object.
   * 
   * @return The unique identifying integer of this object.
   */
  int id();
  void setId(final int id);
}
