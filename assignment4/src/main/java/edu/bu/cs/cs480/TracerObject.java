/**
 * TracerObject.java - an object with a unique identifying integer
 */
package edu.bu.cs.cs480;

/**
 * An object with a unique identifying integer.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class TracerObject implements Identifiable {
  /** The unique identifying integer of this object. */
  private int id = -1;

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see edu.bu.cs.cs480.Identifiable#id()
   */
  @Override
  public int id() {
    return this.id;
  }

  /**
   * {@inheritDoc}
   * 
   * @param id
   *          {@inheritDoc}
   * @see edu.bu.cs.cs480.Identifiable#setId(int)
   */
  @Override
  public void setId(final int id) {
    this.id = id;
  }

}
