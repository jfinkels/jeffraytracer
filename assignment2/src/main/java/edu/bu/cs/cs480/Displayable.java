/**
 * Displayable.java - an object which can draw itself
 */
package edu.bu.cs.cs480;

import javax.media.opengl.GL;

/**
 * An object which can draw itself, and update whatever state it maintains which
 * is necessary for drawing.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 */
public interface Displayable {
  void draw(final GL gl);

  void init(final GL gl);

  void update(final GL gl);
}
