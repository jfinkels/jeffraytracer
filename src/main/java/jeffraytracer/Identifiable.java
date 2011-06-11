/**
 * Identifiable.java - an object which has a unique identifying integer
 */
package jeffraytracer;

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

  /**
   * Sets the unique identifying integer of this object.
   * 
   * @param id
   *          The unique identifying integer of this object.
   */
  void setId(final int id);
}
