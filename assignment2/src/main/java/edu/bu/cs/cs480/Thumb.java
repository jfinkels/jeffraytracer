/**
 * Thumb.java - a model for a thumb
 */
package edu.bu.cs.cs480;

import javax.media.opengl.GL;

/**
 * A thumb, which is basically just a finger that has been rotated.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Thumb extends Finger {
  /** The angle at which to rotate this finger so that it looks like a thumb. */
  public static final float THUMB_ROTATION = -45;
  /** The OpenGL handle (integer) for the OpenGL call list object. */
  private int displayListHandle;

  /**
   * Instantiates this object by calling the corresponding constructor of the
   * superclass.
   * 
   * @param x
   *          The x component of the position of this thumb.
   * @param y
   *          The y component of the position of this thumb.
   * @param z
   *          The z component of the position of this thumb.
   */
  public Thumb(double x, double y, double z) {
    super(x, y, z);
  }

  /**
   * {@inheritDoc}
   * 
   * @param gl
   *          {@inheritDoc}
   */
  @Override
  public void draw(final GL gl) {
    gl.glCallList(this.displayListHandle);
  }

  /**
   * {@inheritDoc}
   * 
   * @param gl
   *          {@inheritDoc}
   */
  @Override
  public void initialize(final GL gl) {
    super.initialize(gl);
    this.displayListHandle = gl.glGenLists(1);
  }

  /**
   * {@inheritDoc}
   * 
   * @param gl
   *          {@inheritDoc}
   */
  @Override
  public void update(final GL gl) {
    super.update(gl);

    gl.glNewList(this.displayListHandle, GL.GL_COMPILE);

    gl.glPushMatrix();

    // translate out
    gl.glTranslated(this.x(), this.y(), this.z());

    // rotate
    gl.glRotatef(THUMB_ROTATION, 0, 0, 1);

    // translate in
    gl.glTranslated(-this.x(), -this.y(), -this.z());

    super.draw(gl);

    gl.glPopMatrix();

    gl.glEndList();
  }

}
