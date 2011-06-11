/**
 * TracerObject.java - an object with a unique identifying integer
 */
package jeffraytracer;

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
   * @see jeffraytracer.Identifiable#id()
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
   * @see jeffraytracer.Identifiable#setId(int)
   */
  @Override
  public void setId(final int id) {
    this.id = id;
  }

}
