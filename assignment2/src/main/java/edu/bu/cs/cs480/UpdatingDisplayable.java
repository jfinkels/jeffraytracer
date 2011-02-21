/**
 * UpdatingDisplayable.java - an object which can draw update itself
 */
package edu.bu.cs.cs480;

import javax.media.opengl.GL;

/**
 * An object which can draw itself, and update whatever state it maintains which
 * is necessary for drawing.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public interface UpdatingDisplayable extends Displayable {
  /**
   * Updates the state of this object using the specified OpenGL object (for
   * example, redefining a GL call list based on a change to the state of this
   * object).
   * 
   * @param gl
   *          The OpenGL object on which to draw this object.
   */
  void update(final GL gl);
}
