/**
 * Displayable.java - an object which can draw itself using OpenGL
 */
package edu.bu.cs.cs480;

import javax.media.opengl.GL;

/**
 * An object which can draw itself using OpenGL.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public interface Displayable {

  /**
   * Draws this object using the specified OpenGL object.
   * 
   * Implementing classes should require that this method be called after the
   * {@link #initialize(GL)} method.
   * 
   * @param gl
   *          The OpenGL object on which to draw this object.
   */
  void draw(final GL gl);

  /**
   * Performs initialization required by this object (for example defining a GL
   * call list).
   * 
   * Implementing classes should require that this method be called before the
   * {@link #draw(GL)} method.
   * 
   * @param gl
   *          The OpenGL object on which to draw this object.
   */
  void initialize(final GL gl);

}